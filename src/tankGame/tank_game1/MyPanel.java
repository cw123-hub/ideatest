package tankGame.tank_game1;

import javax.swing.*;
import java.awt.*;

/**
 * 坦克大战绘图区域
 */
public class MyPanel extends JPanel {
    //
    Hero hero = null;
    public MyPanel() {
        hero = new Hero(100, 100);//初始化我的坦克
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.gray);
        g.fillRect(0, 0, 1000, 750);//填充矩形,默认黑色
        drawTank(hero.getX(), hero.getY(),g,0,1);
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

        switch (direct){
            case 0://up
                g.fill3DRect(x, y,10,60,false); //左轮子
                g.fill3DRect(x+30, y,10,60,false);//右轮子
                g.fill3DRect(x+10,y+10,20,40,false);//盖子
                g.fillOval(x+10,y+20,20,20);//圆盖子
                g.drawLine(x+20,y+30,x+20,y);//炮筒
                break;
        }
    }

}
