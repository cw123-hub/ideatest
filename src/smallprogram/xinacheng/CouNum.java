package smallprogram.xinacheng;

public class CouNum {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Cat());
        System.out.println(thread.getName()+"×´Ì¬£º"+thread.getState());
        thread.start();
        while(thread.getState()!=Thread.State.TERMINATED){
            System.out.println(thread.getName()+"×´Ì¬£º"+thread.getState());
            Thread.sleep(500);
        }
        System.out.println(thread.getName()+"×´Ì¬£º"+thread.getState());


    }
}

class Cat   implements Runnable{
    int times=0;

    @Override
    public void run() {
        while (true){
            for (int i = 0; i < 10; i++) {
                System.out.println("hi" + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            break;
        }
    }
}
