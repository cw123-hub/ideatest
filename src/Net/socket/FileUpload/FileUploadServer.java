package Net.socket.FileUpload;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class FileUploadServer {
    public static void main(String[] args) throws IOException {
        //1.������ڱ�������8888�˿�
        ServerSocket serverSocket=new ServerSocket(8888);
        System.out.println("������ڱ�������8888�˿�,waiting to be connected...");
        //2.waiting to be connected...
        Socket socket = serverSocket.accept();
        //3. ��ȡ�ͻ��˷��͵�����
        InputStream inputStream = socket.getInputStream();
        BufferedInputStream bufferedInputStream=new BufferedInputStream(inputStream);
        byte[] bytes = FileUtils.streamToByteArray(bufferedInputStream);
        // 4.д�뵽Ŀ���ļ�
        FileOutputStream fileOutputStream = new FileOutputStream("d:\\net.jpg");
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        bufferedOutputStream.write(bytes);
        bufferedOutputStream.close();

        //5.�ظ��ͻ���
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        bufferedWriter.write("�յ�ͼƬfrom Client!");
        bufferedWriter.flush();//ˢ�����ݵ�����ͨ��
        socket.shutdownOutput();


        //�ر�������Դ
        bufferedWriter.close();
        bufferedInputStream.close();
        socket.close();
        serverSocket.close();


    }
}
