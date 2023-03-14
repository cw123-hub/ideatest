package Net.HomeWork;

import jdk.nashorn.internal.ir.CallNode;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class SocketTCP03Client {
    public static void main(String[] args) throws IOException {


        //1
        Scanner scanner = new Scanner(System.in);
        System.out.println("�������ص��ļ�����");
        String next = scanner.next();

        //2.
        Socket socket = new Socket(InetAddress.getLocalHost(),9999);
        //3.
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(next.getBytes());
        socket.shutdownOutput();
        outputStream.flush();

        //4
        BufferedInputStream bufferedInputStream = new BufferedInputStream(socket.getInputStream());
        byte[] bytes = FileUtils.streamToByteArray(bufferedInputStream);

        //5
        String FilePath="C:\\Users\\33419\\Desktop\\ideatest\\src\\������Ӱ.docx";
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(FilePath));
        bufferedOutputStream.write(bytes);


        //6
        bufferedOutputStream.close();
        bufferedInputStream.close();
        outputStream.close();
        socket.close();


        System.out.println("������ϣ��˳�������");



    }

}
