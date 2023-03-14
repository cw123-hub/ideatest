package Net.HomeWork;

import java.awt.image.ImagingOpException;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client01 {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(InetAddress.getLocalHost(), 9966);
        OutputStream outputStream = socket.getOutputStream();
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

        //
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入你的问题");
        String s = scanner.next();

        bufferedWriter.write(s);
        bufferedWriter.newLine();
        bufferedWriter.flush();

        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        System.out.println(bufferedReader.readLine());
        bufferedReader.close();
        //
        bufferedWriter.close();
        socket.close();
        System.out.println("Client 退出");
    }
}
