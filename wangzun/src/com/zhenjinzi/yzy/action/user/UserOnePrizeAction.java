package com.zhenjinzi.yzy.action.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.fire.modules.web.struts2.Struts2Utils;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.zhenjinzi.util.Arith;
import com.zhenjinzi.yzy.action.UserBaseAction;
import com.zhenjinzi.yzy.model.ZunmiDomain;
import com.zhenjinzi.yzy.model.ZunmiDomainOnePrize;
import com.zhenjinzi.yzy.model.ZunmiMessage;
import com.zhenjinzi.yzy.model.ZunmiUser;
import com.zhenjinzi.yzy.model.ZunmiUserLog;
import com.zhenjinzi.yzy.model.enums.MessageStatus;
import com.zhenjinzi.yzy.service.ZunmiDomainOnePrizeService;
import com.zhenjinzi.yzy.service.ZunmiDomainService;
import com.zhenjinzi.yzy.service.ZunmiUserLogService;


@Results({
	@Result(name="domainOnePrizeList",location="/WEB-INF/content/user/accountpages/domainOnePrizeList.jsp"),
	@Result(name="domainOnePrizeDetail",location="/WEB-INF/content/user/accountpages/domainOnePrizeDetail.jsp"),
	@Result(name="accountIndex",location="/WEB-INF/content/user/accountpages/accountIndex.jsp")
})
public class UserOnePrizeAction extends UserBaseAction{

	private static final long serialVersionUID = 1L;
	
	@Resource
	private ZunmiDomainOnePrizeService domainOnePrizeService;
	@Resource
	private ZunmiDomainService domainService;
	@Resource
	private ZunmiUserLogService userLogService;
	
	private ZunmiUser user;
	private ZunmiUserLog userLog;
	private List<ZunmiDomainOnePrize> list;
	private ZunmiDomainOnePrize domainOnePrize;
	private Integer domainOnePrizeId;
	private Integer domainId;
	
	private List<Integer> pageList;
	private int end;
	private int begin;
	private int totalPages;
	
	@Action("/user/userDomainOnePrize")
	public String execute(){
		ZunmiUser user=checkUser();
		Search search=new Search();
		
		search.setFirstResult((pageNum - 1) * numPerPage);
		search.setMaxResults(numPerPage);
		search.addFilterEqual("zunmiUser", user);
		search.addSortDesc("addDate");
		SearchResult<ZunmiDomainOnePrize> result=domainOnePrizeService.searchAndCount(search);
		list=result.getResult();
		totalCount=result.getTotalCount();
		pageList(user);
		return "domainOnePrizeList";
	}

	@Action("/user/domainOnePrizeListajax")
	public String domainOnePrizeListAjax(){
		ZunmiUser user=checkUser();
		Search search=new Search();
		
		search.setFirstResult((pageNum - 1) * numPerPage);
		search.setMaxResults(numPerPage);
		search.addFilterEqual("zunmiUser", user);
		search.addSortDesc("addDate");
		SearchResult<ZunmiDomainOnePrize> result=domainOnePrizeService.searchAndCount(search);
		list=result.getResult();
		totalCount=result.getTotalCount();
		pageList(user);
		pageListAjax();
		return null;
	}
	
	@Action("/user/deletedomainOnePrizes")
	public String delDomainOnePrizes(){//多删除
		ZunmiUser user=checkUser();
		String domainOPIdsStr=request.getParameter("domainopIds");
		
		if(domainOPIdsStr==null){
			Struts2Utils.renderJson("{\"failed\":\"您输入有误或是还没选择要删除的站内消息！\"}");
			return null;
		}
		domainOPIdsStr=domainOPIdsStr.substring(domainOPIdsStr.indexOf(";")+1);
		String[] ids=domainOPIdsStr.split(";");
		Integer[] intIds=new Integer[ids.length];
		for(int i=0;i<ids.length;i++){
			try{//如id不是数字将是异常，所以
			Integer kj=Integer.parseInt(ids[i]);
			intIds[i]=kj;
			}catch(Exception e){}
		}
		try{
		       domainOnePrizeService.delDomainOPS(intIds);
		}catch(Exception e){
		}
		Search search=new Search();
		search.setFirstResult((pageNum - 1) * numPerPage);
		search.setMaxResults(numPerPage);
		search.addFilterEqual("zunmiUser", user);
		search.addSortDesc("addDate");
		SearchResult<ZunmiDomainOnePrize> result=domainOnePrizeService.searchAndCount(search);
		list=result.getResult();
		totalCount=result.getTotalCount();
		pageList(user);
		pageListAjax();
		return null;
	}
	
