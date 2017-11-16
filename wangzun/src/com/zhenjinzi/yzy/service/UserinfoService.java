package com.zhenjinzi.yzy.service;

import java.util.List;

import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.SearchResult;
import com.zhenjinzi.yzy.model.YzyRepairlog;
import com.zhenjinzi.yzy.model.YzyUserinfo;

public interface UserinfoService {
	/**
	 * 增加或更新serviceInfo
	 * 
	 * @param serviceInfo
	 * @return
	 */
	public boolean save(YzyUserinfo serviceInfo);

	/**
	 * 批量增加或更新serviceInfo
	 * 
	 * @param serviceInfo
	 * @return
	 */
	public boolean[] save(YzyUserinfo[] serviceInfos);

	/**
	 * 查询serviceInfo数据记录
	 * 
	 * @return
	 */
	public List<YzyUserinfo> findAll();

	/**
	 * 查询有效serviceInfo记录
	 * @return
	 */
	public SearchResult<YzyUserinfo> findEnable(int firstResult,int maxResult);
	
	/**
	 * 根据主键查询admin
	 * 
	 * @param id
	 * @return
	 */
	public YzyUserinfo findById(Integer id);

	/**
	 * 批量根据主键查询serviceInfo记录
	 * 
	 * @param ids
	 * @return
	 */
	public YzyUserinfo[] findByIds(Integer[] ids);

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
	public YzyUserinfo findByName(String userName);

	/**
	 * 根据用户状态查询admin记录
	 * 
	 * @param status
	 * @return
	 */
	public List<YzyUserinfo> findByStatus(String status);

	/**
	 * 根据条件查询admin记录
	 * @param search
	 * @return
	 */
	public SearchResult<YzyUserinfo> searchAndCount(ISearch search);
	
	/**
	 * 持久化session数据到数据库
	 */
	public void flush();
}
