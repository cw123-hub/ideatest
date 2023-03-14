package QQServer.com.chen.qqserver.service;

import java.util.HashMap;
import java.util.Iterator;

/**
 * 用于管理在服务端并且和客户端通信的线程
 */
public class ManageServerThreads {
    private static HashMap<String , ServerConnectClientThread>hm=new HashMap<>();
    //添加线程对象到集合
    public static void addServerThreads(String userID, ServerConnectClientThread serverConnectClientThread){
        hm.put(userID, serverConnectClientThread);
    }
    //根据userid获取线程
    public static ServerConnectClientThread getServerConnectClientThread(String userId){
        return hm.get(userId);
    }

    //从集合中移走线程
    public static void removeServerThread(String userId){
        hm.remove(userId);
    }
    //用户列表在线
    public static  String getOnlineUser(){
        String onlineList="";
        Iterator<String> iterator = hm.keySet().iterator();
        while (iterator.hasNext()) {
            onlineList+=  iterator.next().toString()+" ";

            
        }
        return onlineList;

    }
    //
    public static HashMap<String, ServerConnectClientThread> getHm() {
        return hm;
    }
}
