package com.zhenjinzi.yzy.action.outeract;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.fire.modules.web.struts2.Struts2Utils;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.ibm.icu.text.SimpleDateFormat;
import com.zhenjinzi.yzy.action.BaseAction;
import com.zhenjinzi.yzy.model.YzyAppoitmentcheck;
import com.zhenjinzi.yzy.model.YzyUserinfo;
//import com.zhenjinzi.yzy.model.YzyWatermachine;
import com.zhenjinzi.yzy.service.AppoitmentcheckService;
import com.zhenjinzi.yzy.service.UserinfoService;
//import com.zhenjinzi.yzy.service.YinzyWatermechineService;

/**
 * 预约检测
 * 预约检测上门服务
 * @author ly
 *
 */
public class AppointmentouterAction extends BaseAction{
	
	@Resource
	private AppoitmentcheckService appoitmentcheckService; 
//	@Resource
//	private YinzyWatermechineService watermechineService;
	@Resource
	private UserinfoService userinfoService;
	
	private String machine_id;
	private String appointmentdate;//（预约时间），
	private String name;
	private String tel;
	
	
	
	@Override
	public String execute() throws Exception {
		return "input";
	}

	public String yuyue() throws ParseException{
		String retMsg="";
		Search search = new Search();
		if(machine_id!=null ){
			search.addFilterEqual("machineid", machine_id);
		}
		SearchResult<YzyUserinfo> searchResult = userinfoService.searchAndCount(search);
		if(searchResult==null){
			  retMsg="饮水机id为"+machine_id+"，无此饮水机！";
		      Struts2Utils.renderJson("{\"statusCode\":\"200\",\"message\":\""+retMsg+"\"}");
		      return null;
		}
		List<YzyUserinfo> list = searchResult.getResult();
		if(list==null || list.size() == 0){
			  retMsg="饮水机id为"+machine_id+"，无此饮水机！";
			  Struts2Utils.renderJson("{\"statusCode\":\"200\",\"message\":\""+retMsg+"\"}");
			  return null;
		}else{
//			user=new YzyUserinfo();
//			user.setMachineid(machine_id);
//		}else{
		}
		YzyUserinfo 	user = list.get(0);
		YzyAppoitmentcheck check = new YzyAppoitmentcheck();
//		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
//		Date appoint = sdf.parse(appointmentdate);
//		check.setAppoit_date(new Timestamp(appoint.getTime()));
		check.setText3(appointmentdate);  /**预约时间*/
		check.setCreate_Date(new Timestamp(System.currentTimeMillis()));
		check.setText1(name);
		check.setText2(tel);
		check.setUserInfo(user);
		check.setText5("1");
		boolean flag = appoitmentcheckService.save(check);
		if(flag){
			Struts2Utils.renderJson("{\"statusCode\":\"200\",\"message\":\"预约成功！\"}");
		}else{
			Struts2Utils.renderJson("{\"statusCode\":\"300\",\"message\":\"预约失败，请联系管理员！\"}");
		}
		return null;
	}
	
	/***
	 * 根据 饮水机id  获得维修记录
	 * @return
	 */
	public String  getWeixiuInfoByMechineId(){
		Search search = new Search();
		search.addFilterILike("machineid", "%"+machine_id+"%");//, value)("", machine_id);
		
		SearchResult<YzyUserinfo> searchResult = userinfoService.searchAndCount(search);
		List<YzyUserinfo> list = searchResult.getResult();
		if(searchResult == null){
			return null;
		}
		YzyUserinfo water = null;
		if(list==null || list.size()!=1){
			water=new YzyUserinfo();
			water.setMachineid(machine_id);
		}else{
			water = list.get(0);
		}
		return null;
	}
	
	public String getMachine_id() {
		return machine_id;
	}
	public void setMachine_id(String machine_id) {
		this.machine_id = machine_id;
	}
	public String getAppointmentdate() {
		return appointmentdate;
	}
	public void setAppointmentdate(String appointmentdate) {
		this.appointmentdate = appointmentdate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	
	
}
