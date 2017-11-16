package com.zhenjinzi.yzy.action.user;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.zhenjinzi.yzy.action.UserBaseAction;
import com.zhenjinzi.yzy.model.ZunmiQuestion;
import com.zhenjinzi.yzy.model.ZunmiUser;
import com.zhenjinzi.yzy.model.enums.QuestionStatus;
import com.zhenjinzi.yzy.service.ZunmiQuestionService;
import com.zhenjinzi.yzy.service.ZunmiUserService;


@Results({
	@Result(name="message",location="/WEB-INF/content/user/accountpages/message.jsp"),
	@Result(name="question",location="/WEB-INF/content/user/accountpages/question.jsp"),
	@Result(name="domainList",location="/WEB-INF/content/user/accountpages/domainList.jsp"),
	@Result(name="questionDetail",location="/WEB-INF/content/user/accountpages/questionDetail.jsp")
})
public class UserQuestionAction extends UserBaseAction {

	private static final long serialVersionUID = 1L;
	
	@Resource
	private ZunmiUserService userService;
	@Resource
	private ZunmiQuestionService questionService;
	
	private List<ZunmiQuestion> list;
	private ZunmiQuestion question;
	private ZunmiUser user;
	private Integer questionId;
	private Integer[] questions;
	
	
	@Action(value="/user/question")
	public String question(){
		ZunmiUser user=checkUser();
		Search search=new Search();
		
		search.addFilterEqual("zunmiUser", user);
		search.addSortDesc("questionTime");
		SearchResult<ZunmiQuestion> result=questionService.searchAndCount(search);
		list=result.getResult();
		return "question";
	}  
	
	// 前台客户 , 问题反馈
	@Action("/user/addQuestion")
	public String userAddQuestion() {
		ZunmiUser user = checkUser();
	    String questionTitle=request.getParameter("questionTitle");
	    String questionConten=request.getParameter("questionContent");
	    String questionType=request.getParameter("questionType");
		if (questionTitle == null || questionConten == null||questionType==null) {
			return "反馈描述不能为空";
		}
		question=new ZunmiQuestion();
		question.setQuestionTime(new Timestamp(System.currentTimeMillis()));
		question.setStatus(QuestionStatus.UNRESOLVED);
		question.setZunmiUser(user);
		question.setDescription(questionConten);
		question.setQuestion(questionTitle);
		question.setQuestionType(questionType);
		try{
			questionService.save(question);
			
		}catch(Exception e){
		}
		list=questionService.findByUser(user);
		return "question";
	}

	//查看
	@Action("/user/questionDetail")
	public String userViewQuestion(){
		//判断question是不是当前用户的
		ZunmiUser user=checkUser();
		question=questionService.findById(questionId);
		if(question==null||!user.equals(question.getZunmiUser())){
			list=questionService.findByUser(user);
			return "question";
		}
		return "questionDetail";
	}
	
	//单个删除
	@Action("/user/delQuestion")
	public String delPerQuestion(){
		ZunmiUser user=checkUser();
		if(questionId==null){
			request.setAttribute("message", "您还没选择要删除的提问！");
			list=questionService.findByUser(user);
			return "question";
		}
		ZunmiQuestion delQuestion=questionService.findById(questionId);
		if(delQuestion==null||!delQuestion.getZunmiUser().getId().equals(user.getId())){
			request.setAttribute("message", "您不能删除一个不属于您的问题！");
			list=questionService.findByUser(user);
			return "question";
		}
		try{
			questionService.deleteQuestionById(questionId);
			request.setAttribute("message", "删除成功!");
		}catch(Exception e){
			request.setAttribute("message", "删除失败，如有需要请联系管理员！");
		}
		list=questionService.findByUser(user);
		return "question";
	}

	//多个删除：questions是不是当前登陆用户的还没验证
	@Action("/user/delQuestions")
	public String delQuestion(){
		ZunmiUser user=checkUser();
		if(questions==null){
			request.setAttribute("message", "没有可删除项!");
			list=questionService.findByUser(user);
			return "question";
		}
		try{
			questionService.deleteQuestionByIds(questions);
			request.setAttribute("message", "删除成功!");
		}catch(Exception e){
			request.setAttribute("message", "删除失败，如有需要请联系管理员！");
		}
		list=questionService.findByUser(user);
		return "question";
	}
	
	// 前台客户 , 反馈问题查询
	public String userQuestionSearch() {
		ZunmiUser user = checkUser();
		Search search = new Search();

		if (question.getStatus() != null && !question.getStatus().equals("ALL")) {
			search.addFilterEqual("status", question.getStatus());
		}
		if (question.getQuestion() != null
				&& question.getQuestion().length() > 0) {
			search.addFilterLike("question", "%" + question.getQuestion() + "%");
		}
		if (question.getQuestionType() != null
				&& question.getQuestionType().length() > 0
				&& !question.getQuestionType().equals("ALL")) {
			search.addFilterEqual("questionType", question.getQuestionType());
		}

//		if (question.getQuestionTime() != null && littleTime != null
//				&& greatTime != null && !littleTime.before(greatTime)) {
//			search.addFilterGreaterOrEqual("questionTime", littleTime);
//			search.addFilterLessOrEqual("questionTime", greatTime);
//		}
		search.addFilterEqual("zunmiUser", user);
		search.setFirstResult((pageNum - 1) * numPerPage);
		search.setMaxResults(pageNum * numPerPage);
		SearchResult<ZunmiQuestion> searchResult = questionService
				.searchAndCount(search);
		list = searchResult.getResult();
		totalCount = searchResult.getTotalCount();
		return SUCCESS;
	}

	
	
	public List<ZunmiQuestion> getList() {
		return list;
	}
	public void setList(List<ZunmiQuestion> list) {
		this.list = list;
	}
	public ZunmiQuestion getQuestion() {
		return question;
	}
	public void setQuestion(ZunmiQuestion question) {
		this.question = question;
	}
	public ZunmiUser getUser() {
		return user;
	}
	public void setUser(ZunmiUser user) {
		this.user = user;
	}
	public Integer getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
	public Integer[] getQuestions() {
		return questions;
	}
	public void setQuestions(Integer[] questions) {
		this.questions = questions;
	}
}
