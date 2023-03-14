package Net.HomeWork;

import Net.socket.FileUpload.FileUtils;

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
        InputStream inputStream = socket.getInputStream();
        byte[] bytes = new byte[1024];
        int len=0;
        String downLoadFileName="";
        while((len=inputStream.read(bytes))!=-1){
            downLoadFileName+=new String (bytes,0,len);
        }
        System.out.println("Ҫ���ص��ļ�"+downLoadFileName);
        String resFileName=     "";
        if("������Ӱ".equals(downLoadFileName)){
            resFileName="d:\\������Ӱ.docx";
        }else{
            resFileName="d:\\qqq.jpg";
        }

        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(resFileName));
        byte[] bytes1 = FileUtils.streamToByteArray(bufferedInputStream);

        OutputStream outputStream = socket.getOutputStream();
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
        bufferedOutputStream.write(bytes1);
        socket.shutdownOutput();//�ܹؼ�


        bufferedOutputStream.close();
        bufferedInputStream.close();
        inputStream.close();
        socket.close();
        serverSocket.close();

    }
}
