package Net.socket.FileUpload;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.util.Locale;


public class FileUtils {
    /**
     *   将输入流转换成字节数组
     * @param is
     * @return
     * @throws IOException
     */
         public  static byte[] streamToByteArray(InputStream is) throws IOException {
             ByteArrayOutputStream bos=new ByteArrayOutputStream();
             byte[] bytes = new byte[1024];
             int len=0;
             while((len=is.read(bytes))!=-1){
                 bos.write(bytes,0,len);
             }
             byte[] array = bos.toByteArray();
             bos.close();
             return array;
         }

    /**
     * 将INput流的内容转为字符串
     * @param is
     * @return
     * @throws IOException
     */
    public static String streamToString(InputStream is) throws IOException {
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
             StringBuilder stringBuilder=new StringBuilder();
             String line;
             while((line =bufferedReader.readLine())!=null){
                 stringBuilder.append(line+"\r\n");
             }
             return stringBuilder.toString();


         }
}
