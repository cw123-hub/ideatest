package smallprogram;
public class Hello1 {
    public static void main(String[] args) {
        int [][]map = new int [8][7];
        for(int i=0;i<7;i++){
            map[0][i]=1;
            map[7][i]=1;
        }
        for(int i=0;i<8;i++){
            map[i][0]=1;
            map[i][6]=1;
        }
        map[3][1] = 1; map[3][2] = 1;
        System.out.println("=====此时的二维数组=====");
        new mapway().printTwoDimension(map);
        new mapway().mapway(map,1,1);   //find way
        new mapway().printTwoDimension(map);   //打印找操作后的地图

    }

}


class mapway {
    public void printTwoDimension(int[][] a) { //打印二维数组
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }

    }

    //标记  0没有被找过   1，墙壁    2.遍历过，可以走    3.死路
    public boolean mapway(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            System.out.println("走通了！路线如下");   //已经找到
            return true;
        } else {
            if (map[i][j] == 0) {
                map[i][j] = 2;
                if (mapway(map, i - 1, j)) {   //按照上右下左的方式找路
                    return true;
                } else if (mapway(map, i, j + 1)) {
                    return true;
                } else if (mapway(map, i + 1, j)) {
                    return true;
                } else if (mapway(map, i, j - 1)) {
                    return true;
                } else {
                    map[i][j] = 3;
                    return false;
                }
            } else {
                return false;
            }

        }


    }
}
