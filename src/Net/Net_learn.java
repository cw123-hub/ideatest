package Net;

import org.junit.jupiter.api.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Net_learn {
    public static void main(String[] args) throws UnknownHostException {
        //1.��ȡ������InetAddress����   DESKTOP-LHMF63V/192.168.41.3
        InetAddress inetAddress = InetAddress.getLocalHost();
        System.out.println(inetAddress);
        //2.
        InetAddress ii = InetAddress.getByName("DESKTOP-LHMF63V");
        System.out.println(ii);
        //3����������ȡ InetAddress
        InetAddress host2 = InetAddress.getByName("www.baidu.com");
        System.out.println(host2);
        //��ȡIP
        System.out.println(host2.getHostAddress());
        System.out.println(host2.getHostName());
    }
    @Test
    public void ii() throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getLocalHost();
        System.out.println(inetAddress);
        String hostName = inetAddress.getHostName();
        System.out.println(hostName);
    }
}
