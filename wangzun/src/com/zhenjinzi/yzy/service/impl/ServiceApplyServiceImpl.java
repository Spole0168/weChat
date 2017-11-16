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
import com.zhenjinzi.yzy.dao.ZunmiAdminDao;
import com.zhenjinzi.yzy.model.YzyAdmin;
import com.zhenjinzi.yzy.model.YzyServiceApply;
import com.zhenjinzi.yzy.model.enums.AdminStatus;
import com.zhenjinzi.yzy.service.ServiceApplyService;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class ServiceApplyServiceImpl  implements ServiceApplyService{

	@Resource
	private ServiceApplyDao applyDao;
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean save(YzyServiceApply serviceInfo) {
		return applyDao.save(serviceInfo);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean[] save(YzyServiceApply[] serviceInfos) {
		return applyDao.save(serviceInfos);
	}

	@Override
	public List<YzyServiceApply> findAll() {
		return applyDao.findAll();
	}

	@Override
	public SearchResult<YzyServiceApply> findEnable(int firstResult,
			int maxResult) {
		Search search = new Search();
		search.setFirstResult(firstResult);
		search.setMaxResults(maxResult);
//		search.addFilterNotEqual("status", AdminStatus.DISABLED);
		SearchResult<YzyServiceApply> searchResult = this.searchAndCount(search);
		return searchResult;
	}

	@Override
	public YzyServiceApply findById(Integer id) {
		return applyDao.find(id);
	}

	@Override
	public YzyServiceApply[] findByIds(Integer[] ids) {
		return applyDao.find(ids);
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
	public YzyServiceApply findByName(String userName) {
		Filter f = new Filter("applyeName",userName,Filter.OP_LIKE);
		YzyServiceApply apply = applyDao.searchUnique(new Search().addFilter(f));
		return apply;
	}

	/**
	 * TODO
	 */
	@Override
	public List<YzyServiceApply> findByStatus(String status) {
		return null;
	}

	@Override
	public SearchResult<YzyServiceApply> searchAndCount(ISearch search) {
		return applyDao.searchAndCount(search);
	}

	@Override
	public void flush() {
		applyDao.flush();
	}

}
