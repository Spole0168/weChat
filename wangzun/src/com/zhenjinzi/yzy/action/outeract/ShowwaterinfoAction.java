package com.zhenjinzi.yzy.action.outeract;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.liufeng.course.util.MySQLUtil;

import com.fire.modules.web.struts2.Struts2Utils;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.zhenjinzi.yzy.action.BaseAction;
import com.zhenjinzi.yzy.model.YzyRepairlog;
import com.zhenjinzi.yzy.model.YzyUserinfo;
import com.zhenjinzi.yzy.service.UserinfoService;
//import com.zhenjinzi.yzy.model.YzyWatermachine;
//import com.zhenjinzi.yzy.service.YinzyWatermechineService;

/**
 * center 展示界面
 * @author ly
 *
 */
public class ShowwaterinfoAction extends BaseAction{
	public static Logger logger = Logger.getLogger(ShowwaterinfoAction.class.getName());
//	@Resource
//	private YinzyWatermechineService watermechineService;
	@Resource
	private UserinfoService userinfoService;
	private String machineid;
	private String code;
	
	@Override
	public String execute() throws Exception {
		return "input";
	}
	
	public String show(){
		Search search = new Search();
		String openid  = MySQLUtil.getOpenidByCode(code);
		System.out.println("UserjihuoAction code:"+code+"   openiddd:"+openid +":machineid:"+machineid);
		logger.info("UserjihuoAction code:"+code+"  openid:"+openid+"   machineid:"+machineid);
		if(openid!=null&&!openid.trim().equals("")){
			search.addFilterEqual("openid", openid);
		}
		if(machineid!=null &&!machineid.trim().equals("")){
			search.addFilterEqual("machineid", machineid.trim());
		}
		SearchResult<YzyUserinfo> searchResult = userinfoService.searchAndCount(search);
		if(searchResult==null){
		      Struts2Utils.renderJson("{\"statusCode\":\"300\",\"message\":\"无机器id为"+machineid+"的饮水机1，请正确输入\"}");
		      return null;
		}
		List<YzyUserinfo> list = searchResult.getResult();
		if(list==null || list.size() == 0){
			Struts2Utils.renderJson("{\"statusCode\":\"300\",\"message\":\"无机器id为"+machineid+"的饮水机2，请正确输入\"}");
		}else{
			YzyUserinfo user=list.get(0);
			
//			YzyUserinfo user = water.getUserInfo();
//			Map<String,String> retInfo = new HashMap<String, String>();
			if(user != null){
				Date createDate = user.getCreate_Date();
				long serviceSecong = 0l;
				YzyRepairlog repairlog  = getRepairLogNew(user);
				
				if(createDate!=null){
//					serviceSecong = System.currentTimeMillis()-repairlog.getRepairtime().getTime();//getTime();
					serviceSecong = System.currentTimeMillis()-createDate.getTime();//getTime();
				}
				int days = (int) (serviceSecong/(24*60*60*1000));//在除数上增加括号，解决
				int SYDays=365;
				if(user.getText4()==null){
				   SYDays=365-days;     //TODO  找一个字段管理起来***
				}else{
					try{
						SYDays=Integer.parseInt(user.getText4().trim())-days;
						System.out.println("showwatermechine:SYDays"+SYDays+"   Integer.parseInt(user.getText4().trim()):"+Integer.parseInt(user.getText4().trim()));
					}catch(Exception e){
						e.printStackTrace();
						System.out.println("日期错误"+e.getMessage());
						 SYDays=365-days;     //TODO  找一个字段管理起来***
					}
				}
				String smell =(repairlog.getText2()==null?"":repairlog.getText2());
				String texture=(repairlog.getText1()==null?"":repairlog.getText1());
				String scale=(repairlog.getScaleflag()==null?"":repairlog.getScaleflag());
//				获取服务总时长   天数
				System.out.println("{\"statusCode\":\"200\",\"tel\":\""+user.getTelphone()+"\",\"name\":\""+user.getContact_name()+"\",\"tds_in\":\""+user.getInputtds()+"\",\"tds_out\":\""+user.getOutputtds()+"\",\"create_date\":\""+user.getCreate_Date()+"\",\"totalDays\":\""+SYDays+"\"}");
				Struts2Utils.renderJson("{\"statusCode\":\"200\",\"machineid\":\""+user.getMachineid()+"\",\"tel\":\""+user.getTelphone()+"\",\"name\":\""+user.getContact_name()+"\",\"tds_in\":\""+user.getInputtds()+"\",\"tds_out\":\""+user.getOutputtds()+"\",\"create_date\":\""+user.getCreate_Date()+"\",\"SYDays\":\""+SYDays+"\",\"scale\":\""+scale+"\",\"texture\":\""+texture+"\",\"smell\":\""+smell+"\"}");
			}else{
				Struts2Utils.renderJson("{\"statusCode\":\"300\",\"message\":\"无机器id为"+machineid+"的饮水机3，请正确输入\"}");
			}
		}
		return null;
	}
	/***
	 * 查询饮水机信息 新方式
	 * @return
	 */
	public String shownew(){
		Search search = new Search();
//		String openid  = MySQLUtil.getOpenidByCode(code);
//		System.out.println("UserjihuoAction code:"+code+"   openiddd:"+openid +":machineid:"+machineid);
//		logger.info("UserjihuoAction code:"+code+"  openid:"+openid+"   machineid:"+machineid);
//		if(openid!=null&&!openid.trim().equals("")){
//			search.addFilterEqual("openid", openid);
//		}
		if(machineid!=null &&!machineid.trim().equals("")){
			search.addFilterEqual("machineid", machineid.trim());
		}else{
			  Struts2Utils.renderJson("{\"statusCode\":\"300\",\"message\":\"饮水机id必须传入，请正确输入\"}");
		      return null;
		}
		SearchResult<YzyUserinfo> searchResult = userinfoService.searchAndCount(search);
		if(searchResult==null){
		      Struts2Utils.renderJson("{\"statusCode\":\"300\",\"message\":\"无机器id为"+machineid+"的饮水机1，请正确输入\"}");
		      return null;
		}
		List<YzyUserinfo> list = searchResult.getResult();
		if(list==null || list.size() == 0){
			Struts2Utils.renderJson("{\"statusCode\":\"300\",\"message\":\"无机器id为"+machineid+"的饮水机2，请正确输入\"}");
		}else{
			YzyUserinfo user=list.get(0);
			
//			YzyUserinfo user = water.getUserInfo();
//			Map<String,String> retInfo = new HashMap<String, String>();
			if(user != null){
				Date createDate = user.getCreate_Date();
				long serviceSecong = 0l;
				YzyRepairlog repairlog  = getRepairLogNew(user);
				
				if(createDate!=null){
//					serviceSecong = System.currentTimeMillis()-repairlog.getRepairtime().getTime();//getTime();
					serviceSecong = System.currentTimeMillis()-createDate.getTime();//getTime();
				}
				int days = (int) (serviceSecong/(24*60*60*1000));//在除数上增加括号，解决
				int SYDays=365;
				if(user.getText4()==null){
				   SYDays=365-days;     //TODO  找一个字段管理起来***
				}else{
					try{
						SYDays=Integer.parseInt(user.getText4().trim())-days;
						System.out.println("showwatermechine:SYDays"+SYDays+"   Integer.parseInt(user.getText4().trim()):"+Integer.parseInt(user.getText4().trim()));
					}catch(Exception e){
						e.printStackTrace();
						System.out.println("日期错误"+e.getMessage());
						 SYDays=365-days;     //TODO  找一个字段管理起来***
					}
				}
				String smell =(repairlog.getText2()==null?"":repairlog.getText2());
				String texture=(repairlog.getText1()==null?"":repairlog.getText1());
				String scale=(repairlog.getScaleflag()==null?"":repairlog.getScaleflag());
//				获取服务总时长   天数
				System.out.println("{\"statusCode\":\"200\",\"tel\":\""+user.getTelphone()+"\",\"name\":\""+user.getContact_name()+"\",\"tds_in\":\""+user.getInputtds()+"\",\"tds_out\":\""+user.getOutputtds()+"\",\"create_date\":\""+user.getCreate_Date()+"\",\"totalDays\":\""+SYDays+"\"}");
				Struts2Utils.renderJson("{\"statusCode\":\"200\",\"machineid\":\""+user.getMachineid()+"\",\"tel\":\""+user.getTelphone()+"\",\"name\":\""+user.getContact_name()+"\",\"tds_in\":\""+user.getInputtds()+"\",\"tds_out\":\""+user.getOutputtds()+"\",\"create_date\":\""+user.getCreate_Date()+"\",\"SYDays\":\""+SYDays+"\",\"scale\":\""+scale+"\",\"texture\":\""+texture+"\",\"smell\":\""+smell+"\"}");
			}else{
				Struts2Utils.renderJson("{\"statusCode\":\"300\",\"message\":\"无机器id为"+machineid+"的饮水机3，请正确输入\"}");
			}
		}
		return null;
	}
	
//	public static void main(String[] args) throws ParseException {
//		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//		Date createDate = sdf.parse("2016-03-14 20:32:19");
//		
//		
//		long serviceSecong = 0l;
//		if(createDate!=null){
//			serviceSecong = System.currentTimeMillis()-createDate.getTime();
//		}
//		int yu = (int) (serviceSecong%(24*60*60*1000));
//		int days = (int) (serviceSecong/(24*60*60*1000));
//		System.out.println(days);
//		System.out.println(yu);
//	}
	
