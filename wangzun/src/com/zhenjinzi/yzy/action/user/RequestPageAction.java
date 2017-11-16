package com.zhenjinzi.yzy.action.user;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.zhenjinzi.yzy.action.UserBaseAction;

@Results({
	@Result(name="addDomainSecond",location="/WEB-INF/content/user/accountpages/addDomainSecond.jsp"),
})
public class RequestPageAction extends UserBaseAction{
	
	private static final long serialVersionUID = 1L;
	
	@Action(value="/user/addDomainSecond")
	public String addDomainSecond(){
		return "addDomainSecond";
	}

}
