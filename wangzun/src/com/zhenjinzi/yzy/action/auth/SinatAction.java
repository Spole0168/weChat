package com.zhenjinzi.yzy.action.auth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.struts2.convention.annotation.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import weibo4j.Oauth;
import weibo4j.model.WeiboException;
import weibo4j.util.BareBonesBrowserLaunch;

import com.zhenjinzi.yzy.action.BaseAction;

public class SinatAction extends BaseAction{
	
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger(SinatAction.class);
	
	@Action(value="/auth/sinat")
	public void sinat() throws WeiboException,IOException{
		Oauth oauth = new Oauth();
		BareBonesBrowserLaunch.openURL(oauth.authorize("code"));
		System.out.print("Hit enter when it's done.[Enter]:");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String code = br.readLine();
		logger.info("code: " + code);
		try {
			System.out.println(oauth.getAccessTokenByCode(code));
		} catch (WeiboException e) {
			if (401 == e.getStatusCode()) {
				logger.info("Unable to get the access token.");
			} else {
				e.printStackTrace();
			}
		}
	}
}
