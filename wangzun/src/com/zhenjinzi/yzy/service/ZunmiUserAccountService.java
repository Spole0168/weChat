package com.zhenjinzi.yzy.service;

import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.SearchResult;
import com.zhenjinzi.yzy.model.ZunmiTransactionLog;
import com.zhenjinzi.yzy.model.ZunmiUserAccount;
import com.zhenjinzi.yzy.model.enums.TransactionAction;
import com.zhenjinzi.yzy.model.enums.UserAccountStatus;

public interface ZunmiUserAccountService {
	/**
	 * 初始化account信息
	 * 
	 */
	public void init(Integer userId);
	
	/**
	 * 更改userAccount状态
	 * @param userAccountId 
	 * @param UserAccountStatus 状态：LOCKED，NOMAL
	 */
	public void changeStatus(Integer userAccountId,UserAccountStatus status);
	
	/**
	 * 添加或更新userAccount
	 * 
	 * @param zunmiUserAccount
	 * @return
	 */
	public boolean save(ZunmiUserAccount zunmiUserAccount);
	
	/**
	 * 根据id查询userAccount
	 * 
	 * @param userAccountId
	 * @return
	 */
	public ZunmiUserAccount find(Integer userAccountId);
	
	/**
	 * 账户预授权
	 * 
	 * @param userAccountId 账户ID
	 * @param transAmount 授权金额
	 * @param action 操作类型
	 * @param descriptioni 描述
	 * @return 明细ID
	 */
	public ZunmiTransactionLog accredit(Integer userAccountId,Double transAmount,TransactionAction action,String description);
	
	/**
	 * 充值
	 * 
	 * @param accountId 账户ID
	 * @param cash 充值金额
	 */
	public void deposit(Integer accountId,Double cash);
	
	/**
	 * 预授权扣款
	 * @param transId
	 */
	public void accreditDone(Integer transId);
	
	/**
	 * 取消预授权
	 * @param transId
	 */
	public void disaccredit(Integer transId);

	/**
	 * 根据条件查询userAccount
	 * @param search
	 * @return
	 */
	public SearchResult<ZunmiUserAccount> searchAndCount(ISearch search);
}
