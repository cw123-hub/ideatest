package smallprogram.xinacheng;

public class exit  {
    public static void main(String[] args) throws InterruptedException {
        T t = new T();
        t.start();
        //�����߳�����ʮ�룬�ٴ�֪ͨ���߳��˳�
        System.out.println("���߳�����ʮ��");
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
            System.out.println("T ������"+(count++));

        }

    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }
}
