package smallprogram.aaa;

import javax.swing.*;
import java.awt.*;

class panel extends JPanel {
    //panel ��һ������
    //Graphic ��һ������
    @Override
    public void paint(Graphics g) {
        super.paint(g);




        //��ͼƬ
        // g.setColor(Color.blue);g.drawLine(0,0,100,100);
         /*//��ȡͼƬ��Դ,�̶�д��,/��ʾ��Ŀ¼����Ŀ¼��ʾ��ĿĿ¼��hhhh��
        Image image = Toolkit.getDefaultToolkit().getImage(panel.class.getResource("/bg.jpg"));
        g.drawImage(image,10,10,400,550,this);*/


        /*�����ַ���*/
        g.setFont(new Font("����",Font.BOLD,50));
        //100��100���ַ���s���½�
        g.drawString("Hello World",100,100);


        /**//**/



    }
}
