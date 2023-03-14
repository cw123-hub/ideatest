package smallprogram.aaa;
import java.util.Locale;
import java.util.Scanner;

public class DeadLock {
    public static void main(String[] args) {
//        A a = new A();
//        Thread thread1=new Thread(a);
//        B thread2 = new B(a);  //把线程A的对象传给B,B就能够控制A.
//        thread1.start();
//        thread2.start();
        T t=new T();
        Thread thread1 = new Thread(t);
        Thread thread2 = new Thread(t);
        thread1.start();
        thread2.start();
    }
}
class T implements Runnable{//涉及多个线程共享资源，只能采用Runbable
int money=10000;
    @Override
    public void run() {
        while (true){
            //实现了线程同步。
            //当多个线程执行到这里时，就去争夺this对象锁。
            //争夺到this对象的的线程可以执行synchronized代码块
            //为争夺到的就堵在这里（blocked）,准备继续争夺
            //非公平锁
            synchronized (this) {//通过
                if(money<1000){
                    System.out.println("余额不足");
                    break;
                }

                money-=1000;
                System.out.println(Thread.currentThread().getName()+"来了" +"Current Money:"+money);
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
//            System.out.println("请输入=====(Q指代退出):");
//            Scanner scanner=new Scanner(System.in);
//            char c = scanner.next().toUpperCase().charAt(0);
//            if(c=='Q'){
//                a.setLoop(false);
//                System.out.println("B线程退出");
//                break;
//            }
//            else {
//                System.out.println("指令错误。。。");
//            }
//        }
//
//    }
//}
