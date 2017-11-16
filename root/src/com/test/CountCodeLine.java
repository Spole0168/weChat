package com.test;

import java.io.*;
import java.util.regex.*;

import org.junit.Test;
/**
 * 统计java代码行数
 * @author Spole
 *
 */
public class CountCodeLine {
	@Test
	public void test01(){
		System.out.println(conuntCodeLines(new File("D:/Java/Work_ec/mvc_v7.5/src"),10));
		
	}
	public static long conuntCodeLines(File f,int num) {
		long sum = 0L;
		File[] ary = f.listFiles();
		for (File file : ary) {
			String fn = file.getName();
			Long lines = 0L;
			if (file.isDirectory()) {
				lines = conuntCodeLines(file,num);//递归
				System.out.println(fn+"是 文件夹 Folder"+"代码总行数：\t\t\t\t"+lines);
			} else if(file.isFile()&&file.getName().matches("^[A-Z]\\w+\\.java$")){
				lines = 	handleFile(file,num);  //处理文件.java
//				System.out.println(fn+"是 文件 File Java" +"代码总行数：\t\t\t\t"+lines);
			}
			sum = sum + lines;
		}
		return sum;
	}
	
	private static long handleFile(File file,int num) {
		long codeLine = 0L;
		 BufferedReader br = null; 
		 try {
			 br = new BufferedReader(new FileReader(file));
			String tempString = null;   
	          // 一次读入一行，直到读入null为文件结束   
	          while ((tempString = br.readLine()) != null) {   
	              // 显示行号   
	              if(tempString!=null&&tempString.length()>num){
	            	  codeLine ++;
	              }
	          }   
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
		return codeLine;
	}
	@Test
	public void test(){
		//D:\Uisftech
		DelFilesInFolder(new File("D:/Uisftech"));
	}
	public static void DelFilesInFolder(File f) {
		File[] ary = f.listFiles();
		for (File file : ary) {
			String fn = file.getName();
			if (file.isDirectory()) {
				System.out.println(fn);
				DelFilesInFolder(file);
				//&&file.getName().matches("^[A-Z]\\w+\\.java$")
			} else if(file.isFile()&&(file.getName().matches("^[A-Z]\\w+\\.java$"))){
				file.delete();
			}
		}
	}
}