package tankGame.tank_game4;

/**
 * 发射子弹
 */
public class Shot implements Runnable{
    int x;
    int y;
    int direct=0;
    int speed=2;
    boolean isAlive=true;

    public Shot(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }
    @Override
    public void run() {
        while (true){
            //休眠50ms
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            switch (direct){
                case 0:
                    y-=speed;
                    break;
                case 1:
                    x+=speed;
                    break;
                case 2:
                    y+=speed;
                    break;
                case 3:
                    x-=speed;
                    break;

            }
          //  System.out.println("子弹x="+x+" y="+" "+y);
            if(!(x>=0&&x<=1000&&y>=0&&y<=750 && isAlive )){
                isAlive=false;
                System.out.println("子弹线程退出");
                break;
            }
        }
    }
}
