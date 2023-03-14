package QQServer.com.chen.qqserver.service;


import QQClient.com.chen.qqclient.Service.ManageClientConnectServerThread;
import  qqcommon.messageType;
import  qqcommon.Message;
import  qqcommon.User;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;

/**
 * �����һ�������ĳһ���ͻ��˱���ͨѶ��
 */
public class ServerConnectClientThread extends Thread{
    private Socket socket;
    private String userId;//�ͻ��˵�userID���û����ӵ�������(�ͻ��˷��͹�����User���������userId)

    public Socket getSocket() {
        return socket;
    }

    public ServerConnectClientThread(Socket socket, String userId) {
        this.socket = socket;
        this.userId = userId;
    }

    @Override
    public void run() {
        while (true){
            System.out.println("����˺Ϳͻ���"+userId+"����ͨѶ����ȡ����...");
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                Object o = objectInputStream.readObject();
                Message message=(Message) o;
                //
                if(message.getMesType().equals(messageType.MESSAGE_GET_ONLINE_FRIEND)){
                    //�ͻ���Ҫ�û��б�
                    System.out.println(message.getSender()+"�������û��б�");

                    String onlineUser = ManageServerThreads.getOnlineUser();
                    //����Message����to �ͻ���
                    Message message1=new Message();
                    message1.setMesType(messageType.MESSAGE_RET_ONLINE_FRIEND);
                    message1.setContent(onlineUser);
                    message1.setReceiver(message.getSender());
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                    objectOutputStream.writeObject(message1 );


                }else if(message.getMesType().equals(messageType.MESSAGE_CLIENT_EXIT)){
                    System.out.println(message.getSender()+"׼���˳�");
                    ManageServerThreads.removeServerThread(message.getSender());
                    socket.close();
                    //�˳��߳�
                    break;

                } else if (message.getMesType().equals(messageType.MESSAGE_COMMON_MES)) {
                    //�õ����ܱ�������Ϣ���߳�
                    ServerConnectClientThread serverConnectClientThread = ManageServerThreads.getServerConnectClientThread(message.getReceiver());
                    //��message����ת����ָ���Ŀͻ���
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(serverConnectClientThread.getSocket().getOutputStream());
                    objectOutputStream.writeObject(message);//�ͻ������ߣ��ͱ��浽���ݿ�
                } else if (message.getMesType().equals(messageType.MESSAGE_TO_ALL_MES)) {//Ⱥ��
                    //bianni ȡ������
                    HashMap<String, ServerConnectClientThread> hm = ManageServerThreads.getHm();
                    Iterator<String> iterator = hm.keySet().iterator();
                    while (iterator.hasNext()) {
                      //Qȡ�������û�ID
                        String onlineUserId = iterator.next().toString();
                        if(!onlineUserId.equals(message.getSender())){//�ų������Լ�
                            //ת��
                            ObjectOutputStream objectOutputStream = new ObjectOutputStream(hm.get(onlineUserId).getSocket().getOutputStream());
                            objectOutputStream.writeObject(message);
                        }

                    }
                } else if (message.getMesType().equals(messageType.MESSAGE_FILE_MES)) {
                    //���ݽ�����id��Ӧ���߳�
                    ServerConnectClientThread serverConnectClientThread = ManageServerThreads.getServerConnectClientThread(message.getReceiver());
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(serverConnectClientThread.getSocket().getOutputStream());
                    objectOutputStream.writeObject(message);
                }
            }  catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
