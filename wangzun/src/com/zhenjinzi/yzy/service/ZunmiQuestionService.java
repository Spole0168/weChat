package com.zhenjinzi.yzy.service;

import java.util.List;

import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.SearchResult;
import com.zhenjinzi.yzy.model.ZunmiQuestion;
import com.zhenjinzi.yzy.model.ZunmiUser;

public interface ZunmiQuestionService {
	
	/**
	 * 增加或更新question
	 * @param question
	 * @return
	 */
	public boolean save(ZunmiQuestion question);	
	
	/**
	 * 根据ID获取question记录
	 * @param id
	 * @return
	 */
	public ZunmiQuestion findById(Integer id);
	
	/**
	 * 根据用户ID获取question记录
	 * @param userId
	 * @return
	 */
	public List<ZunmiQuestion> findByUser(ZunmiUser user);
	/**
	 * 根据条件查询ZunmiQuestion
	 * @param search
	 * @return
	 */

	public SearchResult<ZunmiQuestion> searchAndCount(ISearch search);
	/**
	 * 根据反馈问题Id删除
	 * @param questionId
	 * @return
	 */

	public boolean deleteQuestionById(Integer questionId);
	/**
	 * 根据反馈问题Ids删除
	 * @param questionIds
	 * @return
	 */

	public void deleteQuestionByIds(Integer[] questionIds);
	/**
	 * 
	 * @return 返回所有的反馈问题
	 */

	public List<ZunmiQuestion> findAll();
}
