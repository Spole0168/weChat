package com.zhenjinzi.yzy.service.impl.security;

import java.util.List;




import org.property.core.modules.security.springsecurity.SpringSecurityUtils;
import org.property.core.modules.spring.SpringContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import com.zhenjinzi.yzy.dao.ZunmiAdminDao;
import com.zhenjinzi.yzy.model.YzyAdmin;
import com.zhenjinzi.yzy.model.ZunmiResource;
import com.zhenjinzi.yzy.service.ZunmiResourceService;


/**
 * 安全相关实体的管理类, 包括用户,角色,资源与授权类.
 * 
 */
@Service
@Transactional
public class SecurityEntityManager {
	private static Logger logger = LoggerFactory.getLogger(SecurityEntityManager.class);
	
	@Autowired
	private ZunmiAdminDao adminDao;
	
	//--Admin Manager--//
	public YzyAdmin getAdmin(Integer id){
		return adminDao.find(id);
	}
	public List<YzyAdmin> getAllAdmin(){
		List<YzyAdmin> list = adminDao.findAll();
		logger.info("get {} admin sucessful.",list.size());
		return list;
	}
	public void saveAdmin(YzyAdmin admin){
		adminDao.save(admin);
	}
	public void deleteAdmin(Integer id){
		if(id==1){
			logger.warn("操作员{}尝试删除超级管理员用户",SpringSecurityUtils.getCurrentUserName());
		}
		adminDao.removeById(id);
	}
	public int deleteAdmin(String []ids){
		if (ids.length > 0) {
			for (int i = 0; i < ids.length; i++) {
				Integer userid = Integer.parseInt(ids[i]);
				if (userid==1) {
					logger.warn("操作员{}尝试删除超级管理员用户", SpringSecurityUtils
							.getCurrentUserName());
					return 99;
				}
				adminDao.removeById(userid);
			}
		}
		return 0;
	}
	
	@Transactional(readOnly = true)
	public YzyAdmin  findAdminByName(String userName) {
		Filter f = new Filter("userName",userName,Filter.OP_LIKE);
		YzyAdmin admin = adminDao.searchUnique(new Search().addFilter(f));
		return admin;
	}
	
	

	// -- Resource Manager --//
	

}
