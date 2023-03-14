package Net.socket.FileUpload;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class FileUploadClient {
    public static void main(String[] args) throws IOException {
        //���ӷ���ˣ�������8888�˿�
       // Socket socket = new Socket(InetAddress.getLocalHost(), 8888);
        Socket socket = new Socket(InetAddress.getLocalHost(), 8888);
        //
        BufferedInputStream bufferedInputStream =
                new BufferedInputStream(new FileInputStream("d:\\qqq.jpg"));
        //qqq.jpg��Ӧ���ֽ�����
        byte[] bytes = FileUtils.streamToByteArray(bufferedInputStream);
        //
        OutputStream outputStream = socket.getOutputStream();
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
        //
        bufferedOutputStream.write(bytes);
        socket.shutdownOutput();//����д������ı��

        //���ܴӷ���˻ظ�����Ϣ
        String s = FileUtils.streamToString(socket.getInputStream());
        System.out.println(s);

        //�ر���
        bufferedOutputStream.close();
        bufferedInputStream.close();
        socket.close();



    }
}
