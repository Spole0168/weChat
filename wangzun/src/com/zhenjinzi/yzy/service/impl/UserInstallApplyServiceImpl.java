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
import com.zhenjinzi.yzy.dao.ServiceApplyDao;
import com.zhenjinzi.yzy.dao.UserInstallApplyDao;
import com.zhenjinzi.yzy.dao.ZunmiAdminDao;
import com.zhenjinzi.yzy.model.YzyAdmin;
import com.zhenjinzi.yzy.model.YzyServiceApply;
import com.zhenjinzi.yzy.model.YzyUserinstallapply;
import com.zhenjinzi.yzy.model.enums.AdminStatus;
import com.zhenjinzi.yzy.service.ServiceApplyService;
import com.zhenjinzi.yzy.service.UserInstallApplyService;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class UserInstallApplyServiceImpl  implements UserInstallApplyService{

	@Resource
	private UserInstallApplyDao installApplyDao;
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean save(YzyUserinstallapply serviceInfo) {
		return installApplyDao.save(serviceInfo);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean[] save(YzyUserinstallapply[] serviceInfos) {
		return installApplyDao.save(serviceInfos);
	}

	@Override
	public List<YzyUserinstallapply> findAll() {
		return installApplyDao.findAll();
	}

	@Override
	public SearchResult<YzyUserinstallapply> findEnable(int firstResult,
			int maxResult) {
		Search search = new Search();
		search.setFirstResult(firstResult);
		search.setMaxResults(maxResult);
//		search.addFilterNotEqual("status", AdminStatus.DISABLED);
		search.addSortDesc("apply_Date");//根据申请时间  降序排列
		SearchResult<YzyUserinstallapply> searchResult = this.searchAndCount(search);
		return searchResult;
	}

	@Override
	public YzyUserinstallapply findById(Integer id) {
		return installApplyDao.find(id);
	}

	@Override
	public YzyUserinstallapply[] findByIds(Integer[] ids) {
		return installApplyDao.find(ids);
	}

	/**
	 * 暂时没实现
	 */
	@Override
	public boolean hasUserName(String userName) {
//		Filter f = new Filter("userName",userName,Filter.OP_EQUAL);
//		YzyAdmin admin = adminDao.searchUnique(new Search().addFilter(f));
//		return admin.equals(null)?false:true;
		return false;
	}

	/**
	 * applyeName  申请人名称
	 */
	@Override
	public YzyUserinstallapply findByName(String userName) {
		Filter f = new Filter("applyeName",userName,Filter.OP_LIKE);
		YzyUserinstallapply apply = installApplyDao.searchUnique(new Search().addFilter(f));
		return apply;
	}

	/**
	 * TODO
	 */
	@Override
	public List<YzyUserinstallapply> findByStatus(String status) {
		return null;
	}

	@Override
	public SearchResult<YzyUserinstallapply> searchAndCount(ISearch search) {
		return installApplyDao.searchAndCount(search);
	}

	@Override
	public void flush() {
		installApplyDao.flush();
	}

}
