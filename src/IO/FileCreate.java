package IO;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Properties;
import java.util.Scanner;

@SuppressWarnings({"all"})
public class FileCreate {
    public static void main(String[] args) {


    }

    /**
     * 演示读取文件，单个字节的读取，效率很低
     */
    @Test
    public void readFile01(){
        int readData;
        String filePath="d:\\hello.txt";
        FileInputStream fileInputStream = null;
        try {
            //创建File
              fileInputStream = new FileInputStream(filePath);
              //返回-1,读取完毕
            while((readData=fileInputStream.read())!=-1){
                System.out.print ((char) readData);
            }
        } catch ( IOException e) {
            throw new RuntimeException(e);
        }finally {
            //关闭文件流，释放资源
            try {
                fileInputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

    }

    /**
     * 使用read(byte[]b)方式
     */
    @Test
    public void readFile02(){
        int readData;
        String filePath="d:\\hello.txt";
        byte[]buf=new byte[8];
        int readLen=0;
        FileInputStream fileInputStream = null;
        try {
            //创建File
            fileInputStream = new FileInputStream(filePath);
            //从该输入流读取至多buf.length字节的数据到字符数组,如果读取正常，返回实际读取的字节数,读取结束返回-1.

            while ((readLen=fileInputStream.read(buf))!=-1) {
                System.out.println(new String(buf,0,readLen));

            }

        } catch ( IOException e) {
            throw new RuntimeException(e);
        }finally {
            //关闭文件流，释放资源
            try {
                fileInputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

    }

    /**
     * 写入文件
     */
    @Test
    public void write(){

        FileOutputStream fileOutputStream = null;

        String FilePath="d:\\a.txt";
        try {

            //public FileOutputStream(File file, boolean append)
            //        throws FileNotFoundException;
            //append = true
            // if <code>true</code>, then bytes will be written
            //     *                   to the end of the file rather than the beginning
            fileOutputStream = new FileOutputStream(FilePath,true );
            //fileOutputStream.write('a');
            String str="dqwdwqdq";
            //getByte()把字符串转化为字节数组
            fileOutputStream.write(str.getBytes(),0,str.length()-2);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    /**
     * File Copy (将d盘的图拷贝到C盘)
     */
    @Test
    public void fileCopy(){
        int readDatalen=0;
        //思路：创建文件输入流，将文件读入到程序
        //创建文件的输出流，将读到的文件数据，写入到指定文件
        FileInputStream fileInputStream=null;
        FileOutputStream outputStream=null;
        try {
              fileInputStream=new FileInputStream("d:\\qqq.jpg");
              outputStream=new FileOutputStream("d:\\nimeide.jpg");
              byte by[]=new byte[1024 ];
            while ( (readDatalen=fileInputStream.read(by))!=-1) {
                //一边读，一边写
                outputStream.write(by,0,readDatalen);
            }
            System.out.println("success");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally{
            try {
                if(fileInputStream!=null) {
                fileInputStream.close();
            }
            if(outputStream!=null) {
                outputStream.close();
            }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }


    /**
     *  FileReader读取文件。（以字符流的形式）read()
     */
    @Test
    public void Filereader01(){
        String path="d:\\story.txt";
        FileReader fileReader =null;
        int  readData= 0;
        try {
            fileReader=new FileReader( path);
          while ((readData= fileReader.read())!=-1 ){
              //单个字符读取。
              System.out.print ((char)readData);

          }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            if(fileReader!=null){
                try {
                    fileReader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * FileReader读取文件。（以字符流的形式） read(char[])
     */
    @Test
    public void Filereader02(){
        String path="d:\\story.txt";
        FileReader fileReader =null;
        char[]ch=new char[8];
        int readLength=0;
        try {
            fileReader=new FileReader( path);
            while ((readLength= fileReader.read(ch))!=-1 ){//ch会更新,如果文件够长每次读取字符数组.length的长度
                System.out.printf(new String (ch,0,readLength));//字符数组转化为String
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            if(fileReader!=null){
                try {
                    fileReader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


    @Test
    public void FileWriter(){
        FileWriter fileWriter =null;
        String FileName="d:\\note.txt";
        try {
             fileWriter = new FileWriter(FileName, true);//true表示在文件末尾加
            fileWriter.write("风雨之后定见彩虹");
            System.out.println("success");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            if(fileWriter!=null){
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    //BufferedReader读取文本文件
    @Test
    public void BufferedReader(){
        String path="d:\\readme.txt";
//        BufferedReader bufferedReader=null;
        BufferedReader bufferedReader=null;
        try {
             bufferedReader = new BufferedReader(new FileReader(path));
             String line=null;//按照行读取
            while((line=bufferedReader.readLine())!=null){
                System.out.println(line);
            }
            //关闭流，只需要关闭BufferedReader即可，底层会自动关闭节点流（这里是FileReader）
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    //BufferedWriter写入文件
    @Test
    public void BufferedWriter(){
        String path="d:\\Wen.txt";
        BufferedWriter bufferedWriter=null;
        try {
            bufferedWriter=new BufferedWriter(new FileWriter(path,true));//带true以追加方式写入
            bufferedWriter.write("我爱你啊",0,3);
            bufferedWriter.write("抽萝卜");
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    //Buffered 文件拷贝   (BufferedReader和BufferedWriter是字符操作流，不能用于操作二进制文件)
    @Test
    public void BufferedCopy(){
        String srcFilePath="d:\\readme.txt";
        String desFilePath="d:\\wocao.txt";
        BufferedReader bufferedReader=null;
        BufferedWriter bufferedWriter=null;
        try {
            bufferedReader= new BufferedReader(new FileReader(srcFilePath));
            bufferedWriter=new BufferedWriter(new FileWriter(desFilePath,true));
            String line ;
            while( (line=bufferedReader.readLine())!=null){

                bufferedWriter.write(line);
                bufferedWriter.newLine();//换行输入。

            }
            System.out.println("success");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if(bufferedReader!=null){
                    bufferedReader.close();
                }
                if(bufferedWriter!=null){
                    bufferedWriter.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    //使用BUfferedInputStream 和 BufferedOutputStream 拷贝字节文件
    @Test
    public void BufferedInputStream_(){
        String source="d:\\qqq.jpg";
        String des="d:\\yyyyyyyyyyyyyyyyyyyyyyyyyy.jpg";
        BufferedInputStream bufferedInputStream=null;
        BufferedOutputStream bufferedOutputStream=null;
         byte[] byt=new byte[8];
         int len=0;
        try {
           bufferedInputStream=new BufferedInputStream(new FileInputStream(source));
           bufferedOutputStream =new BufferedOutputStream(new FileOutputStream(des,true));
           while( (len=bufferedInputStream.read(byt))!=-1){
               bufferedOutputStream.write(byt);
           }
            System.out.println("success!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if(bufferedInputStream!=null)
                bufferedInputStream.close();
                if(bufferedOutputStream!=null)
                    bufferedOutputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

    //演示ObjectoutputStream的运用
    @Test
    public void objoutputStream(){
        String path="d:\\outputStream.txt";//序列化后，保存的文件格式不是纯文本，而是随她意愿
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream=new ObjectOutputStream((new FileOutputStream(path)));
            objectOutputStream.writeInt(100);//int ->Integer(它实现了Serialized接口)
            objectOutputStream.writeChar('a');
            objectOutputStream.writeUTF("韩顺平教育");
            objectOutputStream.writeObject(new Dog("小黄",23));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if(objectOutputStream!=null)
                objectOutputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    //反序列化
    @Test
    public void inputstream(){
        String source="d:\\outputStream.txt";
       ObjectInputStream objectInputStream=null;
        try {
            objectInputStream= new ObjectInputStream(new FileInputStream(source));
            //反序列化的顺序要和序列化顺序一致。
            System.out.println(objectInputStream.readInt());
            System.out.println(objectInputStream.readChar());
            System.out.println(objectInputStream.readUTF());
            Object o = objectInputStream.readObject();
            System.out.println("运行哦ing类型"+o.getClass());
            System.out.println("Dog message:"+o);
            //关闭外层流
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //演示使用InputStreamReader将字节流转换为字符流(InputStreamReader)来读取文件
    @Test
    public void InputStreamReader() throws IOException {
        String path="d:\\test_.txt";
        //gbk    转换为字符流
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(path), "utf-8");

        //用BufferedReader包装字符流(方便,因为不包装的话少了read LIne（）方法)
        BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
        String s = bufferedReader.readLine();
        System.out.println(s);
        //关闭外层
        bufferedReader.close();
    }

    //演示使用OutputStreamWriter将字节流(FileOutputStream)转换为字符流(OutputStreamWriter)
    @Test
    public void OutPutStreamWriter_Test() throws IOException {
        String path="d:\\test_1.txt";
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(path,true),"utf-8");
        outputStreamWriter.write("张悦");
        outputStreamWriter.write("文哥哥");
      outputStreamWriter.close();
    }

    @Test
    public void PrintStream_Test() throws IOException {
          PrintWriter printWriter=new PrintWriter(new FileWriter("d:\\hahahhhahahahhaha.txt"));
          printWriter.println("你哈哈");
          printWriter.close();

    }

    @Test
    public void propertiesdemo() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\33419\\Desktop\\ideatest\\src\\demo.properties"));
        String s = "";
        while((s=bufferedReader.readLine())!=null){
            String []split=s.split("=");
            System.out.println(split[0] +"是"+ split[1]);
        }
        bufferedReader.close();
    }

    @Test
    public void propertiesdemo1() throws IOException {
        Properties properties = new Properties();//properties.setProperty("name","陈文");
        //加载指定配置文件
        properties.load( new FileReader("C:\\Users\\33419\\Desktop\\ideatest\\src\\demo.properties") );

        //显示在控制台
        properties.list(System.out);
        //
        String user=properties.getProperty("name");
        String pwd = properties.getProperty("pwd");
        System.out.println("name="+user+"pwd"+pwd);


    }
    //
    @Test
    void propertiesdemo2() throws IOException {
        Properties properties = new Properties();
        //创建
        properties.setProperty("charset","utf8");
        properties.setProperty("user","Tom");
        properties.setProperty("pwd","cw123456");
        properties.setProperty("pwd","cw1 ");
        //存储kry-value
properties.store(new FileOutputStream("C:\\Users\\33419\\Desktop\\ideatest\\src\\demo.properties"),"demo1啊");
        System.out.println("success");
        properties.list(System.out);
    }

    @Test
    void homeWork01() throws IOException {
        File file = new File("d:\\mytemp");
        if(!file.exists()){
            System.out.println("不存在，创建");
            file.mkdirs();


        }else {
            System.out.println("已经存在Mytemp");
        }
        String FilePath=file.getPath()+"\\hello.txt";
        file=new File(FilePath);
        if(!file.exists()){
            if (file.createNewFile()) {
                BufferedWriter bfr=new BufferedWriter(new FileWriter(file));
                bfr.write("Hello");
                bfr.close();
            }
            System.out.println(
                    FilePath+ "chuang jian success"
            );
        }else{
            System.out.println(FilePath+"已经存在");
        }

    }

    @Test
    void homeWork02() throws IOException {
        String filePath="d:\\readme.txt";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
        String s=null;int line=1;
        while((s=bufferedReader.readLine())!=null){
            System.out.println("第"+(line++) +"行："+s);
        }
       bufferedReader.close();
    }
    @Test
    void homeWork03() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader("C:\\Users\\33419\\Desktop\\ideatest\\src\\demo.properties"));
      //  Dog dog = new Dog(properties.getProperty("name"),properties.getProperty("age") );
        //properties.list(System.out);
        System.out.println(properties.getProperty("name").getClass());
        String name=properties.getProperty("name")+"";
        int age= Integer.parseInt(properties.getProperty("age")+"") ;
        Dog dog = new Dog(name, age);
        System.out.println(dog);
        String fipa="d:\\dog.dat";
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fipa));
        objectOutputStream.writeObject(dog);
        objectOutputStream.close();
        System.out.println("序列化结束");
        ObjectInputStream objectInputStream=new ObjectInputStream(new FileInputStream(fipa));
        try {
            Object o = objectInputStream.readObject();
            System.out.println("反序列化后：+"+o);
            objectInputStream.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
}




//如果需要序列化某个类的对象，实现Serializable接口即可


class Dog implements Serializable{
    private String name;
    private int age;



    public Dog(String  name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }


}