package com.zhenjinzi.yzy.service;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.mail.MessagingException;



public interface ZunmiMailService {
	/**
	 * 发送邮件
	 * 
	 * @param to 收件人email
	 * @param templateLocation 邮件模板
	 * @param model 模板数据
	 * @param attachments 附件
	 * @throws MessagingException 
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException 
	 * @throws UnsupportedEncodingException
	 */
	public void send(String to, String templateLocation, Map<String, Object> model, Map<String, Object> attachments) throws MessagingException, UnsupportedEncodingException;
	/**
	 * 批量发送邮件
	 * 
	 * @param to 收件人email数组
	 * @param templateLocation 邮件模板
	 * @param model 模板数据
	 * @param attachments 附件
	 * @throws UnsupportedEncodingException 
	 * @throws MessagingException 
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	public void send(String[] to, String templateLocation, Map<String, Object> model, Map<String, Object> attachments) throws MessagingException, UnsupportedEncodingException;
}
