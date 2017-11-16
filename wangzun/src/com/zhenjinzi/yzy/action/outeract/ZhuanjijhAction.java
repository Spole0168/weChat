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

/***
 * 装机人员激活功能
 * 暂时用这个处理用户激活功能  20160320
 * @author ly
 *
 */
public class ZhuanjijhAction extends BaseAction{
	public static Logger logger = Logger.getLogger(ZhuanjijhAction.class.getName());
	@Resource
	private UserinfoService userinfoService;
//	@Resource
//	private YinzyWatermechineService watermechineService;
	
	private String machine_id;
	private String zhuangji_id;
	private String name;
	private String tel;
	private String personid;
	private String address;
	private String tds_in;
	private String tds_out;
	private String pay;
	private String pay_by;
	private String code;
	
	
	@Override
	public String execute() throws Exception {
		return "input";
	}


	public String jihuo(){
		Timestamp stamp = new Timestamp(System.currentTimeMillis());
		String openid  = MySQLUtil.getOpenidByCode(code);
		
		System.out.println("UserjihuoAction code:"+code+"   openiddd:"+openid);
		logger.info("UserjihuoAction code:"+code+"  openid:"+openid);
		
		Search search = new Search();
		if(openid!=null&&!openid.trim().equals("")){
			search.addFilterEqual("openid", openid);
		}else if(machine_id!=null&&!machine_id.trim().equals("")){
		       search.addFilterEqual("machineid", machine_id);
		}
		
		YzyUserinfo user= null;
		
		SearchResult<YzyUserinfo> searchResult = userinfoService.searchAndCount(search);
		
		if(searchResult == null ){
			user=new YzyUserinfo();
			user.setCreate_Date(stamp);   
		}else{
			List<YzyUserinfo> list = searchResult.getResult();
			if(list==null || list.size()!=1){
				user=new YzyUserinfo();
				user.setCreate_Date(stamp);   
			}else{
				user = list.get(0);
			}
		}
		if(user==null){
			user = new YzyUserinfo();
			user.setId(getIdByUserInfoId());
		}
	
		user.setOpenid(openid);
		
		user.setContact_name(name);
		user.setCardno(personid);
		user.setInstak_address(address);
		user.setTelphone(tel);
		
		user.setText1(zhuangji_id);
		user.setText2(pay);
		user.setText3(pay_by);
		user.setMachineid(machine_id);
//		user.setUserInfo(user);
		user.setInputtds(tds_in);
		user.setOutputtds(tds_out);
		user.setActive_Date(stamp);
		user.setText5("1");
		user.setText4("365");/**默认的服务天数，可以修改，可删除*/
		boolean flag = userinfoService.save(user);
//		boolean flag1 = watermechineService.save(water);
		if(flag){
			Struts2Utils.renderJson("{\"statusCode\":\"200\",\"message\":\"装机人员激活成功!\"}");
		}else{
			Struts2Utils.renderJson("{\"statusCode\":\"300\",\"message\":\"装机人员激活失败!\"}");
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

	public String getZhuangji_id() {
		return zhuangji_id;
	}

	public void setZhuangji_id(String zhuangji_id) {
		this.zhuangji_id = zhuangji_id;
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

	public String getPay() {
		return pay;
	}

	public void setPay(String pay) {
		this.pay = pay;
	}

	public String getPay_by() {
		return pay_by;
	}

	public void setPay_by(String pay_by) {
		this.pay_by = pay_by;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}
	
	
}
