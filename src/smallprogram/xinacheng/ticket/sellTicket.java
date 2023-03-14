package smallprogram.xinacheng.ticket;

/**
 * 多线程，模拟三各窗口同时售票
 */
public class sellTicket {
    public static void main(String[] args) {
        sellTicketSychronized sellTicketSychronized = new sellTicketSychronized();
        new Thread(sellTicketSychronized).start();
        new Thread(sellTicketSychronized).start();
        new Thread(sellTicketSychronized).start();
    }

}
