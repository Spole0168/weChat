package com.zhenjinzi.yzy.action.manager;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.sql.Timestamp;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.fire.modules.web.struts2.Struts2Utils;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.zhenjinzi.util.EncryptionHelper;
import com.zhenjinzi.yzy.action.BaseAction;
import com.zhenjinzi.yzy.model.YzyAdmin;
import com.zhenjinzi.yzy.model.ZunmiDomain;
import com.zhenjinzi.yzy.model.ZunmiDomainOnePrize;
import com.zhenjinzi.yzy.model.ZunmiUser;
import com.zhenjinzi.yzy.model.enums.AuctionStatus;
import com.zhenjinzi.yzy.model.enums.AuctionStyle;
import com.zhenjinzi.yzy.service.ZunmiAdminService;
import com.zhenjinzi.yzy.service.ZunmiDomainOnePrizeService;
import com.zhenjinzi.yzy.service.ZunmiDomainService;
import com.zhenjinzi.yzy.service.ZunmiUserService;

@Controller
public class DomainSaleAction extends BaseAction{

	private static final long serialVersionUID = 6705211043539529559L;

	/**
	 * author LY
	 * 关于    一口价域名    
	 * 管理员功能：
	 */
	@Resource
	private ZunmiDomainOnePrizeService domainOnePrizeService;
	@Resource
	private ZunmiAdminService adminService;
	@Resource
	private ZunmiUserService userService;
	
	@Resource
	private ZunmiDomainService domainService;
	
	private List<ZunmiDomainOnePrize> list;
	private ZunmiDomainOnePrize domainOnePrize;//查询
	private Integer domainOnePrizeId;
	private List<ZunmiUser> userList;
	private List<ZunmiDomain> domainList;
	private Double totalDays;
	
	private Integer onePriceLittle;//查询价钱较小者
	private Integer onepriceGreate;//查询价钱较大者
	
	private Timestamp littleTime;//查询时间较小者
	private Timestamp greatTime;//查询时间较大者
	
	
	public String execute(){//
		
		Search search=new Search();
		search.addSortDesc("addDate");            //按照拍卖时间降序排列
		search.setFirstResult((pageNum-1)*numPerPage);
		search.setMaxResults(pageNum*numPerPage);
		SearchResult<ZunmiDomainOnePrize> searchResult=domainOnePrizeService.searchAndCount(search);
		list=searchResult.getResult();
		totalCount=searchResult.getTotalCount();
		return "list";
	}
	
	
	//查询出售域名:
	public String search(){
		
		AuctionStatus status=turnStatus(request.getParameter("domainAuctionStatus"));
//		domainOnePrize.setStatus(status); TODO
		
		Search search=new Search();
		if(domainOnePrize!=null&&domainOnePrize.getDomainName()!=null&&domainOnePrize.getDomainName().length()>0){
			search.addFilterLike("domainName", "%"+domainOnePrize.getDomainName()+"%");
		}
		
		if(domainOnePrize.getStatus()!=null&&!domainOnePrize.getStatus().equals("ALL")){
			search.addFilterEqual("status", domainOnePrize.getStatus());
		}
		
		if(domainOnePrize.getZunmiUser()!=null&&domainOnePrize.getZunmiUser().getUserName()!=null
				&&domainOnePrize.getZunmiUser().getUserName().length()>0){
			search.addFilterLike("zunmiUser.userName", domainOnePrize.getZunmiUser().getUserName());
		}
		
		if(littleTime!=null&&greatTime!=null&&littleTime.before(greatTime)){
			search.addFilterGreaterOrEqual("addDate", littleTime);
			search.addFilterLessOrEqual("addDate", greatTime);  
		}else if(littleTime!=null&&greatTime!=null&&greatTime.before(littleTime)){
			search.addFilterGreaterOrEqual("addDate", greatTime);
			search.addFilterLessOrEqual("addDate",littleTime);  
		}//可以继续加判断
		//价钱区间--onePriceLittle    greatTime
		if(onePriceLittle!=null&&onepriceGreate!=null&&onePriceLittle<onepriceGreate){
			search.addFilterGreaterOrEqual("prize", onePriceLittle);
			search.addFilterLessOrEqual("prize", onepriceGreate);  
		}else if(onePriceLittle!=null&&onepriceGreate!=null&&onePriceLittle>onepriceGreate){
			search.addFilterGreaterOrEqual("prize",onepriceGreate);
			search.addFilterLessOrEqual("prize",onePriceLittle);  
		}else if(onePriceLittle!=null&&onepriceGreate==null){
			search.addFilterGreaterOrEqual("prize",onePriceLittle);
		}else if(onePriceLittle==null&&onepriceGreate!=null){
			search.addFilterLessOrEqual("prize",onepriceGreate);  
			search.addFilterGreaterOrEqual("prize",0);//***************
		}
	
		search.setFirstResult((pageNum-1)*numPerPage);
		search.setMaxResults(pageNum*numPerPage);
		SearchResult<ZunmiDomainOnePrize> searchResult=domainOnePrizeService.searchAndCount(search);
		list=searchResult.getResult();
		totalCount=searchResult.getTotalCount();
		return "list";
	}
	
