package smallprogram.lingqiantong;
import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.SimpleTimeZone;

public class smallChangeSystem {
    //
    public static void main(String[] args) {
        Scanner scanner =new Scanner(System.in);
        //private static final String KEY_ = "";
        String key="";
        boolean loop =true;
        double money=0;   //����
        double balance=0;//���
        String note="";
        Date date;
        String details="--------------��Ǯͨ��ϸ----------------------";
        do{
          System.out.println("===============��Ǯͨ�˵�=======");
            System.out.println("\t\t\t1 ��Ǯͨ��ϸ");
            System.out.println("\t\t\t2 ��������");
            System.out.println("\t\t\t3 ����");
            System.out.println("\t\t\t4 ��\t��");
            System.out.println("��ѡ��1-4");
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
            key = scanner.next();
            switch (key){
                case "1":
                    System.out.println(details);
                    System.out.println();
                    break;
                case "2":
                    System.out.println("2 ��������");
                    System.out.println("�������˽��");
                    money=scanner.nextDouble();
                    balance+=money;
                    //����������Ϣ��details
                    date=new Date();
                    details+="\n��������\t"+'\t'+"+"+money+"\t"+simpleDateFormat.format(date)+"\t" +balance;


                    break;
                case "3":
                    System.out.println("3 ����");
                    System.out.println("���ѽ��:");
                    money=scanner.nextDouble();
                    System.out.println("����˵��:");
                    note=scanner.next();
                    balance-=money;
                    date=new Date();
                    //ƴ��������Ϣ��details
                    details+="\n"+note+"\t-"+money+"\t"+simpleDateFormat.format(date)+"\t"+"\t"+balance;



                    break;
                case "4":
                    System.out.println("�˳�ϵͳ");
                    String choice;
                    while(true){
                        System.out.println("��Ҫ�˳���y/n");
                        choice=scanner.next();
                        if(choice.equals("y")||choice.equals("n")){
                            break;
                        }

                    }
                    if(choice.equals("y"))
                    loop=false;
                    break;
                default:
                    System.out.println("ѡ������");


            }
        }while(loop);
    }
}
