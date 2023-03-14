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
 * ��ɵ�½��֤��ע��ȵȹ���
 */
public class UserClientService {
   private  User user=new User();//��Ϊ���������ط�Ҫʹ��user��Ϣ�������Ϊ��Ա����
   private Socket socket;//�����ط�Ҳ����ʹ�á�����
    public boolean checkUser(String userId,String pwd)    {
        boolean b=false;

        user.setUserId(userId);
        user.setPassword(pwd);


        try {//���ӵ�������������User����
           // socket = new Socket(InetAddress.getByName("192.168.41.5"), 9999);
            socket = new Socket(InetAddress.getLocalHost().getHostName(), 9999);
            //
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(user);//����user����

            //��ȡ�ӷ���˻��͵�Message
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Object o = ois.readObject();
            Message message=(Message) o;
            if(message.getMesType().equals(messageType.MESSAGE_LOGIN_SUCCEED)){

                //Login Success!
                //����һ���ͷ���˱���ͨ�ŵ��߳�-��ClientConnectServerThread
                ClientConnectServerThread ccst = new ClientConnectServerThread(socket);
                //�����ͻ����߳�
                ccst.start();
                //Ϊ�˿ͻ�����չ�����̷߳��뼯����
                ManageClientConnectServerThread.addClientConnectServerThread(userId,ccst);
                b=true;

            }else {
                 //Login failed
                //�ر�socket
                socket.close();
            }
        }
        catch ( Exception e){

        }

        return b ;
    }

    //����������������û��б�
    public void onlineFriendList(){
        //����һ��Message��������String MESSAGE_GET_ONLINE_FRIEND="4";//�õ������û��б�
        Message message = new Message();
        message.setSender(user.getUserId());
        message.setMesType(messageType.MESSAGE_GET_ONLINE_FRIEND);
        //���͸�������
        try {
            //�õ���ǰsocket��Ӧ��objoutputStream
            ObjectOutputStream objectOutputStream = new
                    ObjectOutputStream(ManageClientConnectServerThread.getClientConnectServerThread(user.getUserId()).getSocket().getOutputStream());
        objectOutputStream.writeObject(message);//����һ��Message��������Ҫ�������û��б�
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //�˳��ͻ��˲��Ҹ��������˷���һ���˳�ϵͳ��message����
    public    void logOut() throws IOException {
        Message message = new Message();
        message.setMesType(messageType.MESSAGE_CLIENT_EXIT);
        message.setSender(user.getUserId());
        //����

        try {  ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(message);
            System.out.println(user.getUserId()+"�˳���ϵͳ");
            System.exit(0);//��������
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



   @Test
   void test() throws UnknownHostException {
    System.out.println(InetAddress.getLocalHost().getHostName());
}
}
