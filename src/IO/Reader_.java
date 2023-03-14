package IO;

public abstract  class Reader_ {//抽象类
    public   void readFile(){};
    public   void readString(){};
}

//节点流
class FileReader_ extends Reader_{
    @Override
    public  void  readFile(){
        System.out.println("读取文件");
    }
}

//节点流
class  Stringreader extends Reader_{
    @Override
    public void readString(){
        System.out.println("读字符串");
    }
}
class BufferedReader extends Reader_{
  private   Reader_ reader_;

  //接受reader子类对象
    public BufferedReader (Reader_ reader_) {
        this.reader_ = reader_;
    }
    public   void readFile(){//封装一层。
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