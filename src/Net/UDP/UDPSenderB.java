package Net.UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPSenderB {

    public static void main(String[] args) throws IOException {
        //����datagramSocket,׼����9998�˿ڽ������ݡ�
        DatagramSocket datagramSocket = new DatagramSocket(9998);
        //��װ��
        byte[] data = "Hello,ȥ�Կϵ»�".getBytes();
        DatagramPacket datagramPacket = new DatagramPacket(data, data.length, InetAddress.getByName("192.168.41.5"),9999);
        //send
        datagramSocket.send(datagramPacket);
        //receive
        byte[] bytes = new byte[1024];
        DatagramPacket datagramPacket1 = new DatagramPacket(bytes, bytes.length);
        datagramSocket.receive(datagramPacket1);
        byte[] data1 = datagramPacket1.getData();
        int length = datagramPacket1.getLength();
        String s = new String(data1, 0, length);
        System.out.println(s);

        //�ر���Դ
        datagramSocket.close();
        System.out.println("B���˳�~");


    }
}
