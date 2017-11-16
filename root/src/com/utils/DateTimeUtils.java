package com.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**入库，出库	
 * 处理数据库中 日期转化，
 * @author Spole
 *
 */
public class DateTimeUtils {
	public static final SimpleDateFormat formatStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * 获得当前日期
	 * yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getCurrentDateStr(){
		return formatStr.format(new Date());
//		Calendar calendar = Calendar.getInstance();   
//		return calendar.
	}
	public static String getDateTimeStrByCondition(String dtstr,int value){
		Date curDate = null;
		try {
			curDate = formatStr.parse(dtstr);
		} catch (ParseException e) {
			e.printStackTrace();
			return "输入的时间格式不合要求";
		} catch(NullPointerException e2){
			System.out.println("你传入的参数："+dtstr+"是空值，默认使用当前时间");
			curDate = new Date();
		}
		Calendar calendar = Calendar.getInstance(); 
		calendar.setTime(curDate);
		calendar.set(Calendar.DAY_OF_YEAR, value);
		return formatStr.format(calendar.getTime());
	}
	
	public static Timestamp toTimestamp(String ss){
		Timestamp tt = null;
		Date curDate = null;
		new Timestamp(System.currentTimeMillis());
		try {
			curDate = formatStr.parse(ss);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new Error("输入的时间格式不合要求");
		} catch(NullPointerException e2){
			System.out.println("你传入的参数："+ss+"是空值，默认使用当前时间");
			curDate = new Date();
		}
		tt.setTime(curDate.getTime());
//		tt.valueOf(ss);
		return tt; 
	}
	
	
	public static Timestamp toDtStr(){
		//java.util.Date  : 当前时间：new Datw();  指定时间：new Date(long num);
		Date dateUtil = new Date();
		dateUtil.getTime();
//		dateUtil.compareTo(anotherDate)
		//java.sql.Date  : new Date(Long num);
		
		java.sql.Date dateSql  = new java.sql.Date(System.currentTimeMillis());
		dateSql.valueOf(getCurrentDateStr());
		//java.sql.Timestamp  new Timestamp(Long num);
		Timestamp tt = new Timestamp(System.currentTimeMillis());
		tt.valueOf(getCurrentDateStr());
		
		Calendar calendar = Calendar.getInstance(); 
		return null;
	}
	
	

}
