package Net.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

//��������
public class SocketTCP03Server {
    public static void main(String[] args) throws IOException {
        //1�ڱ���������ˣ���9999�˿ڼ������ȴ�����(serversocket�൱��һ�������������ж��socket)
        //serversocket����ͨ��accept()�������ض��socket  [����ͻ����������ǵķ�����]
        ServerSocket serverSocket= new ServerSocket(9999);
        System.out.println("����������ˣ���9999�˿ڼ������ȴ�����");
        //2û�пͻ������ӷ���ˣ�����������ڴˣ��ȴ�����
        //���пͻ������ӣ���᷵��Socket���󣬳��������
        Socket socket = serverSocket.accept();
        System.out.println("��������socket:"+socket.getClass());
        //3ͨ��socket.getIputStream()��ȡ�ͻ���д������ͨ��������
        InputStream inputStream = socket.getInputStream();

        //4.IO��ȡ(ʹ��ת����)
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        System.out.println(bufferedReader.readLine());

        //5.��ȡSocket�����������
        OutputStream outputStream = socket.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedWriter.write("hello, client�ַ���");
        bufferedWriter.newLine();//���뻻�з�����ʾ�ظ����ݵĽ���
        bufferedWriter.flush();//��Ҫ�ֶ�ˢ��



        //6 �ر�����socket
        bufferedWriter.close();//һ���򿪵��ȹر�
        bufferedReader.close();

        socket.close();
        serverSocket.close();


    }
}
