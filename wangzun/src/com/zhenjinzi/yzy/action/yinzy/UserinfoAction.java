package com.zhenjinzi.yzy.action.yinzy;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.fire.modules.web.struts2.Struts2Utils;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.zhenjinzi.yzy.action.BaseAction;
import com.zhenjinzi.yzy.model.YzyServiceApply;
import com.zhenjinzi.yzy.model.YzyUserinfo;
import com.zhenjinzi.yzy.model.YzyUserinstallapply;
//import com.zhenjinzi.yzy.model.YzyWatermachine;
import com.zhenjinzi.yzy.service.UserinfoService;
//import com.zhenjinzi.yzy.service.YinzyWatermechineService;

/**
 * 查询用户信息
 * @author ly
 *
 */
public class UserinfoAction  extends BaseAction{

//	@Resource
//	private YinzyWatermechineService watermechineService;
	@Resource
	private UserinfoService userinfoService;
	
	private List<YzyUserinfo> list;
	
	private YzyUserinfo userInfo;
	
	private Integer serviceId;
	//用户修改积分  
	private String type;//
	private String jifen;
    //列表页的四个查询项
	private String  contact_name;
    private Timestamp   creationDate;
    private String    mechineid;
    private String  tel;
    
	@Override
	public String execute() throws Exception {
		
		SearchResult<YzyUserinfo> searchResult = userinfoService.findEnable(firstResult(),maxResult());
		if(searchResult == null){
			return "list";
		}
		List<YzyUserinfo> listNew=searchResult.getResult();
		list = new ArrayList<YzyUserinfo>();
		for (YzyUserinfo user : listNew) {
			int days=365;
			if(user.getText4()!=null){
				days = Integer.parseInt(user.getText4());
			}
			if(user.getActive_Date()!=null){
				days = (int) (days - (System.currentTimeMillis()-user.getActive_Date().getTime())/(24*60*60*1000));
			}
			user.setText6(days+"");/**计算出服务天数*/
			list.add(user);
		}
		totalCount = searchResult.getTotalCount();
		return "list";
	}
	public String list() throws Exception {
		list = userinfoService.findAll();
		return "list";
	}
	
	public String del() {
		if(serviceId!=null){
			userInfo = userinfoService.findById(serviceId);
			try {
				userInfo.setText5("2");
				userinfoService.save(userInfo);
				Struts2Utils.renderJson("{\"statusCode\":\"200\",\"message\":\"删除成功!\",\"navTabId\":\"userinfo-list\"}");
				return null;
			} catch (Exception e) {
				e.printStackTrace();
				Struts2Utils.renderJson("{\"statusCode\":\"300\",\"message\":\"操作失败!\"}");
			}
		}
		return null;
	}
	
	public String save() {
//		if (httpSession.getAttribute("adminId") == null) {
//			Struts2Utils.renderJson("{\"statusCode\":\"301\"}");
//			return null;
//		}
		YzyUserinfo userinfoNew = userinfoService.findById(userInfo.getId());
		try {
			if(userinfoNew!=null){
			userinfoNew.setMachineid(userInfo.getMachineid());
			userinfoNew.setText1(userInfo.getText1());//装机人员id
			userinfoNew.setContact_name(userInfo.getContact_name());//名称
			userinfoNew.setTelphone(userInfo.getTelphone());//电话
			userinfoNew.setCardno(userInfo.getCardno());//身份证号
			userinfoNew.setInstak_address(userInfo.getInstak_address());//地址
			userinfoNew.setInputtds(userInfo.getInputtds());//tds_in
			userinfoNew.setOutputtds(userInfo.getOutputtds());//tds_out
			userinfoNew.setText2(userInfo.getText2());//付款金额
			userinfoNew.setText3(userInfo.getText3());//付款方式
			userinfoService.save(userinfoNew);
			}
			Struts2Utils.renderJson("{\"statusCode\":\"200\",\"message\":\"修改成功!\",\"callbackType\":\"closeCurrent\",\"navTabId\":\"userinfo-list\"}");
			} catch (Exception e) {
			e.printStackTrace();
			Struts2Utils.renderJson("{\"statusCode\":\"300\",\"message\":\"操作失败!\"}");
		}
		return null;
	}
	
