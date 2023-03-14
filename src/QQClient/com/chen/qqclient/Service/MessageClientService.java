package QQClient.com.chen.qqclient.Service;

import qqcommon.Message;
import qqcommon.messageType;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;

/**
 *提供和消息相关的服务方法
 */
public class MessageClientService {
    public void sendMessageToOne(String content,String senderId,String receiverId){
        //构建message
        Message message = new Message();
        message.setSender(senderId);
        message.setMesType(messageType.MESSAGE_COMMON_MES);
        message.setReceiver(receiverId);
        message.setContent(content);
        message.setSendTime(new Date().toString());
        System.out.println(senderId + "对"+receiverId +"说了"+content);
        //发送给服务端
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(ManageClientConnectServerThread.
                    getClientConnectServerThread(senderId).getSocket().getOutputStream());
            objectOutputStream.writeObject(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessageToOAll(String senderId,String content) {
        //构建message
        Message message = new Message();
        message.setSender(senderId);
        message.setMesType(messageType.MESSAGE_TO_ALL_MES );
        message.setContent(content);
        message.setSendTime(new Date().toString());
        System.out.println(senderId + "对大家" +"说了"+content);
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(ManageClientConnectServerThread.
                    getClientConnectServerThread(senderId).getSocket().getOutputStream());
            objectOutputStream.writeObject(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
