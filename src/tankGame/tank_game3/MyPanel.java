package tankGame.tank_game3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

/**
 * 坦克大战绘图区域
 */
//为了让panel不停的绘制子弹，需要mypanel成为一个线程，也就是实现runnable接口
public class MyPanel extends JPanel implements KeyListener , Runnable{
    //定义本人坦克
    Hero hero = null;
    //定义敌人坦克放入Vector中，Vector是线程安全的。
    Vector<EnemyTank> enemyTanks=new Vector<>();
    int enemySize = 3;
    //定义一个Vector,用于存放炸弹
    //子弹集中坦克，就加入对象到bombs;
    Vector<Bomb> bombs=new Vector<Bomb>();
    //定义3张炸弹图片，用于显示爆炸效果
    Image image1=null,image2=null,image3=null;
    public MyPanel() {
        hero = new Hero(100, 100);//初始化我的坦克
        hero.setSpeed(10);//初始化我的速度
        for(int i=0;i < enemySize;i++){//初始化敌人坦克
            EnemyTank enemyTank = new EnemyTank(100 * (i + 1), 0);
            enemyTank.setDirect(2);
            //启动敌人坦克线程
            new Thread(enemyTank).start();

            Shot shot=new Shot(enemyTank.getX()+20,enemyTank.getY()+60,enemyTank.getDirect());
            enemyTank.shots.add(shot);
            new Thread(shot).start();
            enemyTanks.add(enemyTank);
        }
        //初始化图片对象
        image1=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_1.gif"));
        image2=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_2.gif"));
        image3=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_3.gif"));

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.gray);

        g.fillRect(0, 0, 1000, 750);//填充矩形,默认黑色
        // 画自己坦克
        if(hero.isAlive && hero!=null) {
            drawTank(hero.getX(), hero.getY(), g, hero.getDirect(), 1);
        }


        //画hero的子弹
        if(hero.shot!=null && hero.shot.isAlive){
            g.draw3DRect(hero.shot.x,hero.shot.y,2,2,false);
            System.out.println("子弹被绘制");
        }
        //将hero的子弹遍历取出，绘制。
//        for(int i=0;i<hero.shots.size();i++){
//            Shot shot = hero.shots.get(i);
//                    if( shot!=null &&  shot.isAlive){
//            g.draw3DRect( shot.x, shot.y,2,2,false);
//            System.out.println("子弹被绘制");
//       }else{
//                        //shot对象已经无效，就拿掉
//                        hero.shots.remove(shot);
//                    }
//        }
        //画出敌人坦克
        for(int i=0; i<enemyTanks.size();i++){

            EnemyTank enemyTank = enemyTanks.get(i);
            if(enemyTank.isAlive) {//只画活着的坦克
                drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(), 0);
                //画敌人所有子弹
                for (int j = 0; j < enemyTank.shots.size(); j++) {
                    //取出子弹
                    Shot shot = enemyTank.shots.get(j);
                    //绘制
                    if (shot.isAlive) {
                        g.draw3DRect(shot.x, shot.y, 1, 1, false);
                    } else {
                        //从Vector移除
                        enemyTank.shots.remove(shot);
                    }

                }
            }
        }
        //if bombs集合中有对象，就画出
        for(int i=0;i<bombs.size();i++){
            //取出炸弹
            Bomb bomb = bombs.get(i);
            if(bomb.life>6){
                g.drawImage(image1,bomb.x,bomb.y,60,60,this);
            }else if(bomb.life>3){
                g.drawImage(image2,bomb.x,bomb.y,60,60,this);
            }else {
                g.drawImage(image3,bomb.x,bomb.y,60,60,this);
            }
            //减少炸弹生命值
            bomb.lifeDown( );
            if(bomb.life==0){
                bombs.remove(bomb);
            }
        }




    }

    //编写方法画出坦克；

    /**
     *
     * @param x 坦克左上角x
     * @param y 坦克左上角y
     * @param g 画笔
     * @param direct 坦克方向
     * @param Type  坦克类型（敌人，我）
     */
    public void drawTank(int x,int y,Graphics g,int direct,int Type){
            switch (Type){
                case 0:
                    g.setColor(Color.cyan);
                    break;
                case 1:
                    g.setColor(Color.yellow);
                    break;
        }
        //根据坦克的方向，绘制对应形状的坦克。
        switch (direct){
            case 0://up
                g.fill3DRect(x, y,10,60,false); //左轮子
                g.fill3DRect(x+30, y,10,60,false);//右轮子
                g.fill3DRect(x+10,y+10,20,40,false);//盖子
                g.fillOval(x+10,y+20,20,20);//圆盖子
                g.drawLine(x+20,y+30,x+20,y);//炮筒
                break;
            case 1://right
                g.fill3DRect(x, y,60,10,false); //上轮子
                g.fill3DRect(x, y+30,60,10,false);//下轮子
                g.fill3DRect(x+10,y+10,40,20,false);//盖子
                g.fillOval(x+20,y+10,20,20);//圆盖子
                g.drawLine(x+30,y+20,x+60,y+20);//炮筒
                break;
            case 2: //下
                g.fill3DRect(x, y,10,60,false); //左轮子
                g.fill3DRect(x+30, y,10,60,false);//右轮子
                g.fill3DRect(x+10,y+10,20,40,false);//盖子
                g.fillOval(x+10,y+20,20,20);//圆盖子
                g.drawLine(x+20,y+30,x+20,y+60);//炮筒
                break;
            case 3://左
                g.fill3DRect(x, y,60,10,false); //上轮子
                g.fill3DRect(x, y+30,60,10,false);//下轮子
                g.fill3DRect(x+10,y+10,40,20,false);//盖子
                g.fillOval(x+20,y+10,20,20);//圆盖子
                g.drawLine(x+30,y+20,x,y+20);//炮筒
                break;
      }
    }
    //单科子弹打中
    public void hitEnemyTank(){
        if(hero.shot!=null&&hero.shot.isAlive){//我的子弹活着,但不知道击中哪一个
            for (int i = 0; i < enemyTanks.size(); i++) {
                EnemyTank enemyTank = enemyTanks.get(i);
                hitTank(hero.shot,enemyTank);
            }

        }
    }

    //如果我方可以发射多颗子弹
    //在判断我方坦克是否集中敌人时候，就需要把集合中所有子弹取出
    //所有的子弹都取出和敌人的坦克，进行判断
