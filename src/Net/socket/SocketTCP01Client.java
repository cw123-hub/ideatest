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
        //1连接服务器（ip，端口）
        //1连接本机的9999端口，连接成功，返回SOcket对象
        Socket socket = new Socket(InetAddress.getLocalHost(),9999);
        System.out.println("客户端socket返回："+socket.getClass());
        //2.连接上后，生成Socket，通过socket.getoutputStream()
        //得到和Socket对象关联的输出流
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("hello,server ".getBytes( ));
        //设置写入结束标记
        socket.shutdownOutput();
        //3.获取socket广联的输入流
        InputStream inputStream = socket.getInputStream();
        byte[] bytes = new byte[1024];
        int readLen=0;
        while((readLen=inputStream.read(bytes))!=-1){
            System.out.println(new String (bytes,0,readLen));
        }

        //4.关闭流对象和Socket
        inputStream.close();
        outputStream.close();
        socket.close();
        System.out.println("客户端退出~");

    }

}
