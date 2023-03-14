package smallprogram.aaa;

import javax.swing.*;
import java.awt.*;

public class drawCircle extends JFrame {//理解为画框
    private panel mp=null;
    public static void main(String[] args) {
        new drawCircle();
    }
    public drawCircle(){
        //初始化面板
        mp=new panel();
        //面板加入到画框
        this.add(mp);
        //设置大小
        this.setSize(400,300);
        this.setVisible(true);
    }
}
