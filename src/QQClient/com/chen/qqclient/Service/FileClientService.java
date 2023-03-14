package QQClient.com.chen.qqclient.Service;

import qqcommon.Message;
import qqcommon.messageType ;

import java.io.*;

/**
 * ����ļ��������
 */
public class FileClientService {
    /**
     *
     * @param src
     * @param dest  chuan shu dao de mulu
     * @param senderId
     * @param receiverId
     */
    public void sendToOne(String src,String dest,String senderId,String receiverId){
//src-->message
        Message message = new Message();
        message.setMesType(messageType.MESSAGE_FILE_MES);
        message.setSender(senderId);
        message.setReceiver(receiverId);
        message.setSrc(src);
        message.setDest(dest);

        //��ȡ�ļ�
        FileInputStream fileInputStream=null;
        byte[]fileBytes=new byte[(int)new File(src).length()];

        try {
            fileInputStream=new FileInputStream(src);
            fileInputStream.read(fileBytes);//src�ļ����뵽�ֽ�����
            //���ļ�����Ӧ���ֽ��������õ�message��
            message.setFileBytes(fileBytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            if(fileInputStream!=null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        System.out.println("\n"+senderId+"��"+receiverId+"�����ļ� "+src +" ���Է����� "+dest);
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(ManageClientConnectServerThread.
                    getClientConnectServerThread(senderId).getSocket().getOutputStream());
            objectOutputStream.writeObject(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
