package QQClient.com.chen.qqclient.Service;

import  qqcommon.messageType;
import  qqcommon.Message;
import  qqcommon.User;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
**
* �ͻ����߳�
 */
public class ClientConnectServerThread extends Thread{
    //���߳���Ҫ����Socket����
    private Socket socket;

    public ClientConnectServerThread( Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    @Override
    public void run() {
        //��Ϊ�߳���Ҫ�ں�̨�ͷ�����ͨѶ���������while
        while (true){
            System.out.println("�ͻ����߳�,�ȴ���ȡ�ӷ������˷��͹�������Ϣ");
            try {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                 //���������û�з���message,�߳���������
                Message message = (Message) (ois.readObject());
                if(message.getMesType().equals(messageType.MESSAGE_RET_ONLINE_FRIEND)){
                    String[]  onlineUsers= message.getContent().split(" ");
                    System.out.println("\n=======��ǰ�����û��б�======");
                    for (int i=0;i< onlineUsers.length;i++){
                        System.out.println("�û���"+onlineUsers[i]);
                    }
                }  else if(message.getMesType().equals(messageType.MESSAGE_COMMON_MES)){//˽��
                    System.out.println("\n"+message.getSender()+"��"+
                            message.getReceiver()+"˵"
                            +message.getContent());

                } else if (message.getMesType().equals(messageType.MESSAGE_TO_ALL_MES))//Ⱥ��
                {
                    System.out.println(message.getSender()+"�Դ��˵��"+message.getContent());
                    
                }else if(message.getMesType().equals(messageType.MESSAGE_FILE_MES)){//�ļ�
                    System.out.println(message.getSender()+"��"+message.getReceiver()+"���ļ���"+message.getSrc()+"���ҵĵ���Ŀ¼"+message.getDest());
                    FileOutputStream fileOutputStream = new FileOutputStream  (message.getDest());
                    fileOutputStream.write(message.getFileBytes());
                    fileOutputStream.close();
                    System.out.println("�����ļ��ɹ�����");

                }
                else{

                }
            } catch ( Exception e) {
                e.printStackTrace();
            }
        }
    }
}
