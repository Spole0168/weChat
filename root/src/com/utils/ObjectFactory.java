package com.utils;

import java.io.*;
import java.util.*;


public class ObjectFactory {
	private static Map<String,String> bmap = null;
	
	public ObjectFactory() {
		super();
		bmap = new HashMap<String,String>();
		System.out.println("AAAAAAAAAAAA");
		//加载类
		Properties props = new Properties();
		InputStream in = ObjectFactory.class.getClassLoader().getResourceAsStream("config/Beans.properties");
		try {
			props.load(in);
			in.close();
			Enumeration<?> en = props.propertyNames();
			 while (en.hasMoreElements()){
			        String k = (String) en.nextElement();
			        bmap.put(k,props.getProperty(k));
			    }

			} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static  <T> T getBean(String beanName){
		Object obj = null;
		if(null!=bmap.get(beanName)){
			String className = bmap.get(beanName).trim();
			try {
				obj = Class.forName(className).newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}else{
			System.out.println("没有配置"+beanName+"实例");
		}
		return (T) obj;
	}
}
