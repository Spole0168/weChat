package com.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class TestJob2 implements Job {
    SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date d = new Date();
    String returnstr = DateFormat.format(d);  

    public void execute(JobExecutionContext arg0) throws JobExecutionException {
    	Random r = new Random();
    	int num = r.nextInt(20);
    	System.out.println("TestJob2-"+returnstr+"========="+arg0);
//    	if(num%3==0){
//    	}else{
//    		System.out.println("Job2"+returnstr+"(��o��)"+arg0);
//    	}
    }

}
