package com.zhenjinzi.yzy.service;

import com.zhenjinzi.yzy.model.ZunmiUserDetail;

public interface ZunmiUserDetailService {
	
	/**
	 * 增加或更新userDetail
	 * @param userDetail
	 * @return
	 */
	public boolean save(ZunmiUserDetail userDetail);
	
	/**
	 * 通过客户信息表Id找客户详情信息
	 * @param userId
	 * @return
	 */

	public ZunmiUserDetail findUserByUserId(Integer userId);
	
	/**
	 * 查询证件号是否被占用
	 * @param identity
	 * @return
	 */

	public boolean hasIdentity(String identity);

}
