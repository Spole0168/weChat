package com.zhenjinzi.yzy.action.auth;

import java.io.IOException;

import org.apache.struts2.convention.annotation.Action;

import com.zhenjinzi.yzy.action.BaseAction;

public class QqAction extends BaseAction{

	private static final long serialVersionUID = 1L;

	@Action(value="/auth/qq")
	public void qq(){
		try {
			response.sendRedirect("https://graph.qq.com/oauth2.0/authorize");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
