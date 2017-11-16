package com.zhenjinzi.yzy.action.user;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.fire.modules.web.struts2.Struts2Utils;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.zhenjinzi.yzy.action.UserBaseAction;
import com.zhenjinzi.yzy.model.ZunmiDomainOnePrize;
import com.zhenjinzi.yzy.model.ZunmiMessage;
import com.zhenjinzi.yzy.model.ZunmiUser;
import com.zhenjinzi.yzy.model.enums.MessageStatus;
import com.zhenjinzi.yzy.service.ZunmiMessageService;

//站内短信
@Results({
	@Result(name="message",location="/WEB-INF/content/user/accountpages/message.jsp"),
	@Result(name="domainList",location="/WEB-INF/content/user/accountpages/domainList.jsp"),
	@Result(name="messageDetail",location="/WEB-INF/content/user/accountpages/messageDetail.jsp")
})
public class UserMessageAction extends UserBaseAction{

	private static final long serialVersionUID = 1L;

	@Resource
	private ZunmiMessageService messageService;
	
	private List<ZunmiMessage> list;
	private int readNumber;
	private int unreadNumber;
	private List<Integer> pageList;
	private int end;
	private int begin;
	private int totalPages;
	
	private ZunmiMessage message;
	private Integer messageId;
	
	@Action(value="/user/message")
	public String message(){
		ZunmiUser user=checkUser();
		Search search=new Search();
		
		search.setFirstResult((pageNum - 1) * numPerPage);
		search.setMaxResults(numPerPage);
		search.addFilterEqual("zunmiUserByReceiver", user);//接受者
		search.addSortDesc("sendTime");
		SearchResult<ZunmiMessage> result=messageService.searchAndCount(search);
		list=result.getResult();
		totalCount=result.getTotalCount();
		pageList(user);//公共的分页部分
		return "message";
	}
	
	@Action("/user/messageAjax")
	public String messageAjax(){
		ZunmiUser user=checkUser();
		Search search=new Search();
		
		search.setFirstResult((pageNum - 1) * numPerPage);
		search.setMaxResults(numPerPage);
		search.addFilterEqual("zunmiUserByReceiver", user);//接受者
		search.addSortDesc("sendTime");
		SearchResult<ZunmiMessage> result=messageService.searchAndCount(search);
		list=result.getResult();
		totalCount=result.getTotalCount();
		pageList(user);//公共的分页部分
		pageListAjax();
		return null;
	}
	
	
	//查看短消息
	@Action(value="/user/showRMessage")
	public String showMessage(){
		System.out.println("asdasd");
		ZunmiUser user=checkUser();
		if(messageId==null){
			System.out.println();
			message();//
		}
		message=messageService.findById(messageId);
		if(message==null||!message.getZunmiUserByReceiver().getId().equals(user.getId())){
			request.setAttribute("error_message", "短消息不存在或是不是您的短消息，如有问题请联系管理员！");
			return "messageDetail";
		}
		message.setStatus(MessageStatus.READ);
		messageService.save(message);
		return "messageDetail";
	}
	
	//单个删除站内信
	@Action("/user/delMessage")
	public String perDelMessage(){
		ZunmiUser user=checkUser();
		ZunmiMessage delMessage=messageService.findById(messageId);
		if(delMessage==null||!delMessage.getZunmiUserByReceiver().getId().equals(user.getId())){
			//不他的站内信或是此站内信不存在
			Struts2Utils.renderJson("false");
			return null;
		}
		messageService.delMessage(delMessage);
        Search search=new Search();
		search.addFilterEqual("zunmiUserByReceiver", user);//接受者
		search.addSortDesc("sendTime");
		search.setFirstResult((pageNum - 1) * numPerPage);
		search.setMaxResults(numPerPage);
		SearchResult<ZunmiMessage> result=messageService.searchAndCount(search);
		list=result.getResult();
		totalCount=result.getTotalCount();
		pageList(user);//公共的分页部分
		pageListAjax();
		return null;
	}
	
