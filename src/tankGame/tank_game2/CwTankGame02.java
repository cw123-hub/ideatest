package tankGame.tank_game2;

import javax.swing.*;

public class CwTankGame02 extends JFrame { //����

    MyPanel mp=null;
    public static void main(String[] args) {
        new CwTankGame02();
    }

    public CwTankGame02()  {
        MyPanel mp;
        mp=new MyPanel();
        this.add(mp);//����壨��Ϸ��ͼ�����룩
        this.addKeyListener(mp);//��JFrame����mp�ļ����¼�
        this.setSize(1000,750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
