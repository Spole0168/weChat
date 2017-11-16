package com.zhenjinzi.yzy.service;

import java.util.List;

import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.SearchResult;
//import com.zhenjinzi.yzy.model.YzyAdmin;
import com.zhenjinzi.yzy.model.YzyServiceApply;
import com.zhenjinzi.yzy.model.YzyUserinstallapply;

/***
 * 用户预约申请安装service
 * @author ly
 *
 */
public interface UserInstallApplyService {

	/**
	 * 增加或更新serviceInfo
	 * 
	 * @param serviceInfo
	 * @return
	 */
	public boolean save(YzyUserinstallapply installApply);

	/**
	 * 批量增加或更新serviceInfo
	 * 
	 * @param serviceInfo
	 * @return
	 */
	public boolean[] save(YzyUserinstallapply[] installApplys);

	/**
	 * 查询serviceInfo数据记录
	 * 
	 * @return
	 */
	public List<YzyUserinstallapply> findAll();

	/**
	 * 查询有效serviceInfo记录
	 * @return
	 */
	public SearchResult<YzyUserinstallapply> findEnable(int firstResult,int maxResult);
	
	/**
	 * 根据主键查询admin
	 * 
	 * @param id
	 * @return
	 */
	public YzyUserinstallapply findById(Integer id);

	/**
	 * 批量根据主键查询serviceInfo记录
	 * 
	 * @param ids
	 * @return
	 */
	public YzyUserinstallapply[] findByIds(Integer[] ids);

	/**
	 * 查询用户名是否存在
	 * 
	 * @param userName
	 * @return
	 */
	public boolean hasUserName(String userName);

	/**
	 * 根据用户名查询admin记录
	 * 
	 * @param userName
	 * @return
	 */
	public YzyUserinstallapply findByName(String userName);

	/**
	 * 根据用户状态查询admin记录
	 * 
	 * @param status
	 * @return
	 */
	public List<YzyUserinstallapply> findByStatus(String status);

	/**
	 * 根据条件查询admin记录
	 * @param search
	 * @return
	 */
	public SearchResult<YzyUserinstallapply> searchAndCount(ISearch search);
	
	/**
	 * 持久化session数据到数据库
	 */
	public void flush();
}
