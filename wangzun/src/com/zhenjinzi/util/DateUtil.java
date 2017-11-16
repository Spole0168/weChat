package com.zhenjinzi.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class DateUtil {
	
	public static final String SMALL="SMALL";//判断大小与下面的BIG是一对
	public static final String BIG="BIG";//判断大小与上面的SMALL是一对
	
	public static Date getCurntDate() {
		return Calendar.getInstance().getTime();
	}
	
	/**
	 * 获取当天的起始时间
	 * @return Date
	 */
	public static Date getToday(){
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE,0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}
	
	/**
	 * 获取第二天的起始时间
	 * @return
	 */
	public static Date getTomorrow(){
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE,0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	/**
	 *将一个日期字符串转化成日期
	 */
	public static Date switchStringToDate(String sDate) throws Exception {
		Date date = null;
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			date = df.parse(sDate);
			return date;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}
	
	public static String GetTime(Date timestamp){
		return new java.text.SimpleDateFormat("MM.dd HH:mm").format(timestamp);
		
	}

	/**
	 *判断系统当前时间是不是润年
	 */
	@SuppressWarnings("static-access")
	public static boolean isRunNian() {
		java.util.Calendar rightNow = java.util.Calendar.getInstance();
		int year = rightNow.get(rightNow.YEAR);
		if (0 == year % 4 && (year % 100 != 0 || year % 400 == 0)) {
			return true;
		} else {
			return false;
		}

	}


	/**
	 *跟椐类型格式化时间为字符串
	 */
	public static String formatDateToStringByType(Date newDate,
			String formatType) {
		String sDate = null;
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatType);
			sDate = simpleDateFormat.format(newDate);
		} catch (NullPointerException e) {

		} catch (IllegalArgumentException e) {

		} catch (Exception e) {

		}
		return sDate;
	}

	/**
	 *判断当前时间是否在时间date2之前 时间格式 2005-4-21 16:16:34
	 */
	public static boolean isDateBefore(String date2) {
		try {
			Date date1 = new Date();
			DateFormat df = DateFormat.getDateTimeInstance();
			return date1.before(df.parse(date2));
		} catch (Exception e) {
			System.out.print("[SYS] " + e.getMessage());
			return false;
		}
	}

	
	/**
     * 获取时间戳，将日期对象转换为时间戳类型。
     * 
     * @param date 日期对象
     * @return Timestamp 时间戳
     */
    public static Timestamp getTimestamp(Date date) {
    	return new Timestamp(date.getTime());
    }

    /**
     * 获取时间戳，将当前日期转换为时间戳类型。
     * 
     * @return Timestamp 时间戳
     */
    public static Timestamp getTimestamp() {
    	return new Timestamp(new Date().getTime());
    }
    
	/**
	 * 获取相对于某时刻的时间
	 * @param dateStr 时间基数，yyyy-MM-dd HH:mm:ss格式的字符串
	 * @param second 偏移秒数，10|-10 ，负数表示之前
	 * @return 时间戳
	 */
	public static Timestamp SecondCalc(String dateStr, Integer second) {
		String newDateStr = "";
		Calendar cal;

		SimpleDateFormat dateDf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			cal = Calendar.getInstance();
			cal.setTime(dateDf.parse(dateStr));
			cal.add(Calendar.SECOND, second);
			newDateStr = dateDf.format(cal.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return Timestamp.valueOf(newDateStr);
	}
	
	/**
	 * 
	 * @param dateStr
	 * @param days
	 *            days sample:10,-10
	 * @return 指定时间{dateStr}+{days}天的日期
	 */
	public static Date DayCalc(Integer days){
		SimpleDateFormat dateDf = new SimpleDateFormat();
		String dateStr = dateDf.format(new Date());
		return DayCalc(dateStr,days);
	}
	
	/**
	 * 
	 * @param dateStr
	 * @param days
	 *            days sample:10,-10
	 * @return 指定时间{dateStr}+{days}天的日期
	 */
	public static Date DayCalc(String dateStr, Integer days) {
		SimpleDateFormat dateDf = new SimpleDateFormat(
			"yyyy-MM-dd");
		Calendar cal;
		cal = Calendar.getInstance();
		try {
			cal.setTime(dateDf.parse(dateStr));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cal.add(Calendar.DAY_OF_MONTH, days);
		return cal.getTime();
	}
	
	public static void main(String []args){
		System.out.println(DayCalc(-7).toString());;
	}
	/**
	 * 把一个存有String(date)的set集合转换成date的set集合
	 * @param strs
	 * @return
	 */
	public static Set<Date> setString2Date(Set<String> strs) {
		Set<Date> dates=new HashSet<Date>();
		if(strs==null){return null;}
		
		for (String str : strs) {
			Date date=stringToDate(str);
			if(date!=null){
				dates.add(date);
			}
		}
		return dates;
	}
	/**
	   * 字符串转换为java.util.Date<br>
	   * @param time String 字符串<br>
	   * @return Date 日期<br> 
	   * 有些类型还没添加如 dd-mm-yyyy等等 
	   * 慢慢判断
	   */
	@SuppressWarnings("deprecation")
	public static Date stringToDate(String time){
		Date dd=null;
		if(time==null){return null;}
		time=time.trim();
		SimpleDateFormat formatter;
		try{
		     dd=new Date(time);
		}catch(Exception e){
		}
		formatter=new SimpleDateFormat();
		if(dd==null){
			if((time.indexOf("-")>-1)&&(time.indexOf(" ")>-1)){
				formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
			}else if(time.indexOf("-")>-1){
				formatter = new SimpleDateFormat ("yyyy-MM-dd");
			}else if((time.indexOf("/")>-1)&&(time.indexOf(" ")>-1)){
				formatter = new SimpleDateFormat ("yyyy/MM/dd HH:mm:ss");
			}else if((time.indexOf(".")>-1)&&(time.indexOf(" ")>-1)){
				formatter = new SimpleDateFormat ("yyyy.MM.dd HH:mm:ss");
			}else if(time.indexOf("/")>-1){
				formatter = new SimpleDateFormat ("yyyy.MM.dd");
			}
			ParsePosition pos = new ParsePosition(0);
		    dd = formatter.parse(time, pos);
		}
		return dd;
	}

	/**
	 * 将date转换成符合规范的String
	 * @param time Date
	 * @return
	 */
	public static String dateToString(Date time){
	    SimpleDateFormat formatter;
	    formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
	    String ctime = formatter.format(time);
	    return ctime;
	}
	/**
	 * 根据smallOrBig参数返回dates最大或是最小的值
	 * @param dates 时间的set集合
	 * @param smallOrBig DateUtil.SMALL或是DateUtil.BIG只能是这两个值
	 * @return smallOrBig值为SMALL的话，返回dates里面时间最小的那个
	 *         smallOrBig值为BIG的话，返回dates里面时间最小的那个
	 */
	public static Date getDateSmallOrBig(Set<Date> dates,String smallOrBig){
		Date date=null;
		if(DateUtil.BIG.equals(smallOrBig)){//获得最大的
			date=Collections.max(dates);
		}else if(DateUtil.SMALL.equals(smallOrBig)){
			date=Collections.min(dates);
		}
		return date;
	}
	
	
	
}
