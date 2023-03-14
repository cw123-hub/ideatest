package QQServer.com.chen.qqserver.service;

import QQClient.com.chen.qqclient.Service.ManageClientConnectServerThread;
import QQServer.com.chen.utils.Utility;
import qqcommon.Message;
import qqcommon.messageType;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;

/**
 * �㲥�����
 */
public class SendNewsToAllService implements Runnable{

    @Override
    public void run() {
        while (true) {
            System.out.println("�����������Ҫ���͵���Ϣ����exit�����˳�������");
            String news= Utility.readString(50);
            if(news.equals("exit")){
                System.out.println("�ɹ��Ƴ�����");
                break;
            }
            Message message = new Message();
            message.setContent(news);
            message.setSender("������");
            message.setMesType(messageType.MESSAGE_TO_ALL_MES);

            message.setSendTime(new Date().toString());
            System.out.println("������������Ϣ�������ˣ�˵��"+news);
            HashMap<String, ServerConnectClientThread> hm = ManageServerThreads.getHm();

            Iterator<String> iterator = hm.keySet().iterator();
            while (iterator.hasNext()) {
              String onlineUserId=iterator.next().toString();

                try { ServerConnectClientThread serverConnectClientThread = hm.get(onlineUserId);
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(serverConnectClientThread.getSocket().getOutputStream());
                    objectOutputStream.writeObject(message);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        }


    }
}