	@Action("/user/searchDomainOP")
	public String searchDomainOP(){
		ZunmiUser user=checkUser();
		Search search=new Search();
		
		String domainName = request.getParameter("domainName");
		if (domainName != null && domainName.length() > 0) {
			search.addFilterLike("domainName", "%" + domainName + "%");
		}
		search.setFirstResult((pageNum - 1) * numPerPage);
		search.setMaxResults(numPerPage);
		search.addFilterEqual("zunmiUser", user);
		search.addSortDesc("addDate");
		SearchResult<ZunmiDomainOnePrize> result=domainOnePrizeService.searchAndCount(search);
		list=result.getResult();
		totalCount=result.getTotalCount();
		pageList(user);
		pageListAjax();
		return null;
	}
	@Action("/user/getDomainOP")
	public String getDomainById(){//获得一口价域名,id是从域名列表出来的。 验证域名时通过验证的 TODO
		 user=checkUser();
		if(domainId==null){
			try {
				user = checkUser();
				userLog = userLogService.getLastLoginLog(user.getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "accountIndex";
		}
		ZunmiDomain domain=domainService.findById(domainId);
		if(domain==null||domain.getZunmiUser().getId()!=user.getId()){
			Search search=new Search();
			search.setFirstResult((pageNum - 1) * numPerPage);
			search.setMaxResults(numPerPage);
			search.addFilterEqual("zunmiUser", user);
			search.addSortDesc("addDate");
			SearchResult<ZunmiDomainOnePrize> result=domainOnePrizeService.searchAndCount(search);
			list=result.getResult();
			totalCount=result.getTotalCount();
			pageList(user);
			return "domainOnePrizeList";
		}
		domainOnePrize=domainOnePrizeService.findByDomainId(domainId, user.getId());
		if(domainOnePrize==null){
			domainOnePrize=new ZunmiDomainOnePrize();
			domainOnePrize.setDomainName(domain.getDomain());
			domainOnePrize.setZunmiDomain(domain);
			domainOnePrize.setZunmiUser(user);
		}
		return "domainOnePrizeDetail";
	}
	
	@Action("/user/getDomainOPById")
	public String getDomainOnePrizeById(){//一口价列表,验证域名时通过验证的 TODO
		ZunmiUser user=checkUser();
		domainOnePrize=domainOnePrizeService.findById(domainOnePrizeId);
		if(domainOnePrize==null||domainOnePrize.getZunmiUser().getId()!=user.getId()){
			try {
				userLog = userLogService.getLastLoginLog(user.getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "accountIndex";
		}
		return "domainOnePrizeDetail";
	}
	
	@Action("/user/saveDomainOnePrize")
	public String saveDomainOnePrize(){
		ZunmiUser user=checkUser();
		ZunmiDomain domain=domainService.findById(domainOnePrize.getZunmiDomain().getId());
		
		String onePrize=request.getParameter("onePrize");
		String description=request.getParameter("description");
		Double prize=0d;
		try{
			prize=Double.parseDouble(onePrize);
			if(prize<=0){
				throw new NullPointerException();//只是想把异常抛到异常处理那
			}
//			prize=Arith.add(prize, Arith.mul(prize, 0.15));//TODO 真正的要买此域名要付的钱数
		}catch(Exception e){
			domainOnePrize.setId(domainOnePrize.getId());
			domainOnePrize.setPrize(prize);
			domainOnePrize.setDescription(description);
			domainOnePrize.setId(domainOnePrize.getId());
			domainOnePrize.setZunmiDomain(domain);
			domainOnePrize.setDomainName(domain.getDomain());
			request.setAttribute("error_prize", "您没输入一口价或是格式不正确！");
			return "domainOnePrizeDetail";
		}
		if(domain==null||!domain.getZunmiUser().getId().equals(user.getId())){
			request.setAttribute("error_message", "这个域名不属于您当前用户啊！");
			return "domainOnePrizeDetail";
		}
		if(description!=null){
			description=description.trim().replace("\\n","");
		}
		if(domainOnePrize.getId()!=null){
			domainOnePrize=domainOnePrizeService.findById(domainOnePrize.getId());
		}
		if(domainOnePrize.getId()!=null){
			domainOnePrize.setHits(domainOnePrize.getHits());
		}else{
			domainOnePrize.setHits(1);
		}
		domainOnePrize.setAddDate(new Date());
		domainOnePrize.setPrize(prize);
		domainOnePrize.setZunmiDomain(domain);
		domainOnePrize.setZunmiUser(user);
		   long nowTime=System.currentTimeMillis();
		   long times=180*24*60*60*1000L+nowTime;
		   Date endDate=new Date(times);
		domainOnePrize.setEndDate(endDate);
		domainOnePrize.setDomainName(domain.getDomain());
		domainOnePrize.setStatus("PENDING");//TODO 具体再考虑    这状态是PENDING还是WAIT
		domainOnePrize.setIsRecommend(false);
		domainOnePrize.setDescription(description);
		domainOnePrize.setDomainItems(domainOnePrize.getDomainItems());
		try{
			domainOnePrizeService.save(domainOnePrize);
		}catch(Exception e){
			domainOnePrize.setId(domainOnePrize.getId());
			domainOnePrize.setPrize(prize);
			domainOnePrize.setDescription(description);
			domainOnePrize.setId(domainOnePrize.getId());
			domainOnePrize.setZunmiDomain(domain);
			domainOnePrize.setDomainName(domain.getDomain());
			request.setAttribute("error_message", "添加失败啊！如有需要请联系管理员！！");
			return "domainOnePrizeDetail";
		}
		Search search=new Search();
		search.setFirstResult((pageNum - 1) * numPerPage);
		search.setMaxResults(numPerPage);
		search.addFilterEqual("zunmiUser", user);
		search.addSortDesc("addDate");
		SearchResult<ZunmiDomainOnePrize> result=domainOnePrizeService.searchAndCount(search);
		list=result.getResult();
		totalCount=result.getTotalCount();
		pageList(user);
		return "domainOnePrizeList";
	}
	
	@Action("/user/deldomainOnePrize")
	public String delDomainOnePrize(){
		ZunmiUser user=checkUser();
		String domainOnePrizeStrId=request.getParameter("domainopId");
		ZunmiDomainOnePrize dop=null;
		try{
			Integer dopId=Integer.parseInt(domainOnePrizeStrId);
			dop=domainOnePrizeService.findById(dopId);
			if(dop==null||dop.getZunmiUser().getId()!=user.getId()){
				throw new NullPointerException();
			}
		}catch(Exception e){
			Struts2Utils.renderJson("{\"error_message\":\"这个一口价不是您的或您的输入有误！如有需要请联系管理员！\"}");
			return null;
		}
		try{
			domainOnePrizeService.delete(dop);
		}catch(Exception e){
			e.printStackTrace();//TODO forTest
		}
		Search search=new Search();
		search.setFirstResult((pageNum - 1) * numPerPage);
		search.setMaxResults(numPerPage);
		search.addFilterEqual("zunmiUser", user);
		search.addSortDesc("addDate");
		SearchResult<ZunmiDomainOnePrize> result=domainOnePrizeService.searchAndCount(search);
		list=result.getResult();
		totalCount=result.getTotalCount();
		pageList(user);
		pageListAjax();
		return null;
	}
	
	
	
	
	private void pageListAjax(){
		StringBuffer sb=new StringBuffer();
		sb.append("[");
		for (ZunmiDomainOnePrize ze : list) {
			sb.append("{\"id\":\""+ze.getId()+"\",\"status\":\""+ze.getStatus()+"\",\"domainName\":\""+ze.getDomainName()+"\",\"prize\":\""+ze.getPrize()+"\"},");
		}
		sb.deleteCharAt(sb.lastIndexOf(","));
		sb.append("]");
		response.setContentType("text/plain");
		String jsonString="{\"begin\":"+begin+",\"end\":"+end+",\"numPerPage\":"+numPerPage+",\"list\":"+sb.toString()+",\"totalPages\":"+totalPages+",\"pageNum\":"+pageNum+",\"totalCount\":"+totalCount+"}";
		Struts2Utils.renderJson(jsonString);
	}
	
	
	private void pageList(ZunmiUser user){
		totalPages = totalCount / numPerPage;
		if (totalCount % numPerPage != 0) {
			totalPages = totalPages + 1;
		}
		begin = pageNum - 3;
		end = begin + 6;
		if (begin <= 0) {
			begin = 1;
			end = 9;
		}// 前面的特殊一些，和最后面的也一样
		if (end > totalPages) {
			end = totalPages;
		}
		pageList = new ArrayList<Integer>();
		for (int i = begin; i <= end; i++) {
			pageList.add(i);
		}
	}
	
	public Integer getDomainId() {
		return domainId;
	}
	public void setDomainId(Integer domainId) {
		this.domainId = domainId;
	}
	public List<Integer> getPageList() {
		return pageList;
	}
	public void setPageList(List<Integer> pageList) {
		this.pageList = pageList;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public int getBegin() {
		return begin;
	}
	public void setBegin(int begin) {
		this.begin = begin;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public List<ZunmiDomainOnePrize> getList() {
		return list;
	}
	public void setList(List<ZunmiDomainOnePrize> list) {
		this.list = list;
	}
	public Integer getDomainOnePrizeId() {
		return domainOnePrizeId;
	}
	public void setDomainOnePrizeId(Integer domainOnePrizeId) {
		this.domainOnePrizeId = domainOnePrizeId;
	}
	public void setDomainOnePrize(ZunmiDomainOnePrize domainOnePrize) {
		this.domainOnePrize = domainOnePrize;
	}
	public ZunmiDomainOnePrize getDomainOnePrize() {
		return domainOnePrize;
	}
	public ZunmiUser getUser() {
		return user;
	}
	public void setUser(ZunmiUser user) {
		this.user = user;
	}
	public ZunmiUserLog getUserLog() {
		return userLog;
	}
	public void setUserLog(ZunmiUserLog userLog) {
		this.userLog = userLog;
	}
}
