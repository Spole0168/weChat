package com.zhenjinzi.base;

public class BaseMessage {

	//开发者微信账号
	private String ToUserName;
	//发送发账号（一个Openid）
	private String FromUserName;
//	消息创建时间
	private long Createtime;
//	消息类型(text/image/location/link/voice)
	private String MsgType;
//	消息ID，64位整形
	private long MsgID;
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public long getCreatetime() {
		return Createtime;
	}
	public void setCreatetime(long createtime) {
		Createtime = createtime;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	public long getMsgID() {
		return MsgID;
	}
	public void setMsgID(long msgID) {
		MsgID = msgID;
	}
	
}