//    public void hitEnemyTank(){
//        for(int j=0;j<hero.shots.size();j++){
//            Shot shot = hero.shots.get(j);
//            //是否打中的判断
//            if( shot!=null&& shot.isAlive){//我的子弹活着,但不知道击中哪一个
//                for (int i = 0; i < enemyTanks.size(); i++) {
//                    EnemyTank enemyTank = enemyTanks.get(i);
//                    hitTank(hero.shot,enemyTank);
//                }
//
//            }
//        }

//
//    }

    //判断别人坦克是否打中我方坦克
    public void hitHero(){
        //
        for(int i=0;i< enemyTanks.size();i++){
            //得到坦克
            EnemyTank enemyTank = enemyTanks.get(i);
            for(int j=0;j<enemyTank.shots.size();j++){
                 //取出子弹
                Shot shot=enemyTank.shots.get(j);
                if(hero.isAlive && shot.isAlive){
                    hitTank(shot,hero);
                }

            }
        }
    }
    /**
     * 判断子弹击中
     * @param
     */
    public  void hitTank(Shot s, Tank enemyTank){
        switch (enemyTank.getDirect()){
            case 0://处理坦克上下方向
            case 2:
                if(s.x>enemyTank.getX() && s.x< enemyTank.getX()+40
                && s.y> enemyTank.getY() && s.y<enemyTank.getY()+60){
                    s.isAlive=false;
                    enemyTank.isAlive=false;
                    //我的子弹打中后，就把敌人坦克从Vector中去掉
                    enemyTanks.remove(enemyTank);
                    //创建Bomb对象，加入bombs集合
                    Bomb bomb=new Bomb(enemyTank.getX(), enemyTank.getY());
                    bombs.add(bomb);
                }
            case 1://处理坦克左右
            case 3:
                if(s.x>enemyTank.getX() && s.x< enemyTank.getX()+60
                        && s.y> enemyTank.getY() && s.y<enemyTank.getY()+40){
                    s.isAlive=false;
                    enemyTank.isAlive=false;
                    //我的子弹打中后，就把敌人坦克从集合人中去掉
                    enemyTanks.remove(enemyTank);
                    //创建Bomb对象，加入bombs集合
                    Bomb bomb=new Bomb(enemyTank.getX(), enemyTank.getY());
                    bombs.add(bomb);
                }


        }

    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    //处理wdsa按下的情况
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_W){
            //改变坦克的方向
            hero.setDirect(0);
            //修改坦克坐标
            if(hero.getY()>0) {
                hero.moveUp();
            }
        }
        else if(e.getKeyCode()==KeyEvent.VK_D){
            hero.setDirect(1);
            if(hero.getX()+60<1000) {
                hero.moveRight();
            }
        }
        else if(e.getKeyCode()==KeyEvent.VK_S){
            hero.setDirect(2);
            if(hero.getY()+60<750) {
                hero.moveDown();
            }
        }
        else if(e.getKeyCode()==KeyEvent.VK_A){
            hero.setDirect(3);
            if(hero.getX()>0) {
                hero.moveLeft();
            }
        }
        //如果按下的是J，射击
        if(e.getKeyCode()==KeyEvent.VK_J){
          //  发射一颗子弹的情况
            if(hero.shot==null/*第一次准备发子弹*/|| hero.shot.isAlive==false) {//判断hero的子弹是否被销毁
                hero.shotEnemy();
            }


            //发射多科子弹
            //hero.shotEnemy();
        }
        this.repaint();   //repaint会调用到paint方法.
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {//每隔100ms,重绘,刷新绘图区域，子弹就移动
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //是否打中敌人的判断
//            if(hero.shot!=null&&hero.shot.isAlive){//我的子弹活着,但不知道击中哪一个
//                for (int i = 0; i < enemyTanks.size(); i++) {
//                    EnemyTank enemyTank = enemyTanks.get(i);
//                    hitTank(hero.shot,enemyTank);
//                }
//
//            }
            hitEnemyTank();
            //敌人是否打中我们
            hitHero();
            this.repaint();
        }

    }
}
