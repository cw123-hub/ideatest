package Net.HomeWork;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server01 {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9966);
        Socket socket = serverSocket.accept();
        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        String s=bufferedReader.readLine();String ans="";


        if(s.equals("name")){
              ans="chenwen";
        } else if (s.equals("hobby")) {

            ans="JAVA";
        }else {

            ans="gun!";
        }
        OutputStream outputStream = socket.getOutputStream();
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
        bufferedWriter.write(ans);
        bufferedWriter.newLine();
        bufferedWriter.flush();

        socket.close();
        serverSocket.close();
    }
}
