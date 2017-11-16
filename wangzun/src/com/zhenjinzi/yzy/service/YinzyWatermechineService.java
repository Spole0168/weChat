//package com.zhenjinzi.yzy.service;
//
//import java.util.List;
//
//import com.googlecode.genericdao.search.ISearch;
//import com.googlecode.genericdao.search.SearchResult;
////import com.zhenjinzi.yzy.model.YzyAdmin;
//import com.zhenjinzi.yzy.model.YzyServiceApply;
//import com.zhenjinzi.yzy.model.YzyUserinstallapply;
//import com.zhenjinzi.yzy.model.YzyWatermachine;
//
///***
// * 用户预约申请安装service
// * @author ly
// *
// */
//public interface YinzyWatermechineService {
//
//	/**
//	 * 增加或更新serviceInfo
//	 * 
//	 * @param serviceInfo
//	 * @return
//	 */
//	public boolean save(YzyWatermachine installApply);
//
//	/**
//	 * 批量增加或更新serviceInfo
//	 * 
//	 * @param serviceInfo
//	 * @return
//	 */
//	public boolean[] save(YzyWatermachine[] installApplys);
//
//	/**
//	 * 查询serviceInfo数据记录
//	 * 
//	 * @return
//	 */
//	public List<YzyWatermachine> findAll();
//
//	/**
//	 * 查询有效serviceInfo记录
//	 * @return
//	 */
//	public SearchResult<YzyWatermachine> findEnable(int firstResult,int maxResult);
//	
//	/**
//	 * 根据主键查询admin
//	 * 
//	 * @param id
//	 * @return
//	 */
//	public YzyWatermachine findById(Integer id);
//
//	/**
//	 * 批量根据主键查询serviceInfo记录
//	 * 
//	 * @param ids
//	 * @return
//	 */
//	public YzyWatermachine[] findByIds(Integer[] ids);
//
//	/**
//	 * 查询用户名是否存在
//	 * 
//	 * @param userName
//	 * @return
//	 */
//	public boolean hasUserName(String userName);
//
//	/**
//	 * 根据用户名查询admin记录
//	 * 
//	 * @param userName
//	 * @return
//	 */
//	public YzyWatermachine findByName(String userName);
//
//	/**
//	 * 根据用户状态查询admin记录
//	 * 
//	 * @param status
//	 * @return
//	 */
//	public List<YzyWatermachine> findByStatus(String status);
//
//	/**
//	 * 根据条件查询admin记录
//	 * @param search
//	 * @return
//	 */
//	public SearchResult<YzyWatermachine> searchAndCount(ISearch search);
//	
//	/**
//	 * 持久化session数据到数据库
//	 */
//	public void flush();
//}
