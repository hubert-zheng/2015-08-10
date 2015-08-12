package com.hand.Exam2;
import java.util.Vector;

/**
 * 一个聊天服务器只能有一个管理器，所以单例化
 * @author Hubrt
 *
 */
public class ChatManager {
    //单例化第一步，构造方法私有化
    private ChatManager(){}
    //单例：创建最后一个实例对象
    private static final ChatManager cm = new ChatManager();
    //其他使用者只能通过方法调用上述单例对象
    public static ChatManager getChatManager(){
         return cm ;
    }
    //线程集合
    Vector<ChatSocket> vector = new Vector<ChatSocket>();
    /**
     * 创建ChatSocket时将添加进集合中
     * @param sc
     */
    public void add(ChatSocket sc ){
         vector.add( sc);
    }
    /**
     * 其他客户端线程想向其他人发送信息，可调用该函数
     * @param cs
     */
    public void publish(ChatSocket cs ,byte[] b){
         //发送时，需要发送给集合中的所有线程，所有需要循环
         for (int i = 0; i <vector .size(); i++) {
            ChatSocket csChatSocket = vector.get(i );
             //当前发送信息的线程则不需要再接收该信息
             if(!cs .equals(csChatSocket )){
                 csChatSocket.input(b);
            }
        }
    }
}
