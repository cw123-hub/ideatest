package smallprogram.lingqiantong;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * �������ܶ�Ӧһ������,�Ѹ���������Ϊ������ԡ�
 */

public class smallChangeSystemOop {
    Scanner scanner =new Scanner(System.in);
    //private static final String KEY_ = "";
    String key="";
    boolean loop =true;
    double money=0;   //����
    double balance=0;//���
    String note="";
    Date date;
    String details="--------------��Ǯͨ��ϸ----------------------";
    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public void mainMenu() {
        do{

        System.out.println("��ʾ��Ǯͨ�˵�");
        System.out.println("===============��Ǯͨ�˵�=======");
        System.out.println("\t\t\t1 ��Ǯͨ��ϸ");
        System.out.println("\t\t\t2 ��������");
        System.out.println("\t\t\t3 ����");
        System.out.println("\t\t\t4 ��\t��");
        System.out.println("��ѡ��1-4");
        key = scanner.next();
        switch (key) {
            case "1":
                showDetails();
                break;

            case "2":
                income();
                break;
            case "3":
                expend();
                break;

            case "4":
                exit();
                break;
            default:
                System.out.println("�����д���");
                break;


        }
    }while (loop);
    }

    public void showDetails(){
        System.out.println(details);

    }

    public void income(){
        System.out.println("2 ��������");
        System.out.println("�������˽��");
        money=scanner.nextDouble();
        balance+=money;
        //����������Ϣ��details
        date=new Date();
        details+="\n��������\t"+'\t'+"+"+money+"\t"+simpleDateFormat.format(date)+"\t" +balance;

    }
    public void expend(){
        System.out.println("3 ����");
        System.out.println("���ѽ��:");
        money=scanner.nextDouble();
        System.out.println("����˵��:");
        note=scanner.next();
        balance-=money;
        date=new Date();
        //ƴ��������Ϣ��details
        details+="\n"+note+"\t-"+simpleDateFormat.format(date)+"\t"+money+"\t"+"\t"+balance;


    }
    public void exit(){
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
    }


}
