package smallprogram.aaa;

import javax.swing.*;
import java.awt.*;

public class drawCircle extends JFrame {//���Ϊ����
    private panel mp=null;
    public static void main(String[] args) {
        new drawCircle();
    }
    public drawCircle(){
        //��ʼ�����
        mp=new panel();
        //�����뵽����
        this.add(mp);
        //���ô�С
        this.setSize(400,300);
        this.setVisible(true);
    }
}
