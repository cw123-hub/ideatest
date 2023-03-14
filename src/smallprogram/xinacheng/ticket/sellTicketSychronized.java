package smallprogram.xinacheng.ticket;

public class sellTicketSychronized implements Runnable{
    private static int ticketNum=100;
    boolean loop=true;

    //静态同步方法的锁为当前类自己  (sellTicketSynchronized.class)
    public synchronized static void m1(){

    }
    public static void m2(){
        synchronized (sellTicketSychronized.class){
            System.out.println("m2");
        }
    }



//public synchronized void  m();同步方法，这时候锁是在this上
//也可以在代码块上(非静态)些synchronized，这时候叫同步代码块，互斥锁还是在this上。
    public /*synchronized*/ void  m(){//同步方法，同一时刻只能由一个线程来执行m 方法
        synchronized (this) {
            if (ticketNum <= 0) {
                System.out.println("售票结束...");
                loop = false;
                return;
            }
            //xiu mian 50ms
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("窗口" + Thread.currentThread().getName() + "售出一张票子，剩余票数：" + (--ticketNum));
        }

    }
    @Override
    public  void run() {
        while (loop) {
            m();//m是一个同步方法
        }
    }


}
