package com.zhenjinzi.yzy.action.outeract;

import java.sql.Timestamp;

import javax.annotation.Resource;

import com.fire.modules.web.struts2.Struts2Utils;
import com.zhenjinzi.yzy.action.BaseAction;
import com.zhenjinzi.yzy.model.YzyUserinstallapply;
import com.zhenjinzi.yzy.service.UserInstallApplyService;

/**
 * 对外提供,预约安装
 * @author ly
 *
 */
public class ApplyinstallouterAction extends BaseAction{

	@Resource
	private UserInstallApplyService installApplyService;
	
	private String name;
	private String tel;
	private String address;
	
	
	
	@Override
	public String execute() throws Exception {
		return "input";
	}

	public String install(){
//		vaildate();  ^[1][358][0-9]{9}$
		if(tel!=null&&tel.length()<10){
			Struts2Utils.renderJson("{\"statusCode\":\"300\",\"message\":\"请输入正确的手机号!\"}");
			return null;
		}
		if(address!=null && address.length()>500){
			Struts2Utils.renderJson("{\"statusCode\":\"300\",\"message\":\"您的地址太长了，能简短说明吗！!\"}");
			return null;
		}
		if(name!=null && name.length()>500){
			Struts2Utils.renderJson("{\"statusCode\":\"300\",\"message\":\"您的姓名太长了，能简短说明吗！!\"}");
			return null;
		}
		YzyUserinstallapply apply =new YzyUserinstallapply();
		apply.setInstak_address(address);
		apply.setTelphone(tel);
		apply.setUserName(name);
		Timestamp stamp=new Timestamp(System.currentTimeMillis());
		apply.setApply_Date(stamp);
		apply.setText5("1");
		boolean flag=installApplyService.save(apply);
		if(flag){
		    Struts2Utils.renderJson("{\"statusCode\":\"200\",\"message\":\"保存成功!\"}");
		}else{
			Struts2Utils.renderJson("{\"statusCode\":\"300\",\"message\":\"失败了!\"}");
		}
		return null;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

//	private String vaildate() {
//		return null;
//	}
	
}
