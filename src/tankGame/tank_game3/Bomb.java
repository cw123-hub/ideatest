package tankGame.tank_game3;

/**
 * zha dan
 */
public class Bomb {
    int x;
    int y;
    int life=20;//ը������������
    boolean isAlive=true; //ը������

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
    }
    //��������ֵ

    public void lifeDown() {
        if(life>0){
            life--;
        }else{
            isAlive=false;
        }
    }
}
