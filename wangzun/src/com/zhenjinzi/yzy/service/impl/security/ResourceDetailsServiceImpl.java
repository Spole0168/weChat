package com.zhenjinzi.yzy.service.impl.security;

import java.util.LinkedHashMap;
import java.util.List;

import org.property.core.modules.security.springsecurity.ResourceDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.zhenjinzi.yzy.model.ZunmiResource;


/**
 * 从数据库查询URL--授权定义Map的实现类.
 * 
 */
@Transactional(readOnly = true)
public class ResourceDetailsServiceImpl implements ResourceDetailsService {
	
	@Autowired
	private SecurityEntityManager securityEntityManager;

	/**
	 * @see ResourceDetailsService#getRequestMap()
	 */
	public LinkedHashMap<String, String> getRequestMap() throws Exception {// NOSONAR
//		List<ZunmiResource> resourceList = securityEntityManager.getUrlResourceWithPermissions();
//		LinkedHashMap<String, String> requestMap = new LinkedHashMap<String, String>(
//				resourceList.size());
//		
//		for (ZunmiResource resource : resourceList) {
//			requestMap.put(resource.getValue(), resource.getPrefixedAuthNames());
//		}
//		return requestMap;
		return null;
	}
}