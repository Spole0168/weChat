package com.zhenjinzi.yzy.action.yinzy;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.zhenjinzi.yzy.action.BaseAction;
import com.zhenjinzi.yzy.model.YzyServiceApply;
import com.zhenjinzi.yzy.model.YzyUserinstallapply;
import com.zhenjinzi.yzy.service.UserInstallApplyService;

/**
 * 查询用户预约列表
 * @author ly
 *
 */
public class UserinstallapplyAction extends BaseAction{

	@Resource
	private UserInstallApplyService applyService;
	
	private List<YzyUserinstallapply> list;
	
	private YzyUserinstallapply installApply;
	
	private Timestamp apply_Date;
	private String userName;
	private String tel;
	
	@Override
	public String execute() throws Exception {
		try {
			SearchResult<YzyUserinstallapply> searchResult = applyService.findEnable(firstResult(),maxResult());
			list = searchResult.getResult();
			totalCount = searchResult.getTotalCount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}
	
	public String search(){
		Search search = new Search();
//		if(serviceApply.getCommunityName() != null && installApply.getCommunityName().length()>0){
//			search.addFilterLike("communityname", "%"+serviceApply.getCommunityName()+"%");
//		}
		if(userName!= null && userName.length()>0){
			search.addFilterLike("userName", "%"+userName+"%");
		}
		if(tel!= null && tel.length()>0){
			search.addFilterLike("telphone", "%"+tel+"%");
		}
		if(apply_Date!=null ){
			long creationD = apply_Date.getTime();
			long onedays = 24*60*60*1000;
			Timestamp creationDtimeafter= new Timestamp(creationD+onedays);
			Timestamp creationDtimebefore= new Timestamp(creationD-onedays);
			search.addFilterGreaterOrEqual("apply_Date", creationDtimebefore);
			search.addFilterLessOrEqual("apply_Date", creationDtimeafter);
		}
//		search.addFilterEqual("text5", "1");
		search.setFirstResult((pageNum-1)*numPerPage);
		search.setMaxResults(pageNum*numPerPage);
		search.addSortDesc("apply_Date");//根据申请时间  降序排列
		SearchResult<YzyUserinstallapply> searchResult = applyService.searchAndCount(search);
		list = searchResult.getResult();
		totalCount = searchResult.getTotalCount();
		return "list";
	}

	public List<YzyUserinstallapply> getList() {
		return list;
	}

	public void setList(List<YzyUserinstallapply> list) {
		this.list = list;
	}

	public YzyUserinstallapply getInstallApply() {
		return installApply;
	}

	public void setInstallApply(YzyUserinstallapply installApply) {
		this.installApply = installApply;
	}

	public Timestamp getApply_Date() {
		return apply_Date;
	}

	public void setApply_Date(Timestamp apply_Date) {
		this.apply_Date = apply_Date;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	
	
}
