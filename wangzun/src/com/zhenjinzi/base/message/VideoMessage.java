package com.zhenjinzi.base.message;

import com.zhenjinzi.base.BaseMessage;

public class VideoMessage extends BaseMessage {
//  视频媒体ID
	private String MediaId;
	
//	/视频结构消息缩略图ID
	private String ThumbMediaId;

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getThumbMediaId() {
		return ThumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}
	
}
