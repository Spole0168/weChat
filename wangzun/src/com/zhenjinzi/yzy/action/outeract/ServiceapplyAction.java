package com.zhenjinzi.yzy.action.outeract;

import java.sql.Timestamp;

import javax.annotation.Resource;

import com.fire.modules.web.struts2.Struts2Utils;
import com.zhenjinzi.yzy.action.BaseAction;
import com.zhenjinzi.yzy.model.YzyServiceApply;
import com.zhenjinzi.yzy.service.ServiceApplyService;

/***
 * 服务商申请的action
 * @author ly
 *
 */
public class ServiceapplyAction extends BaseAction{
	
	@Resource
	private ServiceApplyService applyService;
	
	private String  shequname; //申请社区名称
	private String address ;   //社区地址
	private String yezhuNums  ; //社区业主数量
	private String ruzhulv ;  //入住率
	private String watersource ;  //居民直饮水主要来源
	private String machineNums ;  //本社区收水机数量
	private String waterprice  ; //直饮水价格
	private String waterquality ;  //本社区水质状况
	private String tds     ;   //TDS值   
	private String others ;  //其他
	private String unitname  ;//申请单位资质
	private String  businessproject ; //主要经营项目
	private String customerresources  ;//主要客户资源
	private String employeesNums  ; //员工人数
	private String unitcontacts   ;//申请单位联系人
	private String  training     ;  //15 能否经过培训，实现安装
	private String tel      ; //电话
	private String mailbox ;  //邮箱
	private String sqname;   //申请人
	private String date     ; //日期
	
	public  String save() {
		YzyServiceApply service=new YzyServiceApply();
		service.setCommunityName(shequname);//	申请社区名称	varchar(500)
		service.setCommunityAddress(address);//	地址	varchar(300)
		try{
			int yezhun = Integer.parseInt(yezhuNums);
			service.setCommunitPersonNum(yezhun);//社区业主数量	int(11)unsigned
		}catch(Exception e){
			e.printStackTrace();
			service.setCommunitPersonNum(-1);//社区业主数量	int(11)unsigned
		}
		try{
			int machineNum = Integer.parseInt(machineNums);
			service.setMachineNum(machineNum);//	社区直饮水机数量	int(11)unsigned
		}catch(Exception e){
			e.printStackTrace();
			service.setMachineNum(-1);//	社区直饮水机数量	int(11)unsigned
		}	
		try{
			int applyStaffnum = Integer.parseInt(employeesNums);
			service.setApplyStaffnum(applyStaffnum);//	员工人数	int(11)unsigned
		}catch(Exception e){
			e.printStackTrace();
			service.setApplyStaffnum(-1);//	员工人数	int(11)unsigned
		}	
	
		service.setCommunitRate(ruzhulv);//入住率	varchar(30)
		service.setWaterSource(watersource);//居民直饮水主要来源	varchar(300)
		service.setMechinePrice(waterprice);//直饮水价格
		service.setWaterQuality(waterquality);// 	本社区水质情况	varchar(300)
		service.setWaterTDS(tds); //TDS值   
		service.setOther(others);	//其他	varchar(300)
		
		service.setApplyBusineeslic(unitname);//申请单位资质//	申请者有无营业执照	varchar(30)
		service.setApplyProject(businessproject);//主要经营项目
		service.setApplyContactsource(customerresources);//	申请者主要客户资源	varchar(500)
		
//		service.setApplyOrgName("");//private String applyOrgName;//	申请者单位姓名	varchar(300)
		
		
		service.setApplyOrgPerson(unitcontacts);//申请人单位联系人	varchar(100)
		service.setIsIstallOK(training);;//经过培训是否可以完成安装服务	varchar(30)
		service.setText5("1");//		20160313统一用来做 逻辑删除   1 代表正常   2代表删除
		service.setApplyTelphone(tel);//	电话	varchar(40)
		service.setApplyEmail(mailbox);;////邮箱
		service.setApplyeName(sqname); //申请人
		service.setApplyDate(new Timestamp(System.currentTimeMillis()));
		service.setText1(date);;//text1;//		varchar(300);
		boolean flag= applyService.save(service);
		if(flag){
		    Struts2Utils.renderJson("{\"statusCode\":\"200\",\"message\":\"保存成功!\"}");
		}else{
			Struts2Utils.renderJson("{\"statusCode\":\"300\",\"message\":\"修改成功!\"}");
		}
		return null;
	}
	
	
	public String getShequname() {
		return shequname;
	}
	public void setShequname(String shequname) {
		this.shequname = shequname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getYezhuNums() {
		return yezhuNums;
	}
	public void setYezhuNums(String yezhuNums) {
		this.yezhuNums = yezhuNums;
	}
	public String getRuzhulv() {
		return ruzhulv;
	}
	public void setRuzhulv(String ruzhulv) {
		this.ruzhulv = ruzhulv;
	}
	public String getWatersource() {
		return watersource;
	}
	public void setWatersource(String watersource) {
		this.watersource = watersource;
	}
	public String getMachineNums() {
		return machineNums;
	}
	public void setMachineNums(String machineNums) {
		this.machineNums = machineNums;
	}
	public String getWaterprice() {
		return waterprice;
	}
	public void setWaterprice(String waterprice) {
		this.waterprice = waterprice;
	}
	public String getWaterquality() {
		return waterquality;
	}
	public void setWaterquality(String waterquality) {
		this.waterquality = waterquality;
	}
	public String getTds() {
		return tds;
	}
	public void setTds(String tds) {
		this.tds = tds;
	}
	public String getOthers() {
		return others;
	}
	public void setOthers(String others) {
		this.others = others;
	}
	public String getUnitname() {
		return unitname;
	}
	public void setUnitname(String unitname) {
		this.unitname = unitname;
	}
	public String getBusinessproject() {
		return businessproject;
	}
	public void setBusinessproject(String businessproject) {
		this.businessproject = businessproject;
	}
	public String getCustomerresources() {
		return customerresources;
	}
	public void setCustomerresources(String customerresources) {
		this.customerresources = customerresources;
	}
	public String getEmployeesNums() {
		return employeesNums;
	}
	public void setEmployeesNums(String employeesNums) {
		this.employeesNums = employeesNums;
	}
	public String getUnitcontacts() {
		return unitcontacts;
	}
	public void setUnitcontacts(String unitcontacts) {
		this.unitcontacts = unitcontacts;
	}
	public String getTraining() {
		return training;
	}
	public void setTraining(String training) {
		this.training = training;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getMailbox() {
		return mailbox;
	}
	public void setMailbox(String mailbox) {
		this.mailbox = mailbox;
	}
	public String getSqname() {
		return sqname;
	}
	public void setSqname(String sqname) {
		this.sqname = sqname;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
	
	
}
