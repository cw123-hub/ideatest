package Net.HomeWork;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPSenderB {

    public static void main(String[] args) throws IOException {
        //创建datagramSocket,准备在9998端口接收数据。
        DatagramSocket datagramSocket = new DatagramSocket(9998);
        //封装包
        byte[] data = "四大名著是那些".getBytes();
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

        //关闭资源
        datagramSocket.close();
        System.out.println("B端退出~");


    }
}
