package Net.HomeWork;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPReceiverA {
    public static void main(String[] args) throws IOException {
        //创建一个DatagramSocket 对象，准备在9999接收数据
        DatagramSocket datagramSocket = new DatagramSocket(9999);
        //构建一个Datagrampackge对象准备接收数据
        byte[] bytes = new byte[1024];
        DatagramPacket  packet = new DatagramPacket(bytes, bytes.length);
        //3.调用接受方法,将网络传输的datagrampacket对象填充到packet对象
        //而当没有数据发送到9999端口时候，就会阻塞
        System.out.println("接收端A等待数据接受。。");
        datagramSocket.receive(packet);

        //4.packet拆包，取出数据并显示
        int len=packet.getLength();//实际接受的字节长度
        byte[] data = packet.getData();//接收到的数据
        String s = new String(data, 0, len);
        if(s.equals("四大名著是那些")){
            s="四大明珠";
        }else{
            s="what";
        }
        //System.out.println(s);

        //send
        byte[] bytes1 = s.getBytes();
        DatagramPacket datagramPacket = new DatagramPacket(bytes1, bytes1.length, InetAddress.getByName("192.168.41.5"),9998);
        datagramSocket.send(datagramPacket);


        //5关闭socket

        datagramSocket.close();
        System.out.println("A端退出");

    }
}
