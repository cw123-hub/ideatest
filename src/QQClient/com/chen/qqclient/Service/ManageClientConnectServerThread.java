package QQClient.com.chen.qqclient.Service;

import java.util.HashMap;

/**
 * 管理客户端连接到服务器端的线程的类
 */
public class ManageClientConnectServerThread {
    //多个线程放入到HashMap集合,key是Id,value是线程
    public static HashMap<String,ClientConnectServerThread> hm=new HashMap<>();

    //将某个线程加入集合
    public static void addClientConnectServerThread(String userID,ClientConnectServerThread ccst){
        hm.put(userID,ccst);
    }
    //通过userId找到对应的线程
    public static ClientConnectServerThread getClientConnectServerThread(String userId){
         return hm.get(userId);
    }
}
