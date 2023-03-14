package IO;

public abstract  class Reader_ {//������
    public   void readFile(){};
    public   void readString(){};
}

//�ڵ���
class FileReader_ extends Reader_{
    @Override
    public  void  readFile(){
        System.out.println("��ȡ�ļ�");
    }
}

//�ڵ���
class  Stringreader extends Reader_{
    @Override
    public void readString(){
        System.out.println("���ַ���");
    }
}
class BufferedReader extends Reader_{
  private   Reader_ reader_;

  //����reader�������
    public BufferedReader (Reader_ reader_) {
        this.reader_ = reader_;
    }
    public   void readFile(){//��װһ�㡣
        reader_.readFile();
    }
    public void readString(){
        reader_.readString();
    }
}
class Test{
    @org.junit.jupiter.api.Test
    public void test(){
        BufferedReader bufferedReader = new BufferedReader(new FileReader_());
        bufferedReader.readFile();
          bufferedReader = new BufferedReader(new Stringreader());
        bufferedReader.readString();
    }
}