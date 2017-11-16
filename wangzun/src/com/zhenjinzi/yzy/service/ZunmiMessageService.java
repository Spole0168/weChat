package com.zhenjinzi.yzy.service;

import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.SearchResult;
import com.zhenjinzi.yzy.model.ZunmiMessage;
import com.zhenjinzi.yzy.model.ZunmiUser;
import com.zhenjinzi.yzy.model.enums.MessageStatus;

public interface ZunmiMessageService {

	/**
	 * 根据条件查询站内信
	 * @param search
	 * @return
	 */
	public SearchResult<ZunmiMessage> searchAndCount(ISearch search);

	/**
	 * 根据Id查找站内短信息
	 * @param messageId
	 * @return
	 */
	public ZunmiMessage findById(Integer messageId);

	/**
	 * 保存站内消息到数据库
	 * @param message
	 */
	public boolean save(ZunmiMessage message);

	/**
	 * 删除delMessage站内短信
	 * @param delMessage
	 * @return
	 */
	public boolean delMessage(ZunmiMessage delMessage);
	
	/**
	 * 一起删除删除多个站内消息
	 * @param intIds
	 */
	public void delMessages(Integer[] intIds);

	/**
	 * 根据状态和用户信息查询此状态下的站内信的个数
	 * @param user
	 * @param read
	 * @return
	 */
	public int findNumByStatus(ZunmiUser user, MessageStatus read);

	/**
	 * 一起存储多个站内短消息
	 * @param messages
	 */
	public boolean[] saveMessages(ZunmiMessage[] messages);

	

}
