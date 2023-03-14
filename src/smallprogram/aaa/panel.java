package smallprogram.aaa;

import javax.swing.*;
import java.awt.*;

class panel extends JPanel {
    //panel 是一个画板
    //Graphic 是一个画笔
    @Override
    public void paint(Graphics g) {
        super.paint(g);




        //画图片
        // g.setColor(Color.blue);g.drawLine(0,0,100,100);
         /*//获取图片资源,固定写法,/表示根目录，根目录表示项目目录（hhhh）
        Image image = Toolkit.getDefaultToolkit().getImage(panel.class.getResource("/bg.jpg"));
        g.drawImage(image,10,10,400,550,this);*/


        /*画自字符串*/
        g.setFont(new Font("隶书",Font.BOLD,50));
        //100，100是字符串s左下角
        g.drawString("Hello World",100,100);


        /**//**/



    }
}
