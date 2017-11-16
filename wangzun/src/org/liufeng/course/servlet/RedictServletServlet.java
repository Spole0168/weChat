package org.liufeng.course.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.liufeng.course.util.MySQLUtil;

/***
 * 用作地址跳转
 * @author ly
 *
 */
public class RedictServletServlet extends HttpServlet {

	@Override
	public void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		String flag             =  req.getParameter("f");
		String mechineid  = req.getParameter("m");
		if(isNotNull(flag)){
			String urlPath=MySQLUtil.getUrlpathByFlag(flag);
			System.out.println("urlPath:"+flag+":urlPath"+urlPath+" m:"+mechineid);
			if(isNotNull(mechineid)){
				res.sendRedirect(urlPath+mechineid);
			}else{
				res.sendRedirect(urlPath);
			}
		}
	}
	/**
	 * 如果为非空的传回true
	 * @return
	 */
    private boolean isNotNull(String temp){
    	if(temp==null){
    		return false;
    	}
    	if(temp.trim().equals("")){
    		return false;
    	}
    	return true;
    }
	@Override
	public void init() throws ServletException {
		super.init();
	}

	
}
