package QQClient.com.chen.qqclient.Service;

import qqcommon.Message;
import qqcommon.messageType;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;

/**
 *�ṩ����Ϣ��صķ��񷽷�
 */
public class MessageClientService {
    public void sendMessageToOne(String content,String senderId,String receiverId){
        //����message
        Message message = new Message();
        message.setSender(senderId);
        message.setMesType(messageType.MESSAGE_COMMON_MES);
        message.setReceiver(receiverId);
        message.setContent(content);
        message.setSendTime(new Date().toString());
        System.out.println(senderId + "��"+receiverId +"˵��"+content);
        //���͸������
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(ManageClientConnectServerThread.
                    getClientConnectServerThread(senderId).getSocket().getOutputStream());
            objectOutputStream.writeObject(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessageToOAll(String senderId,String content) {
        //����message
        Message message = new Message();
        message.setSender(senderId);
        message.setMesType(messageType.MESSAGE_TO_ALL_MES );
        message.setContent(content);
        message.setSendTime(new Date().toString());
        System.out.println(senderId + "�Դ��" +"˵��"+content);
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(ManageClientConnectServerThread.
                    getClientConnectServerThread(senderId).getSocket().getOutputStream());
            objectOutputStream.writeObject(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
