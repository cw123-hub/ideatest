package tankGame.tank_game4;

import java.util.Vector;

public class EnemyTank extends Tank implements  Runnable{
    public boolean isAlive=true;
    Vector<Shot> shots=new Vector<Shot>();
    //增加乘员，EnemyTank可以得到敌人坦克的Vector
    Vector<EnemyTank> enemyTanks =new Vector<>();
    public EnemyTank(int x, int y) {
        super(x, y);
    }

    //提供方法获取MyPanel对象的enemyTanks
    public void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        this.enemyTanks = enemyTanks;
    }

    //判断当前坦克是否和Vector中的其他坦克发生碰撞
    public  boolean isTouchEnemyTank(){
        //当前敌人坦克的方向
        switch (this.getDirect()){
            case 0://此敌人坦克为上
                for (int i=0;i<enemyTanks.size();i++){
                    //从Vector中取出一辆坦克
                    EnemyTank enemyTank = enemyTanks.get(i);
                    //不和自己比较

                    if(enemyTank !=this){

                        //敌人坦克 上/ 下  对应图1
                        if(enemyTank.getDirect()==0 || enemyTank.getX()==2){
                            //该坦克左上角
                            if(this.getX()>= enemyTank.getX() &&
                                    this.getX()<= enemyTank.getX()+40 &&
                                    this.getY()>= enemyTank.getY() &&
                                    this.getY()<= enemyTank.getY()+60){
                                return  true;
                            }
                           //该坦克右上角
                            if(this.getX()+40>= enemyTank.getX() &&
                                    this.getX()+40<= enemyTank.getX()+40 &&
                                    this.getY()>= enemyTank.getY() &&
                                    this.getY()<= enemyTank.getY()+60){
                                    return  true;
                            }
                        }
                        //敌人坦克左右 对应图二
                        if(enemyTank.getDirect()==1 || enemyTank.getDirect()==3){
                            //该坦克左上角
                            if(this.getX()>= enemyTank.getX() &&
                                    this.getX()<= enemyTank.getX()+60 &&
                                    this.getY()>= enemyTank.getY() &&
                                    this.getY()<= enemyTank.getY()+40){
                                return  true;
                            }
                            //该坦克右上角
                            if(this.getX()+40>= enemyTank.getX() &&
                                    this.getX()+40<= enemyTank.getX()+60 &&
                                    this.getY()>= enemyTank.getY() &&
                                    this.getY()<= enemyTank.getY()+40){
                                return  true;
                            }
                        }



                    }
                }
                break;

            case 1:
                for (int i=0;i<enemyTanks.size();i++){
                    //从Vector中取出一辆坦克
                    EnemyTank enemyTank = enemyTanks.get(i);
                    //不和自己比较

                    if(enemyTank !=this){

                        //敌人坦克 上/ 下  对应图3
                        if(enemyTank.getDirect()==0 || enemyTank.getX()==2){
                            //该坦克右上角
                            if(this.getX()+60>= enemyTank.getX() &&
                                    this.getX()+60<= enemyTank.getX()+40 &&
                                    this.getY()>= enemyTank.getY() &&
                                    this.getY()<= enemyTank.getY()+60){
                                return  true;
                            }
                            //该坦克右下角
                            if(this.getX()+60>= enemyTank.getX() &&
                                    this.getX()+60<= enemyTank.getX()+40 &&
                                    this.getY()+40>= enemyTank.getY() &&
                                    this.getY()+40<= enemyTank.getY()+60){
                                return  true;
                            }
                        }
                        //敌人坦克左右 对应图4
                        if(enemyTank.getDirect()==1 || enemyTank.getDirect()==3){
                            //该坦克右上角
                            if(this.getX()+60>= enemyTank.getX() &&
                                    this.getX()+60<= enemyTank.getX()+60 &&
                                    this.getY()>= enemyTank.getY() &&
                                    this.getY()<= enemyTank.getY()+40){
                                return  true;
                            }
                            //该坦克右下角
                            if(this.getX()+60>= enemyTank.getX() &&
                                    this.getX()+60<= enemyTank.getX()+60 &&
                                    this.getY()+40>= enemyTank.getY() &&
                                    this.getY()+40<= enemyTank.getY()+40){
                                return  true;
                            }
                        }



                    }
                }
                break;
            case 2:
                for (int i=0;i<enemyTanks.size();i++){
                    //从Vector中取出一辆坦克
                    EnemyTank enemyTank = enemyTanks.get(i);
                    //不和自己比较

                    if(enemyTank !=this){

                        //敌人坦克 上/ 下  对应图5
                        if(enemyTank.getDirect()==0 || enemyTank.getX()==2){

                            if(this.getX() >= enemyTank.getX() &&
                                    this.getX() <= enemyTank.getX()+40 &&
                                    this.getY()+60>= enemyTank.getY() &&
                                    this.getY()+60<= enemyTank.getY()+60){
                                return  true;
                            }
                            //该坦克右下角
                            if(this.getX()+40>= enemyTank.getX() &&
                                    this.getX()+40<= enemyTank.getX()+40 &&
                                    this.getY()+60>= enemyTank.getY() &&
                                    this.getY()+60<= enemyTank.getY()+60){
                                return  true;
                            }
                        }
                        //敌人坦克左右
                        if(enemyTank.getDirect()==1 || enemyTank.getDirect()==3){
                            //该坦克右上角
                            if(this.getX()>= enemyTank.getX() &&
                                    this.getX()<= enemyTank.getX()+60 &&
                                    this.getY()+60>= enemyTank.getY() &&
                                    this.getY()+60<= enemyTank.getY()+40){
                                return  true;
                            }
                            //该坦克右下角
                            if(this.getX()+40>= enemyTank.getX() &&
                                    this.getX()+40<= enemyTank.getX()+60 &&
                                    this.getY()+60>= enemyTank.getY() &&
                                    this.getY()+60<= enemyTank.getY()+40){
                                return  true;
                            }
                        }



                    }
                }
                break;

            case 3:
                for (int i=0;i<enemyTanks.size();i++){
                    //从Vector中取出一辆坦克
                    EnemyTank enemyTank = enemyTanks.get(i);
                    //不和自己比较

                    if(enemyTank !=this){

                        //敌人坦克 上/ 下  对应图5
                        if(enemyTank.getDirect()==0 || enemyTank.getX()==2){

                            if(this.getX() >= enemyTank.getX() &&
                                    this.getX() <= enemyTank.getX()+40 &&
                                    this.getY() >= enemyTank.getY() &&
                                    this.getY() <= enemyTank.getY()+60){
                                return  true;
                            }
                            //该坦克右下角
                            if(this.getX()  >= enemyTank.getX() &&
                                    this.getX() <= enemyTank.getX()+40 &&
                                    this.getY()+40>= enemyTank.getY() &&
                                    this.getY()+40<= enemyTank.getY()+60){
                                return  true;
                            }
                        }
                        //敌人坦克左右
                        if(enemyTank.getDirect()==1 || enemyTank.getDirect()==3){
                            //该坦克右上角
                            if(this.getX()>= enemyTank.getX() &&
                                    this.getX()<= enemyTank.getX()+60 &&
                                    this.getY() >= enemyTank.getY() &&
                                    this.getY() <= enemyTank.getY()+40){
                                return  true;
                            }
                            //该坦克右下角
                            if(this.getX() >= enemyTank.getX() &&
                                    this.getX() <= enemyTank.getX()+60 &&
                                    this.getY()+40>= enemyTank.getY() &&
                                    this.getY()+40<= enemyTank.getY()+40){
                                return  true;
                            }
                        }



                    }
                }
                break;

        }
        return false;
    }

    @Override
    public void run() {
        while(true){
            //控制只有一颗子弹
            if(shots.size()==0 && isAlive){
                Shot s=null;
                //判断坦克方向，创建对应子弹
                switch (getDirect()){
                    case 0:
                        s=new Shot(getX()+20,getY(),0);
                        break;
                    case 1:
                        s=new Shot(getX()+60,getY()+20,1);
                        break;
                    case 2:
                        s=new Shot(getX()+20,getY()+60,2);
                        break;
                    case 3:
                        s=new Shot(getX(),getY()+20,3);
                        break;
                }
                shots.add(s);
                new Thread(s).start();

            }
            //根据坦克的方向来自由移动
            switch (getDirect()){
                case 0:
                    for (int i=0;i<30;i++) {
                        if(getY()>0 && !isTouchEnemyTank()) {
                            moveUp();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 1:

                    for (int i=0;i<30;i++) {
                        if(getX()+60<1000 && !isTouchEnemyTank()){
                        moveRight();}
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }break;
                case 2:

                    for (int i=0;i<30;i++) {
                        if(getY()+60<750 && !isTouchEnemyTank()) {
                            moveDown();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }break;
                case 3:

                    for (int i=0;i<30;i++) {
                        if(getX()>0 && !isTouchEnemyTank()) {
                            moveLeft();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //随机改变坦克方向  0-3 [0-4)
            setDirect((int)(Math.random()*4));
            //写并发程序一定要考虑什么时候退出
            if(isAlive==false){
                break;
            }
        }
    }
}
