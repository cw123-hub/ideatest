package tankGame.tank_game3;

import javax.swing.*;

public class CwTankGame02 extends JFrame { //画框

    MyPanel mp=null;
    public static void main(String[] args) {
        new CwTankGame02();
    }

    public CwTankGame02()  {
        MyPanel mp;
        mp=new MyPanel();
        //启动
        new Thread(mp).start();
        this.add(mp);//把面板（游戏绘图区加入）
        this.addKeyListener(mp);//让JFrame监听mp的键盘事件
        this.setSize(1200,950);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
