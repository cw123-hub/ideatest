package smallprogram.aaa;

import java.util.*;
public class exceptionTest {
    public static void main(String[] args) {

        Set hashSet = new HashSet();
        Node[]table=new Node[16];
        System.out.println("table="+table);
        //3.�����ڵ�
        Node john = new Node("john", null);
        table[2]=john;
        Node jack = new Node("jack", null);
        //����jack��jhon�ĺ���
        john.next=jack;
        Node rose = new Node("Rose", null);
        jack.next=rose;
        Node lucy = new Node("lucy", null);
        table[3]=lucy;
        System.out.println(table);
    }
}
class Node{
    Object item;   //�������
    Node next;   //ָ����һ���ڵ�

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
