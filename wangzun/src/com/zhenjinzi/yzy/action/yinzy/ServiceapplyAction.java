package com.zhenjinzi.yzy.action.yinzy;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import com.fire.modules.web.struts2.Struts2Utils;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.zhenjinzi.util.EncryptionHelper;
import com.zhenjinzi.yzy.action.BaseAction;
import com.zhenjinzi.yzy.model.YzyAdmin;
import com.zhenjinzi.yzy.model.YzyServiceApply;
import com.zhenjinzi.yzy.service.ServiceApplyService;
/**
 * 银之源  社区服务商申请列表
 * @author ly
 *
 */
public class ServiceapplyAction extends BaseAction{

	private static final long serialVersionUID = 1L;

	@Resource
	private ServiceApplyService applyService;
	
	private List<YzyServiceApply> list;
	
	private YzyServiceApply serviceApply;
	
	private Integer serviceId;
	
	private String community_name;
	private String applyeName;
	
	@Override
	public String execute() throws Exception {
		try {
			SearchResult<YzyServiceApply> searchResult = applyService.findEnable(firstResult(),maxResult());
			list = searchResult.getResult();
			totalCount = searchResult.getTotalCount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}

	public String list() throws Exception {
		list = applyService.findAll();
		return "list";
	}
	
	public String save() {
//		if (httpSession.getAttribute("adminId") == null) {
//			Struts2Utils.renderJson("{\"statusCode\":\"301\"}");
//			return null;
//		}
		try {
			applyService.save(serviceApply);
			Struts2Utils.renderJson("{\"statusCode\":\"200\",\"message\":\"修改成功!\",\"callbackType\":\"closeCurrent\"}");
		} catch (Exception e) {
			e.printStackTrace();
			Struts2Utils.renderJson("{\"statusCode\":\"300\",\"message\":\"操作失败!\"}");
		}
		return null;
	}
	
	public String search(){
		Search search = new Search();
		if(community_name != null && community_name.length()>0){
			search.addFilterLike("communityName", "%"+community_name+"%");
		}
		if(applyeName!= null && applyeName.length()>0){
			search.addFilterLike("applyeName", "%"+applyeName+"%");
//			search.addFilterEqual("status", admin.getStatus());
		}
//		if(serviceApply.getApplyDate()!=null ){
//			search.addFilterEqual("applydate", serviceApply.getApplyDate());
//			//search.addFilter();
//		}
//		search.addFilterNotEqual("status", "DISABLED");
		search.setFirstResult((pageNum-1)*numPerPage);
		search.setMaxResults(pageNum*numPerPage);
		SearchResult<YzyServiceApply> searchResult = applyService.searchAndCount(search);
		list = searchResult.getResult();
		totalCount = searchResult.getTotalCount();
		return "list";
	}

	public String input(){
		if(serviceId>0){serviceApply = applyService.findById(serviceId);}
		return INPUT;
	}
	
	public String show(){
		if(serviceId>0){serviceApply = applyService.findById(serviceId);}
		return "show";
	}
	
	public ServiceApplyService getApplyService() {
		return applyService;
	}
	public void setApplyService(ServiceApplyService applyService) {
		this.applyService = applyService;
	}
	public List<YzyServiceApply> getList() {
		return list;
	}
	public void setList(List<YzyServiceApply> list) {
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

	public String getCommunity_name() {
		return community_name;
	}

	public void setCommunity_name(String community_name) {
		this.community_name = community_name;
	}

	public String getApplyeName() {
		return applyeName;
	}

	public void setApplyeName(String applyeName) {
		this.applyeName = applyeName;
	}
	
}
