package com.hand.Exam3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        new Getsina().start();
        
    }
}

class Getsina extends Thread{
	@Override
	public void run() {
		try {
			
			URL url = new URL("http://hq.sinajs.cn/list=sh601006");
			URLConnection connection = url.openConnection();
			InputStream is = connection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is,"GBK");
			BufferedReader br = new BufferedReader(isr);

			String line;
	        StringBuilder builder = new StringBuilder();
	        while((line = br .readLine())!=null){
	                 builder.append( line);
	        }
	      
            br.close();
            isr.close();
            is.close();
            
            System.out.println(builder.toString());
            System.out.println(builder.toString().substring(50,55));
            
            
            //XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder xmlbuilder = factory.newDocumentBuilder();
            Document document = xmlbuilder.newDocument(); 
            
            Element root = document.createElement("xml");
            
            Element lan1 = document.createElement("stock");
            Element name1 = document.createElement("name");
			name1.setTextContent(builder.toString().substring(21,25));
            
			Element name2 = document.createElement("open");
			name2.setTextContent(builder.toString().substring(26,31));
			
			Element name3 = document.createElement("close");
			name3.setTextContent(builder.toString().substring(32,37));
			
			Element name4 = document.createElement("current");
			name4.setTextContent(builder.toString().substring(38,43));
			
			Element name5 = document.createElement("high");
			name5.setTextContent(builder.toString().substring(44,49));
			
			Element name6 = document.createElement("low");
			name6.setTextContent(builder.toString().substring(50,55));
			
			lan1.appendChild(name1);
			lan1.appendChild(name2);
			lan1.appendChild(name3);
			lan1.appendChild(name4);
			lan1.appendChild(name5);
			lan1.appendChild(name6);
			root.appendChild(lan1);
			document.appendChild(root);
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			StringWriter writer = new StringWriter();
			transformer.transform(new DOMSource(document), new StreamResult(writer));
			System. out.println(writer.toString());
			transformer.transform(new DOMSource(document), new StreamResult(new File("Exam3-xml.xml" )));
			System.out.println("XML写入完成！");
			
			
			//Json
			JsonObject object = new JsonObject();
			object.addProperty("name",builder.toString().substring(21,25));
			object.addProperty("open",builder.toString().substring(26,31));
			object.addProperty("close",builder.toString().substring(32,37));
			object.addProperty("current",builder.toString().substring(38,43));
			object.addProperty("high",builder.toString().substring(44,49));
			object.addProperty("low",builder.toString().substring(50,55));
			
			System.out.println(object.toString());
			FileOutputStream fos = new FileOutputStream(new File("Exam3-Json.json"));
			OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
			BufferedWriter bw = new BufferedWriter(osw);
			
			bw.write(object.toString());
			bw.flush(); 
			bw.close();
			osw.close();
			fos.close();
			System.out.println("Json写入完成!");
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
		
	}
}