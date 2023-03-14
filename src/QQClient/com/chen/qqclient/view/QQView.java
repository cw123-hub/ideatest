package QQClient.com.chen.qqclient.view;

import QQClient.com.chen.qqclient.Service.FileClientService;
import QQClient.com.chen.qqclient.Service.MessageClientService;
import QQClient.com.chen.qqclient.utils.Utility;
import QQClient.com.chen.qqclient.Service.UserClientService;
import qqcommon.Message;

import java.io.IOException;

public class QQView {
    private boolean loop=true;//�����Ƿ���ʾ�˵�
    private String key="";//���ܼ�������
    private UserClientService userClientService = new UserClientService();//���ڵ�¼������
    private MessageClientService messageClientService=new MessageClientService();//��Ϣ����
    private FileClientService fileClientService=new FileClientService();//�ļ�����
    public static void main(String[] args) {
        new QQView().mainView();
        System.out.println("�ͻ����˳�ϵͳ~");
    }
    //��ʾ���˵�
    public void mainView(){
        while(loop){
            System.out.println("=========��ӭ��¼qqϵͳ:============");
            System.out.println("\t\t 1 ��¼ϵͳ:");
            System.out.println("\t\t 9 �˳�ϵͳ:");
            System.out.println("Please input your selection:");

            key= Utility.readString(1);
            switch (key){
                case "1":
                    System.out.println("Please input your ID:");
                   String userID= Utility.readString(50);
                    System.out.println("Please input your password:");
                    String pwd=Utility.readString(50);

                    //2���˵�
                    if(userClientService.checkUser(userID,pwd)){
                        System.out.println("====��ӭ(�û�"+userID+"��¼�ɹ�)======");
                        //
                        while (loop){
                            System.out.println("====����ͨ��ϵͳ�����˵�(�û�"+userID+")====");
                            System.out.println("\t\t 1 ��ʾ�����û��б�");
                            System.out.println("\t\t 2 Ⱥ����Ϣ");
                            System.out.println("\t\t 3 ˽����Ϣ");
                            System.out.println("\t\t 4 �����ļ�");
                            System.out.println("\t\t 9 �˳�ϵͳ");
                            System.out.println("���������ѡ��:");
                            key=Utility.readString(1);
                            switch (key){
                                case "1":
                                    userClientService.onlineFriendList();
                                    break;
                                case "2":
                                    System.out.println("������Դ��˵�Ļ�");
                                    String content1 = Utility.readString(100);
                                    //
                                    messageClientService.sendMessageToOAll(userID,content1);
                                    break;
                                case "3":
                                    System.out.println("��������������û���(����):");
                                    String receiverId=Utility.readString(50);
                                    System.out.println("��������˵�Ļ�:");
                                    String content=Utility.readString(50);
                                    messageClientService.sendMessageToOne(content,userID,receiverId);

                                    break;
                                case "4":
                                    System.out.println("�������뷢������:");
                                    String toWho=Utility.readString(50);
                                    System.out.println("���뷢�͵��ļ���·��(d:\\qqq.jpg):");
                                    String src=Utility.readString(100);
                                    System.out.println("��������ļ����͵���·��(d:\\qqq.jpg):");
                                    String dest=Utility.readString(100);
                                    fileClientService.sendToOne(src,dest,userID,toWho);
                                    break;
                                case "9":
                                    loop=false;
                                    try {
                                        userClientService.logOut();
                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }
                                    System.out.println("�˳�ϵͳ");
                                    break;

                            }

                        }

                    }else {
                        System.out.println("�û�ID="+userID+",����="+pwd);
                        System.out.println("��½ʧ�ܣ�������");
                    }
                    break;
                case "9":
                    loop=false;
                    System.out.println("�˳�ϵͳ������");
                    break;
            }


        }
    }
}
