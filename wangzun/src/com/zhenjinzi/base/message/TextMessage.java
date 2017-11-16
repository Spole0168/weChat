package com.zhenjinzi.base.message;

import com.zhenjinzi.base.BaseMessage;

/**
 * 文本消息
 * @author Administrator
 *
 */
public class TextMessage extends BaseMessage{
//消息内容
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}
