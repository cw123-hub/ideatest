package smallprogram.aaa;
import java.util.Locale;
import java.util.Scanner;

public class DeadLock {
    public static void main(String[] args) {
//        A a = new A();
//        Thread thread1=new Thread(a);
//        B thread2 = new B(a);  //���߳�A�Ķ��󴫸�B,B���ܹ�����A.
//        thread1.start();
//        thread2.start();
        T t=new T();
        Thread thread1 = new Thread(t);
        Thread thread2 = new Thread(t);
        thread1.start();
        thread2.start();
    }
}
class T implements Runnable{//�漰����̹߳�����Դ��ֻ�ܲ���Runbable
int money=10000;
    @Override
    public void run() {
        while (true){
            //ʵ�����߳�ͬ����
            //������߳�ִ�е�����ʱ����ȥ����this��������
            //���ᵽthis����ĵ��߳̿���ִ��synchronized�����
            //Ϊ���ᵽ�ľͶ������blocked��,׼����������
            //�ǹ�ƽ��
            synchronized (this) {//ͨ��
                if(money<1000){
                    System.out.println("����");
                    break;
                }

                money-=1000;
                System.out.println(Thread.currentThread().getName()+"����" +"Current Money:"+money);
            }
            try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


        }

    }
}
//class A implements Runnable{
//    boolean loop=true;
//
//    public void setLoop(boolean loop) {
//        this.loop = loop;
//    }
//
//    @Override
//    public void run() {
//
//        while(loop){
//            System.out.println((int)(Math.random()*100+1));
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            ;
//        }
//
//    }
//}
//class B extends Thread{
//    private A a ;
//
//    public B(A a) {
//        this.a = a;
//    }
//
//    public A getA() {
//        return a;
//    }
//
//    public void setA(A a) {
//        this.a = a;
//    }
//
//    @Override
//    public void run() {
//
//        while (true) {
//            System.out.println("������=====(Qָ���˳�):");
//            Scanner scanner=new Scanner(System.in);
//            char c = scanner.next().toUpperCase().charAt(0);
//            if(c=='Q'){
//                a.setLoop(false);
//                System.out.println("B�߳��˳�");
//                break;
//            }
//            else {
//                System.out.println("ָ����󡣡���");
//            }
//        }
//
//    }
//}
