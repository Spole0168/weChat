package com.zhenjinzi.yzy.service;

import java.io.UnsupportedEncodingException;

import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.SearchResult;
import com.zhenjinzi.yzy.model.ZunmiUser;

public interface ZunmiUserService {
	
	
	/**
	 * 增加或更新user
	 * 
	 * @param admin
	 * @return
	 */
	public boolean save(ZunmiUser User);

	/***
	 * 验证邮箱是否存在
	 * 
	 * @param email
	 * @return
	 */
	public boolean hasEmail(String email);

	/***
	 * 验证用户名是否存在
	 * 
	 * @param userName
	 * @return
	 */
	public boolean hasUserName(String userName);
	/**
	 * 通过用户名查找用户
	 * 
	 * @param userName
	 * @return
	 */
	public ZunmiUser findByName(String userName);

	/**通过id查找用户
	 * 
	 * @param parseInt
	 * @return
	 */
	public ZunmiUser findById(Integer parseInt);
	/**
	 * 通过邮箱查找用户
	 * @param email
	 * @return
	 */

	public ZunmiUser findByEmail(String email);
	/**
	 * 根据条件查询ZunmiUser
	 * @param search
	 * @return
	 */

	public SearchResult<ZunmiUser> searchAndCount(ISearch search);

	/**
	 * 删除用户信息
	 * @param user
	 * @return
	 */
	public boolean delete(ZunmiUser user);

	/**
	 * 根据用户名找到唯一的一条用户信息
	 * @param userName
	 * @return
	 */
	public ZunmiUser findByNameUnique(String userName);

	/**
	 * 根据邮箱号找到唯一的一条用户信息
	 * @param userName
	 * @return
	 */
	public ZunmiUser findByEmailUnique(String email);

	/**
	 * 校验票证
	 * @param userName 用户名
	 * @param userInfo 用户票证
	 * @return
	 */
	public ZunmiUser validCheckCode(String userName,String userInfo) throws UnsupportedEncodingException;
	
	/**
	 * 获取票证
	 * @param userName 用户名
	 * @return
	 */
	public String getValidCode(String userName);
}
