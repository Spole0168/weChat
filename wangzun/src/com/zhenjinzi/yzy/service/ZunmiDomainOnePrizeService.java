package com.zhenjinzi.yzy.service;

import java.util.List;

import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.SearchResult;
import com.zhenjinzi.yzy.model.ZunmiDomainOnePrize;

public interface ZunmiDomainOnePrizeService {
	/**
	 * 根据search查询结果集
	 * 
	 * @param search
	 * @return
	 */
	public SearchResult<ZunmiDomainOnePrize> searchAndCount(ISearch search);
	
	/**
	 * 
	 * @param search
	 * @return
	 */
	public List<ZunmiDomainOnePrize> search(ISearch search);
	
	/**
	 * 根据拍卖域名（一口价）的domainAuctionId 查找
	 * 
	 * @param domainAuctionId
	 * @return 返回ZunmiDomainAuction
	 */
	public ZunmiDomainOnePrize findById(Integer domainAuctionId);

	/**
	 * 删除ZunmiDomainAuction
	 * 
	 * @param domainAuctionDel
	 * @return 删除成功返回true，失败返回false
	 */
	public boolean delete(ZunmiDomainOnePrize domainAuctionDel);

	/**
	 * 根据用户userId，查询出用户一口价列表
	 * 
	 * @param userId
	 * @return
	 */
	public List<ZunmiDomainOnePrize> findByUserId(Integer userId);

	/**
	 * 根据用户ID和一口价域名的ID查找
	 * 
	 * @param userId
	 *            用户Id
	 * @param domainAuctionId
	 *            一口价域名的Id
	 * @return
	 */
	public ZunmiDomainOnePrize findByUserIdAndId(Integer userId,
			Integer domainAuctionId);

	/**
	 * 保存 domainAuction
	 * 
	 * @param domainAuction
	 * @return
	 */
	public boolean save(ZunmiDomainOnePrize domainAuction);

	/**
	 * 根据域名查询一口价域名
	 * 
	 * @param domain
	 * @return
	 */
	public ZunmiDomainOnePrize findByDomainName(String domain);

	/**
	 * 获得一口列表的总数据条数
	 * 
	 * @return
	 */
	public Integer getTotalNumber();

	/**
	 * 查出所有一口价域名
	 * 
	 * @return
	 */
	public List<ZunmiDomainOnePrize> findAll();

	/**
	 * 根据Id删除一口价域名信息
	 * 
	 * @param string
	 */
	public boolean deleteDOPById(Integer domainOPId);

	/**
	 * 一删除多个域名，根据id数组
	 * 
	 * @param intIds
	 */
	public void delDomainOPS(Integer[] intIds);

	/**
	 * 根据域名Id和用户Id查找一口价域名
	 * 
	 * @param domainOnePrizeId
	 * @return
	 */
	public ZunmiDomainOnePrize findByDomainId(Integer domainOnePrizeId,
			Integer userId);

	/**
	 * 查询推荐一口价
	 * @param count
	 * @return
	 */
	List<ZunmiDomainOnePrize> findOnePrizeRecommend(Integer count);

}
