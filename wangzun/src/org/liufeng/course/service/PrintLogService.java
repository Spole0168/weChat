package org.liufeng.course.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.liufeng.course.message.resp.TextMessage;
import org.liufeng.course.util.MessageUtil;
import org.liufeng.course.util.MySQLUtil;

public class PrintLogService {

	private static String simpleMsg="发送的信息格式为：学校名称#复印张数，例如：XXX理工大学#14。感谢您的合作！";
	private static Integer smallNum=1;
	private static Integer bigNum=15;
	
	public String processRequest(HttpServletRequest request,TextMessage tm){
		String temp="";
		String content=tm.getContent();
		if(content==null||content.trim().equals("")){
			temp="";//此次请求没有内容，什么信息也不用回复
		}else{
			if(content.indexOf("#")>-1){
				String[] tempArr=content.split("#");
				if(tempArr.length!=2){
					temp=""+simpleMsg;
				}else{
					//先判断今天申请没申请打印，如果没申请，则可以继续，
//					boolean flag=true;//true没申请打印，false 为今天已经申请了这个打印的张数
					
					String message=MySQLUtil.query(request,tm);//message为null说明今天还没申请，不为null，说明已经申请了。返回详细信息
					if(message==null||message.equals("")){
					     String schoolName=tempArr[0];
					     String printNum=tempArr[1];
					     int num=1;
					     try{
						     num=Integer.parseInt(printNum.trim());
						     if(num<smallNum||num>bigNum){
						    	 System.out.println("schoolename"+schoolName+"   "+""+printNum+"    aaaa");
//						    	 return "你发送的消息中：学校名称#复印张数，#后面的'复印张数必须是大于等于"+smallNum+",并且小于等于"+bigNum+"的整数哦！例如：9、14等";
						    	 return null;
						     }
					     }catch(Exception e){
						      temp="你发送的消息中：学校名称#复印张数，#后面的'复印张数必须是大于等于"+smallNum+",并且小于等于"+bigNum+"的整数哦！例如：9、14等";
						      System.out.println(temp+"   "+e.getMessage());
					         return null;
					     }
					     //TODO 添加到数据库
					     String signature="";
					     Date now=new Date(System.currentTimeMillis());
					     SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
					     if(tm.getToUserName()!=null&&tm.getToUserName().length()>7){
					           signature=sdf.format(now)+tm.getToUserName().substring(0, 7)+(new Random().nextInt(999));
					     }else{
					    	   signature=sdf.format(now)+(new Random().nextInt(9999));
					     }
					     int pintid=MySQLUtil.savePrintLog(request,tm,schoolName,num,signature);		 
					   
					     temp="恭喜您，成功的领取到了免费打印数为"+num+" 张,标识为："+signature+"（不要泄露哦，凭此才能免费打印哦）";
					     System.out.println("xxxx"+temp+"   toUser"+tm.getToUserName());
					     return temp;
					}else{
						temp=message;
					}
				}
			}else{
				temp=""+simpleMsg;
			}
		}
		System.out.println("xxxxxaaaa"+temp);
		
		return null;
	}
	
	public static void main(String[] args) {
		TextMessage tm=new TextMessage();
		tm.setContent("北湖大学学#12");
		tm.setCreateTime(System.currentTimeMillis());
		tm.setFromUserName("sxxxxxaasd");
		tm.setMsgType("");
//		tm.set
		PrintLogService pls=new PrintLogService();
		String dd=pls.processRequest(null, tm);
		System.out.println(dd);
//		String ss="北华大学#15# ";
//		String[] sss=ss.split("#");
//		System.out.println(sss.length);
//		Random r=new Random();
//		System.out.println(r.nextInt(9999));
//		System.out.println(sss[0]);
//		System.out.println(sss[1]);
//		System.out.println(sss[2]);
//		SimpleDateFormat dateformat1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E");
//		  String a1=dateformat1.format(new Date());
//		  System.out.println("时间2:"+a1);
//		  System.out.println(new Date().getYear()+1900);
//		  
//		  SimpleDateFormat dateformat2=new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 E ");   
//		        String a2=dateformat2.format(new Date());
//		        System.out.println("时间2:"+a2); 

	}
}
