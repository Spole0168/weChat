package com.zhenjinzi.yzy.action.manager;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.springframework.stereotype.Controller;

import com.fire.modules.web.struts2.Struts2Utils;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.zhenjinzi.util.EncryptionHelper;
import com.zhenjinzi.util.RandomController;
import com.zhenjinzi.yzy.action.BaseAction;
import com.zhenjinzi.yzy.model.YzyAdmin;
import com.zhenjinzi.yzy.model.enums.AdminStatus;
import com.zhenjinzi.yzy.service.ZunmiAdminService;


@Controller
public class AdminAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Resource
	private ZunmiAdminService adminService;

	private List<YzyAdmin> list;
	private YzyAdmin admin;
	private Integer adminId;
	
	@Override
	public String execute() {
		try {
			SearchResult<YzyAdmin> searchResult = adminService.findEnable(firstResult(),maxResult());
			list = searchResult.getResult();
			totalCount = searchResult.getTotalCount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}

	public String list() throws Exception {
		list = adminService.findAll();
		return "list";
	}

	@Action(value = "/manager/resetadminpassword")
	public String resetPassword() {
		int adminId = Integer.parseInt(request
				.getParameter("adminId"));
		YzyAdmin admin = adminService.findById(adminId);
		String newPassword = RandomController.getCharacterAndNumber(6);
		admin.setPassword(EncryptionHelper.Enc(newPassword, "SHA-1"));
		boolean b = adminService.save(admin);
		if (!b) {
			return "重置密码失败";
		}
		request.setAttribute("ADMIN_PASSWORD", newPassword);
		return SUCCESS;
	}

	public String input(){
		if(adminId!=null&&adminId>0){admin = adminService.findById(adminId);}
		return INPUT;
	}

	public String save() {
		String password2 = request.getParameter("password2");
		if (!admin.getPassword().equals(password2)) {
			Struts2Utils.renderJson("{\"statusCode\":\"300\",\"message\":\"两次密码不一致!\"}");
		}
		admin.setPassword(EncryptionHelper.Enc(admin.getPassword(), "SHA-1"));
		admin.setCreationDate(new Timestamp(System.currentTimeMillis()));
		admin.setCreatorUserName(request.getSession().getAttribute(
				"adminName")
				+ "");
		admin.setCountOfLogin(0);
		admin.setStatus(AdminStatus.NOMAL);
		try {
			adminService.save(admin);
			Struts2Utils.renderJson("{\"statusCode\":\"200\",\"message\":\"操作成功\",\"callbackType\":\"closeCurrent\",\"navTabId\":\"admin_list\"}");
		} catch (Exception e) {
			Struts2Utils.renderJson("{\"statusCode\":\"300\",\"message\":\"添加失败!\"}");
		}
		return null;
	}
	
	public String del(){
		YzyAdmin admin = adminService.findById(adminId);
		admin.setStatus(AdminStatus.DISABLED);
		
		try {
			adminService.save(admin);
			Struts2Utils.renderJson("{\"statusCode\":\"200\",\"message\":\"删除成功!\",\"navTabId\":\"admin_list\"}");
		} catch (Exception e) {
			Struts2Utils.renderJson("{\"statusCode\":\"300\",\"message\":\"删除失败!\"}");
		}
		return null;
	}

	public String search(){
		Search search = new Search();
		if(admin.getUserName()!=null&&admin.getUserName().length()>0){
			search.addFilterLike("userName", "%"+admin.getUserName()+"%");
		}
		if(admin.getStatus()!=null){
			search.addFilterEqual("status", admin.getStatus());
		}
		if(admin.getCreationDate()!=null){
			//search.addFilter();
		}
		search.addFilterNotEqual("status", "DISABLED");
		search.setFirstResult((pageNum-1)*numPerPage);
		search.setMaxResults(pageNum*numPerPage);
		SearchResult<YzyAdmin> searchResult = adminService.searchAndCount(search);
		list = searchResult.getResult();
		totalCount = searchResult.getTotalCount();
		return "list";
	}
	
	public List<YzyAdmin> getList() {
		return list;
	}

	public void setList(List<YzyAdmin> list) {
		this.list = list;
	}
	
	public YzyAdmin getAdmin() {
		return admin;
	}

	public void setAdmin(YzyAdmin admin) {
		this.admin = admin;
	}



	public Integer getAdminId() {
		return adminId;
	}



	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	protected void prepareModel() throws Exception {
		if (adminId != null) {
			admin = adminService.findById(adminId);
		} else {
			admin = new YzyAdmin();
		}
	}
}
