package Net.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
//��������
public class SocketTCP01Server {
    public static void main(String[] args) throws IOException {
        //1�ڱ���������ˣ���9999�˿ڼ������ȴ�����(serversocket�൱��һ�������������ж��socket)
        //serversocket����ͨ��accept()�������ض��socket  [����ͻ����������ǵķ�����]
        ServerSocket serverSocket= new ServerSocket(9999);
        System.out.println("����������ˣ���9999�˿ڼ������ȴ�����");
        //2û�пͻ������ӷ���ˣ�����������ڴˣ��ȴ�����
        //���пͻ������ӣ���᷵��Socket���󣬳��������
        Socket socket = serverSocket.accept();
        System.out.println("��������socket:"+socket.getClass());
        //3ͨ��socket.getIputStream()��ȡ�ͻ���д������ͨ��������
        InputStream inputStream = socket.getInputStream();

        //4.IO��ȡ
        byte[] bytes = new byte[1024];
        int readLength=0;
        while((readLength=inputStream.read(bytes))!=-1){
            System.out.println(new String(bytes,0,readLength));
        }
        //5.��ȡSocket�����������
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("hello,client!".getBytes( ));
        //����д��������
        socket.shutdownOutput();
        //6 �ر�����socket
        inputStream.close();
        outputStream.close();
        socket.close();
        serverSocket.close();


    }
}
