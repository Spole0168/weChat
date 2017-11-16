package com.shang.process;

import com.shang.Constants;
import com.shang.Bean.ReceiveXmlEntity;


public class WechatProcess {
	/**
	 * 解析处理xml、获取智能回复结果（通过图灵机器人api接口）
	 * @param xml 接收到的微信数据
	 * @return	最终的解析结果（xml格式数据）
	 */
	public String processWechatMag(String xml){
		/** 解析xml数据 */
		ReceiveXmlEntity xmlEntity = new ReceiveXmlProcess().getMsgEntity(xml);
		
		/** 以文本消息为例，调用图灵机器人api接口，获取回复内容 */
		String result = "";
		if (xmlEntity.getMsgType().equals(Constants.REQ_MESSAGE_TYPE_EVENT)) {
			String eventType = xmlEntity.getEvent();
			if (eventType.equals(Constants.EVENT_TYPE_SUBSCRIBE)) {// 订阅
				result = "欢迎关注";
			}else if(eventType.equals(Constants.EVENT_TYPE_UNSUBSCRIBE)){
				result = "期待再次与你见面";
			}else if(eventType.equals(Constants.EVENT_TYPE_CLICK)){
				String eventKey = xmlEntity.getEventKey();
				result = "eventKey:"+eventKey;
			}
			
		}else if(!xmlEntity.getMsgType().equals(Constants.REQ_MESSAGE_TYPE_EVENT)){
			if("text".endsWith(xmlEntity.getMsgType())){
				result = new TulingApiProcess().getTulingResult(xmlEntity.getContent());
			}else if("image".endsWith(xmlEntity.getMsgType())){
				result = "图片链接："+xmlEntity.getPicUrl()+"\n 媒体id:"+xmlEntity.getMediaId();
			}else if("location".endsWith(xmlEntity.getMsgType())){
				result = "位置信息：\n Location_X"+xmlEntity.getLocation_X()+"\n Location_Y:"+xmlEntity.getLocation_Y()+
						"位置："+xmlEntity.getLabel();
			}else if("link".endsWith(xmlEntity.getMsgType())){
				result = "链接信息：\n Title"+xmlEntity.getTitle()+"\n des:"+xmlEntity.getDescription()+
						"链接地址："+xmlEntity.getUrl();
			}else{
				result = "我们暂时只接受文本数据，正在学习中。。。。";
			}
		}
		
		/** 此时，如果用户输入的是“你好”，在经过上面的过程之后，result为“你也好”类似的内容 
		 *  因为最终回复给微信的也是xml格式的数据，所有需要将其封装为文本类型返回消息
		 * */
		result = new FormatXmlProcess().formatXmlAnswer(xmlEntity.getFromUserName(), xmlEntity.getToUserName(), result);
		
		return result;
	}
}