	private YzyRepairlog getRepairLogNew(YzyUserinfo user) {
		YzyRepairlog rlog = new YzyRepairlog();
		rlog.setRepairtime(new Timestamp(System.currentTimeMillis()));
		if(user!=null){
			Set<YzyRepairlog> logs=user.getYzyRepairlogs();
			if(logs!=null&&logs.size()>0)
			{
//				rlog = logs.
				int i=0;
				for (YzyRepairlog rl : logs) {
					if(i==0){
						rlog = rl;
					}else{
						int tempId=rlog.getId();
						int rlId=rl.getId();
						if(tempId<rlId){
							rlog = rl;
						}
					}
					i++;
				}
			}else{
				rlog.setOutputtds(user.getOutputtds());
				rlog.setWatertds(user.getInputtds());//(user.getOutputtds());
				rlog.setRepairtime(user.getCreate_Date());
			}
		}
		return rlog;
	}

	public String getMachineid() {
		return machineid;
	}

	public void setMachineid(String machineid) {
		this.machineid = machineid;
	}

	/**
	* @param date1 <String>
    * @param date2 <String>
    * @return int
    * @throws ParseException
    */
   public int getDateSpace(String date1, String date2)
           throws ParseException {

       int result = 0;

       Calendar calst = Calendar.getInstance();;
       Calendar caled = Calendar.getInstance();

//       calst.setTime(parseDate("yyyyMMdd",date1));
//       caled.setTime(parseDate("yyyyMMdd",date2));

        //设置时间为0时   
        calst.set(Calendar.HOUR_OF_DAY, 0);   
        calst.set(Calendar.MINUTE, 0);   
        calst.set(Calendar.SECOND, 0);   
        caled.set(Calendar.HOUR_OF_DAY, 0);   
        caled.set(Calendar.MINUTE, 0);   
        caled.set(Calendar.SECOND, 0);   
       //得到两个日期相差的天数   
        int days = ((int)(caled.getTime().getTime()/1000)-(int)(calst.getTime().getTime()/1000))/3600/24;   
        
       return days;   
   }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
   
}
