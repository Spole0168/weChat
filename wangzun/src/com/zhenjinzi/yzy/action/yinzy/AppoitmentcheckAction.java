package com.zhenjinzi.yzy.action.yinzy;

import java.util.List;

import javax.annotation.Resource;

import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.zhenjinzi.yzy.action.BaseAction;
import com.zhenjinzi.yzy.model.YzyAppoitmentcheck;
import com.zhenjinzi.yzy.model.YzyServiceApply;
import com.zhenjinzi.yzy.service.AppoitmentcheckService;

/**
 * 预约检测维修列表
 * @author ly
 *
 */
public class AppoitmentcheckAction extends BaseAction{
	
	@Resource
	private AppoitmentcheckService  appoitmentcheckService;

	private List<YzyAppoitmentcheck> list;
	
	private YzyAppoitmentcheck serviceApply;
	
	private Integer serviceId;
	
	@Override
	public String execute() throws Exception {
		try {
			SearchResult<YzyAppoitmentcheck> searchResult = appoitmentcheckService.findEnable(firstResult(),maxResult());
			list = searchResult.getResult();
			totalCount = searchResult.getTotalCount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}

	public String search(){
		Search search = new Search();
//		if(serviceApply.getCommunityName() != null && serviceApply.getCommunityName().length()>0){
//			search.addFilterLike("communityname", "%"+serviceApply.getCommunityName()+"%");
//		}
//		if(serviceApply.getApplyeName()!= null && serviceApply.getApplyeName().length()>0){
//			search.addFilterLike("applyename", "%"+serviceApply.getApplyeName()+"%");
////			search.addFilterEqual("status", admin.getStatus());
//		}
		if(serviceApply!=null && serviceApply.getAppoit_date()!=null ){
			search.addFilterEqual("appoit_date", serviceApply.getAppoit_date());
			//search.addFilter();
		}
//		search.addFilterNotEqual("status", "DISABLED");
		search.setFirstResult((pageNum-1)*numPerPage);
		search.setMaxResults(pageNum*numPerPage);
		search.addSortDesc("create_Date");//申请时间降序
		SearchResult<YzyAppoitmentcheck> searchResult = appoitmentcheckService.searchAndCount(search);
		list = searchResult.getResult();
		totalCount = searchResult.getTotalCount();
		return "list";
	}
	
	public AppoitmentcheckService getAppoitmentcheckService() {
		return appoitmentcheckService;
	}

	public void setAppoitmentcheckService(
			AppoitmentcheckService appoitmentcheckService) {
		this.appoitmentcheckService = appoitmentcheckService;
	}

	public List<YzyAppoitmentcheck> getList() {
		return list;
	}

	public void setList(List<YzyAppoitmentcheck> list) {
		this.list = list;
	}

	public YzyAppoitmentcheck getServiceApply() {
		return serviceApply;
	}

	public void setServiceApply(YzyAppoitmentcheck serviceApply) {
		this.serviceApply = serviceApply;
	}

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}
	
	
}
