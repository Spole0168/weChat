package com.zhenjinzi.yzy.action.manager;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.fire.modules.web.struts2.Struts2Utils;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.zhenjinzi.yzy.action.BaseAction;
import com.zhenjinzi.yzy.model.ZunmiDomainStore;
import com.zhenjinzi.yzy.model.ZunmiUser;
import com.zhenjinzi.yzy.service.ZunmiDomainStoreService;
import com.zhenjinzi.yzy.service.ZunmiUserService;

import freemarker.core.ReturnInstruction.Return;

@Controller
public class DomainStoreAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Resource
	private ZunmiDomainStoreService domainStoreService;
	
	@Resource
	private ZunmiUserService userService;
	
	private ZunmiDomainStore domainStore;
	private Integer domainStoreId;
	private List<ZunmiDomainStore> list;
	private Integer userId;              //将要开通旺铺的用户Id
	private ZunmiUser user;
	
	
	@Override
	public String execute() throws Exception {
		Search search=new Search();
		search.addSortDesc("creationTime");//默认按创建时间降序排列
		search.setFirstResult((pageNum - 1) * numPerPage);
		search.setMaxResults(pageNum * numPerPage);
		SearchResult<ZunmiDomainStore> searchResult =domainStoreService.searchAndCount(search);
		list = searchResult.getResult();
		totalCount = searchResult.getTotalCount();
		return "list";
	}

    //获得将要开旺铺客户信息到界面上
	public String getUserDetail(){
		if(domainStoreId!=null){
			domainStore=domainStoreService.findById(domainStoreId);
			if(userId==null){//2012-07-27这个方法一个是在user-manager-list.jsp(开铺),
				//另一个是在domain-store-list.jsp(编辑)调用
				user=domainStore.getZunmiUser();
			}else{
				user=userService.findById(userId);
				return INPUT;
				}
		}else{
			user=userService.findById(userId);
		}
		
		
		
		return INPUT;
	}
	
	//管理员：查询旺铺 ,查询旺铺值根据用户名查询
	public String search(){
		Search search=new Search();
		if(domainStore!=null&&domainStore.getZunmiUser()!=null&&
				domainStore.getZunmiUser().getUserName()!=null&&
				domainStore.getZunmiUser().getUserName().length()>0){
			
			search.addFilterLike("zunmiUser.userName","%"+ domainStore.getZunmiUser().getUserName()+"%");
		}
		search.addSortDesc("creationTime");//默认按创建时间降序排列
		search.setFirstResult((pageNum - 1) * numPerPage);
		search.setMaxResults(pageNum * numPerPage);
		SearchResult<ZunmiDomainStore> searchResult =domainStoreService.searchAndCount(search);
		list = searchResult.getResult();
		totalCount = searchResult.getTotalCount();
		return SUCCESS;
	}
	
	//管理员：开通旺铺 
	public String adminUpdate(){
		
		boolean flag=false;
		ZunmiDomainStore domainStoreOld=null;
		if(domainStore.getId()!=null){
			System.out.println("旺铺ID"+domainStore.getId());
			domainStoreOld=domainStoreService.findById(domainStore.getId());
			flag=true;
		}
		
		boolean hasStoreName=domainStoreService.hasStoreName(domainStore.getStoreName());
		if(hasStoreName){
			//这么理解啊：如果存在这个店铺名并且与这个店铺名不相同的情况下。才能提示下面的提示消息。
			if(flag){//这是修改下的验证
				if(domainStoreOld.getStoreName().equals(domainStore.getStoreName())){
					
				}
			}else{//这是添加的情况下的验证
				Struts2Utils
				.renderJson("{\"statusCode\":\"300\",\"message\":\"店铺名已存在，请您另换一个吧!\"}");
				return null;
			}
		}
		domainStore.setCreationTime(new Timestamp(System.currentTimeMillis()));
		
		if(domainStore.getRecommend()){
			//TODO 如果是新添加的推荐日期可以**（暂不清楚），如果是修改的呢，就应该获得丄一个推荐日期
			//TODO 这个都是最新添加的，默认是添加的时间吧！
			domainStore.setRecommendTime(new Timestamp(System.currentTimeMillis()));
		}else{
			domainStore.setRecommendTime(new Timestamp(System.currentTimeMillis()));
		}
		domainStore.setZunmiUser(user);
		
		try{
			if(flag){//旺铺编辑
				domainStoreOld.setStoreName(domainStore.getStoreName());
				if(domainStore.getRecommend()){
					domainStoreOld.setRecommendTime(new Timestamp(System.currentTimeMillis()));
				}else{
					domainStoreOld.setRecommendTime(new Timestamp(System.currentTimeMillis()));
				}
				domainStoreService.save(domainStoreOld);
				Struts2Utils.renderJson("{\"statusCode\":\"200\",\"message\":\"操作成功\",\"callbackType\":\"closeCurrent\",\"navTabId\":\"domain_store_list\"}");
			}else{//开通旺铺
				domainStoreService.save(domainStore);
				Struts2Utils.renderJson("{\"statusCode\":\"200\",\"message\":\"操作成功\",\"callbackType\":\"closeCurrent\",\"navTabId\":\"user_manager_list\"}");
			}
		}catch(Exception e){
			e.printStackTrace();
			Struts2Utils.renderJson("{\"statusCode\":\"300\",\"message\":\"添加失败!\"}");
		}
		return null;
	}

	
	//管理员：编辑旺铺  (分两步) 第一步：获得旺铺信息
	public String getDomainStoreInfo(){
		domainStore=domainStoreService.findById(domainStoreId);
		return INPUT;
	}
	
	//管理员：编辑旺铺  (分两步) 第二步：编辑并保存旺铺信息
	public String adminEdit(){
		boolean hasStoreName=domainStoreService.hasStoreName(domainStore.getStoreName());
		if(hasStoreName){
			Struts2Utils
			.renderJson("{\"statusCode\":\"300\",\"message\":\"店铺名已存在，请您另换一个吧!\"}");
		}
		
		
		try{
			domainStoreService.save(domainStore);
			Struts2Utils.renderJson("{\"statusCode\":\"200\",\"message\":\"操作成功\",\"callbackType\":\"closeCurrent\",\"navTabId\":\"admin_list\"}");
		}catch(Exception e){
			Struts2Utils.renderJson("{\"statusCode\":\"300\",\"message\":\"添加失败!\"}");
		}
		return null;
	}
	
	
	//管理员：删除旺铺        --> 单一删除
	public String del(){
		if(httpSession.getAttribute("adminId")==null){
			Struts2Utils.renderJson("{\"statusCode\":\"301\", \"message\":\"会话超时\"}");
		}
		
		try{
			domainStoreService.delete(domainStoreId);
			Struts2Utils.renderJson("{\"statusCode\":\"200\",\"message\":\"操作成功\",\"callbackType\":\"closeCurrent\",\"navTabId\":\"admin_list\"}");
		}catch(Exception e){
			Struts2Utils.renderJson("{\"statusCode\":\"300\",\"message\":\"添加失败!\"}");
		}
		return null;
	}
	
	//用户：旺铺信息修改
	public String userUpdate(){
		if(httpSession.getAttribute("userId")==null){
			return "用户未登录，或是登陆超时！！";
		}
		
		boolean hasStoreName=domainStoreService.hasStoreName(domainStore.getStoreName());
		if(hasStoreName){
			return "店铺名已存在，请您另换一个吧!";
		}
		
		boolean b=domainStoreService.save(domainStore);
		if(b){
			return "添加失败！！";
		}
		return SUCCESS;
	}
	
	
	public ZunmiDomainStore getDomainStore() {
		return domainStore;
	}
	public void setDomainStore(ZunmiDomainStore domainStore) {
		this.domainStore = domainStore;
	}
	
	public Integer getDomainStoreId() {
		return domainStoreId;
	}

	public void setDomainStoreId(Integer domainStoreId) {
		this.domainStoreId = domainStoreId;
	}

	public List<ZunmiDomainStore> getList() {
		return list;
	}
	public void setList(List<ZunmiDomainStore> list) {
		this.list = list;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public ZunmiUser getUser() {
		return user;
	}

	public void setUser(ZunmiUser user) {
		this.user = user;
	}
	

}
