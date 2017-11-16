package com.zhenjinzi.yzy.service;

import java.text.ParseException;
import java.util.List;

import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.SearchResult;
import com.zhenjinzi.yzy.model.ZunmiContent;

public interface ZunmiContentService {

	/**
	 * 增加或更新content
	 * @param content
	 * @return
	 */
	public boolean save(ZunmiContent content);
	
	/**
	 * 删除content
	 * @param contentId
	 * @return
	 */
	public boolean removeById(Integer contentId);
	
	/**
	 * 根据主键查询content
	 * @param id
	 * @return
	 */
	public ZunmiContent findById(Integer id);
	
	/**
	 * 根据条件查询content记录
	 * @param search
	 * @return
	 */
	public SearchResult<ZunmiContent> searchAndCount(ISearch search);
	
	/**
	 * 查询content幻灯记录
	 * @param count 新闻数
	 * @return
	 */
	public List<ZunmiContent> searchSlideShowContent(Integer count);
	
	
	/**
	 * 查询content最新记录
	 * @param count 新闻数
	 * @return
	 */
	public List<ZunmiContent> searchLastContent(Integer count);
	
	/**
	 * 根据缩略图imageUrl是不为空的域名新闻
	 * 根据添加时间降序排列
	 * @param nodeId zunmiNode的id值
	 * @param firstResult 从第几条数据开始
	 * @param count 插叙个数
	 * @return
	 */
	public List<ZunmiContent> searchContentByImages(Integer nodeId,Integer firstResult, Integer count);
	/**
	 * 查询content头条记录
	 * @param count 新闻数
	 * @return
	 */
	public List<ZunmiContent> searchTopContent(Integer count);
	
	/**
	 * 查询content推荐记录
	 * @param count 新闻数
	 * @return
	 */
	public List<ZunmiContent> searchRecommend(Integer count);
	
	/**
	 * 查询content特别推荐记录
	 * @param count 新闻数
	 * @return
	 */
	public List<ZunmiContent> searchSpecialRecommend(Integer count);

	/**
	 * 查询content特别推荐记录(带缩略图)
	 * @param count 新闻数
	 * @return
	 */
	public List<ZunmiContent> searchSpecialRecommendHasImg(Integer count);
    /**
     * 查询某个栏目下（包含子栏目）已生成页面的content记录
     * 
     * @param nodeId 栏目编号
     * @param count 新闻数
     * @return
     */
	public List<ZunmiContent> searchContentByNodeId(Integer nodeId,Integer count);
	
	/**
     * 查询某个栏目下（包含子栏目）的content记录
     * @param nodeId 栏目编号
     * @return
     */
	public List<ZunmiContent> searchContentByNodeId(Integer nodeId);
	/**
	 * 查询某栏目结点下的头条
	 * @param nodeId
	 * @param count
	 * @return
	 */
	public List<ZunmiContent> searchTopContentByNodeId(Integer nodeId,Integer count);
	

	
	/**
	 * 查询周热点资讯
	 * @param count 新闻数
	 * @return
	 */
	public List<ZunmiContent> searchContentHitsByWeek(Integer count);
	
	/**
	 * 查询月热点资讯
	 * @param count 新闻数
	 * @return
	 * @throws ParseException 
	 */
	public List<ZunmiContent> searchContentHitsByMonth(Integer count);
	
	/**
	 * 持久化session数据到数据库
	 */
	public void flush();

	/**
	 * LY获得nodeId下面新闻的总条数
	 * @param nodeId
	 * @return
	 */
	public Integer getTotalNumber(Integer nodeId);

	/**LY
	 * 根据nodeId和第一条记录位置firstResult，查询几条perPageNum查询ZunmiContent
	 * @param nodeId
	 * @param firstResult
	 * @param perPageNum
	 * @return
	 */
	public List<ZunmiContent> searchContentByNodeIdAndFirstR(Integer nodeId, int firstResult,
			int perPageNum);
	/**
	 * 查找上一篇文章(时间稍早的)
	 * @param contentId 文章id 
	 * @return
	 */
	public ZunmiContent findPreviousPage(Integer contentId);
	/**
	 * 查找下一篇文章(时间稍晚的)
	 * @param contentId　文章id 
	 * @return
	 */
	public ZunmiContent findNextPage(Integer contentId);
	/**
	 * 根据据结点查找推荐内容　
	 * @param nodeId　结点id
	 * @param count　查询的条数
	 * @return
	 */
	public List<ZunmiContent> searchRecommendContentByNodeId(Integer nodeId,Integer count);
	/**
	 * 查询某栏目下的最新的带有图片的新闻
	 * @param nodeId
	 * @param count
	 * @return
	 */
	public List<ZunmiContent> searchImageContentByNodeId(Integer nodeId,Integer count);
	
}
