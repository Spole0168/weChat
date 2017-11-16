package com.zhenjinzi.yzy.service;

import java.util.List;

import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.SearchResult;
import com.zhenjinzi.yzy.model.YzyAdmin;

public interface ZunmiAdminService {

	/**
	 * 增加或更新admin
	 * 
	 * @param admin
	 * @return
	 */
	public boolean save(YzyAdmin admin);

	/**
	 * 批量增加或更新admin
	 * 
	 * @param admin
	 * @return
	 */
	public boolean[] save(YzyAdmin[] admins);

	/**
	 * 查询admin数据记录
	 * 
	 * @return
	 */
	public List<YzyAdmin> findAll();

	/**
	 * 查询有效admin记录
	 * @return
	 */
	public SearchResult<YzyAdmin> findEnable(int firstResult,int maxResult);
	
	/**
	 * 根据主键查询admin
	 * 
	 * @param id
	 * @return
	 */
	public YzyAdmin findById(Integer id);

	/**
	 * 批量根据主键查询admin记录
	 * 
	 * @param ids
	 * @return
	 */
	public YzyAdmin[] findByIds(Integer[] ids);

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
	public YzyAdmin findByName(String userName);

	/**
	 * 根据用户状态查询admin记录
	 * 
	 * @param status
	 * @return
	 */
	public List<YzyAdmin> findByStatus(String status);

	/**
	 * 根据条件查询admin记录
	 * @param search
	 * @return
	 */
	public SearchResult<YzyAdmin> searchAndCount(ISearch search);
	
	/**
	 * 持久化session数据到数据库
	 */
	public void flush();
}
