package tankGame.tank_game3;

import java.util.Vector;

/**
 * my tank;
 */
public class Hero extends Tank {
    //表示一个射击。
    Shot shot=null;
    //可以发射多颗子弹
   // Vector<Shot>shots=new Vector<>();
    public Hero(int x, int y) {
        super(x, y);
    }
    //射击
    public void shotEnemy(){
//        if(shots.size()==5){
//            return;
//        }
        switch (getDirect()){
            case 0:
                shot=new Shot(getX()+20,getY(),0);
                break;
            case 1:
                shot=new Shot(getX()+60,getY()+20,1);
                break;
            case 2:
                shot=new Shot(getX()+20,getY()+60,2);
                break;
            case 3:
                shot =new Shot(getX(),getY()+20,3);
                break;

        }//新创建的shot放入集合shots;
      //  shots.add(shot);
        //启动shot线程
        Thread thread = new Thread(shot);
        thread.start();

    }
}
