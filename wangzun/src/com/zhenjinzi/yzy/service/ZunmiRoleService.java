package com.zhenjinzi.yzy.service;

import java.util.List;

import com.zhenjinzi.yzy.model.ZunmiRole;

public interface ZunmiRoleService {

	/**
	 * 增加或更新role
	 * 
	 * @param role
	 * @return
	 */
	public boolean save(ZunmiRole role);

	/**
	 * 批量增加或更新role
	 * 
	 * @param roles
	 * @return
	 */
	public boolean[] save(ZunmiRole[] roles);

	/**
	 * 查询role数据记录
	 * 
	 * @return
	 */
	public List<ZunmiRole> findAll();

	/**
	 * 持久化session数据到数据库
	 */
	public void flush();
}
