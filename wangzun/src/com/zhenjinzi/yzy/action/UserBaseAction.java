package com.zhenjinzi.yzy.action;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;

import com.fire.modules.web.struts2.Struts2Utils;
import com.zhenjinzi.yzy.model.ZunmiUser;
import com.zhenjinzi.yzy.service.ZunmiUserService;

public class UserBaseAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	
	@Resource
	private ZunmiUserService userService;
	
	/**
	 * 校验用户登录权限
	 * @return 当前用户
	 * @throws UnsupportedEncodingException 
	 */
	public ZunmiUser checkUser(){
		Cookie userName = getCookie("UserName");
		Cookie userInfo = getCookie("UserInfo");
		ZunmiUser user = null;
		try {
			if(userName==null||userInfo==null){
				deleteCookie(userName);
				deleteCookie(userInfo);
			}else{
				user = userService.validCheckCode(userName.getValue(),userInfo.getValue());
			}
		} catch (UnsupportedEncodingException e) {
		}
		return user;
	}
}
