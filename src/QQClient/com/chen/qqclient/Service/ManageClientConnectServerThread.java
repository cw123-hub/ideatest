package QQClient.com.chen.qqclient.Service;

import java.util.HashMap;

/**
 * ����ͻ������ӵ��������˵��̵߳���
 */
public class ManageClientConnectServerThread {
    //����̷߳��뵽HashMap����,key��Id,value���߳�
    public static HashMap<String,ClientConnectServerThread> hm=new HashMap<>();

    //��ĳ���̼߳��뼯��
    public static void addClientConnectServerThread(String userID,ClientConnectServerThread ccst){
        hm.put(userID,ccst);
    }
    //ͨ��userId�ҵ���Ӧ���߳�
    public static ClientConnectServerThread getClientConnectServerThread(String userId){
         return hm.get(userId);
    }
}