	//管理出售域名:编辑,1
	public String getDomainOPDetail(){
		if(domainOnePrizeId==null){//新添加的
			return INPUT;
		}
		domainOnePrize=domainOnePrizeService.findById(domainOnePrizeId);//存在domainAuctionId
		return INPUT;
	}
	
    @Override
	public String input() throws Exception {
		return "list";
	}
    
	public String addDomainOP(){
	    String domainId=request.getParameter("domainId");
	    ZunmiDomain domain=domainService.findById(Integer.parseInt(domainId));
	    ZunmiDomainOnePrize onePrize=domainOnePrizeService.findByDomainName(domain.getDomain());
	    if(onePrize!=null){   //存在可以编辑
	    	domainOnePrize=onePrize;
	    	return INPUT;
	    }
	    domainOnePrize=new ZunmiDomainOnePrize();
	    	
	    domainOnePrize.setDomainName(domain.getDomain());
	    domainOnePrize.setZunmiDomain(domain);
	    domainOnePrize.setZunmiUser(domain.getZunmiUser());
	    domainOnePrize.setStatus(domain.getStatus());
	    domainOnePrize.setIsRecommend(false);
	    return INPUT;
	  }    

//    //就是AJAX查询管理员所输入的邮箱是不是所要添加一口价域名的用户
//    public String searchSaleAccount(){
//    	String email=request.getParameter("email");
//    	String subEmail=null;    	
//    	try{
//    		subEmail=email.substring(0, email.indexOf("@"));
//    	}catch(Exception e){
//    		subEmail=email;
//    	}
//    	Search search=new Search();
//    	search.addFilterLike("email", "%"+subEmail+"%");
////    	search.setMaxResults(5);
//    	SearchResult<ZunmiUser> result=userService.searchAndCount(search);
//    	userList=result.getResult();
//    	StringBuffer sb=new StringBuffer("与检测账号相似的邮箱:");
//    	for (ZunmiUser list : userList) {
//			sb.append("用户Id："+list.getId()+",邮箱："+list.getEmail()+",账号："+list.getUserName()+";");
//		}
//    	if(userList==null||userList.size()==0){
//    		sb.append("暂时还没有和此账号相类似的账号");
//    	}
//    	System.out.println(userList.size());
//    	String info=sb.toString();
//    	Struts2Utils.renderJson("{\"uses\":\""+info+"\"}");
//    	System.out.println("{\"uses\":\""+info+"\"}");
//    	return null;
//    }

