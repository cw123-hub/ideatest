package Net.UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPReceiverA {
    public static void main(String[] args) throws IOException {
        //����һ��DatagramSocket ����׼����9999��������
        DatagramSocket datagramSocket = new DatagramSocket(9999);
        //����һ��Datagrampackge����׼����������
        byte[] bytes = new byte[1024];
        DatagramPacket  packet = new DatagramPacket(bytes, bytes.length);
        //3.���ý��ܷ���,�����紫���datagrampacket������䵽packet����
        //����û�����ݷ��͵�9999�˿�ʱ�򣬾ͻ�����
        System.out.println("���ն�A�ȴ����ݽ��ܡ���");
        datagramSocket.receive(packet);

        //4.packet�����ȡ�����ݲ���ʾ
        int len=packet.getLength();//ʵ�ʽ��ܵ��ֽڳ���
        byte[] data = packet.getData();//���յ�������
        String s = new String(data, 0, len);
        System.out.println(s);

        //send
        byte[] bytes1 = "�õģ������".getBytes();
        DatagramPacket datagramPacket = new DatagramPacket(bytes1, bytes1.length, InetAddress.getByName("192.168.41.5"),9998);
        datagramSocket.send(datagramPacket);


        //5�ر�socket

        datagramSocket.close();
        System.out.println("A���˳�");

    }
}
