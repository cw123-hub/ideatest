package tankGame.hhh;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//KeyListener��������������
public class BallMove extends JFrame {
    MyPanel mp=null;

    public BallMove()  {
        mp=new MyPanel();
        this.add(mp);
        this.setSize(400,300);
        //����JFrame������Լ��������¼��������Լ�������巢���ļ����¼�
        this.addKeyListener(mp);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        BallMove ballMove=new BallMove();

    }
}
class MyPanel extends JPanel  implements KeyListener{
    //Ϊ����С���ܶ�,�������Ͻǵ���������Ϊ����
    private int x;private int y;
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillOval(x,y,20,20);
    }
    //���ַ����ʱ�򴥷�
    @Override
    public void keyTyped(KeyEvent e) {

    }
    //��һ��������
    @Override
    public void keyPressed(KeyEvent e) {
        //System.out.println((char)(e.getKeyCode())+ "������");
        //ÿһ����ֵ���������һ��int��ֵ
        if(e.getKeyCode()==KeyEvent.VK_DOWN){//xia jian
           y=y+100;
            System.out.println("��");
        }else if(e.getKeyCode()==KeyEvent.VK_UP){
           y-=100; System.out.println("up");
        }else if(e.getKeyCode()==KeyEvent.VK_LEFT){
            x-=100; System.out.println("left");
        }
        else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            x=x+100; System.out.println("right");
        }
        this.repaint();//����ػ�(����repaintʱ������paint)
    }
    //���ɿ�
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
