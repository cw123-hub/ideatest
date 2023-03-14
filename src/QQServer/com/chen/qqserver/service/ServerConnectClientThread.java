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
 * 该类的一个对象和某一个客户端保持通讯。
 */
public class ServerConnectClientThread extends Thread{
    private Socket socket;
    private String userId;//客户端的userID号用户连接到服务器(客户端发送过来的User对象里面的userId)

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
            System.out.println("服务端和客户端"+userId+"保持通讯，读取数据...");
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                Object o = objectInputStream.readObject();
                Message message=(Message) o;
                //
                if(message.getMesType().equals(messageType.MESSAGE_GET_ONLINE_FRIEND)){
                    //客户端要用户列表
                    System.out.println(message.getSender()+"在请求用户列表");

                    String onlineUser = ManageServerThreads.getOnlineUser();
                    //返回Message对象to 客户端
                    Message message1=new Message();
                    message1.setMesType(messageType.MESSAGE_RET_ONLINE_FRIEND);
                    message1.setContent(onlineUser);
                    message1.setReceiver(message.getSender());
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                    objectOutputStream.writeObject(message1 );


                }else if(message.getMesType().equals(messageType.MESSAGE_CLIENT_EXIT)){
                    System.out.println(message.getSender()+"准备退出");
                    ManageServerThreads.removeServerThread(message.getSender());
                    socket.close();
                    //退出线程
                    break;

                } else if (message.getMesType().equals(messageType.MESSAGE_COMMON_MES)) {
                    //得到接受被发送消息的线程
                    ServerConnectClientThread serverConnectClientThread = ManageServerThreads.getServerConnectClientThread(message.getReceiver());
                    //将message对象转发给指定的客户端
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(serverConnectClientThread.getSocket().getOutputStream());
                    objectOutputStream.writeObject(message);//客户不在线，就保存到数据库
                } else if (message.getMesType().equals(messageType.MESSAGE_TO_ALL_MES)) {//群聊
                    //bianni 取出所有
                    HashMap<String, ServerConnectClientThread> hm = ManageServerThreads.getHm();
                    Iterator<String> iterator = hm.keySet().iterator();
                    while (iterator.hasNext()) {
                      //Q取出在线用户ID
                        String onlineUserId = iterator.next().toString();
                        if(!onlineUserId.equals(message.getSender())){//排除发给自己
                            //转发
                            ObjectOutputStream objectOutputStream = new ObjectOutputStream(hm.get(onlineUserId).getSocket().getOutputStream());
                            objectOutputStream.writeObject(message);
                        }

                    }
                } else if (message.getMesType().equals(messageType.MESSAGE_FILE_MES)) {
                    //根据接收者id对应地线程
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
