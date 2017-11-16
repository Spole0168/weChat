package com.zhenjinzi.yzy.action.yinzy;

import java.util.List;

import javax.annotation.Resource;

import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.zhenjinzi.yzy.action.BaseAction;
import com.zhenjinzi.yzy.model.YzyAdmin;
import com.zhenjinzi.yzy.model.YzyServiceApply;
import com.zhenjinzi.yzy.model.YzyUserinstallapply;
import com.zhenjinzi.yzy.service.ServiceApplyService;
import com.zhenjinzi.yzy.service.UserInstallApplyService;
/**
 * 银之源  社区服务商申请列表
 * @author ly
 *
 */
public class UserinstalapplyAction extends BaseAction{

	private static final long serialVersionUID = 1L;

	@Resource
	private UserInstallApplyService installapplyService;
	
	private List<YzyUserinstallapply> list;
	
	private YzyServiceApply serviceApply;
	
	private Integer serviceId;
	
	@Override
	public String execute() throws Exception {
		try {
			SearchResult<YzyUserinstallapply> searchResult = installapplyService.findEnable(firstResult(),maxResult());
			list = searchResult.getResult();
			totalCount = searchResult.getTotalCount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}

	public String list() throws Exception {
		list = installapplyService.findAll();
		return "list";
	}
	
	public String search(){
		Search search = new Search();
//		if(admin.getUserName()!=null&&admin.getUserName().length()>0){
//			search.addFilterLike("userName", "%"+admin.getUserName()+"%");
//		}
//		if(admin.getStatus()!=null){
//			search.addFilterEqual("status", admin.getStatus());
//		}
//		if(admin.getCreationDate()!=null){
//			//search.addFilter();
//		}
		search.addFilterNotEqual("status", "DISABLED");
		search.setFirstResult((pageNum-1)*numPerPage);
		search.setMaxResults(pageNum*numPerPage);
		SearchResult<YzyUserinstallapply> searchResult = installapplyService.searchAndCount(search);
		list = searchResult.getResult();
		totalCount = searchResult.getTotalCount();
		return "list";
	}
	
	public UserInstallApplyService getInstallapplyService() {
		return installapplyService;
	}

	public void setInstallapplyService(UserInstallApplyService installapplyService) {
		this.installapplyService = installapplyService;
	}

	public List<YzyUserinstallapply> getList() {
		return list;
	}

	public void setList(List<YzyUserinstallapply> list) {
		this.list = list;
	}

	public YzyServiceApply getServiceApply() {
		return serviceApply;
	}
	public void setServiceApply(YzyServiceApply serviceApply) {
		this.serviceApply = serviceApply;
	}
	public Integer getServiceId() {
		return serviceId;
	}
	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}
	
}
