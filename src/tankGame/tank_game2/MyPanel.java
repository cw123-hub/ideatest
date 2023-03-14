package tankGame.tank_game2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

/**
 * 坦克大战绘图区域
 */

public class MyPanel extends JPanel implements KeyListener {
    //定义本人坦克
    Hero hero = null;
    //定义敌人坦克放入Vector中，Vector是线程安全的。
    Vector<EnemyTank> enemyTanks=new Vector<>();
    int enemySize = 3;
    public MyPanel() {
        hero = new Hero(100, 100);//初始化我的坦克
        hero.setSpeed(10);//初始化我的速度
        for(int i=0;i < enemySize;i++){//初始化敌人坦克
            EnemyTank enemyTank = new EnemyTank(100 * (i + 1), 0);
            enemyTank.setDirect(2);
            enemyTanks.add(enemyTank);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.gray);
        //画自己坦克
        g.fillRect(0, 0, 1000, 750);//填充矩形,默认黑色
        drawTank(hero.getX(), hero.getY(),g, hero.getDirect(), 1);
        //画出敌人坦克
        for(int i=0; i<enemyTanks.size();i++){
            EnemyTank enemyTank = enemyTanks.get(i);
            drawTank(enemyTank.getX(),enemyTank.getY(),g,enemyTank.getDirect(),0);
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
            hero.moveUp();
        }
        else if(e.getKeyCode()==KeyEvent.VK_D){
            hero.setDirect(1);
            hero.moveRight();
        }
        else if(e.getKeyCode()==KeyEvent.VK_S){
            hero.setDirect(2);
            hero.moveDown();
        }
        else if(e.getKeyCode()==KeyEvent.VK_A){
            hero.setDirect(3);
            hero.moveLeft();
        }
        this.repaint();   //repaint会调用到paint方法.
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
