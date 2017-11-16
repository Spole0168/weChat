package com.zhenjinzi.yzy.action.user;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Controller;

import com.zhenjinzi.yzy.action.UserBaseAction;
import com.zhenjinzi.yzy.model.ZunmiUser;
import com.zhenjinzi.yzy.model.ZunmiUserLog;
import com.zhenjinzi.yzy.service.ZunmiUserLogService;

@Controller
@Results({
	@Result(name="accountIndex",location="/WEB-INF/content/user/accountpages/accountIndex.jsp")
})
public class UserCenterAction extends UserBaseAction{
	
	private static final long serialVersionUID = 1L;

	private ZunmiUser user;
	private ZunmiUserLog userLog;
	@Resource
	private ZunmiUserLogService userLogService;
	
	@Action(value="/user/accountIndex")
	public String accountIndex(){
		try {
			user = checkUser();
			userLog = userLogService.getLastLoginLog(user.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "accountIndex";
	}
	
	public ZunmiUser getUser() {
		return user;
	}

	public void setUser(ZunmiUser user) {
		this.user = user;
	}

	public ZunmiUserLog getUserLog() {
		return userLog;
	}

	public void setUserLog(ZunmiUserLog userLog) {
		this.userLog = userLog;
	}
}
