package tankGame.tank_game3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

/**
 * ̹�˴�ս��ͼ����
 */
//Ϊ����panel��ͣ�Ļ����ӵ�����Ҫmypanel��Ϊһ���̣߳�Ҳ����ʵ��runnable�ӿ�
public class MyPanel extends JPanel implements KeyListener , Runnable{
    //���屾��̹��
    Hero hero = null;
    //�������̹�˷���Vector�У�Vector���̰߳�ȫ�ġ�
    Vector<EnemyTank> enemyTanks=new Vector<>();
    int enemySize = 3;
    //����һ��Vector,���ڴ��ը��
    //�ӵ�����̹�ˣ��ͼ������bombs;
    Vector<Bomb> bombs=new Vector<Bomb>();
    //����3��ը��ͼƬ��������ʾ��ըЧ��
    Image image1=null,image2=null,image3=null;
    public MyPanel() {
        hero = new Hero(100, 100);//��ʼ���ҵ�̹��
        hero.setSpeed(10);//��ʼ���ҵ��ٶ�
        for(int i=0;i < enemySize;i++){//��ʼ������̹��
            EnemyTank enemyTank = new EnemyTank(100 * (i + 1), 0);
            enemyTank.setDirect(2);
            //��������̹���߳�
            new Thread(enemyTank).start();

            Shot shot=new Shot(enemyTank.getX()+20,enemyTank.getY()+60,enemyTank.getDirect());
            enemyTank.shots.add(shot);
            new Thread(shot).start();
            enemyTanks.add(enemyTank);
        }
        //��ʼ��ͼƬ����
        image1=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_1.gif"));
        image2=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_2.gif"));
        image3=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_3.gif"));

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.gray);

        g.fillRect(0, 0, 1000, 750);//������,Ĭ�Ϻ�ɫ
        // ���Լ�̹��
        if(hero.isAlive && hero!=null) {
            drawTank(hero.getX(), hero.getY(), g, hero.getDirect(), 1);
        }


        //��hero���ӵ�
        if(hero.shot!=null && hero.shot.isAlive){
            g.draw3DRect(hero.shot.x,hero.shot.y,2,2,false);
            System.out.println("�ӵ�������");
        }
        //��hero���ӵ�����ȡ�������ơ�
//        for(int i=0;i<hero.shots.size();i++){
//            Shot shot = hero.shots.get(i);
//                    if( shot!=null &&  shot.isAlive){
//            g.draw3DRect( shot.x, shot.y,2,2,false);
//            System.out.println("�ӵ�������");
//       }else{
//                        //shot�����Ѿ���Ч�����õ�
//                        hero.shots.remove(shot);
//                    }
//        }
        //��������̹��
        for(int i=0; i<enemyTanks.size();i++){

            EnemyTank enemyTank = enemyTanks.get(i);
            if(enemyTank.isAlive) {//ֻ�����ŵ�̹��
                drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(), 0);
                //�����������ӵ�
                for (int j = 0; j < enemyTank.shots.size(); j++) {
                    //ȡ���ӵ�
                    Shot shot = enemyTank.shots.get(j);
                    //����
                    if (shot.isAlive) {
                        g.draw3DRect(shot.x, shot.y, 1, 1, false);
                    } else {
                        //��Vector�Ƴ�
                        enemyTank.shots.remove(shot);
                    }

                }
            }
        }
        //if bombs�������ж��󣬾ͻ���
        for(int i=0;i<bombs.size();i++){
            //ȡ��ը��
            Bomb bomb = bombs.get(i);
            if(bomb.life>6){
                g.drawImage(image1,bomb.x,bomb.y,60,60,this);
            }else if(bomb.life>3){
                g.drawImage(image2,bomb.x,bomb.y,60,60,this);
            }else {
                g.drawImage(image3,bomb.x,bomb.y,60,60,this);
            }
            //����ը������ֵ
            bomb.lifeDown( );
            if(bomb.life==0){
                bombs.remove(bomb);
            }
        }




    }

    //��д��������̹�ˣ�

    /**
     *
     * @param x ̹�����Ͻ�x
     * @param y ̹�����Ͻ�y
     * @param g ����
     * @param direct ̹�˷���
     * @param Type  ̹�����ͣ����ˣ��ң�
     */
    public void drawTank(int x,int y,Graphics g,int direct,int Type){
            switch (Type){
                case 0:
                    g.setColor(Color.cyan);
                    break;
                case 1:
                    g.setColor(Color.yellow);
                    break;
        }
        //����̹�˵ķ��򣬻��ƶ�Ӧ��״��̹�ˡ�
        switch (direct){
            case 0://up
                g.fill3DRect(x, y,10,60,false); //������
                g.fill3DRect(x+30, y,10,60,false);//������
                g.fill3DRect(x+10,y+10,20,40,false);//����
                g.fillOval(x+10,y+20,20,20);//Բ����
                g.drawLine(x+20,y+30,x+20,y);//��Ͳ
                break;
            case 1://right
                g.fill3DRect(x, y,60,10,false); //������
                g.fill3DRect(x, y+30,60,10,false);//������
                g.fill3DRect(x+10,y+10,40,20,false);//����
                g.fillOval(x+20,y+10,20,20);//Բ����
                g.drawLine(x+30,y+20,x+60,y+20);//��Ͳ
                break;
            case 2: //��
                g.fill3DRect(x, y,10,60,false); //������
                g.fill3DRect(x+30, y,10,60,false);//������
                g.fill3DRect(x+10,y+10,20,40,false);//����
                g.fillOval(x+10,y+20,20,20);//Բ����
                g.drawLine(x+20,y+30,x+20,y+60);//��Ͳ
                break;
            case 3://��
                g.fill3DRect(x, y,60,10,false); //������
                g.fill3DRect(x, y+30,60,10,false);//������
                g.fill3DRect(x+10,y+10,40,20,false);//����
                g.fillOval(x+20,y+10,20,20);//Բ����
                g.drawLine(x+30,y+20,x,y+20);//��Ͳ
                break;
      }
    }
    //�����ӵ�����
    public void hitEnemyTank(){
        if(hero.shot!=null&&hero.shot.isAlive){//�ҵ��ӵ�����,����֪��������һ��
            for (int i = 0; i < enemyTanks.size(); i++) {
                EnemyTank enemyTank = enemyTanks.get(i);
                hitTank(hero.shot,enemyTank);
            }

        }
    }

    //����ҷ����Է������ӵ�
    //���ж��ҷ�̹���Ƿ��е���ʱ�򣬾���Ҫ�Ѽ����������ӵ�ȡ��
    //���е��ӵ���ȡ���͵��˵�̹�ˣ������ж�
