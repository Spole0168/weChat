package com.zhenjinzi.yzy.action.manager;

import java.io.IOException;
import java.sql.Timestamp;

import javax.annotation.Resource;








import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Controller;

import com.fire.modules.web.struts2.Struts2Utils;
import com.zhenjinzi.util.EncryptionHelper;
import com.zhenjinzi.yzy.action.BaseAction;
import com.zhenjinzi.yzy.model.YzyAdmin;
import com.zhenjinzi.yzy.model.enums.AdminStatus;
import com.zhenjinzi.yzy.service.ZunmiAdminService;

@Results({
	@Result(name="domainGroupList",location="/WEB-INF/content/user/accountpages/domainGroupManager.jsp"),
	@Result(name="domainUpdate",location="/WEB-INF/content/user/accountpages/domainsUpdateGroup.jsp")
})
public class LoginAction extends BaseAction{

	private static final long serialVersionUID = 1L;

	@Resource
	private ZunmiAdminService adminService;
	
	public String execute(){
		System.out.println("loginAction   aaa");
		return SUCCESS;
	}
	
	public String login(){
		 String userName = request.getParameter("j_username");
		 String password = request.getParameter("j_password");
		 if(userName==null||userName.trim().equals("")){
			 Struts2Utils.renderJson("{\"statusCode\":\"300\",\"message\":\"用户名必须输入!\"}");
//			 Struts2Utils.renderHtml("<script>parent.callback('300')</script>");
			 return null;
		 }
		 if(password==null||password.trim().equals("")){
			 Struts2Utils.renderJson("{\"statusCode\":\"300\",\"message\":\"密码必须输入!\"}");
			 return null;
		 }
		 
		 YzyAdmin admin = adminService.findByName(userName);
		 if (admin == null) {
			 Struts2Utils.renderJson("{\"statusCode\":\"300\",\"message\":\"密码错误或用户不存在!\"}");
//			 Struts2Utils.renderHtml("<script>parent.callback('300')</script>");
			 return null;
		 }
		 if (!admin.getPassword().equals(EncryptionHelper.Enc(password,"SHA-1"))) {
			 Struts2Utils.renderJson("{\"statusCode\":\"300\",\"message\":\"用户不存在或是密码错误!\"}");
//			 Struts2Utils.renderHtml("<script>parent.callback('300')</script>");
			 return null;
		 }
		 
		 httpSession.setAttribute("adminId", admin.getId());
		 httpSession.setAttribute("adminName",admin.getUserName());
		 admin.setCountOfLogin(admin.getCountOfLogin()+1);
		 admin.setStatus(AdminStatus.NOMAL);
		 admin.setLastActivityDate(new Timestamp(System.currentTimeMillis()));
		 boolean f= adminService.save(admin);
		 System.out.println(f);
//		 Struts2Utils.renderHtml("<script>parent.callback('200')</script>");
		 Struts2Utils.renderJson("{\"statusCode\":\"200\",\"message\":\"登陆成功!\"}");
		 return null;
	}
	
	public void logoff() throws IOException {
		httpSession.removeAttribute("adminId");
		httpSession.removeAttribute("adminName");
		response.sendRedirect("login.action");
	}
	
	public static void main(String[] args) {
//		5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8
		System.out.println(EncryptionHelper.Enc("password","SHA-1"));
	}
}
