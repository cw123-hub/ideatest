package QQClient.com.chen.qqclient.Service;

import org.junit.jupiter.api.Test;
import  qqcommon.messageType;
import  qqcommon.Message;
import  qqcommon.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 完成登陆验证，注册等等功能
 */
public class UserClientService {
   private  User user=new User();//因为可能其他地方要使用user信息，因此作为成员属性
   private Socket socket;//其他地方也可能使用。。。
    public boolean checkUser(String userId,String pwd)    {
        boolean b=false;

        user.setUserId(userId);
        user.setPassword(pwd);


        try {//连接到服务器，发送User对象
           // socket = new Socket(InetAddress.getByName("192.168.41.5"), 9999);
            socket = new Socket(InetAddress.getLocalHost().getHostName(), 9999);
            //
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(user);//发送user对象

            //读取从服务端回送的Message
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Object o = ois.readObject();
            Message message=(Message) o;
            if(message.getMesType().equals(messageType.MESSAGE_LOGIN_SUCCEED)){

                //Login Success!
                //创建一个和服务端保持通信的线程-》ClientConnectServerThread
                ClientConnectServerThread ccst = new ClientConnectServerThread(socket);
                //启动客户端线程
                ccst.start();
                //为了客户端扩展，将线程放入集合中
                ManageClientConnectServerThread.addClientConnectServerThread(userId,ccst);
                b=true;

            }else {
                 //Login failed
                //关闭socket
                socket.close();
            }
        }
        catch ( Exception e){

        }

        return b ;
    }

    //向服务器请求在线用户列表
    public void onlineFriendList(){
        //发送一个Message，类型是String MESSAGE_GET_ONLINE_FRIEND="4";//得到在线用户列表
        Message message = new Message();
        message.setSender(user.getUserId());
        message.setMesType(messageType.MESSAGE_GET_ONLINE_FRIEND);
        //发送给服务器
        try {
            //得到当前socket对应的objoutputStream
            ObjectOutputStream objectOutputStream = new
                    ObjectOutputStream(ManageClientConnectServerThread.getClientConnectServerThread(user.getUserId()).getSocket().getOutputStream());
        objectOutputStream.writeObject(message);//发送一个Message，向服务端要求在线用户列表
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //退出客户端并且给服务器端发送一个退出系统的message对象
    public    void logOut() throws IOException {
        Message message = new Message();
        message.setMesType(messageType.MESSAGE_CLIENT_EXIT);
        message.setSender(user.getUserId());
        //发送

        try {  ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(message);
            System.out.println(user.getUserId()+"退出了系统");
            System.exit(0);//结束进程
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



   @Test
   void test() throws UnknownHostException {
    System.out.println(InetAddress.getLocalHost().getHostName());
}
}
