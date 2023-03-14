package tankGame.tank_game3;

/**
 * zha dan
 */
public class Bomb {
    int x;
    int y;
    int life=20;//炸弹的生命周期
    boolean isAlive=true; //炸弹存在

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
    }
    //减少生命值

    public void lifeDown() {
        if(life>0){
            life--;
        }else{
            isAlive=false;
        }
    }
}
