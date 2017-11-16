package org.liufeng.course.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.liufeng.course.service.CoreService;
import org.liufeng.course.util.SignUtil;

/**
 * 请求处理的核心类
 * 
 * @author liufeng
 * @date 2013-09-29
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 4440739483644821986L;

	/**
	 * 请求校验（确认请求来自微信服务器）
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * 请求校验与处理
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		// 接收参数微信加密签名、 时间戳、随机数
		String j_username = request.getParameter("j_username");
		String j_password = request.getParameter("j_password");
//		response.encodeRedirectUrl("");
		System.out.println(j_username);
		response.sendRedirect("/wangzun/manager/main.action");
//		request.getRequestDispatcher("/WEB-INF/content/manager/main.jsp").forward(request, response);
	}
}
