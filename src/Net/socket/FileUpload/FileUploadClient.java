package Net.socket.FileUpload;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class FileUploadClient {
    public static void main(String[] args) throws IOException {
        //连接服务端（本机）8888端口
       // Socket socket = new Socket(InetAddress.getLocalHost(), 8888);
        Socket socket = new Socket(InetAddress.getLocalHost(), 8888);
        //
        BufferedInputStream bufferedInputStream =
                new BufferedInputStream(new FileInputStream("d:\\qqq.jpg"));
        //qqq.jpg对应的字节数组
        byte[] bytes = FileUtils.streamToByteArray(bufferedInputStream);
        //
        OutputStream outputStream = socket.getOutputStream();
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
        //
        bufferedOutputStream.write(bytes);
        socket.shutdownOutput();//设置写入结束的标记

        //接受从服务端回复的消息
        String s = FileUtils.streamToString(socket.getInputStream());
        System.out.println(s);

        //关闭流
        bufferedOutputStream.close();
        bufferedInputStream.close();
        socket.close();



    }
}
