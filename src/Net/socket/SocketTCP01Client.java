package Net.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketTCP01Client {
    public static void main(String[] args) throws IOException {
        //1���ӷ�������ip���˿ڣ�
        //1���ӱ�����9999�˿ڣ����ӳɹ�������SOcket����
        Socket socket = new Socket(InetAddress.getLocalHost(),9999);
        System.out.println("�ͻ���socket���أ�"+socket.getClass());
        //2.�����Ϻ�����Socket��ͨ��socket.getoutputStream()
        //�õ���Socket��������������
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("hello,server ".getBytes( ));
        //����д��������
        socket.shutdownOutput();
        //3.��ȡsocket������������
        InputStream inputStream = socket.getInputStream();
        byte[] bytes = new byte[1024];
        int readLen=0;
        while((readLen=inputStream.read(bytes))!=-1){
            System.out.println(new String (bytes,0,readLen));
        }

        //4.�ر��������Socket
        inputStream.close();
        outputStream.close();
        socket.close();
        System.out.println("�ͻ����˳�~");

    }

}
