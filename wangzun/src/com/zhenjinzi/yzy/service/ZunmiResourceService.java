package com.zhenjinzi.yzy.service;

import java.util.List;

import com.zhenjinzi.yzy.model.ZunmiPermission;
import com.zhenjinzi.yzy.model.ZunmiResource;

public interface ZunmiResourceService {
	
	/**
	 * 增加或更新resource
	 * 
	 * @param resource
	 * @return
	 */
	public boolean save(ZunmiResource resource);
	
	/**
	 * 查询URL类型的资源并预加载可访问该资源的授权信息.
	 */
	public List<ZunmiResource> getUrlResourceWithPermissions();
	
	/**
	 * 新增 权限-资源 信息
	 */
	public void save_sourcesWithAuthorityty(List<ZunmiPermission> permission,ZunmiResource resources);
	
	/**
	 * 解除关系
	 * 
	 * **/
	public void removeRelation(Long id);
	
	
}
