package smallprogram.xinacheng.ticket;

/**
 * ���̣߳�ģ����������ͬʱ��Ʊ
 */
public class sellTicket {
    public static void main(String[] args) {
        sellTicketSychronized sellTicketSychronized = new sellTicketSychronized();
        new Thread(sellTicketSychronized).start();
        new Thread(sellTicketSychronized).start();
        new Thread(sellTicketSychronized).start();
    }

}
