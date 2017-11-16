package com.zhenjinzi.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取配置文件类
 * @author Brad
 *
 */
public class PropertyReader {
	
	private static String property(String fileName,String key){
		PropertyReader pd = new PropertyReader();
		InputStream inStream = pd.getClass().getClassLoader().getResourceAsStream(fileName);
		Properties p = new Properties();
		try {
			p.load(inStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return p.getProperty(key);
	}

	/**
	 * Get value by key from property file 'mail.properties' 
	 * @param key
	 * @return value
	 */
	public static String ReadMailProperty(String key){
		return property("mail.properties",key);
	}
	public static String ReadprofitProperty(String key){
		return property("profit.properties",key);
	}
	
}
