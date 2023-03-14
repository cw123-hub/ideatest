package QQServer.com.chen.qqserver.service;

import  qqcommon.messageType;
import  qqcommon.Message;
import  qqcommon.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 这是服务器在监听9999，等待客户端的连接并保持通讯
 */
public class QQServer {
    private ServerSocket ss=null;
    //存放合法的用户
    //这里亦可使用ConcurrentHashMap，可以处理并发的集合，没有线程安全问题。
    private static ConcurrentHashMap <String ,User>validUsers=new ConcurrentHashMap<>() ;
    //处理离线用户收到的消息
    private static ConcurrentHashMap <String , ArrayList<Message>>offLineDb=new ConcurrentHashMap<>() ;
    static {//静态代码块中初始化ValidUsers
        validUsers.put("100",new User("100","123456"));
        validUsers.put("300",new User("300","123456"));
        validUsers.put("200",new User("200","123456"));
        validUsers.put("至尊宝",new User("至尊宝","123456"));
        validUsers.put("紫霞仙子",new User("紫霞仙子","123456"));
    }

    private boolean checkUser(String userId,String passwd){
        User user = validUsers.get(userId);
        if(user == null){//没有这个User
            return false;
        }
        if(!user.getPassword().equals(passwd))//ID正确，密码错误
        {
            return false;
        }
        return true;
    }

    public QQServer()   {
        //注意：端口可以卸载配置文件中

        try {
            System.out.println("服务端在9999端口监听...");
            ss=new ServerSocket(9999);
            //启动广播推送线程
            new Thread(new SendNewsToAllService()).start();
            while(true){//和某个客户端建立连接后，会继续监听
                Socket socket = ss.accept();//没有刻划断连接会阻塞
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                //
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                //读取客户端传来的User对象
                Object o = ois.readObject();
                User user=(User)o;
                //创建一个Message对象，准备回复客户端
                Message message = new Message();
                //验证
                if(checkUser(user.getUserId(), user.getPassword())){
                    message.setMesType(messageType.MESSAGE_LOGIN_SUCCEED);
                    //将Message对象回复客户端
                    oos.writeObject(message);
                    //new 一个线程和客户端保持通讯,线程需要持有socket对象
                    ServerConnectClientThread serverConnectClientThread =
                            new ServerConnectClientThread(socket, user.getUserId());
                    //启动线程
                    serverConnectClientThread.start();
                    //线程对象在集合中管理
                    ManageServerThreads.addServerThreads(user.getUserId(), serverConnectClientThread);

                }else{
                    System.out.println("用户id="+user.getUserId()+"pwd="+user.getPassword()+"验证失败");
                    message.setMesType(messageType.MESSAGE_LOGIN_FAILED);
                    oos.writeObject(message);
                    //该socket无需再次使用。。。
                    socket.close();
                }

            }
        } catch ( Exception e) {
            throw new RuntimeException(e);
        }finally {
            //若服务端推出while,表示不再监听，ServerSocket就关闭吧
            try {
                ss.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

    }
}