	//	管理出售域名:编辑，2  以及添加
	public String save(){
		
		boolean flag=false;//用flag标识是编辑（managerSlaeDomain）还是添加（addSaleDomain）
		ZunmiDomainOnePrize domainOnePrizeOld=null;
		if(domainOnePrize.getId()!=null){
			domainOnePrizeOld=domainOnePrizeService.findById(domainOnePrize.getId());
			flag=true;             //
		}
		ZunmiDomain domain=domainService.findById(domainOnePrize.getZunmiDomain().getId());
		domainOnePrize.setDomainName(domainOnePrize.getDomainName().toLowerCase());
		if(domainOnePrize.getPrize()==null||domainOnePrize.getPrize()<0){
			Struts2Utils.renderJson("{\"statusCode\":\"300\", \"message\":\"价格不合理\"}");
			return null;
		}
		//开始时间不能小2当前时间    TODO
		if(domainOnePrize.getStatus()==null||domainOnePrize.getStatus().equals("ALL")){
			Struts2Utils.renderJson("{\"statusCode\":\"300\", \"message\":\"域名状态还没选择！！\"}");
			return null;
		}
		if(totalDays==null||totalDays<=0){
			Struts2Utils.renderJson("{\"statusCode\":\"300\", \"message\":\"一口价有效天数不合理呀！\"}");
			return null;
		}
		
		if(domainOnePrize.getIsRecommend()==null){
			Struts2Utils.renderJson("{\"statusCode\":\"300\", \"message\":\"是否推荐还没有选择呀！\"}");
			return null;
		}
		Date finishDate=double2Date(totalDays,domainOnePrize.getAddDate());
		domainOnePrize.setEndDate(finishDate);
		
		ZunmiUser user=userService.findByEmail(domainOnePrize.getZunmiUser().getEmail());
		if(user==null){
			Struts2Utils.renderJson("{\"statusCode\":\"300\", \"message\":\"用户不存在！\"}");
			return null;
		}
		domainOnePrize.setZunmiUser(user);
		domainOnePrize.setZunmiDomain(domain);
		//编辑域名
		if(domainOnePrizeOld!=null){
			domainOnePrizeOld.setAddDate(domainOnePrize.getAddDate());
			domainOnePrizeOld.setEndDate(domainOnePrize.getEndDate());
			domainOnePrizeOld.setDescription(domainOnePrize.getDescription());
			domainOnePrizeOld.setPrize(domainOnePrize.getPrize());
			domainOnePrizeOld.setStatus(domainOnePrize.getStatus());
			domainOnePrizeOld.setDomainName(domainOnePrize.getDomainName());
			domainOnePrizeOld.setDomainItems(domainOnePrize.getDomainItems());
			domainOnePrizeOld.setZunmiDomain(domain);
			domainOnePrizeOld.setZunmiUser(user);
		}
		try{
			if(flag){//编辑
				domainOnePrizeService.save(domainOnePrizeOld);
			}else{//添加
				domainOnePrizeService.save(domainOnePrize);
			}
			Struts2Utils.renderJson("{\"statusCode\":\"200\",\"message\":\"操作成功\",\"callbackType\":\"closeCurrent\",\"navTabId\":\"domain_sale_list\"}");
		}catch(Exception e){
			e.printStackTrace();
			Struts2Utils.renderJson("{\"statusCode\":\"300\",\"message\":\"操作失败!\"}");
		}
		return null;
	}
	public String login(){
		 String userName = request.getParameter("j_userName");
		 String password = request.getParameter("j_password");
		 if(userName==null||userName.trim().equals("")){
			 Struts2Utils.renderJson("{\"statusCode\":\"200\",\"message\":\"操作成功\",\"callbackType\":\"closeCurrent\",\"navTabId\":\"domain_sale_list\"}");
			 return null;
		 }
		 if(password==null||userName.trim().equals("")){
			 Struts2Utils.renderJson("{\"statusCode\":\"200\",\"message\":\"操作成功\",\"callbackType\":\"closeCurrent\",\"navTabId\":\"domain_sale_list\"}");
			 return "";
		 }
		 
		 YzyAdmin admin = adminService.findByName(userName);
		 if (admin == null) {
			 Struts2Utils.renderJson("{\"statusCode\":\"200\",\"message\":\"操作成功\",\"callbackType\":\"closeCurrent\",\"navTabId\":\"domain_sale_list\"}");
			 return "";
		 }
		 if (!admin.getPassword().equals(EncryptionHelper.Enc(password,"SHA-1"))) {
			 Struts2Utils.renderJson("{\"statusCode\":\"200\",\"message\":\"操作成功\",\"callbackType\":\"closeCurrent\",\"navTabId\":\"domain_sale_list\"}");
			 return "";
		 }
		 
		 httpSession.setAttribute("adminId", admin.getId());
		 httpSession.setAttribute("adminName",admin.getUserName());
		 admin.setCountOfLogin(admin.getCountOfLogin()+1);
		 adminService.save(admin);
		 Struts2Utils.renderJson("{\"statusCode\":\"200\",\"message\":\"操作成功\",\"callbackType\":\"closeCurrent\",\"navTabId\":\"domain_sale_list\"}");
		 return "";
	}
	
	
	
	private Date double2Date(Double totalDays2, Date addDate) {
		long days=(long) (totalDays2*24*60*60*1000);
		long addDate2Long=addDate.getTime();
		Date finishDate=new Date(days+addDate2Long);
		return finishDate;
	}


	//处理String转换成AuctionStatus（枚举）方法
	private static AuctionStatus turnStatus(String parameter) {
		AuctionStatus[] status=AuctionStatus.values();
		for (int i = 0; i < status.length; i++) {
			if(status[i].toString().equals(parameter)){
				return status[i];
			}
		}
		return null;
	}


