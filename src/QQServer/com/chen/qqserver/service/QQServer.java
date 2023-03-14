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
 * ���Ƿ������ڼ���9999���ȴ��ͻ��˵����Ӳ�����ͨѶ
 */
public class QQServer {
    private ServerSocket ss=null;
    //��źϷ����û�
    //�������ʹ��ConcurrentHashMap�����Դ������ļ��ϣ�û���̰߳�ȫ���⡣
    private static ConcurrentHashMap <String ,User>validUsers=new ConcurrentHashMap<>() ;
    //���������û��յ�����Ϣ
    private static ConcurrentHashMap <String , ArrayList<Message>>offLineDb=new ConcurrentHashMap<>() ;
    static {//��̬������г�ʼ��ValidUsers
        validUsers.put("100",new User("100","123456"));
        validUsers.put("300",new User("300","123456"));
        validUsers.put("200",new User("200","123456"));
        validUsers.put("����",new User("����","123456"));
        validUsers.put("��ϼ����",new User("��ϼ����","123456"));
    }

    private boolean checkUser(String userId,String passwd){
        User user = validUsers.get(userId);
        if(user == null){//û�����User
            return false;
        }
        if(!user.getPassword().equals(passwd))//ID��ȷ���������
        {
            return false;
        }
        return true;
    }

    public QQServer()   {
        //ע�⣺�˿ڿ���ж�������ļ���

        try {
            System.out.println("�������9999�˿ڼ���...");
            ss=new ServerSocket(9999);
            //�����㲥�����߳�
            new Thread(new SendNewsToAllService()).start();
            while(true){//��ĳ���ͻ��˽������Ӻ󣬻��������
                Socket socket = ss.accept();//û�п̻������ӻ�����
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                //
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                //��ȡ�ͻ��˴�����User����
                Object o = ois.readObject();
                User user=(User)o;
                //����һ��Message����׼���ظ��ͻ���
                Message message = new Message();
                //��֤
                if(checkUser(user.getUserId(), user.getPassword())){
                    message.setMesType(messageType.MESSAGE_LOGIN_SUCCEED);
                    //��Message����ظ��ͻ���
                    oos.writeObject(message);
                    //new һ���̺߳Ϳͻ��˱���ͨѶ,�߳���Ҫ����socket����
                    ServerConnectClientThread serverConnectClientThread =
                            new ServerConnectClientThread(socket, user.getUserId());
                    //�����߳�
                    serverConnectClientThread.start();
                    //�̶߳����ڼ����й���
                    ManageServerThreads.addServerThreads(user.getUserId(), serverConnectClientThread);

                }else{
                    System.out.println("�û�id="+user.getUserId()+"pwd="+user.getPassword()+"��֤ʧ��");
                    message.setMesType(messageType.MESSAGE_LOGIN_FAILED);
                    oos.writeObject(message);
                    //��socket�����ٴ�ʹ�á�����
                    socket.close();
                }

            }
        } catch ( Exception e) {
            throw new RuntimeException(e);
        }finally {
            //��������Ƴ�while,��ʾ���ټ�����ServerSocket�͹رհ�
            try {
                ss.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

    }
}
