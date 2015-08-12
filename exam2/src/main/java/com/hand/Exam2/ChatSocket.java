package com.hand.Exam2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

public class ChatSocket extends Thread {
    
    Socket socket;
    public ChatSocket(Socket s){
         this.socket = s ;
    }
    
    public boolean input(byte[] b){
        try {
        	while (socket.getInputStream().read(b)!=-1) {
        		return true;
			}          
        } catch (UnsupportedEncodingException e ) {
             e.printStackTrace();
        } catch (IOException e ) {
             e.printStackTrace();
        }
		return false;
    }
    
    
    @Override
    public void run() {
		try {
			if(new File("new_SampleChapter1.pdf").exists()){
				new File("new_SampleChapter1.pdf").delete();
			}
			
			FileOutputStream fos = new FileOutputStream("new_SampleChapter1.pdf");
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			
			 byte input [] = new byte[100];
			 while (input(input)) {
                 bos.write( input); //写入文件
			 }
			 bos.close();
             fos.close();
             System.out.println("new_SampleChapter1.pdf接收完毕");
             
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
    }
    
    
    
}


