package com.zhenjinzi.yzy.action.manager;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Controller;

import com.fire.modules.web.struts2.Struts2Utils;
import com.zhenjinzi.util.EncryptionHelper;
import com.zhenjinzi.yzy.action.BaseAction;
import com.zhenjinzi.yzy.model.YzyAdmin;
import com.zhenjinzi.yzy.service.ZunmiAdminService;

@Controller
@Results({
	@Result(name="success",location="/WEB-INF/content/manager/main.jsp",type="dispatcher"),
	@Result(name="error",location="/WEB-INF/content/manager/login.jsp",type="dispatcher")
})
public class UserLoginAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Resource
	private ZunmiAdminService adminService;
	
	public String login(){
		 String userName = request.getParameter("userName");
		 String password = request.getParameter("password");
		 if(userName==null||userName.trim().equals("")){
			 Struts2Utils.renderHtml("<script>parent.callback('300')</script>");
			 return "error";
		 }
		 if(password==null||userName.trim().equals("")){
			 Struts2Utils.renderHtml("<script>parent.callback('300')</script>");
			 return "error";
		 }
		 
		 YzyAdmin admin = adminService.findByName(userName);
		 if (admin == null) {
			 Struts2Utils.renderHtml("<script>parent.callback('300')</script>");
			 return "error";
		 }
		 if (!admin.getPassword().equals(EncryptionHelper.Enc(password,"SHA-1"))) {
			 Struts2Utils.renderHtml("<script>parent.callback('300')</script>");
			 return "error";
		 }
		 
		 httpSession.setAttribute("adminId", admin.getId());
		 httpSession.setAttribute("adminName",admin.getUserName());
		 admin.setCountOfLogin(admin.getCountOfLogin()+1);
		 adminService.save(admin);
		 Struts2Utils.renderHtml("<script>parent.callback('200')</script>");
		 return "success";
	}

}
