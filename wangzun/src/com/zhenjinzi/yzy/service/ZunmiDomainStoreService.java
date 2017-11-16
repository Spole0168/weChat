package com.zhenjinzi.yzy.service;

import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.SearchResult;
import com.zhenjinzi.yzy.model.ZunmiDomainStore;

public interface ZunmiDomainStoreService {

	/**
	 * 根据条件查询ZunmiDomainStore
	 * @param search
	 * @return
	 */
	public SearchResult<ZunmiDomainStore> searchAndCount(ISearch search);

	/**
	 * 判断店铺名是否已经存在
	 * @param storeName 
	 * @return 存在返回true， 不存在false，
	 */
	public boolean hasStoreName(String storeName);
	
	/**
	 * 保存或是修改ZunmiDomainStore到数据库
	 * @param domainStore
	 * @return 保存成功了返回true，修改成功了返回false
	 */
	public boolean save(ZunmiDomainStore domainStore);

	/**
	 * 根据店铺  Id  查找店铺信息
	 * @param domainStoreId
	 * @return 
	 */
	public ZunmiDomainStore findById(Integer domainStoreId);

	/**
	 * 根据店铺ID删除店铺
	 * @param domainStoreId
	 * @return
	 */
	public boolean delete(Integer domainStoreId);

}
