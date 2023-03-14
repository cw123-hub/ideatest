package QQServer.com.chen.qqserver.service;

import QQClient.com.chen.qqclient.Service.ManageClientConnectServerThread;
import QQServer.com.chen.utils.Utility;
import qqcommon.Message;
import qqcommon.messageType;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;

/**
 * 广播给大家
 */
public class SendNewsToAllService implements Runnable{

    @Override
    public void run() {
        while (true) {
            System.out.println("请输入服务器要推送的消息：【exit代表退出！！】");
            String news= Utility.readString(50);
            if(news.equals("exit")){
                System.out.println("成功推出！！");
                break;
            }
            Message message = new Message();
            message.setContent(news);
            message.setSender("服务器");
            message.setMesType(messageType.MESSAGE_TO_ALL_MES);

            message.setSendTime(new Date().toString());
            System.out.println("服务器推送消息给所有人，说："+news);
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
