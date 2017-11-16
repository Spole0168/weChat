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
import com.zhenjinzi.yzy.dao.AppoitmentcheckDao;
import com.zhenjinzi.yzy.model.YzyAppoitmentcheck;
import com.zhenjinzi.yzy.model.YzyServiceApply;
import com.zhenjinzi.yzy.model.YzyUserinstallapply;
import com.zhenjinzi.yzy.service.AppoitmentcheckService;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class AppoitmentcheckServiceImpl implements AppoitmentcheckService{
    
	@Resource
	private AppoitmentcheckDao dao;
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean save(YzyAppoitmentcheck serviceInfo) {
		return dao.save(serviceInfo);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean[] save(YzyAppoitmentcheck[] serviceInfos) {
		return dao.save(serviceInfos);
	}

	@Override
	public List<YzyAppoitmentcheck> findAll() {
		return dao.findAll();
	}

	@Override
	public SearchResult<YzyAppoitmentcheck> findEnable(int firstResult,
			int maxResult) {
		Search search = new Search();
		search.setFirstResult(firstResult);
		search.setMaxResults(maxResult);
//		search.addFilterNotEqual("text5", "1");
		search.addSortDesc("create_Date");//申请时间降序
		SearchResult<YzyAppoitmentcheck> searchResult = this.searchAndCount(search);
		return searchResult;
	}

	@Override
	public YzyAppoitmentcheck findById(Integer id) {
		return dao.find(id);
	}

	@Override
	public YzyAppoitmentcheck[] findByIds(Integer[] ids) {
		return dao.find(ids);
	}

	@Override
	public boolean hasUserName(String userName) {
		return false;
	}

	@Override
	public YzyAppoitmentcheck findByName(String userName) {
		Filter f = new Filter("applyeName",userName,Filter.OP_LIKE);
		YzyAppoitmentcheck apply = dao.searchUnique(new Search().addFilter(f));
		return apply;
	}

	@Override
	public List<YzyAppoitmentcheck> findByStatus(String status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SearchResult<YzyAppoitmentcheck> searchAndCount(ISearch search) {
		return dao.searchAndCount(search);
	}

	@Override
	public void flush() {
		dao.flush();
	}

}