	//多个一起删除站内信息
	@Action("/user/deleteMessages")
	public String delMessages(){
		ZunmiUser user=checkUser();
		
		String messageIdstr=request.getParameter("messageIds");
		if(messageIdstr==null){
			Struts2Utils.renderJson("{\"failed\":\"您输入有误或是还没选择要删除的站内消息！\"}");
			return null;
		}
		
		messageIdstr=messageIdstr.substring(messageIdstr.indexOf(";")+1);
		String[] ids=messageIdstr.split(";");
		Integer[] intIds=new Integer[ids.length];
		for(int i=0;i<ids.length;i++){
			try{//如id不是数字将是异常，所以
			Integer kj=Integer.parseInt(ids[i]);
			intIds[i]=kj;
			}catch(Exception e){}
		}
		try{
			messageService.delMessages(intIds);
			Search search=new Search();
			
			search.setFirstResult((pageNum - 1) * numPerPage);
			search.setMaxResults(numPerPage);
			search.addFilterEqual("zunmiUserByReceiver", user);//接受者
			search.addSortDesc("sendTime");
			SearchResult<ZunmiMessage> result=messageService.searchAndCount(search);
			list=result.getResult();
			totalCount=result.getTotalCount();
			pageList(user);//公共的分页部分
			pageListAjax();
		}catch(Exception e){
		}
		return null;
	}
	
	@Action("/user/chengeMessageStatus")
	public String chengeMessageStatus(){
		ZunmiUser user=checkUser();
		
		String messageIdstr=request.getParameter("messageIds");
		String status=request.getParameter("stauts");
		if(messageIdstr==null){
			Struts2Utils.renderJson("{\"failed\":\"您输入有误或是还没选择要修改的站内消息！\"}");
			return null;
		}
		
		messageIdstr=messageIdstr.substring(messageIdstr.indexOf(";")+1);
		String[] ids=messageIdstr.split(";");
		Integer[] intIds=new Integer[ids.length];
		ZunmiMessage[] messages=new ZunmiMessage[ids.length];
		for(int i=0;i<ids.length;i++){
			try{//如id不是数字将是异常，所以
			Integer kj=Integer.parseInt(ids[i]);
			intIds[i]=kj;
			messages[i]=messageService.findById(kj);
			if("READ".equals(status)){
				messages[i].setStatus(MessageStatus.READ);
			}
			if("UNREAD".equals(status)){
				messages[i].setStatus(MessageStatus.UNREAD);
			}
			}catch(Exception e){
			}
		}
		try{
			messageService.saveMessages(messages);
			Search search=new Search();
			
			search.setFirstResult((pageNum - 1) * numPerPage);
			search.setMaxResults(numPerPage);
			search.addFilterEqual("zunmiUserByReceiver", user);//接受者
			search.addSortDesc("sendTime");
			SearchResult<ZunmiMessage> result=messageService.searchAndCount(search);
			list=result.getResult();
			totalCount=result.getTotalCount();
			pageList(user);//公共的分页部分
			pageListAjax();
		}catch(Exception e){}
		
		return null;
	}
	
	private void pageListAjax(){
		StringBuffer sb=new StringBuffer();
		sb.append("[");
		for (ZunmiMessage ze : list) {
			sb.append("{\"id\":\""+ze.getId()+"\",\"title\":\""+ze.getTitle()+"\",\"sendTime\":\""+ze.getSendTime()+"\",\"status\":\""+ze.getStatus()+"\"},");
		}
		sb.deleteCharAt(sb.lastIndexOf(","));
		sb.append("]");
		response.setContentType("text/plain");
		String jsonString="{\"begin\":"+begin+",\"end\":"+end+",\"numPerPage\":"+numPerPage+",\"list\":"+sb.toString()+",\"totalPages\":"+totalPages+",\"pageNum\":"+pageNum+",\"readNumber\":"+readNumber+",\"unreadNumber\":"+unreadNumber+",\"totalCount\":"+totalCount+"}";
		Struts2Utils.renderJson(jsonString);
	}
	
	
	private void pageList(ZunmiUser user){
		totalPages = totalCount / numPerPage;
		if (totalCount % numPerPage != 0) {
			totalPages = totalPages + 1;
		}
		begin = pageNum - 2;
		end = begin + 4;
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
		readNumber=0;unreadNumber=0;
		readNumber=messageService.findNumByStatus(user,MessageStatus.READ);
		unreadNumber=totalCount-readNumber;
//		unreadNumber=messageService.findNumByStatus(user,MessageStatus.UNREAD);
	}

	
	public List<ZunmiMessage> getList() {
		return list;
	}
	public void setList(List<ZunmiMessage> list) {
		this.list = list;
	}
	public int getReadNumber() {
		return readNumber;
	}
	public void setReadNumber(int readNumber) {
		this.readNumber = readNumber;
	}
	public int getUnreadNumber() {
		return unreadNumber;
	}
	public void setUnreadNumber(int unreadNumber) {
		this.unreadNumber = unreadNumber;
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
	public Integer getMessageId() {
		return messageId;
	}
	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}
	public ZunmiMessage getMessage() {
		return message;
	}
	public void setMessage(ZunmiMessage message) {
		this.message = message;
	}
	
}
