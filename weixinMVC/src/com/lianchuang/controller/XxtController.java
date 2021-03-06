package com.lianchuang.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lianchuang.entity.WeChat;
import com.lianchuang.service.CoreService;
import com.lianchuang.util.SignUtil;

@Controller
@RequestMapping("/xxt")
public class XxtController {
	
	@RequestMapping(value="/api",method = RequestMethod.GET)
	@ResponseBody
	public String xxtInterface(WeChat wc){
		System.out.println("/api");
		String signature = wc.getSignature(); // 微信加密签名  
        String timestamp = wc.getTimestamp(); // 时间戳  
        String nonce = wc.getNonce();// 随机数  
        String echostr = wc.getEchostr();// 随机字符串  
  
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败  
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {  
            return echostr;  
        } else {  
            System.out.println("不是微信服务器发来的请求,请小心!");  
            return null;
        }  
	}
	
	@RequestMapping(value="/api",method = RequestMethod.POST)
	@ResponseBody
	public String getWeiXinMessage(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		// 将请求、响应的编码均设置为UTF-8（防止中文乱码）  
        request.setCharacterEncoding("UTF-8");  //微信服务器POST消息时用的是UTF-8编码，在接收时也要用同样的编码，否则中文会乱码；
        response.setCharacterEncoding("UTF-8"); //在响应消息（回复消息给用户）时，也将编码方式设置为UTF-8，原理同上；
        //初始化配置文件
        String respMessage = CoreService.processRequest(request);//调用CoreService类的processRequest方法接收、处理消息，并得到处理结果；
        
        // 响应消息  
        //调用response.getWriter().write()方法将消息的处理结果返回给用户
        return respMessage;
	}
	
	
	
	public static void main(String[] args) {
		
		String signature = "E56004E954970049C37E8E3413432A7BB17D1A93"; // 微信加密签名  
        String timestamp = "12131"; // 时间戳  
        String nonce = "32432";// 随机数  
        String echostr = "fddsads";// 随机字符串  
  
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败  
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {  
        	System.out.println("接入成功");
        } else {  
            System.out.println("不是微信服务器发来的请求,请小心!");  
        }  
	}
}
