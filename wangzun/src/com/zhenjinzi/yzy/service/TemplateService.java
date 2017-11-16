package com.zhenjinzi.yzy.service;

import com.zhenjinzi.yzy.exception.BuildHtmlException;
import com.zhenjinzi.yzy.model.ZunmiContent;


public interface TemplateService {
	public void createIndex(String path,String contextPath)throws BuildHtmlException;
	public void createChannel(String path,String template,Integer nodeId,String contextPath) throws BuildHtmlException;
	public void createContentList(String path,String templateName,Integer nodeId,String contextPath) throws BuildHtmlException;
	public void createContent(String path, Integer contentId,String contextPath) throws BuildHtmlException;
}