//    public void hitEnemyTank(){
//        for(int j=0;j<hero.shots.size();j++){
//            Shot shot = hero.shots.get(j);
//            //�Ƿ���е��ж�
//            if( shot!=null&& shot.isAlive){//�ҵ��ӵ�����,����֪��������һ��
//                for (int i = 0; i < enemyTanks.size(); i++) {
//                    EnemyTank enemyTank = enemyTanks.get(i);
//                    hitTank(hero.shot,enemyTank);
//                }
//
//            }
//        }

//
//    }

    //�жϱ���̹���Ƿ�����ҷ�̹��
    public void hitHero(){
        //
        for(int i=0;i< enemyTanks.size();i++){
            //�õ�̹��
            EnemyTank enemyTank = enemyTanks.get(i);
            for(int j=0;j<enemyTank.shots.size();j++){
                 //ȡ���ӵ�
                Shot shot=enemyTank.shots.get(j);
                if(hero.isAlive && shot.isAlive){
                    hitTank(shot,hero);
                }

            }
        }
    }
    /**
     * �ж��ӵ�����
     * @param
     */
    public  void hitTank(Shot s, Tank enemyTank){
        switch (enemyTank.getDirect()){
            case 0://����̹�����·���
            case 2:
                if(s.x>enemyTank.getX() && s.x< enemyTank.getX()+40
                && s.y> enemyTank.getY() && s.y<enemyTank.getY()+60){
                    s.isAlive=false;
                    enemyTank.isAlive=false;
                    //�ҵ��ӵ����к󣬾Ͱѵ���̹�˴�Vector��ȥ��
                    enemyTanks.remove(enemyTank);
                    //����Bomb���󣬼���bombs����
                    Bomb bomb=new Bomb(enemyTank.getX(), enemyTank.getY());
                    bombs.add(bomb);
                }
            case 1://����̹������
            case 3:
                if(s.x>enemyTank.getX() && s.x< enemyTank.getX()+60
                        && s.y> enemyTank.getY() && s.y<enemyTank.getY()+40){
                    s.isAlive=false;
                    enemyTank.isAlive=false;
                    //�ҵ��ӵ����к󣬾Ͱѵ���̹�˴Ӽ�������ȥ��
                    enemyTanks.remove(enemyTank);
                    //����Bomb���󣬼���bombs����
                    Bomb bomb=new Bomb(enemyTank.getX(), enemyTank.getY());
                    bombs.add(bomb);
                }


        }

    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    //����wdsa���µ����
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_W){
            //�ı�̹�˵ķ���
            hero.setDirect(0);
            //�޸�̹������
            if(hero.getY()>0) {
                hero.moveUp();
            }
        }
        else if(e.getKeyCode()==KeyEvent.VK_D){
            hero.setDirect(1);
            if(hero.getX()+60<1000) {
                hero.moveRight();
            }
        }
        else if(e.getKeyCode()==KeyEvent.VK_S){
            hero.setDirect(2);
            if(hero.getY()+60<750) {
                hero.moveDown();
            }
        }
        else if(e.getKeyCode()==KeyEvent.VK_A){
            hero.setDirect(3);
            if(hero.getX()>0) {
                hero.moveLeft();
            }
        }
        //������µ���J�����
        if(e.getKeyCode()==KeyEvent.VK_J){
          //  ����һ���ӵ������
            if(hero.shot==null/*��һ��׼�����ӵ�*/|| hero.shot.isAlive==false) {//�ж�hero���ӵ��Ƿ�����
                hero.shotEnemy();
            }


            //�������ӵ�
            //hero.shotEnemy();
        }
        this.repaint();   //repaint����õ�paint����.
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {//ÿ��100ms,�ػ�,ˢ�»�ͼ�����ӵ����ƶ�
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //�Ƿ���е��˵��ж�
//            if(hero.shot!=null&&hero.shot.isAlive){//�ҵ��ӵ�����,����֪��������һ��
//                for (int i = 0; i < enemyTanks.size(); i++) {
//                    EnemyTank enemyTank = enemyTanks.get(i);
//                    hitTank(hero.shot,enemyTank);
//                }
//
//            }
            hitEnemyTank();
            //�����Ƿ��������
            hitHero();
            this.repaint();
        }

    }
}
