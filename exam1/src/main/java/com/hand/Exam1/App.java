package com.hand.Exam1;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * Download file
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //new Getpdf().start();
    	new Postpdf().start();
    }
}

//class Getpdf extends Thread{
class Postpdf extends Thread{
	HttpClient client = HttpClients.createDefault();
	@Override
	public void run() {
		try {
			
			HttpGet get = new HttpGet("http://www.manning.com/gsmith/SampleChapter1.pdf");
			HttpResponse response = client.execute(get);
			HttpEntity entity = response.getEntity();
			byte input [] = EntityUtils.toByteArray(entity);
			
			FileOutputStream fos = new FileOutputStream(new File("SampleChapter1.pdf"));
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			bos.write(input);
			System.out.println("SampleChapter1.pdf下载完成！");
			//System.out.println(input.toString());
			
//			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//			connection.addRequestProperty("encoding" , "UTF-8");
//			connection.setDoInput(true);
//			connection.setDoOutput(true);
//			connection.setRequestMethod("POST");
//			
//			OutputStream os = connection.getOutputStream();
//            OutputStreamWriter osw = new OutputStreamWriter(os);
//            BufferedWriter bw = new BufferedWriter(osw);
//			
//			
//			InputStream is = connection.getInputStream();
//			BufferedInputStream bis = new BufferedInputStream(is);
//			
//			FileOutputStream fos = new FileOutputStream(new File("SampleChapter1.pdf"));
//			BufferedOutputStream bos = new BufferedOutputStream(fos);
//        	
//			byte[] input = new byte[1000];
//            
//             while (bis.read(input)!= -1) {
//            	 bos.write(input); 
//             }
//             
//             bw.close();
//             osw.close();
//             os.close();
//             is.close();
//             bis.close();
//             bos.close();
//             fos.close();
//
//             //System.out.println("");
			System.out.println("下载完成");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
