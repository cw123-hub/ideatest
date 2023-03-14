package Net.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
//服务器端
public class SocketTCP01Server {
    public static void main(String[] args) throws IOException {
        //1在本机（服务端）的9999端口监听，等待连接(serversocket相当于一个插座，可以有多个socket)
        //serversocket可以通过accept()方法返回多个socket  [多个客户端连接我们的服务器]
        ServerSocket serverSocket= new ServerSocket(9999);
        System.out.println("本机（服务端）在9999端口监听，等待连接");
        //2没有客户端连接服务端，程序会阻塞于此，等待连接
        //若有客户端链接，则会返回Socket对象，程序继续。
        Socket socket = serverSocket.accept();
        System.out.println("服务器端socket:"+socket.getClass());
        //3通过socket.getIputStream()读取客户端写入数据通道的数据
        InputStream inputStream = socket.getInputStream();

        //4.IO读取
        byte[] bytes = new byte[1024];
        int readLength=0;
        while((readLength=inputStream.read(bytes))!=-1){
            System.out.println(new String(bytes,0,readLength));
        }
        //5.获取Socket关联的输出流
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("hello,client!".getBytes( ));
        //设置写入结束标记
        socket.shutdownOutput();
        //6 关闭流和socket
        inputStream.close();
        outputStream.close();
        socket.close();
        serverSocket.close();


    }
}
