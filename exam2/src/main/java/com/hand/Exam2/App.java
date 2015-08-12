package com.hand.Exam2;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        new Postpdf().start();
       
        new ServerListener().start();
	
        
    }
}
class Postpdf extends Thread{
	HttpClient client = HttpClients.createDefault();
	@Override
	public void run() {
		try {
			
			HttpGet get = new HttpGet("http://www.manning.com/gsmith/SampleChapter1.pdf");
			HttpResponse response = client.execute(get);
			HttpEntity entity = response.getEntity();
			byte input [] = EntityUtils.toByteArray(entity);
			if(new File("SampleChapter1.pdf").exists()){
				new File("SampleChapter1.pdf").delete();
			}
			FileOutputStream fos = new FileOutputStream(new File("SampleChapter1.pdf"));
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			bos.write(input);
			System.out.println("SampleChapter1.pdf下载完成！");
			System.out.println("下载完成");
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
