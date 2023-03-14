package smallprogram.Exception;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.util.*;
public class exceptionTest   {

    public static void main(String[] args) {




    }
    @Test
    public void m3(){
        System.out.println("ni hao");
    }
}

class Node{
    Object item;   //存放数据
    Node next;   //指向下一个节点

    public Node(Object item, Node next) {
        this.item = item;
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node{" +
                "item=" + item +
                ", next=" + next +
                '}';
    }
}
class panel extends JPanel{
    //panel 是一个画板
    //Graphic 是一个画笔
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawOval(10,10,100,100);
    }
}
class AA{

}
class BB extends AA{

}
//class Book{
//    String name;
//    String author;
//
//    public Book(String name, String author) {
//        this.name = name;
//        this.author = author;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getAuthor() {
//        return author;
//    }
//
//    public void setAuthor(String author) {
//        this.author = author;
//    }
//
//    @Override
//    public String toString() {
//        return "Book{" +
//                "name='" + name + '\'' +
//                ", author='" + author + '\'' +
//                '}';
//    }
//}
//public class exceptionTest {
//
//    public static void main(String[] args) throws ParseException {
//        String str="qwerty";
//        System.out.println(str);
//        Reverse(str,1,4);
//        System.out.println(Reverse(str,1,4));
//
//    }
//    public  static String Reverse(String str,int start,int end){
//
//        String str1=str.substring(0,start);
//        String str2=str.substring(start,end+1);
//        String str3 =str.substring(end+1,str.length());
//        String temp="";
//        char[] strNew= str2.toCharArray();
//        for(int i=0, j=strNew.length-1;i<strNew.length/2;i++,j--){
//            char c=strNew[i];
//            strNew[i]=strNew[j];
//            strNew[j]=c;
//        }
//        str2=String.copyValueOf(strNew);
//        return str1+str2+str3;
//    }
//
//
//}
