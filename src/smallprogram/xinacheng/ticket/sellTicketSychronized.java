package smallprogram.xinacheng.ticket;

public class sellTicketSychronized implements Runnable{
    private static int ticketNum=100;
    boolean loop=true;

    //��̬ͬ����������Ϊ��ǰ���Լ�  (sellTicketSynchronized.class)
    public synchronized static void m1(){

    }
    public static void m2(){
        synchronized (sellTicketSychronized.class){
            System.out.println("m2");
        }
    }



//public synchronized void  m();ͬ����������ʱ��������this��
//Ҳ�����ڴ������(�Ǿ�̬)Щsynchronized����ʱ���ͬ������飬������������this�ϡ�
    public /*synchronized*/ void  m(){//ͬ��������ͬһʱ��ֻ����һ���߳���ִ��m ����
        synchronized (this) {
            if (ticketNum <= 0) {
                System.out.println("��Ʊ����...");
                loop = false;
                return;
            }
            //xiu mian 50ms
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("����" + Thread.currentThread().getName() + "�۳�һ��Ʊ�ӣ�ʣ��Ʊ����" + (--ticketNum));
        }

    }
    @Override
    public  void run() {
        while (loop) {
            m();//m��һ��ͬ������
        }
    }


}
