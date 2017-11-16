package com.zhenjinzi.yzy.service;

import java.util.List;

import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.SearchResult;
import com.zhenjinzi.yzy.model.ZunmiDomainGroup;
import com.zhenjinzi.yzy.model.ZunmiUser;

public interface ZunmiDomainGroupService {

	/**
	 * 根据用户Id ，查出用户的域名组列表
	 * @param userId
	 * @return
	 */
	public List<ZunmiDomainGroup> findByUserId(Integer userId);

	/**
	 * 根据域名组名查询域名组
	 * @param parameter
	 * @return
	 */
	public ZunmiDomainGroup findByGroupName(String domainGroupName);

    /**
     * 根据用户id和域名组名查询此组名是否存在
     * @param userId
     * @param groupName
     * @return
     */
	public ZunmiDomainGroup findByUserIdAGroupN(Integer userId, String groupName);
	
	/**
	 * 保存domainGroup数据实体到数据库
	 * @param domainGroup
	 * @return
	 */
	public boolean save(ZunmiDomainGroup domainGroup);

	/**
	 * 根据用户判断用户组的域名组里存在newTeam(组名)与否，存在返回true，不存在返回false
	 * @param user
	 * @param newTeam
	 * @return
	 */
	public boolean hasGroupName(ZunmiUser user,String newTeam);

	/**
	 * 根据条件查询域名组列表
	 * @param search
	 * @return
	 */
	public SearchResult<ZunmiDomainGroup> searchAndCount(ISearch search);

	/**
	 * 根据Id查找，域名组记录
	 * @param domainGroupId
	 * @return
	 */
	public ZunmiDomainGroup findById(Integer domainGroupId);

	/**
	 * 删除域名组
	 * @param domainGroup
	 * @return
	 */
	public boolean delDomainGroup(ZunmiDomainGroup domainGroup);


}
