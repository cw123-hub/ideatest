package QQClient.com.chen.qqclient.view;

import QQClient.com.chen.qqclient.Service.FileClientService;
import QQClient.com.chen.qqclient.Service.MessageClientService;
import QQClient.com.chen.qqclient.utils.Utility;
import QQClient.com.chen.qqclient.Service.UserClientService;
import qqcommon.Message;

import java.io.IOException;

public class QQView {
    private boolean loop=true;//控制是否显示菜单
    private String key="";//接受键盘输入
    private UserClientService userClientService = new UserClientService();//用于登录服务器
    private MessageClientService messageClientService=new MessageClientService();//消息发送
    private FileClientService fileClientService=new FileClientService();//文件传输
    public static void main(String[] args) {
        new QQView().mainView();
        System.out.println("客户端退出系统~");
    }
    //显示主菜单
    public void mainView(){
        while(loop){
            System.out.println("=========欢迎登录qq系统:============");
            System.out.println("\t\t 1 登录系统:");
            System.out.println("\t\t 9 退出系统:");
            System.out.println("Please input your selection:");

            key= Utility.readString(1);
            switch (key){
                case "1":
                    System.out.println("Please input your ID:");
                   String userID= Utility.readString(50);
                    System.out.println("Please input your password:");
                    String pwd=Utility.readString(50);

                    //2级菜单
                    if(userClientService.checkUser(userID,pwd)){
                        System.out.println("====欢迎(用户"+userID+"登录成功)======");
                        //
                        while (loop){
                            System.out.println("====网络通信系统二级菜单(用户"+userID+")====");
                            System.out.println("\t\t 1 显示在线用户列表");
                            System.out.println("\t\t 2 群发消息");
                            System.out.println("\t\t 3 私聊消息");
                            System.out.println("\t\t 4 发送文件");
                            System.out.println("\t\t 9 退出系统");
                            System.out.println("请输入你的选择:");
                            key=Utility.readString(1);
                            switch (key){
                                case "1":
                                    userClientService.onlineFriendList();
                                    break;
                                case "2":
                                    System.out.println("输入想对大家说的话");
                                    String content1 = Utility.readString(100);
                                    //
                                    messageClientService.sendMessageToOAll(userID,content1);
                                    break;
                                case "3":
                                    System.out.println("请输入想聊天的用户号(在线):");
                                    String receiverId=Utility.readString(50);
                                    System.out.println("请输入想说的话:");
                                    String content=Utility.readString(50);
                                    messageClientService.sendMessageToOne(content,userID,receiverId);

                                    break;
                                case "4":
                                    System.out.println("输入你想发给的人:");
                                    String toWho=Utility.readString(50);
                                    System.out.println("输入发送的文件的路径(d:\\qqq.jpg):");
                                    String src=Utility.readString(100);
                                    System.out.println("请输入把文件发送到的路径(d:\\qqq.jpg):");
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
                                    System.out.println("退出系统");
                                    break;

                            }

                        }

                    }else {
                        System.out.println("用户ID="+userID+",密码="+pwd);
                        System.out.println("登陆失败！！！！");
                    }
                    break;
                case "9":
                    loop=false;
                    System.out.println("退出系统！！！");
                    break;
            }


        }
    }
}
