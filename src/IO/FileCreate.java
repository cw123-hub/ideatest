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
     * ��ʾ��ȡ�ļ��������ֽڵĶ�ȡ��Ч�ʺܵ�
     */
    @Test
    public void readFile01(){
        int readData;
        String filePath="d:\\hello.txt";
        FileInputStream fileInputStream = null;
        try {
            //����File
              fileInputStream = new FileInputStream(filePath);
              //����-1,��ȡ���
            while((readData=fileInputStream.read())!=-1){
                System.out.print ((char) readData);
            }
        } catch ( IOException e) {
            throw new RuntimeException(e);
        }finally {
            //�ر��ļ������ͷ���Դ
            try {
                fileInputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

    }

    /**
     * ʹ��read(byte[]b)��ʽ
     */
    @Test
    public void readFile02(){
        int readData;
        String filePath="d:\\hello.txt";
        byte[]buf=new byte[8];
        int readLen=0;
        FileInputStream fileInputStream = null;
        try {
            //����File
            fileInputStream = new FileInputStream(filePath);
            //�Ӹ���������ȡ����buf.length�ֽڵ����ݵ��ַ�����,�����ȡ����������ʵ�ʶ�ȡ���ֽ���,��ȡ��������-1.

            while ((readLen=fileInputStream.read(buf))!=-1) {
                System.out.println(new String(buf,0,readLen));

            }

        } catch ( IOException e) {
            throw new RuntimeException(e);
        }finally {
            //�ر��ļ������ͷ���Դ
            try {
                fileInputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

    }

    /**
     * д���ļ�
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
            //getByte()���ַ���ת��Ϊ�ֽ�����
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
     * File Copy (��d�̵�ͼ������C��)
     */
    @Test
    public void fileCopy(){
        int readDatalen=0;
        //˼·�������ļ������������ļ����뵽����
        //�����ļ�������������������ļ����ݣ�д�뵽ָ���ļ�
        FileInputStream fileInputStream=null;
        FileOutputStream outputStream=null;
        try {
              fileInputStream=new FileInputStream("d:\\qqq.jpg");
              outputStream=new FileOutputStream("d:\\nimeide.jpg");
              byte by[]=new byte[1024 ];
            while ( (readDatalen=fileInputStream.read(by))!=-1) {
                //һ�߶���һ��д
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
     *  FileReader��ȡ�ļ��������ַ�������ʽ��read()
     */
    @Test
    public void Filereader01(){
        String path="d:\\story.txt";
        FileReader fileReader =null;
        int  readData= 0;
        try {
            fileReader=new FileReader( path);
          while ((readData= fileReader.read())!=-1 ){
              //�����ַ���ȡ��
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
     * FileReader��ȡ�ļ��������ַ�������ʽ�� read(char[])
     */
    @Test
    public void Filereader02(){
        String path="d:\\story.txt";
        FileReader fileReader =null;
        char[]ch=new char[8];
        int readLength=0;
        try {
            fileReader=new FileReader( path);
            while ((readLength= fileReader.read(ch))!=-1 ){//ch�����,����ļ�����ÿ�ζ�ȡ�ַ�����.length�ĳ���
                System.out.printf(new String (ch,0,readLength));//�ַ�����ת��ΪString
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
             fileWriter = new FileWriter(FileName, true);//true��ʾ���ļ�ĩβ��
            fileWriter.write("����֮�󶨼��ʺ�");
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

    //BufferedReader��ȡ�ı��ļ�
    @Test
    public void BufferedReader(){
        String path="d:\\readme.txt";
//        BufferedReader bufferedReader=null;
        BufferedReader bufferedReader=null;
        try {
             bufferedReader = new BufferedReader(new FileReader(path));
             String line=null;//�����ж�ȡ
            while((line=bufferedReader.readLine())!=null){
                System.out.println(line);
            }
            //�ر�����ֻ��Ҫ�ر�BufferedReader���ɣ��ײ���Զ��رսڵ�����������FileReader��
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    //BufferedWriterд���ļ�
    @Test
    public void BufferedWriter(){
        String path="d:\\Wen.txt";
        BufferedWriter bufferedWriter=null;
        try {
            bufferedWriter=new BufferedWriter(new FileWriter(path,true));//��true��׷�ӷ�ʽд��
            bufferedWriter.write("�Ұ��㰡",0,3);
            bufferedWriter.write("���ܲ�");
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    //Buffered �ļ�����   (BufferedReader��BufferedWriter���ַ����������������ڲ����������ļ�)
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
                bufferedWriter.newLine();//�������롣

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

    //ʹ��BUfferedInputStream �� BufferedOutputStream �����ֽ��ļ�
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

    //��ʾObjectoutputStream������
    @Test
    public void objoutputStream(){
        String path="d:\\outputStream.txt";//���л��󣬱�����ļ���ʽ���Ǵ��ı�������������Ը
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream=new ObjectOutputStream((new FileOutputStream(path)));
            objectOutputStream.writeInt(100);//int ->Integer(��ʵ����Serialized�ӿ�)
            objectOutputStream.writeChar('a');
            objectOutputStream.writeUTF("��˳ƽ����");
            objectOutputStream.writeObject(new Dog("С��",23));

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


    //�����л�
    @Test
    public void inputstream(){
        String source="d:\\outputStream.txt";
       ObjectInputStream objectInputStream=null;
        try {
            objectInputStream= new ObjectInputStream(new FileInputStream(source));
            //�����л���˳��Ҫ�����л�˳��һ�¡�
            System.out.println(objectInputStream.readInt());
            System.out.println(objectInputStream.readChar());
            System.out.println(objectInputStream.readUTF());
            Object o = objectInputStream.readObject();
            System.out.println("����Ŷing����"+o.getClass());
            System.out.println("Dog message:"+o);
            //�ر������
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //��ʾʹ��InputStreamReader���ֽ���ת��Ϊ�ַ���(InputStreamReader)����ȡ�ļ�
    @Test
    public void InputStreamReader() throws IOException {
        String path="d:\\test_.txt";
        //gbk    ת��Ϊ�ַ���
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(path), "utf-8");

        //��BufferedReader��װ�ַ���(����,��Ϊ����װ�Ļ�����read LIne��������)
        BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
        String s = bufferedReader.readLine();
        System.out.println(s);
        //�ر����
        bufferedReader.close();
    }

    //��ʾʹ��OutputStreamWriter���ֽ���(FileOutputStream)ת��Ϊ�ַ���(OutputStreamWriter)
    @Test
    public void OutPutStreamWriter_Test() throws IOException {
        String path="d:\\test_1.txt";
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(path,true),"utf-8");
        outputStreamWriter.write("����");
        outputStreamWriter.write("�ĸ��");
      outputStreamWriter.close();
    }

    @Test
    public void PrintStream_Test() throws IOException {
          PrintWriter printWriter=new PrintWriter(new FileWriter("d:\\hahahhhahahahhaha.txt"));
          printWriter.println("�����");
          printWriter.close();

    }

    @Test
    public void propertiesdemo() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\33419\\Desktop\\ideatest\\src\\demo.properties"));
        String s = "";
        while((s=bufferedReader.readLine())!=null){
            String []split=s.split("=");
            System.out.println(split[0] +"��"+ split[1]);
        }
        bufferedReader.close();
    }

    @Test
    public void propertiesdemo1() throws IOException {
        Properties properties = new Properties();//properties.setProperty("name","����");
        //����ָ�������ļ�
        properties.load( new FileReader("C:\\Users\\33419\\Desktop\\ideatest\\src\\demo.properties") );

        //��ʾ�ڿ���̨
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
        //����
        properties.setProperty("charset","utf8");
        properties.setProperty("user","Tom");
        properties.setProperty("pwd","cw123456");
        properties.setProperty("pwd","cw1 ");
        //�洢kry-value
properties.store(new FileOutputStream("C:\\Users\\33419\\Desktop\\ideatest\\src\\demo.properties"),"demo1��");
        System.out.println("success");
        properties.list(System.out);
    }

    @Test
    void homeWork01() throws IOException {
        File file = new File("d:\\mytemp");
        if(!file.exists()){
            System.out.println("�����ڣ�����");
            file.mkdirs();


        }else {
            System.out.println("�Ѿ�����Mytemp");
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
            System.out.println(FilePath+"�Ѿ�����");
        }

    }

    @Test
    void homeWork02() throws IOException {
        String filePath="d:\\readme.txt";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
        String s=null;int line=1;
        while((s=bufferedReader.readLine())!=null){
            System.out.println("��"+(line++) +"�У�"+s);
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
        System.out.println("���л�����");
        ObjectInputStream objectInputStream=new ObjectInputStream(new FileInputStream(fipa));
        try {
            Object o = objectInputStream.readObject();
            System.out.println("�����л���+"+o);
            objectInputStream.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
}




//�����Ҫ���л�ĳ����Ķ���ʵ��Serializable�ӿڼ���


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