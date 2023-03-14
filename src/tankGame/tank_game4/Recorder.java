package tankGame.tank_game4;

import java.io.*;
import java.util.Vector;

/**
 * 记录信息，以及和文件进行交互
 */
public class Recorder {
    //打掉的坦克数目
     private static int allEnemyTanks = 0;
     //文件IO
    private static FileWriter fw= null;
    private static BufferedWriter bw = null;
    private static BufferedReader br=null;
    static String recordFile="C:\\Users\\33419\\Desktop\\ideatest\\src\\record.txt";
    // private static String recordFile="d:\\record.txt";
    //指向Mypanel对象的敌人坦克。
    public static Vector<EnemyTank>enemyTanks=null;
//定义一个Node的Vector,用于保存敌人的信息node
    private static Vector<Node> nodes=new Vector<>();

    public static void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        Recorder.enemyTanks = enemyTanks;
    }
    //读取文件，恢复相关信息
    //该方法在继续上一局的时候启动
    public   static Vector<Node> getNodesEnemyTankRec(){
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(recordFile),"gbk"));
            //读取敌人坦克数量
            allEnemyTanks=Integer.parseInt(br.readLine());
            //循环读取
            String line="";
            while((line=br.readLine())!=null){
                String[] xyd = line.split(" ");
                Node node = new Node(Integer.parseInt(xyd[0]),
                        Integer.parseInt(xyd[1]),
                        Integer.parseInt(xyd[2]));
                nodes.add(node);


            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if(br!=null){
                    br.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return nodes;

    }

    //游戏退出时存档
    public static void keepRecord(){
        try {
            //转换流解决乱码问题。。因为默认是utf-8，然而我的电脑是gbk
           bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(recordFile),"gbk"));
           bw.write(allEnemyTanks+"\r\n");
           //遍历敌人坦克的Vector，根据情况保存即可
for(int i=0;i<enemyTanks.size();i++){
    //quchu
    EnemyTank enemyTank = enemyTanks.get(i);
    if(enemyTank.isAlive){
        //保存ENenmytank信息
        String record=enemyTank.getX()+" "+enemyTank.getY()+" "+enemyTank.getDirect();
        //写入到文件
        bw.write(record+"\r\n");

    }
}

        } catch (IOException e) {
           e.printStackTrace();
        }finally {
            if(bw !=null){
                try {
                    bw.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }
    public static int getAllEnemyTanks() {
        return allEnemyTanks;
    }

    public static void setAllEnemyTanks(int allEnemyTanks) {
        Recorder.allEnemyTanks = allEnemyTanks;
    }

    //打掉坦克
    public  static  void  addAllEnemyTankNum(){
        allEnemyTanks++;
    }

    public static String getRecordFile() {
        return recordFile;
    }
}
