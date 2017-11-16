package com.zhenjinzi.yzy.action.manager;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.slf4j.LoggerFactory;

import com.zhenjinzi.yzy.action.BaseAction;

@Results({
	@Result(name="login",location="/WEB-INF/content/manager/login.jsp")
})
public class MainAction extends BaseAction{

	private static Logger log = Logger.getLogger(MainAction.class);
	private static final long serialVersionUID = 1L;

	@Override
	public String execute() throws Exception {
		System.out.println("successdddddd");
		log.info("successdddddd        MainAction");
		Integer adminId = (Integer) httpSession.getAttribute("adminId");
		if(adminId==null){
			log.info("用户过期，重新登陆");
			System.out.println("用户过期，重新登陆！");
			return "login";
		}
		return SUCCESS;
	}
}
