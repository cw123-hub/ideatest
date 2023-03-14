package Net.socket.FileUpload;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class FileUploadServer {
    public static void main(String[] args) throws IOException {
        //1.服务端在本机监听8888端口
        ServerSocket serverSocket=new ServerSocket(8888);
        System.out.println("服务端在本机监听8888端口,waiting to be connected...");
        //2.waiting to be connected...
        Socket socket = serverSocket.accept();
        //3. 读取客户端发送的数据
        InputStream inputStream = socket.getInputStream();
        BufferedInputStream bufferedInputStream=new BufferedInputStream(inputStream);
        byte[] bytes = FileUtils.streamToByteArray(bufferedInputStream);
        // 4.写入到目标文件
        FileOutputStream fileOutputStream = new FileOutputStream("d:\\net.jpg");
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        bufferedOutputStream.write(bytes);
        bufferedOutputStream.close();

        //5.回复客户端
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        bufferedWriter.write("收到图片from Client!");
        bufferedWriter.flush();//刷新内容到数据通道
        socket.shutdownOutput();


        //关闭其他资源
        bufferedWriter.close();
        bufferedInputStream.close();
        socket.close();
        serverSocket.close();


    }
}
