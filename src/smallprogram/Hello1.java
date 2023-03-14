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
        System.out.println("=====��ʱ�Ķ�ά����=====");
        new mapway().printTwoDimension(map);
        new mapway().mapway(map,1,1);   //find way
        new mapway().printTwoDimension(map);   //��ӡ�Ҳ�����ĵ�ͼ

    }

}


class mapway {
    public void printTwoDimension(int[][] a) { //��ӡ��ά����
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }

    }

    //���  0û�б��ҹ�   1��ǽ��    2.��������������    3.��·
    public boolean mapway(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            System.out.println("��ͨ�ˣ�·������");   //�Ѿ��ҵ�
            return true;
        } else {
            if (map[i][j] == 0) {
                map[i][j] = 2;
                if (mapway(map, i - 1, j)) {   //������������ķ�ʽ��·
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
