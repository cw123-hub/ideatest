package Net.socket;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class SocketTCP03Client {
    public static void main(String[] args) throws IOException {
        //1连接服务器（ip，端口）
        //1连接本机的9999端口，连接成功，返回SOcket对象
        Socket socket = new Socket(InetAddress.getLocalHost(),9999);
        System.out.println("客户端socket返回："+socket.getClass());
        //2.连接上后，生成Socket，通过socket.getoutputStream()
        //得到和Socket对象关联的输出流
        OutputStream outputStream = socket.getOutputStream();
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
        outputStreamWriter.write("Hello server字符流");
        bufferedWriter.newLine();//插入换行符，表示写入的内容结束，且要求对方以readLie()方法读
        bufferedWriter.flush();//使用字符流需要手动帅新，否则数据不会写入数据通道
        //设置写入结束标记(字符流不需要)
        //outputStreamWriter.
        //3.获取socket广联的输入流
        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
        System.out.println(bufferedReader.readLine());


        //4.关闭流对象和Socket
        bufferedReader.close();
        bufferedWriter.close();
        socket.close();
        System.out.println("客户端退出~");

    }

}
