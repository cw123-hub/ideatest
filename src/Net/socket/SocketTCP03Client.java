package Net.socket;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class SocketTCP03Client {
    public static void main(String[] args) throws IOException {
        //1���ӷ�������ip���˿ڣ�
        //1���ӱ�����9999�˿ڣ����ӳɹ�������SOcket����
        Socket socket = new Socket(InetAddress.getLocalHost(),9999);
        System.out.println("�ͻ���socket���أ�"+socket.getClass());
        //2.�����Ϻ�����Socket��ͨ��socket.getoutputStream()
        //�õ���Socket��������������
        OutputStream outputStream = socket.getOutputStream();
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
        outputStreamWriter.write("Hello server�ַ���");
        bufferedWriter.newLine();//���뻻�з�����ʾд������ݽ�������Ҫ��Է���readLie()������
        bufferedWriter.flush();//ʹ���ַ�����Ҫ�ֶ�˧�£��������ݲ���д������ͨ��
        //����д��������(�ַ�������Ҫ)
        //outputStreamWriter.
        //3.��ȡsocket������������
        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
        System.out.println(bufferedReader.readLine());


        //4.�ر��������Socket
        bufferedReader.close();
        bufferedWriter.close();
        socket.close();
        System.out.println("�ͻ����˳�~");

    }

}
