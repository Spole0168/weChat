package com.zhenjinzi.yzy.service;

import java.util.List;

import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.SearchResult;
import com.zhenjinzi.yzy.model.ZunmiTransactionLog;


public interface ZunmiTransactionLogService {
	
	/**
	 * 增加或更新transactionLog
	 * 
	 * @param transactionLog
	 * @return
	 */
	public boolean save(ZunmiTransactionLog transactionLog);
	

	/**
	 * 根据ID获取transactionLog记录
	 * @param id
	 * @return
	 */
	public ZunmiTransactionLog findById(Integer id);
	
	/**
	 * 根据条件查询transactionlog记录
	 * @param search
	 * @return
	 */
	public List<ZunmiTransactionLog> search(ISearch search);
	
	/**
	 * 根据条件查询transactionlog记录和记录总数
	 * @param search
	 * @return
	 */
	public SearchResult<ZunmiTransactionLog> searchAndCount(ISearch search);
	
	/**
	 * 根据userid查询用户transactionlog记录
	 * @param userId 用户编号
	 * @param firstResult 起始记录
	 * @param maxResults 查询数量
	 * @return 
	 */
	public SearchResult<ZunmiTransactionLog> findByUserId(int userId,int firstResult,int maxResults);

}
