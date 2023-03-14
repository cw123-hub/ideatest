package tankGame.tank_game4;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

public class CwTankGame04 extends JFrame { //画框

    MyPanel mp=null;
    public static void main(String[] args) {

        new CwTankGame04();
    }

    public CwTankGame04()  {
        System.out.println("请输入选择(1表示新游戏,2表示继续上一把):");
        String key =new Scanner(System.in).next();

        mp=new MyPanel(key);
        //启动
        new Thread(mp).start();
        this.add(mp);//把面板（游戏绘图区加入）
        this.addKeyListener(mp);//让JFrame监听mp的键盘事件
        this.setSize(1300,950);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        //JFrame中增加相应关闭窗口的处理
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("监听到关闭窗口");
                Recorder.keepRecord();
//                System.exit(0);
                super.windowClosing(e);
            }
        });

    }
}
