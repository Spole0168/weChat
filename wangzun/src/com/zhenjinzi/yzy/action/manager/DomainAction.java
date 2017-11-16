package com.zhenjinzi.yzy.action.manager;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.zhenjinzi.yzy.action.BaseAction;
import com.zhenjinzi.yzy.model.ZunmiDomain;
import com.zhenjinzi.yzy.model.ZunmiDomainOnePrize;
import com.zhenjinzi.yzy.service.ZunmiDomainOnePrizeService;
import com.zhenjinzi.yzy.service.ZunmiDomainService;

@Controller
public class DomainAction extends BaseAction{

	private static final long serialVersionUID = 7653999634324698075L;
	
	@Resource
	private ZunmiDomainService domainService;
	@Resource
	private ZunmiDomainOnePrizeService domainOnePrizeService;
	
	private List<ZunmiDomain> list;
	private ZunmiDomain domain;
	private ZunmiDomainOnePrize domainOnePrize;
	@Override
	public String execute() throws Exception {
		Search search=new Search();
//		search.addSortDesc("");
		search.setFirstResult((pageNum-1)*numPerPage);
		search.setMaxResults(pageNum*numPerPage);
		SearchResult<ZunmiDomain> result=domainService.searchAndCount(search);
		list=result.getResult();
		totalCount=result.getTotalCount();
		return "list";
	}
	
	public String search(){
		Search search=new Search();
		
		if(domain.getZunmiUser()!=null&&domain.getZunmiUser().getEmail()!=null
				&&domain.getZunmiUser().getEmail().length()>0){
			search.addFilterLike("zunmiUser.email", "%"+domain.getZunmiUser().getEmail()+"%");
		}
		if(domain.getStatus()!=null&&!domain.getStatus().equals("ALL")){
			search.addFilterEqual("status", domain.getStatus());
		}
		
		if(domain.getDomain()!=null&&domain.getDomain().length()>0){
			int i= domain.getDomain().indexOf('.');
			if(i==-1){
				i=domain.getDomain().length();
			}
			String subDomainName=domain.getDomain().substring(0,i);
			search.addFilterLike("domain", "%"+subDomainName+"%");
		}
		
		search.setFirstResult((pageNum-1)*numPerPage);
		search.setMaxResults(pageNum*numPerPage);
		SearchResult<ZunmiDomain> result=domainService.searchAndCount(search);
		list=result.getResult();
		totalCount=result.getTotalCount();
		return "list";
	}
	
	public List<ZunmiDomain> getList() {
		return list;
	}
	public void setList(List<ZunmiDomain> list) {
		this.list = list;
	}
	public ZunmiDomain getDomain() {
		return domain;
	}
	public void setDomain(ZunmiDomain domain) {
		this.domain = domain;
	}
	public ZunmiDomainOnePrize getDomainOnePrize() {
		return domainOnePrize;
	}
	public void setDomainOnePrize(ZunmiDomainOnePrize domainOnePrize) {
		this.domainOnePrize = domainOnePrize;
	}
}
