package tankGame.tank_game4;

import java.io.*;
import java.util.Vector;

/**
 * ��¼��Ϣ���Լ����ļ����н���
 */
public class Recorder {
    //�����̹����Ŀ
     private static int allEnemyTanks = 0;
     //�ļ�IO
    private static FileWriter fw= null;
    private static BufferedWriter bw = null;
    private static BufferedReader br=null;
    static String recordFile="C:\\Users\\33419\\Desktop\\ideatest\\src\\record.txt";
    // private static String recordFile="d:\\record.txt";
    //ָ��Mypanel����ĵ���̹�ˡ�
    public static Vector<EnemyTank>enemyTanks=null;
//����һ��Node��Vector,���ڱ�����˵���Ϣnode
    private static Vector<Node> nodes=new Vector<>();

    public static void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        Recorder.enemyTanks = enemyTanks;
    }
    //��ȡ�ļ����ָ������Ϣ
    //�÷����ڼ�����һ�ֵ�ʱ������
    public   static Vector<Node> getNodesEnemyTankRec(){
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(recordFile),"gbk"));
            //��ȡ����̹������
            allEnemyTanks=Integer.parseInt(br.readLine());
            //ѭ����ȡ
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

    //��Ϸ�˳�ʱ�浵
    public static void keepRecord(){
        try {
            //ת��������������⡣����ΪĬ����utf-8��Ȼ���ҵĵ�����gbk
           bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(recordFile),"gbk"));
           bw.write(allEnemyTanks+"\r\n");
           //��������̹�˵�Vector������������漴��
for(int i=0;i<enemyTanks.size();i++){
    //quchu
    EnemyTank enemyTank = enemyTanks.get(i);
    if(enemyTank.isAlive){
        //����ENenmytank��Ϣ
        String record=enemyTank.getX()+" "+enemyTank.getY()+" "+enemyTank.getDirect();
        //д�뵽�ļ�
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

    //���̹��
    public  static  void  addAllEnemyTankNum(){
        allEnemyTanks++;
    }

    public static String getRecordFile() {
        return recordFile;
    }
}
