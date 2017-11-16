package com.zhenjinzi.yzy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.zhenjinzi.yzy.dao.ZunmiAdminDao;
import com.zhenjinzi.yzy.model.YzyAdmin;
import com.zhenjinzi.yzy.model.enums.AdminStatus;
import com.zhenjinzi.yzy.service.ZunmiAdminService;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class ZunmiAdminServiceImpl implements ZunmiAdminService {

	@Resource
	private ZunmiAdminDao adminDao;
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean save(YzyAdmin admin) {
		return adminDao.save(admin);		
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean[] save(YzyAdmin[] admins) {
		return adminDao.save(admins);
	}

	@Override
	public List<YzyAdmin> findAll() {
		return adminDao.findAll();
	}
	
	@Override
	public SearchResult<YzyAdmin> findEnable(int firstResult,int maxResult){
		Search search = new Search();
		search.setFirstResult(firstResult);
		search.setMaxResults(maxResult);
		search.addFilterNotEqual("status", AdminStatus.DISABLED);
		SearchResult<YzyAdmin> searchResult = this.searchAndCount(search);
		return searchResult;
	}

	@Override
	public YzyAdmin findById(Integer id) {
		return adminDao.find(id);
	}

	@Override
	public YzyAdmin[] findByIds(Integer[] ids) {
		return adminDao.find(ids);
	}


	@Override
	public boolean hasUserName(String userName){
		Filter f = new Filter("userName",userName,Filter.OP_EQUAL);
		YzyAdmin admin = adminDao.searchUnique(new Search().addFilter(f));
		return admin.equals(null)?false:true;
	}
	
	@Override
	public YzyAdmin findByName(String userName){
		System.out.println(userName);
		Filter f = new Filter("userName",userName,Filter.OP_LIKE);
		YzyAdmin admin = adminDao.searchUnique(new Search().addFilter(f));
		return admin;
	}
	
	@Override
	public List<YzyAdmin> findByStatus(String status) {
		Filter f = new Filter("status",status,Filter.OP_EQUAL);
		List<YzyAdmin> adminList = adminDao.search(new Search().addFilter(f));
		return adminList;
	}
	
	@Override
	public SearchResult<YzyAdmin> searchAndCount(ISearch search) {
		return adminDao.searchAndCount(search);
	}
	
	@Override
	public void flush() {
		adminDao.flush();
	}

	

}
