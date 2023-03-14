package Net.HomeWork;

import Net.socket.FileUpload.FileUtils;

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
        InputStream inputStream = socket.getInputStream();
        byte[] bytes = new byte[1024];
        int len=0;
        String downLoadFileName="";
        while((len=inputStream.read(bytes))!=-1){
            downLoadFileName+=new String (bytes,0,len);
        }
        System.out.println("要下载的文件"+downLoadFileName);
        String resFileName=     "";
        if("建筑摄影".equals(downLoadFileName)){
            resFileName="d:\\建筑摄影.docx";
        }else{
            resFileName="d:\\qqq.jpg";
        }

        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(resFileName));
        byte[] bytes1 = FileUtils.streamToByteArray(bufferedInputStream);

        OutputStream outputStream = socket.getOutputStream();
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
        bufferedOutputStream.write(bytes1);
        socket.shutdownOutput();//很关键


        bufferedOutputStream.close();
        bufferedInputStream.close();
        inputStream.close();
        socket.close();
        serverSocket.close();

    }
}
