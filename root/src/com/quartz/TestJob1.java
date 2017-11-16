package com.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Trigger;

public class TestJob1 implements Job {
    SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date d = new Date();
    String returnstr = DateFormat.format(d);  

    public void execute(JobExecutionContext arg0) throws JobExecutionException {
    	Random r = new Random();
    	int num = r.nextInt(30);
    	int flag = num%3;
    	System.out.println("jobName:\t"+arg0.getJobDetail().getName());
//    	System.out.println("Trigger"+arg0.getTrigger());
//    	System.out.println("arg0.getJobDetail()"+arg0.getJobDetail());
//    	System.out.println("arg0.getJobInstance()"+arg0.getJobInstance());
//    	if(flag==0){
//    		System.out.println("TestJob1-"+returnstr+"★★★★★★★★★★★"+arg0);
//    	}else if(flag==1){
//    		System.out.println("TestJob1-"+returnstr+"(⊙o⊙)(⊙o⊙)(⊙o⊙)"+arg0);
//    	}else if(flag==2){
//    		System.out.println("TestJob1-"+returnstr+"O(∩_∩)OO(∩_∩)O"+arg0);
//    	}
    }

}
