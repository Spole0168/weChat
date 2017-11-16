package com.zhenjinzi.yzy.service;

import java.util.List;

import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.SearchResult;
import com.zhenjinzi.yzy.model.ZunmiDomain;
import com.zhenjinzi.yzy.model.ZunmiUser;

public interface ZunmiDomainService {

	
	/**
	 * 根据userId，判断domain是否已经存在,不存在返回false，存在返回true
	 * @param s
	 * @return
	 */
	public boolean hasDomain(String domain,Integer id);
	
	/**
	 * 删除域名
	 * @param domainId
	 * @param userId
	 * @return
	 */
	public boolean delDomain(ZunmiDomain domain);
	
	/**
	 * 根据Id查找ZunmiDomain
	 * @param domainId
	 * @return
	 */
	public ZunmiDomain findById(Integer domainId);
	
	/**
	 * 根据条件查询ZunmiDomain记录
	 * @param search
	 * @return
	 */
	public SearchResult<ZunmiDomain> searchAndCount(ISearch search);
	
	/**
	 * 查找所有域名，按自然顺序排列
	 * @return
	 */
	public List<ZunmiDomain> findAll();
	
	/**
	 * 批量添加域名到数据库
	 * @param array
	 */
	public boolean[] save(ZunmiDomain[] array);
	
	/**
	 * 查询user的域名列表
	 * @param user
	 * @return
	 */
	public List<ZunmiDomain> findByUser(ZunmiUser user);

	/**
	 * 单个保存域名信息到数据库
	 * @param domain
	 * @return
	 */
	public boolean save(ZunmiDomain domain);
	
	/**
	 * 根据状态status查询此状态下的域名个数
	 * @param userId 用户id
	 * @param status 域名状态
	 * @return
	 */
	public int getStatusNum(Integer userId,String status);

	/**
	 * 根据域名Ids删除
	 * @param domianIds
	 * @return
	 */
	
	public void delDomainsByIds(Integer[] domainIds);

}
