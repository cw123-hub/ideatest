package smallprogram.lingqiantong;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * 各个功能对应一个方法,把各个属性作为类的属性。
 */

public class smallChangeSystemOop {
    Scanner scanner =new Scanner(System.in);
    //private static final String KEY_ = "";
    String key="";
    boolean loop =true;
    double money=0;   //收入
    double balance=0;//余额
    String note="";
    Date date;
    String details="--------------零钱通明细----------------------";
    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public void mainMenu() {
        do{

        System.out.println("显示零钱通菜单");
        System.out.println("===============零钱通菜单=======");
        System.out.println("\t\t\t1 零钱通明细");
        System.out.println("\t\t\t2 收益入账");
        System.out.println("\t\t\t3 消费");
        System.out.println("\t\t\t4 退\t出");
        System.out.println("请选择1-4");
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
                System.out.println("输入有错误");
                break;


        }
    }while (loop);
    }

    public void showDetails(){
        System.out.println(details);

    }

    public void income(){
        System.out.println("2 收益入账");
        System.out.println("收益入账金额");
        money=scanner.nextDouble();
        balance+=money;
        //接收入账信息到details
        date=new Date();
        details+="\n收益入账\t"+'\t'+"+"+money+"\t"+simpleDateFormat.format(date)+"\t" +balance;

    }
    public void expend(){
        System.out.println("3 消费");
        System.out.println("消费金额:");
        money=scanner.nextDouble();
        System.out.println("消费说明:");
        note=scanner.next();
        balance-=money;
        date=new Date();
        //拼接消费信息到details
        details+="\n"+note+"\t-"+simpleDateFormat.format(date)+"\t"+money+"\t"+"\t"+balance;


    }
    public void exit(){
        System.out.println("退出系统");
        String choice;
        while(true){
            System.out.println("你要退出吗？y/n");
            choice=scanner.next();
            if(choice.equals("y")||choice.equals("n")){
                break;
            }

        }
        if(choice.equals("y"))
            loop=false;
    }


}
