package smallprogram.xinacheng;

public class exit  {
    public static void main(String[] args) throws InterruptedException {
        T t = new T();
        t.start();
        //让主线程休眠十秒，再次通知子线程退出
        System.out.println("主线程休眠十秒");
        Thread.sleep(10*1000);
        t.setLoop(false);
    }
}
class T extends Thread{
    int count =0;
    private boolean loop=true;
    @Override
    public void run() {
        while (loop) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("T 运行中"+(count++));

        }

    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }
}
