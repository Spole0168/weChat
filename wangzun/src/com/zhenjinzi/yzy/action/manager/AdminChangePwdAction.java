package com.zhenjinzi.yzy.action.manager;

import java.sql.Timestamp;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.fire.modules.web.struts2.Struts2Utils;
import com.zhenjinzi.util.EncryptionHelper;
import com.zhenjinzi.yzy.action.BaseAction;
import com.zhenjinzi.yzy.model.YzyAdmin;
import com.zhenjinzi.yzy.service.ZunmiAdminService;

@Controller
public class AdminChangePwdAction  extends BaseAction{
	private static final long serialVersionUID = 1L;

	@Resource
	private ZunmiAdminService adminService;
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	public String changePassword() {
		if (httpSession.getAttribute("adminId") == null) {
			Struts2Utils.renderJson("{\"statusCode\":\"301\"}");
			return null;
		}
		String oldPassword = request.getParameter("oldPassword");
		String newPassword1 = request.getParameter("newPassword1");
		String newPassword2 = request.getParameter("newPassword2");
		if (!newPassword1.equals(newPassword2)) {
			Struts2Utils.renderJson("{\"statusCode\":\"300\",\"message\":\"两次密码不一致!\"}");
			return null;
		}
		YzyAdmin admin = adminService.findById(Integer.parseInt(httpSession
				.getAttribute("adminId") + ""));
		if (!EncryptionHelper.Enc(oldPassword, "SHA-1").equals(
				admin.getPassword())) {
			Struts2Utils.renderJson("{\"statusCode\":\"300\",\"message\":\"原密码输入有误!\"}");
			return null;
		}
		admin.setPassword(EncryptionHelper.Enc(newPassword1, "SHA-1"));
		admin.setLastActivityDate(new Timestamp(System.currentTimeMillis()));
		try {
			adminService.save(admin);
			Struts2Utils.renderJson("{\"statusCode\":\"200\",\"message\":\"修改成功!\",\"callbackType\":\"closeCurrent\"}");
		} catch (Exception e) {
			e.printStackTrace();
			Struts2Utils.renderJson("{\"statusCode\":\"300\",\"message\":\"操作失败!\"}");
		}
		return null;
	}
}
