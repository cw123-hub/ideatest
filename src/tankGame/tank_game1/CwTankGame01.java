package tankGame.tank_game1;

import javax.swing.*;
import java.awt.*;

public class CwTankGame01 extends JFrame { //画框

    MyPanel  mp=null;
    public static void main(String[] args) {
        new CwTankGame01();
    }

    public CwTankGame01()  {
        MyPanel mp;
        mp=new MyPanel();
        this.add(mp);//把面板（游戏绘图区加入）
        this.setSize(1000,750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
