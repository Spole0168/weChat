package com.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

public class QuartzTest {
	 public static void main(String[] args) {
	        SimpleDateFormat DateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	        Date d = new Date();
	        String returnstr = DateFormat.format(d);    	
	    	
	        TestJob1 job1 = new TestJob1();
	        TestJob2 job2 = new TestJob2();
//	        String job_name1 ="11111";
//	        String job_name2 ="22222";
	        try {
	            System.out.println(returnstr+ "【系统启动】");
	            									//月日时分秒  0/3 21 19 4 3 ? 2016
	            station.addDefaultJob("11111",job1,"0/3 * * * * ?"); //每2秒钟执行一次
	            Thread.sleep(10000);
	            System.out.println("/n【添加定时任务】");
	            station.addDefaultJob("22222",job1,"0/5 * * * * ?"); //每2秒钟执行一次
//	            System.out.println("【修改时间】");
//	            QuartzManager.modifyJobTime(job_name1,"0/10 * * * * ?");
//	            Thread.sleep(20000);
//	            System.out.println("【移除定时】");
//	            QuartzManager.removeJob(job_name1);
	            System.out.println("/n【添加定时任务】");
	            station.addDefaultJob("33333",job1,"0/7 * * * * ?");
	            Thread.sleep(1000);
	            System.out.println("/n【添加定时任务】");
	            station.addDefaultJob("44444",job1,"0/7 * * * * ?");
	            
	        }  catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}