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
* 客户端线程
 */
public class ClientConnectServerThread extends Thread{
    //该线程需要持有Socket对象。
    private Socket socket;

    public ClientConnectServerThread( Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    @Override
    public void run() {
        //因为线程需要在后台和服务器通讯，因此做成while
        while (true){
            System.out.println("客户端线程,等待读取从服务器端发送过来的消息");
            try {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                 //如果服务器没有发送message,线程阻塞在这
                Message message = (Message) (ois.readObject());
                if(message.getMesType().equals(messageType.MESSAGE_RET_ONLINE_FRIEND)){
                    String[]  onlineUsers= message.getContent().split(" ");
                    System.out.println("\n=======当前在线用户列表======");
                    for (int i=0;i< onlineUsers.length;i++){
                        System.out.println("用户："+onlineUsers[i]);
                    }
                }  else if(message.getMesType().equals(messageType.MESSAGE_COMMON_MES)){//私聊
                    System.out.println("\n"+message.getSender()+"对"+
                            message.getReceiver()+"说"
                            +message.getContent());

                } else if (message.getMesType().equals(messageType.MESSAGE_TO_ALL_MES))//群聊
                {
                    System.out.println(message.getSender()+"对大家说了"+message.getContent());
                    
                }else if(message.getMesType().equals(messageType.MESSAGE_FILE_MES)){//文件
                    System.out.println(message.getSender()+"给"+message.getReceiver()+"发文件："+message.getSrc()+"到我的电脑目录"+message.getDest());
                    FileOutputStream fileOutputStream = new FileOutputStream  (message.getDest());
                    fileOutputStream.write(message.getFileBytes());
                    fileOutputStream.close();
                    System.out.println("保存文件成功！！");

                }
                else{

                }
            } catch ( Exception e) {
                e.printStackTrace();
            }
        }
    }
}
