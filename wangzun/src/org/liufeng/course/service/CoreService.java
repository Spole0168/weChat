package org.liufeng.course.service;

import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.liufeng.course.message.resp.TextMessage;
import org.liufeng.course.util.MessageUtil;

/**
 * 核心服务类
 * 
 * @author liufeng
 * @date 2013-09-29
 */
public class CoreService {
	private static String simpleMsg="应该发送文本消息哦！发送的信息格式为：学校名称#复印张数，例如：XXX理工大学#14。";
	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return xml
	 */
	public static String processRequest(HttpServletRequest request) {
		// xml格式的消息数据
		String respXml = null;
		// 默认返回的文本消息内容
		String respContent = null;
		try {
			// 调用parseXml方法解析请求消息
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			// 发送方帐号
			String fromUserName = requestMap.get("FromUserName");
			// 开发者微信号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");

			// 回复文本消息
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);


			// 图片消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
//				respContent = "您发送的是图片消息！";
			}
			// 语音消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
//				respContent= "您发送的是语音消息！";
			}
			// 视频消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) {
//				respContent = "您发送的是视频消息！";
			}
			// 地理位置消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
//				respContent = "您发送的是地理位置消息！";
			}
			// 链接消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
//				respContent = "您发送的是链接消息！";
			}
			// 事件推送
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				// 关注
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					respContent = "你好，欢迎加入珞珈文化微信公众平台。现在开始你可以免费打印了。回复“1”了解免费复印申报流程。";
				}
				// 取消关注
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO 取消订阅后用户不会再收到公众账号发送的消息，因此不需要回复
					respContent = "很期待您下次的关注哦！";
				}
				// 扫描带参数二维码
				else if (eventType.equals(MessageUtil.EVENT_TYPE_SCAN)) {
					// TODO 处理扫描带参数二维码事件
				}
				// 上报地理位置
				else if (eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)) {
					// TODO 处理上报地理位置事件
				}
				// 自定义菜单
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// TODO 处理菜单点击事件
				}
			}
//			respContent=respContent+simpleMsg;//LY
//			respContent=null;
			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
//				respContent = "您发送的是文本消息！";
				String requestContent = requestMap.get("Content");
				if(requestContent!=null&&requestContent.trim().equals("1")){
					StringBuffer sb=new StringBuffer();
					sb.append("免费打印流程：").append('\n');
					sb.append("扫描海报二维码 关注我们，").append('\n');
					sb.append("申报打印数量   发送“学校名称”（分校加上校区名称，例：吉林大学南岭校区）+“#”+“打印数量”   例：东北师范大学#12。").append('\n');
					sb.append("收到申报成功信息交由复印店老板查看,").append('\n');
					sb.append("即可打印,").append('\n');
					sb.append("每人每天只可申报一次，最高15张最低1张，请如实申报打印数量。").append('\n');
					respContent=sb.toString();
				}else{
				      textMessage.setContent(requestContent);
				      PrintLogService pls=new PrintLogService();
				      respContent=pls.processRequest(request,textMessage);
				}
			}
			
			// 设置文本消息的内容
			textMessage.setContent(respContent);
			// 将文本消息对象转换成xml
			respXml = MessageUtil.messageToXml(textMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respXml;
	}
}
