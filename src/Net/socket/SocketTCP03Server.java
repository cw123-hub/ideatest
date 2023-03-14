package Net.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

//服务器端
public class SocketTCP03Server {
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

        //4.IO读取(使用转换流)
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        System.out.println(bufferedReader.readLine());

        //5.获取Socket关联的输出流
        OutputStream outputStream = socket.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedWriter.write("hello, client字符流");
        bufferedWriter.newLine();//插入换行符，表示回复内容的结束
        bufferedWriter.flush();//需要手动刷新



        //6 关闭流和socket
        bufferedWriter.close();//一般后打开的先关闭
        bufferedReader.close();

        socket.close();
        serverSocket.close();


    }
}