	public String search(){
		Search search = new Search();
		if(contact_name != null && contact_name.length()>0){
			search.addFilterLike("contact_name", "%"+contact_name+"%");
		}
		if(creationDate!= null ){
				long creationD = creationDate.getTime();
				long onedays = 24*60*60*1000;
				Timestamp creationDtimeafter= new Timestamp(creationD+onedays);
				Timestamp creationDtimebefore= new Timestamp(creationD-onedays);
				search.addFilterGreaterOrEqual("active_Date", creationDtimebefore);
				search.addFilterLessOrEqual("active_Date", creationDtimeafter);
		}
		if(mechineid!=null && mechineid.trim().length()>0){
			search.addFilterLike("machineid", "%"+mechineid+"%");
		}
		if(tel!=null && tel.length()>0){
//			search.addFilterEqual("telphone", tel);
			search.addFilterLike("telphone", "%"+tel+"%");
		}
		search.addFilterEqual("text5", "1");
		search.setFirstResult((pageNum-1)*numPerPage);
		search.setMaxResults(pageNum*numPerPage);
		search.addSortDesc("create_Date");//根据激活时间降序排列
		SearchResult<YzyUserinfo> searchResult = userinfoService.searchAndCount(search);
		List<YzyUserinfo> listNew=searchResult.getResult();
		
		list = new ArrayList<YzyUserinfo>();
		for (YzyUserinfo user : listNew) {
			int days=365;
			if(user.getText4()!=null){
				days = Integer.parseInt(user.getText4());
			}
			if(user.getActive_Date()!=null){
				days = (int) (days - (System.currentTimeMillis()-user.getActive_Date().getTime())/(24*60*60*1000));
			}
			user.setText6(days+"");/**计算出服务天数*/
			list.add(user);
		}
		
		totalCount = searchResult.getTotalCount();
		return "list";
	}
	
	public String input(){
	if(serviceId>0){userInfo = userinfoService.findById(serviceId);}
	return INPUT;
}

public String show(){
	if(serviceId>0){userInfo = userinfoService.findById(serviceId);}
	return "show";
}
public String update(){
	if(serviceId>0){userInfo = userinfoService.findById(serviceId);}
	return "update";
}
/***
 * 
 * @return
 */
public String updatejifen() {
//	private String type;
//	private String jifen;
	if(userInfo!=null){
		userInfo = userinfoService.findById(userInfo.getId());
	}
	String yuanjifen = userInfo.getText4();
	int yjf = 365;
	try{
		 yjf= Integer.parseInt(yuanjifen);
	}catch(Exception e){	}
	try{
		int jf = Integer.parseInt(jifen);
		if(type==null||type.equals("2")){
			jf=yjf+jf;
		}else{
			jf= yjf-jf;
		}
		userInfo.setText4(jf+"");
	}catch(Exception e){
		Struts2Utils.renderJson("{\"statusCode\":\"300\",\"message\":\"积分不是合法的整数！!\"}");
		return null;
	}
	try {
		userinfoService.save(userInfo);
		Struts2Utils.renderJson("{\"statusCode\":\"200\",\"message\":\"删除成功!\",\"callbackType\":\"closeCurrent\",\"navTabId\":\"userinfo-list\"}");
	} catch (Exception e) {
		e.printStackTrace();
		Struts2Utils.renderJson("{\"statusCode\":\"300\",\"message\":\"操作失败!\"}");
	}
	return null;
}
	public List<YzyUserinfo> getList() {
		return list;
	}
	public void setList(List<YzyUserinfo> list) {
		this.list = list;
	}
	public Integer getServiceId() {
		return serviceId;
	}
	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}
	public YzyUserinfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(YzyUserinfo userInfo) {
		this.userInfo = userInfo;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getJifen() {
		return jifen;
	}
	public void setJifen(String jifen) {
		this.jifen = jifen;
	}
	public String getContact_name() {
		return contact_name;
	}
	public void setContact_name(String contact_name) {
		this.contact_name = contact_name;
	}
	public Timestamp getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}
	public String getMechineid() {
		return mechineid;
	}
	public void setMechineid(String mechineid) {
		this.mechineid = mechineid;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
}
