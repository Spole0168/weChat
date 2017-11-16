package com.zhenjinzi.yzy.service;

import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.SearchResult;
import com.zhenjinzi.yzy.model.ZunmiRecharge;
import com.zhenjinzi.yzy.model.ZunmiUser;
import com.zhenjinzi.yzy.model.ZunmiWithdraw;


public interface ZunmiWithdrawService {
	
	/**
	 * 根据条件查询用户提现记录
	 * 
	 * @param search
	 * @return
	 */
	public SearchResult<ZunmiWithdraw> searchAndCount(ISearch search);
	
	/**
	 * 查询用户提现记录
	 * @param firstResult 起始记录
	 * @param maxResults 查询数量
	 * @return
	 */
	public SearchResult<ZunmiWithdraw> findAll(int firstResult,int maxResults);
	
	/**
	 * 根据用户ID查询用户提现记录
	 * @param userId 用户ID
	 * @param firstResult 起始记录
	 * @param maxResults 查询条数
	 * @return
	 */
	public SearchResult<ZunmiWithdraw> findByUserId(int userId,int firstResult, int maxResults);

	/**
	 * 根据提现ID查询提现记录
	 * @param withdrawId
	 * @return
	 */
	public ZunmiWithdraw findById(Integer withdrawId);

	/**
	 * 保存提现记录
	 * @param withdraw
	 * @return
	 */
	public boolean save(ZunmiWithdraw withdraw);

	/**
	 * 提现申请
	 * @param cash
	 * @param paymentPassword
	 * @param user
	 * @return
	 */
	public void doWithdraw(Double cash,String paymentPassword,ZunmiUser user) throws Exception;
}
