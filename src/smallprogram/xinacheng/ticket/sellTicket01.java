package smallprogram.xinacheng.ticket;

public class sellTicket01 implements Runnable{
    private static int ticketNum=100;

    @Override
    public void run() {

        while (true) {
            if(ticketNum<=0){
                System.out.println("售票结束");
                break;
            }
            //xiu mian 50ms
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("窗口"+Thread.currentThread().getName()+"售出一张票子，剩余票数："+(--ticketNum));
        }
    }
}
