package smallprogram.xinacheng.ticket;

public class sellTicket01 implements Runnable{
    private static int ticketNum=100;

    @Override
    public void run() {

        while (true) {
            if(ticketNum<=0){
                System.out.println("��Ʊ����");
                break;
            }
            //xiu mian 50ms
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("����"+Thread.currentThread().getName()+"�۳�һ��Ʊ�ӣ�ʣ��Ʊ����"+(--ticketNum));
        }
    }
}
