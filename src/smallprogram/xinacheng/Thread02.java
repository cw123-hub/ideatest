package smallprogram.xinacheng;

/**
 * mian �߳������������߳�
 */
public class Thread02 {
    public static void main(String[] args) {
//        Dog dog = new Dog();
//        //dog.start();���ò��ˡ���
//        Thread thread = new Thread(dog);
//        thread.start();
//        Tiger tiger = new Tiger();
//        Proxy proxy = new Proxy(tiger);
//        proxy.start();

        T1 t1 = new T1();
        T2 t2 = new T2();
        Thread thread0 = new Thread(t1);
        Thread thread1 = new Thread(t2);
        thread0.start();//xian cheng 1
        thread1.start();//xian cheng 2
    }
}
class T1 implements Runnable{
    int count=0;
    @Override
    public void run() {
        while (true) {
            System.out.println("Hello World "+(++count));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(count==50){
                break;
            }
        }
    }
}
class T2 implements Runnable{
    int count=0;
    @Override
    public void run() {
        while (true) {
            System.out.println("Hi "+(++count));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(count == 60){
                break;
            }
        }
    }
}
//class Animal{}
//class Tiger extends Animal implements Runnable{
//    @Override
//    public void run() {
//        System.out.println("�ϻ��໽�");
//
//    }
//}
////�̴߳����࣬ģ����һ�������Proxy����
//class Proxy implements Runnable{
//    private Runnable target=null;
//
//    public Proxy(Runnable target) {
//        this.target = target;
//    }
//
//    @Override
//    public void run() {
//        if(target!=null){
//            target.run();
//        }
//    }
//    public void start(){
//        start0();
//    }
//
//    private void start0() {
//        run();
//    }
//}
//class Dog implements Runnable{  //ͨ����ʵ��RUnnable�ӿڿ����߳�
//    int count=0;
//    @Override
//    public void run() {
//        while (true){
//            System.out.println("С��������"+(++count)+":"+Thread.currentThread().getName());
//
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//        }
//
//    }
//}
