package com.utils;

import java.io.*;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

import org.junit.Test;

public class ReadPropertiesFile {
	@Test
	public void readFileByProperties() throws Exception{
		//通过当前类加载器的getResourceAsStream方法获取
		InputStream inStream1 = ReadPropertiesFile.class.getClassLoader().getResourceAsStream("config/mvc_v.properties");  
		/**/
		//从文件获取
//		InputStream inStream1 = new FileInputStream(new File("config/mvc_v.properties")); 
		
//		InputStream inStream1 = ClassLoader.getSystemResourceAsStream("filePath"); 
		//在servlet中，还可以通过context来获取InputStream
//		InputStream in = context.getResourceAsStream("filePath"); 
		//
		URL url = new URL("filePath");  
//		InputStream inStream1 = url.openStream();
		
		Properties prop = new Properties(); 
		
		prop.load(inStream1);
		String key = prop.getProperty("jdbc.username"); 
		System.out.println(key);
		
	}

	@Test
	public void readFileByResourceBundle(){
		ResourceBundle resource = ResourceBundle.getBundle("config.mvc_v");
		String key = resource.getString("jdbc.username");
		System.out.println(key);
	}
	
	
	
}
