package com.zhenjinzi.yzy.service;

import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.SearchResult;
import com.zhenjinzi.yzy.model.ZunmiRecharge;

public interface ZunmiRechargeService {
	/**
	 * 根据条件查询recharge记录和记录条数
	 * 
	 * @param search
	 * @return
	 */
	public SearchResult<ZunmiRecharge> searchAndCount(ISearch search);
	

	/**
	 * 根据userid查询用户recharge记录 
	 * 
	 * @param userId 用户ID
	 * @param firstResult 起始记录
	 * @param maxResults 查询数量
	 * @return
	 */
	public SearchResult<ZunmiRecharge> findByUserId(int userId,int firstResult, int maxResults);
	
	/**
	 * 查询用户recharge记录
	 * 
	 * @param firstResult 起始记录
	 * @param maxResults 查询数量
	 * @return
	 */
	public SearchResult<ZunmiRecharge> findAll(int firstResult,int maxResults); 
    /**
     * 增加或者更新ZunmiRecharge
     * @param recharge
     * @return
     */
    
	public boolean save(ZunmiRecharge recharge);
	
	/**
	 * 根据充值编号查找ZunmiRecharge
	 * @param parseInt
	 * @return
	 */
	public ZunmiRecharge findByOrderId(String rechargeId);
	
	/**
	 * 验证订单金额
	 * @param orderId
	 * @param money
	 * @return
	 */
	public boolean verifyOrder(String orderId,Double money);
	
	/**
	 * 订单成功支付
	 * @param orderId
	 * @return
	 */
	public ZunmiRecharge successRecharge(String orderId);
}
