package org.liufeng.course.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.zhenjinzi.yzy.action.outeract.ShowwaterinfoAction;

/**
 * 网上查询到的跨域访问的问题哦
 * @author ly
 *
 */
public class SimpleCorsFilter implements Filter{
	public static Logger logger = Logger.getLogger(SimpleCorsFilter.class.getName());
	@Override
	public void destroy() {
		System.out.println("SimpleCorsFilter destroy");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		      HttpServletResponse response = (HttpServletResponse) res;  
//		   		response.setHeader("Access-Control-Allow-Origin", DictionaryParam.get("DICTIONARY_GROUP_GLOBAL_SETTING", "AccessControlAllowOrigin"));
		        response.setHeader("Access-Control-Allow-Origin", "*");  
		   		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT,DELETE");  
		   		response.setHeader("Access-Control-Allow-Credentials", "true");
		   		response.setHeader("Access-Control-Max-Age", "3600");  
		   		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");  
				System.out.println("SimpleCorsFilter doFilter");
				logger.info("logger   SimpleCorsFilter  doFilter");
		   		chain.doFilter(req, res);  

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("SimpleCorsFilter init");
	}

}
