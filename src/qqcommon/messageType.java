package qqcommon;

//消息类型
public interface messageType {
    //不同常量代表不同的消息类型。
    String MESSAGE_LOGIN_SUCCEED = "1";//登陆成功
    String MESSAGE_LOGIN_FAILED = "2";//登陆失败
    String MESSAGE_COMMON_MES= "3";//普通信息包
    String MESSAGE_GET_ONLINE_FRIEND="4";//得到在线用户列表
    String MESSAGE_RET_ONLINE_FRIEND="5";//返回在线用户列表
    String MESSAGE_CLIENT_EXIT="6";//客户端请求退出
    String MESSAGE_TO_ALL_MES="7";//群发消息
    String MESSAGE_FILE_MES="8";//发送文件


}