	//删除出售域名
	public String del(){
		try{
			ZunmiDomainOnePrize domainAuctionDel=domainOnePrizeService.findById(domainOnePrizeId);
			domainOnePrizeService.delete(domainAuctionDel);
			Struts2Utils.renderJson("{\"statusCode\":\"200\",\"message\":\"删除成功!\",\"navTabId\":\"domain_sale_list\"}");
		}catch(Exception e){
			e.printStackTrace();
			Struts2Utils.renderJson("{\"statusCode\":\"300\",\"message\":\"删除失败!\"}");
		}
		return null;
	}
	
	/**
	 * 用户：一口价
	 * 域名出售
	 * 域名出售管理
	 * 域名出售查询
	 * 域名停止查询
	 */
	//域名出售
	public String userSaleDomain(){
		if(httpSession.getAttribute("")==null){
			return "用户未登陆或是登陆超时";
		}
		
		boolean b=domainOnePrizeService.save(domainOnePrize);
		if(!b){
			return "添加失败！！";
		}
		return SUCCESS;
	}
	 // 域名出售管理
	 //域名出售查询，查询？只是查询用户自己的一口价
	public String userSearchOnePrize(){
		if(httpSession.getAttribute("")==null){
			return "用户未登陆或是登陆超时";
		}
		Search search=new Search();
		
		search.addFilterEqual("auctionStyle",AuctionStyle.OTHER);//拍卖方式为OTHER（其它：即一口价）
		search.addSortDesc("auctionTime");            //按照拍卖时间降序排列
		search.setFirstResult((pageNum-1)*numPerPage);
		search.setMaxResults(pageNum*numPerPage);
		SearchResult<ZunmiDomainOnePrize> searchResult=domainOnePrizeService.searchAndCount(search);
		list=searchResult.getResult();
		totalCount=searchResult.getTotalCount();
		return SUCCESS;
	}
	
	 //停止出售
	public String stopSaleDomain(){
		if(httpSession.getAttribute("userId")==null){
			return "用户未登录或是登陆超时";
		}
		Integer userId=Integer.parseInt(httpSession.getAttribute("userId").toString());
		ZunmiDomainOnePrize saleDomain=domainOnePrizeService.findByUserIdAndId(userId,domainOnePrizeId);
		
		boolean f=false;
		if(saleDomain!=null){//saleDomain为null，说明用户没权利对这个一口价域名做操作
			f=domainOnePrizeService.delete(saleDomain);
		}
		if(!f){
			return "停止出售失败！";
		}
		return SUCCESS;
	}
	
	public List<ZunmiDomainOnePrize> getList() {
		return list;
	}
	public void setList(List<ZunmiDomainOnePrize> list) {
		this.list = list;
	}
	public ZunmiDomainOnePrize getDomainOnePrize() {
		return domainOnePrize;
	}
	public void setDomainOnePrize(ZunmiDomainOnePrize domainOnePrize) {
		this.domainOnePrize = domainOnePrize;
	}
	public Timestamp getLittleTime() {
		return littleTime;
	}
	public void setLittleTime(Timestamp littleTime) {
		this.littleTime = littleTime;
	}

	public Timestamp getGreatTime() {
		return greatTime;
	}

	public void setGreatTime(Timestamp greatTime) {
		this.greatTime = greatTime;
	}
	public Integer getDomainOnePrizeId() {
		return domainOnePrizeId;
	}
	public void setDomainOnePrizeId(Integer domainOnePrizeId) {
		this.domainOnePrizeId = domainOnePrizeId;
	}
	public List<ZunmiUser> getUserList() {
		return userList;
	}
	public void setUserList(List<ZunmiUser> userList) {
		this.userList = userList;
	}
	public List<ZunmiDomain> getDomainList() {
		return domainList;
	}
	public void setDomainList(List<ZunmiDomain> domainList) {
		this.domainList = domainList;
	}

	public Integer getOnePriceLittle() {
		return onePriceLittle;
	}

	public void setOnePriceLittle(Integer onePriceLittle) {
		this.onePriceLittle = onePriceLittle;
	}
	public Integer getOnepriceGreate() {
		return onepriceGreate;
	}
	public void setOnepriceGreate(Integer onepriceGreate) {
		this.onepriceGreate = onepriceGreate;
	}
	public Double getTotalDays() {
		return this.totalDays;
	}
	public void setTotalDays(Double totalDays) {
		this.totalDays = totalDays;
	}
}
