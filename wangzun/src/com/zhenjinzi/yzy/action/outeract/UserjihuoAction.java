package com.zhenjinzi.yzy.action.outeract;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.liufeng.course.util.MySQLUtil;

import com.fire.modules.web.struts2.Struts2Utils;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.googlecode.genericdao.search.Sort;
import com.zhenjinzi.yzy.action.BaseAction;
import com.zhenjinzi.yzy.model.YzyUserinfo;
//import com.zhenjinzi.yzy.model.YzyWatermachine;
import com.zhenjinzi.yzy.service.UserinfoService;
//import com.zhenjinzi.yzy.service.YinzyWatermechineService;

public class UserjihuoAction extends BaseAction{
	public static Logger logger = Logger.getLogger(UserjihuoAction.class.getName());
	@Resource
	private UserinfoService userinfoService;
	
//	@Resource
//	private YinzyWatermechineService watermechineService;
//	
	private String machine_id;
	private String name;
	private String tel;
	private String personid;
	private String address;
	private String tds_in;
	private String tds_out;
	private String code;
	/**
	 * 用户激活功能
	 * @return
	 */
	public String jihuo(){
		Integer userid=getIdByUserInfoId();  /**主键*/
		Timestamp stamp = new Timestamp(System.currentTimeMillis());
	
		String openid  = MySQLUtil.getOpenidByCode(code);
		System.out.println("UserjihuoAction code:"+code+"   openiddd:"+openid);
		logger.info("UserjihuoAction code:"+code+"  openid:"+openid);
		YzyUserinfo user = new YzyUserinfo();
		user.setId(userid);
		user.setCardno(personid);
		user.setContact_name(name);
		user.setOpenid(openid);                     //TODO
		user.setInstak_address(address);
		
//		YzyWatermachine waterMachine = new YzyWatermachine();
//		waterMachine.setId(userid);
//		user.setUserInfo(user);
		user.setInputtds(tds_in);
		user.setOutputtds(tds_out);
		
		user.setCreate_Date(stamp);  /**用户装机日期*/
		user.setMachineid(machine_id);
//		waterM
		boolean flag = userinfoService.save(user);
//		boolean flag1 = watermechineService.save(waterMachine);
		if(flag){
			Struts2Utils.renderJson("{\"statusCode\":\"200\",\"message\":\"激活成功!\"}");
		}else{
			Struts2Utils.renderJson("{\"statusCode\":\"300\",\"message\":\"激活失败!\"}");
		}
		return null;
	}
	
	/**
	 * 自动获得主键的功能
	 * @return
	 */
	private Integer getIdByUserInfoId(){
		Search search = new Search();
		Sort sort=new Sort();
		sort.setProperty("id");
		sort.setDesc(true);
		search.addSort(sort);
		SearchResult<YzyUserinfo> searchResult = userinfoService.searchAndCount(search);
		if(searchResult == null){
			return new Integer(1);
		}
		List<YzyUserinfo> list = searchResult.getResult();
		if(list == null ||list.size()==0){
			return new Integer(1);
		}
		return list.get(0).getId()+1;
	}
	
	
	
	
	
	public String getMachine_id() {
		return machine_id;
	}
	public void setMachine_id(String machine_id) {
		this.machine_id = machine_id;
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
	public String getPersonid() {
		return personid;
	}
	public void setPersonid(String personid) {
		this.personid = personid;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTds_in() {
		return tds_in;
	}
	public void setTds_in(String tds_in) {
		this.tds_in = tds_in;
	}
	public String getTds_out() {
		return tds_out;
	}
	public void setTds_out(String tds_out) {
		this.tds_out = tds_out;
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
}
