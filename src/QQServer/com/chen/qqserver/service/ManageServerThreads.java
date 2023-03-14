package QQServer.com.chen.qqserver.service;

import java.util.HashMap;
import java.util.Iterator;

/**
 * ���ڹ����ڷ���˲��ҺͿͻ���ͨ�ŵ��߳�
 */
public class ManageServerThreads {
    private static HashMap<String , ServerConnectClientThread>hm=new HashMap<>();
    //����̶߳��󵽼���
    public static void addServerThreads(String userID, ServerConnectClientThread serverConnectClientThread){
        hm.put(userID, serverConnectClientThread);
    }
    //����userid��ȡ�߳�
    public static ServerConnectClientThread getServerConnectClientThread(String userId){
        return hm.get(userId);
    }

    //�Ӽ����������߳�
    public static void removeServerThread(String userId){
        hm.remove(userId);
    }
    //�û��б�����
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
