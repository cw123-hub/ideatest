package tankGame.hhh;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//KeyListener监听器监听键盘
public class BallMove extends JFrame {
    MyPanel mp=null;

    public BallMove()  {
        mp=new MyPanel();
        this.add(mp);
        this.setSize(400,300);
        //窗口JFrame对象可以监听键盘事件，即可以监听到面板发生的键盘事件
        this.addKeyListener(mp);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        BallMove ballMove=new BallMove();

    }
}
class MyPanel extends JPanel  implements KeyListener{
    //为了让小球能动,把它左上角的坐标设置为变量
    private int x;private int y;
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillOval(x,y,20,20);
    }
    //有字符输出时候触发
    @Override
    public void keyTyped(KeyEvent e) {

    }
    //有一个键按下
    @Override
    public void keyPressed(KeyEvent e) {
        //System.out.println((char)(e.getKeyCode())+ "被按下");
        //每一个键值，都会分配一个int的值
        if(e.getKeyCode()==KeyEvent.VK_DOWN){//xia jian
           y=y+100;
            System.out.println("下");
        }else if(e.getKeyCode()==KeyEvent.VK_UP){
           y-=100; System.out.println("up");
        }else if(e.getKeyCode()==KeyEvent.VK_LEFT){
            x-=100; System.out.println("left");
        }
        else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            x=x+100; System.out.println("right");
        }
        this.repaint();//面板重绘(调用repaint时候会调用paint)
    }
    //键松开
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
