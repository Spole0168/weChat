//package com.zhenjinzi.yzy.service.impl;
//
//import java.util.List;
//
//import javax.annotation.Resource;
//
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.googlecode.genericdao.search.ISearch;
//import com.googlecode.genericdao.search.Search;
//import com.googlecode.genericdao.search.SearchResult;
//import com.zhenjinzi.yzy.dao.YinzyWatermechineDao;
//import com.zhenjinzi.yzy.model.YzyUserinstallapply;
//import com.zhenjinzi.yzy.model.YzyWatermachine;
//import com.zhenjinzi.yzy.service.YinzyWatermechineService;
//
//@Service
//@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
//public class YinzyWatermechineServiceImpl implements YinzyWatermechineService{
//
//	@Resource
//	private YinzyWatermechineDao yinzyWatermechinedao;
//	
//	@Override
//	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
//	public boolean save(YzyWatermachine installApply) {
//		return yinzyWatermechinedao.save(installApply);
//	}
//
//	@Override
//	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
//	public boolean[] save(YzyWatermachine[] installApplys) {
//		return yinzyWatermechinedao.save(installApplys);
//	}
//
//	@Override
//	public List<YzyWatermachine> findAll() {
//		return yinzyWatermechinedao.findAll();
//	}
//
//	@Override
//	public SearchResult<YzyWatermachine> findEnable(int firstResult,
//			int maxResult) {
//		Search search = new Search();
//		search.setFirstResult(firstResult);
//		search.setMaxResults(maxResult);
//		SearchResult<YzyWatermachine> searchResult = this.searchAndCount(search);
//		return searchResult;
//	}
//
//	@Override
//	public YzyWatermachine findById(Integer id) {
//		return yinzyWatermechinedao.find(id);
//	}
//
//	@Override
//	public YzyWatermachine[] findByIds(Integer[] ids) {
//		return yinzyWatermechinedao.find(ids);
//	}
//
//	/**
//	 * TODO 暂时未实现
//	 */
//	@Override
//	public boolean hasUserName(String userName) {
//		return false;
//	}
//
//	@Override
//	public YzyWatermachine findByName(String userName) {
//		return null;
//	}
//
//	@Override
//	public List<YzyWatermachine> findByStatus(String status) {
//		return null;
//	}
//
//	@Override
//	public SearchResult<YzyWatermachine> searchAndCount(ISearch search) {
//		return yinzyWatermechinedao.searchAndCount(search);
//	}
//
//	@Override
//	public void flush() {
//		yinzyWatermechinedao.flush();		
//	}
//
//}
