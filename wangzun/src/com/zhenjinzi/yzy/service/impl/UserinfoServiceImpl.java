package com.zhenjinzi.yzy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.zhenjinzi.yzy.dao.UserinfoDao;
import com.zhenjinzi.yzy.model.YzyServiceApply;
import com.zhenjinzi.yzy.model.YzyUserinfo;
import com.zhenjinzi.yzy.model.enums.AdminStatus;
import com.zhenjinzi.yzy.service.UserinfoService;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class UserinfoServiceImpl implements UserinfoService{

	@Resource
	private UserinfoDao userInfoDao;
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean save(YzyUserinfo serviceInfo) {
//		if(serviceInfo!=null&&serviceInfo.getText5()==null){//为text5  设置默认值1
//			serviceInfo.setText5("1");
//		}
		return userInfoDao.save(serviceInfo);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean[] save(YzyUserinfo[] serviceInfos) {
		return userInfoDao.save(serviceInfos);
	}

	@Override
	public List<YzyUserinfo> findAll() {
		return userInfoDao.findAll();
	}

	@Override
	public SearchResult<YzyUserinfo> findEnable(int firstResult, int maxResult) {
		Search search = new Search();
		search.setFirstResult(firstResult);
		search.setMaxResults(maxResult);
		search.addFilterEqual("text5", "1");
		search.addSortDesc("create_Date");//根据激活时间降序排列
		SearchResult<YzyUserinfo> searchResult = this.searchAndCount(search);
		return searchResult;
	}

	@Override
	public YzyUserinfo findById(Integer id) {
		return userInfoDao.find(id);
	}

	@Override
	public YzyUserinfo[] findByIds(Integer[] ids) {
		return userInfoDao.find(ids);
	}

	@Override
	public boolean hasUserName(String userName) {
		return false;
	}

	@Override
	public YzyUserinfo findByName(String userName) {
		return null;
	}

	@Override
	public List<YzyUserinfo> findByStatus(String status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SearchResult<YzyUserinfo> searchAndCount(ISearch search) {
		return userInfoDao.searchAndCount(search);
	}

	@Override
	public void flush() {
		userInfoDao.flush();
	}

}
