package tankGame.tank_game4;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

public class CwTankGame04 extends JFrame { //����

    MyPanel mp=null;
    public static void main(String[] args) {

        new CwTankGame04();
    }

    public CwTankGame04()  {
        System.out.println("������ѡ��(1��ʾ����Ϸ,2��ʾ������һ��):");
        String key =new Scanner(System.in).next();

        mp=new MyPanel(key);
        //����
        new Thread(mp).start();
        this.add(mp);//����壨��Ϸ��ͼ�����룩
        this.addKeyListener(mp);//��JFrame����mp�ļ����¼�
        this.setSize(1300,950);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        //JFrame��������Ӧ�رմ��ڵĴ���
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("�������رմ���");
                Recorder.keepRecord();
//                System.exit(0);
                super.windowClosing(e);
            }
        });

    }
}
