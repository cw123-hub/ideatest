package tankGame.tank_game1;

import javax.swing.*;
import java.awt.*;

/**
 * ̹�˴�ս��ͼ����
 */
public class MyPanel extends JPanel {
    //
    Hero hero = null;
    public MyPanel() {
        hero = new Hero(100, 100);//��ʼ���ҵ�̹��
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.gray);
        g.fillRect(0, 0, 1000, 750);//������,Ĭ�Ϻ�ɫ
        drawTank(hero.getX(), hero.getY(),g,0,1);
    }

    //��д��������̹�ˣ�

    /**
     *
     * @param x ̹�����Ͻ�x
     * @param y ̹�����Ͻ�y
     * @param g ����
     * @param direct ̹�˷���
     * @param Type  ̹�����ͣ����ˣ��ң�
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
                g.fill3DRect(x, y,10,60,false); //������
                g.fill3DRect(x+30, y,10,60,false);//������
                g.fill3DRect(x+10,y+10,20,40,false);//����
                g.fillOval(x+10,y+20,20,20);//Բ����
                g.drawLine(x+20,y+30,x+20,y);//��Ͳ
                break;
        }
    }

}
