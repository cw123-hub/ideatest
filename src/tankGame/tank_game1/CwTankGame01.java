package tankGame.tank_game1;

import javax.swing.*;
import java.awt.*;

public class CwTankGame01 extends JFrame { //����

    MyPanel  mp=null;
    public static void main(String[] args) {
        new CwTankGame01();
    }

    public CwTankGame01()  {
        MyPanel mp;
        mp=new MyPanel();
        this.add(mp);//����壨��Ϸ��ͼ�����룩
        this.setSize(1000,750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
