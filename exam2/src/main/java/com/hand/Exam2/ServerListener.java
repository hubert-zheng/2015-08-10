package com.hand.Exam2;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListener extends Thread {
    @Override
    public void run() {
         try {
             //传入端口号，可以从1-65535，一般指定比较大的数字跟系统预留的端口分开
            ServerSocket serversocket = new ServerSocket(23456);
             while(true){
                 /**
                 * 添加客户端的连接，调用accept()会阻塞当前线程，一般用另外线程调用
                 * 每当有一个客户端连接到当前的 serversocket，accept()会返回一个新的socket对象
                若有多个客户端连接当前的 serversocket，就会有多个socket的对象,所以需要在while循环地监听客户端的连接
                 */
                Socket socket = serversocket.accept();
                 //提示框建立连接
                 //JOptionPane.showMessageDialog(null, "有客户端链接到本机12345端口");
                System. out.println("有客户端链接到本机12345端口" );
                
                 //将对象传递给ChatSocket新线程
                ChatSocket cs = new ChatSocket(socket);
                cs.start();
                 FileInputStream fis = new FileInputStream("SampleChapter1.pdf");
                 BufferedInputStream bis = new BufferedInputStream(fis);
                 byte input [] = new byte[100];
                 while (bis.read(input ) != -1) {
                	 socket.getOutputStream().write(input);
                 }
                 bis.close();
                 fis.close();
                 ChatManager. getChatManager().publish(cs, input);
            }
             
            
        } catch (IOException e ) {
             e.printStackTrace();
        }
    }
    

}
