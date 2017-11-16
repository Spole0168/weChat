package com.shang.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shang.utils.SignUtil;
import com.shang.utils.WechatProcess;


/**
 * Servlet implementation class weChat
 */
@WebServlet("/weChat")
public class weChat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public weChat() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");  
        PrintWriter out = response.getWriter();  
          
        //获取属性:微信加密签名|时间戳|随机数|随机字符串  
        String signature = request.getParameter("signature");  
        String timestamp = request.getParameter("timestamp");  
        String nonce = request.getParameter("nonce");  
        String echostr = request.getParameter("echostr");  
		if (SignUtil.checkSignature(signature, timestamp, nonce)) {  
			out.print(echostr);  
        } 
//		else {  
//            System.out.println("不是微信服务器发来的请求,请小心!");  
//            return false;
//        }  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response);
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		/** 读取接收到的xml消息 */
		StringBuffer sb = new StringBuffer();
		InputStream is = request.getInputStream();
		InputStreamReader isr = new InputStreamReader(is, "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		String s = "";
		while ((s = br.readLine()) != null) {
			sb.append(s);
		}
		String xml = sb.toString();	//次即为接收到微信端发送过来的xml数据

		String result = "";
		/** 判断是否是微信接入激活验证，只有首次接入验证时才会收到echostr参数，
		 * 此时需要把它直接返回 */
		String echostr = request.getParameter("echostr");
		if (echostr != null && echostr.length() > 1) {
			result = echostr;
		} else {
			//正常的微信处理流程
			result = new WechatProcess().processWechatMag(xml);
		}
		try {
			OutputStream os = response.getOutputStream();
			os.write(result.getBytes("UTF-8"));
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
