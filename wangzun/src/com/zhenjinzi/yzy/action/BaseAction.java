package com.zhenjinzi.yzy.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements ServletContextAware,
		ServletResponseAware, ServletRequestAware, SessionAware {

	private static final long serialVersionUID = 1L;

	protected String contextPath;

	protected ServletContext servletContext;

	protected HttpServletRequest request;

	protected HttpServletResponse response;

	protected HttpSession httpSession;

	protected PrintWriter out;

	protected Map<String, Object> session;

	protected int pageNum = 1;// 当前页码,默认1
	protected int numPerPage = 20;// 页大小,默认10
	protected int totalCount = 0;

	@Override
	public void setServletContext(ServletContext context) {
		this.servletContext = context;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");  
   		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT,DELETE");  
   		response.setHeader("Access-Control-Allow-Credentials", "true");
   		response.setHeader("Access-Control-Max-Age", "3600");  
   		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");  
   		
		this.response = response;
		try {
			this.out = this.response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		this.httpSession = request.getSession();
		this.contextPath = request.getContextPath();
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	protected int firstResult() {
		return (pageNum - 1) * numPerPage;
	}

	protected int maxResult() {
		return pageNum * numPerPage;
	}

	protected void setCookie(String name, String value, int maxAge) {
		Cookie cookie = new Cookie(name, value == null ? "" : value);
		cookie.setMaxAge(maxAge);
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	protected Cookie getCookie(String name) {
		Cookie cookies[] = request.getCookies();
		if (cookies == null || name == null || name.length() == 0) {
			return null;
		}
		for (int i = 0; i < cookies.length; i++) {
			if (name.equals(cookies[i].getName())) {
				return cookies[i];
			}
		}
		return null;
	}

	protected void deleteCookie(Cookie cookie) {
		if (cookie != null) {
			cookie.setPath("/");
			cookie.setValue("");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
	}

	protected String getIpAddr() {
		String ipAddress = null;
		// ipAddress = this.getRequest().getRemoteAddr();
		ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null || ipAddress.length() == 0
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if (ipAddress.equals("127.0.0.1")) {
				// 根据网卡取本机配置的IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ipAddress = inet.getHostAddress();
			}

		}

		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
															// = 15
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	}
	public static void main(String[] args) {
		String str="https://open.weimob.com/oauth2/openid/authorize?client_id=4ddc3b8e9ab795be71cf1568bbee7959&redirect_uri=http%3A%2F%2Fweixin.h5garden.cn%2Fcenter%2F&response_type=code&scope=snsapi_base&state=12fggsdff1223";
		System.out.println(str.length());
	}
}
