package com.zhenjinzi.yzy.action.manager;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.springframework.stereotype.Controller;

import com.zhenjinzi.yzy.action.BaseAction;
import com.zhenjinzi.yzy.model.ZunmiRole;
import com.zhenjinzi.yzy.service.ZunmiAdminService;
import com.zhenjinzi.yzy.service.ZunmiRoleService;

@Controller
public class RoleAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	@Resource
	private ZunmiRoleService roleService;
	
	@Resource
	private ZunmiAdminService adminService;

	@Override
	@Action(value="/manager/rolelist")
	public String execute() throws Exception {
		List<ZunmiRole> roleList = roleService.findAll();
		request.setAttribute("ROLE_LIST", roleList);
		return SUCCESS;
	}
	
	@Action(value="/manager/roleadd")
	public String add(){
		String roleName = request.getParameter("roleName");
		
		ZunmiRole role = new ZunmiRole();
		role.setRoleName(roleName);
		boolean b =roleService.save(role);
		if(!b)return "角色创建失败";
		return "创建成功";
	}
	

}
