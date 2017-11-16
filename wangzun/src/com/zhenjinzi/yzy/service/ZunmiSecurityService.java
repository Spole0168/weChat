package com.zhenjinzi.yzy.service;

import com.zhenjinzi.yzy.model.ZunmiSecurity;

public interface ZunmiSecurityService {

	/**
	 * 增加或更新security
	 * @param security 
	 * @param security
	 * @return
	 */
	public boolean save(ZunmiSecurity security);
	/**
	 * 通过客户信息ID查找密保问题实体
	 * @param userId
	 * @return
	 */

	public ZunmiSecurity findSecurityByuserId(Integer userId);

}
