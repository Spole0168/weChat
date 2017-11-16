package com.zhenjinzi.yzy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.zhenjinzi.yzy.dao.RepairlogDao;
import com.zhenjinzi.yzy.model.YzyRepairlog;
import com.zhenjinzi.yzy.model.YzyServiceApply;
import com.zhenjinzi.yzy.service.RepairlogService;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class RepairlogServiceImpl implements RepairlogService{

	@Resource
	private RepairlogDao  repairlog;
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean save(YzyRepairlog serviceInfo) {
		return repairlog.save(serviceInfo);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean[] save(YzyRepairlog[] serviceInfos) {
		return repairlog.save(serviceInfos);
	}

	@Override
	public List<YzyRepairlog> findAll() {
		return repairlog.findAll();
	}

	@Override
	public SearchResult<YzyRepairlog> findEnable(int firstResult, int maxResult) {
		Search search = new Search();
		search.setFirstResult(firstResult);
		search.setMaxResults(maxResult);
//		search.addFilterNotEqual("status", AdminStatus.DISABLED);
		search.addFilterEqual("text5", "1");/**1:代表正常，2：代表删除*/
		SearchResult<YzyRepairlog> searchResult = this.searchAndCount(search);
		return searchResult;
	}

	@Override
	public YzyRepairlog findById(Integer id) {
		return repairlog.find(id);
	}

	@Override
	public YzyRepairlog[] findByIds(Integer[] ids) {
		return repairlog.find(ids);
	}

	@Override
	public boolean hasUserName(String userName) {
		return false;
	}

	@Override
	public YzyRepairlog findByName(String userName) {
		return null;
	}

	@Override
	public List<YzyRepairlog> findByStatus(String status) {
		return null;
	}

	@Override
	public SearchResult<YzyRepairlog> searchAndCount(ISearch search) {
		return repairlog.searchAndCount(search);
	}

	@Override
	public void flush() {
		repairlog.flush();
	}

}
