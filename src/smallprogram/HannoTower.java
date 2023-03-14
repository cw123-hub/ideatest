package smallprogram;

public class HannoTower {
    public static void main(String[] args) {
        new Tower().move(5,'A','B','C');


    }
}


class Tower{   //a是需要被移动，b是辅助，c是需要被移动到的塔
    public void move(int num ,char a,char b,char c){
         if(num==1){
             System.out.println(a+"->"+c);
         }
         else{
               move(num-1,a,c,b);
               move(1,a,b,c);
               move(num-1,b,a,c);
         }
    }
}
