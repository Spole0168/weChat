package com.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;


/**
 * 存放系统常用的公用方法
 * @author Spole
 */
public class HelpUtils {
	private final Logger logger = Logger.getLogger(this.getClass());
	public static final SimpleDateFormat formatStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * 获得当前日期
	 * yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getCurrentDateStr(){
		return formatStr.format(new Date());
	}
	
	
	 /**  
	  * 在指定时间上增加指定年、月、日、时、分、秒，日期类型  
	  * @param field  
	  *            需要做变更的日历时间域  
	  *            1  -year ; 2-month;6-day 10- hour 12-MINUTE 13-SECOND
	  * @param value  
	  *            增加的时间  
	  * @return  
	  */  
	 public static Date markDate(int field, int value) {   
	     Calendar calendar = Calendar.getInstance();   
	     calendar.setTime(new Date());   
	     switch (field) {   
		     case Calendar.YEAR:   
		       calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + value);   
		       break;   
		     case Calendar.MONTH:   
		       calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)+ value);   
		       break;   
		     case Calendar.DAY_OF_YEAR:   
		       calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR)+ value);   
		       break;   
		     case Calendar.HOUR:   
		       calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) + value);   
		       break;   
		     case Calendar.MINUTE:   
		       calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE)+ value);   
		       break;   
		     case Calendar.SECOND:   
		       calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND) + value);   
		       break;   
		     default:   
		       break;   
	     }   
	     return calendar.getTime();   
	 }
	 
	 public static String getDateTimeStrByCondition(DTCondition dtsc, int value) {   
		 Calendar calendar = Calendar.getInstance();   
		 calendar.setTime(new Date()); 
		 switch (dtsc) {   
			 case YEAR:   
				 calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + value);   
				 break;   
			 case MONTH:   
				 calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)+ value);   
				 break;   
			 case DAY:   
				 calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR)+ value);   
				 break;   
			 case HOUR:   
				 calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) + value);   
				 break;   
			 case MINUTE:   
				 calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE)+ value);   
				 break;   
			 case SECOND:   
				 calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND) + value);   
				 break;   
			 default:   
				 break;   
		 }   
		 return formatStr.format(calendar.getTime());   
	 }
	/**
	 * 递归操作文件
	 * @param f
	 * @param oldStr
	 * @param newStr
	 */
	 public static void recursionFile(File f,String oldStr,String newStr) {
		File[] ary = f.listFiles();
		for (File file : ary) {
			String fn = file.getName();
			if (file.isDirectory()) {
				System.out.println(fn+"是 文件夹 Folder");
				recursionFile(file,oldStr, newStr);//递归
			} else if(file.isFile()&&file.getName().matches("^[A-Z]\\w+\\.java$")){
				System.out.println(fn+"是 文件 File Java");
				handleFile(file,oldStr, newStr);  //处理文件.java
			}
		}
	 }
	 private static void handleFile(File file,String oldStr,String newStr) {
		 BufferedReader br = null; 
		 System.out.println("以行为单位读取文件内容，一次读一整行：");   
		 String fc = "";
		 try {
			 br = new BufferedReader(new FileReader(file));
			String tempString = null;   
	          int line = 1;   
	          // 一次读入一行，直到读入null为文件结束   
	          while ((tempString = br.readLine()) != null) {   
	              // 显示行号   
	              if(tempString.contains("package ")&&tempString.contains(oldStr)){
	            	  //com.  替换成 com.shang.
	            	  tempString = tempString.replace(oldStr, newStr);
	              }
	              fc = fc + tempString + "\n";
	              line++;   
	          }   
	          System.out.println(file.getName()+"总行数：\t"+line);
	          fc = fc.substring(0, fc.length()-1);
	          br.close(); 
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if (br != null) {   
               try {   
            	   br.close();   
               } catch (IOException e1) {   
               }   
           }   
		}
		//写入文件BufferedWriter
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
			bw.write(fc);
			bw.close();
			System.out.println("Done");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if (bw != null) {   
	               try {   
	            	   bw.close();   
	               } catch (IOException e1) {   
	               }   
	           }   
		}
	}
	public enum DTCondition {
		YEAR,MONTH,DAY,HOUR,MINUTE,SECOND
	}
	 

}
