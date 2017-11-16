package com.zhenjinzi.yzy.action.user;

import java.net.URLEncoder;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.fire.modules.web.struts2.Struts2Utils;
import com.zhenjinzi.util.Desed3;
import com.zhenjinzi.util.EncryptionHelper;
import com.zhenjinzi.yzy.action.UserBaseAction;
import com.zhenjinzi.yzy.model.ZunmiUser;
import com.zhenjinzi.yzy.model.ZunmiUserLog;
import com.zhenjinzi.yzy.model.enums.MailSubscribe;
import com.zhenjinzi.yzy.model.enums.MessageSubscribe;
import com.zhenjinzi.yzy.model.enums.UserLogType;
import com.zhenjinzi.yzy.service.ZunmiUserLogService;
import com.zhenjinzi.yzy.service.ZunmiUserService;
@Results({
	@Result(name="onePrize",location="/WEB-INF/content/user/domain/onePrize.jsp"),
	@Result(name="addDomain",location="/WEB-INF/content/user/accountpages/addDomain.jsp"),
	@Result(name="addDomainSecond",location="/WEB-INF/content/user/accountpages/addDomainSecond.jsp"),
	@Result(name="domainList",location="/WEB-INF/content/user/accountpages/domainList.jsp"),
	@Result(name="updatePassword",location="/WEB-INF/content/user/accountpages/updatePassword.jsp"),
	@Result(name="updateUserInfo",location="/WEB-INF/content/user/accountpages/updateUserInfo.jsp"),
	@Result(name="updateUserSecurity",location="/WEB-INF/content/user/accountpages/updateUserSecurity.jsp"),
	@Result(name="toCompleteUserInfo",location="/WEB-INF/content/user/accountpages/toCompleteUserInfo.jsp")
})

public class UserInfoAction extends UserBaseAction {
	private static final long serialVersionUID = 1L;

	private ZunmiUser user;
	private ZunmiUserLog userLog;
	@Resource
	private ZunmiUserService userService;
	@Resource
	private ZunmiUserLogService userLogService;
	private boolean emailAuction = false;
	private boolean emailTransaction =false;
	private boolean msgAuction = false;
	private boolean msgTransaction = false;

	
	public ZunmiUser getUser() {
		return user;
	}
	public void setUser(ZunmiUser user) {
		this.user = user;
	}
	
	
	public boolean isEmailAuction() {
		return emailAuction;
	}
	public void setEmailAuction(boolean emailAuction) {
		this.emailAuction = emailAuction;
	}
	public boolean isEmailTransaction() {
		return emailTransaction;
	}
	public void setEmailTransaction(boolean emailTransaction) {
		this.emailTransaction = emailTransaction;
	}
	public boolean isMsgAuction() {
		return msgAuction;
	}
	public void setMsgAuction(boolean msgAuction) {
		this.msgAuction = msgAuction;
	}
	public boolean isMsgTransaction() {
		return msgTransaction;
	}
	public void setMsgTransaction(boolean msgTransaction) {
		this.msgTransaction = msgTransaction;
	}
	@Action(value="/user/updatePassword")
	public String updatePassword(){	
		return "updatePassword";
	}
	@Action(value="/user/updateUserSecurity")
	public String updateUserSecurity(){	
		user = checkUser();
		return "updateUserSecurity";
	}
	@Action(value="/user/doUpdatePassword")
	public String doUpdatePassword(){	
		ZunmiUser currentUser= checkUser();
		String question = request.getParameter("question");
		String customQuestion = request.getParameter("customQuestion");
		String answer = request.getParameter("answer");
		String originalPassword = request.getParameter("originalPassword");
		String newPassword = request.getParameter("newPassword");
		String confirmPassword = request.getParameter("confirmPassword");
	    if(currentUser == null){
	    	Struts2Utils.renderJson("{\"status\":false,\"message\":\"请重新登录重试\",\"data\":{}}");	
	    	return null;
	    }	
		if(originalPassword==null || originalPassword.trim().equals("")){
			Struts2Utils.renderJson("{\"status\":false,\"message\":\"原密码输入不能为空\",\"data\":{}}");	
			return null;
		}
		if(newPassword == null ||newPassword.trim().equals("")){
			Struts2Utils.renderJson("{\"status\":false,\"message\":\"新密码输入不能为空\",\"data\":{}}");	
			return null;
		}
		if(confirmPassword == null ||confirmPassword.trim().equals("")){
			Struts2Utils.renderJson("{\"status\":false,\"message\":\"确认密码输入不能为空\",\"data\":{}}");	
			return null;
		}
		if(!newPassword.equals(confirmPassword)){
			Struts2Utils.renderJson("{\"status\":false,\"message\":\"用户名新密码和确认密码不一致\",\"data\":{}}");
			return null;
		}
		if(StringUtils.isNotBlank(question) && question.equals("-1")) {
			if(StringUtils.isBlank(customQuestion)) {
				Struts2Utils.renderJson("{\"status\":false,\"message\":\"自定义问题不能为空\",\"data\":{}}");
				return null;
			} else {
				question = customQuestion;
			}
		}
		ZunmiUser muser = userService.findByName(currentUser.getUserName());
		if(StringUtils.isBlank(muser.getZunmiSecurity().getAnswer()) ||StringUtils.isBlank(muser.getZunmiSecurity().getQuestion())) {
			if(StringUtils.isBlank(question)||StringUtils.isBlank(answer)){
				Struts2Utils.renderJson("{\"status\":false,\"message\":\"您还没有设置密保问题或答案\",\"data\":{}}");
				return null;
			}
		} else 
			if(!(question.equals(muser.getZunmiSecurity().getQuestion()) && answer.equals(muser.getZunmiSecurity().getAnswer())) ){
			Struts2Utils.renderJson("{\"status\":false,\"message\":\"用户密保或者答案不正确\",\"data\":{}}");
			return null;
		}
		muser.getZunmiSecurity();
		Desed3.getKey("beijingwangzun");
		
		if(currentUser.getPassword().equals(EncryptionHelper.Enc(originalPassword, "SHA-1"))){
			muser.setPassword(EncryptionHelper.Enc(newPassword, "SHA-1"));
			String toEncString = "userId=" + muser.getId() + ",userName="
					+ muser.getUserName() + ",password=" + muser.getPassword()
					+ ",email=" + muser.getEmail();
			String userInfo = Desed3.getEncString(toEncString);
			int maxAge = 7 * 24 * 60 * 60;
			try {
				setCookie("UserName", muser.getUserName(), maxAge);
				setCookie("UserInfo", URLEncoder.encode(userInfo, "utf-8"), maxAge);
				setCookie("UN", muser.getUserName(), 365 * 24 * 60 * 60);
				userLog = new ZunmiUserLog(getIpAddr(), true, UserLogType.CHANGE_PASSWORD,muser);
				userLogService.save(userLog);
				userService.save(muser);
				Struts2Utils.renderJson("{\"status\":false,\"message\":\"密码修改成功\",\"data\":{}}");	
			} catch (Exception e) {
				e.printStackTrace();
				Struts2Utils.renderJson("{\"status\":false,\"message\":\"密码修改失败\",\"data\":{}}");	
			}
			
		}  else {
			Struts2Utils.renderJson("{\"status\":false,\"message\":\"原密码输入错误\",\"data\":{}}");	
		}
		return null;	
	}
	@Action(value="/user/doUpdateUserSecurity")
	public String doUpdateUserSecurity(){
		ZunmiUser currentUser= checkUser();
		if(currentUser == null){
			return ERROR;
		}
		String question = request.getParameter("question");
		String customQuestion = request.getParameter("customQuestion");
		String answer = request.getParameter("answer");
		String password = request.getParameter("password");
		String newQuestion = request.getParameter("newQuestion");
		String newAnswer = request.getParameter("newAnswer");
		if(StringUtils.isNotBlank(question) && question.equals("-1")) {
			if(StringUtils.isBlank(customQuestion)) {
				Struts2Utils.renderJson("{\"status\":false,\"message\":\"自定义问题不能为空\",\"data\":{}}");
				return null;
			} else {
				question = customQuestion;
			}
		}
		if(StringUtils.isBlank(newQuestion) || StringUtils.isBlank(newAnswer) || StringUtils.isBlank(password)){
			Struts2Utils.renderJson("{\"status\":false,\"message\":\"填写信息不完整\",\"data\":{}}");	
			return null;
		}
		//用户有可能没有设置密保,提示用户修改和完善资料
		if(StringUtils.isBlank(currentUser.getZunmiSecurity().getAnswer()) || StringUtils.isBlank(currentUser.getZunmiSecurity().getAnswer())) {
			if(StringUtils.isBlank(question) || StringUtils.isBlank(answer)){
				Struts2Utils.renderJson("{\"status\":false,\"message\":\"用户没有设置密保，请先修改或完善个人资料\",\"data\":{}}");	
				return null;
			} 
		}
		if(!currentUser.getPassword().equals(EncryptionHelper.Enc(password, "SHA-1"))){
			Struts2Utils.renderJson("{\"status\":false,\"message\":\"密码输入错误，请重试\",\"data\":{}}");	
			return null;		
		}
		if(answer.equals(currentUser.getZunmiSecurity().getAnswer())){
				currentUser.getZunmiSecurity().setQuestion(newQuestion);
				currentUser.getZunmiSecurity().setAnswer(newAnswer);
				userService.save(currentUser);	
				Struts2Utils.renderJson("{\"status\":false,\"message\":\"修改成功\",\"data\":{}}");	
				return null;		
			} else {
				Struts2Utils.renderJson("{\"status\":false,\"message\":\"用户输入的问题或者答案不正确\",\"data\":{}}");	
				return null;		
			}
		

	}
	
	@Action(value="/user/doUpdateUserInfo",
			 results={@Result(name="success", location="/WEB-INF/content/user/accountpages/updateSuccess.jsp")}
	)
	public String doUpdateUserInfo(){
		ZunmiUser currentUser= checkUser();
		if(currentUser == null){
			return ERROR;
		}
		if(!checkComplete(currentUser)) {
			return "toCompleteUserInfo";
		}
		ZunmiUser updateUser = copyToUpdateZunmiUser(user,currentUser);
		userService.save(updateUser);
		return SUCCESS;
	}

	@Action(value="/user/completeRegInfo",
			  results={@Result(name="success", location="updateUserInfo", type="redirectAction")}
    )
	public String completeRegInfo(){
		ZunmiUser currentUser= checkUser();
		if(currentUser == null){
			return ERROR;
		}
		ZunmiUser updateUser = copyToCompleteZunmiUser(user,currentUser);
		userService.save(updateUser);
		return SUCCESS;	
	}
	@Action(value="/user/updateUserInfo")
	public String updateUserInfo(){
		ZunmiUser currentUser= checkUser();
		if(currentUser == null){
			return ERROR;
		}
		user = currentUser;
		if(!checkComplete(currentUser)) {
			return "toCompleteUserInfo";
		}
		MailSubscribe mailSubscribe = currentUser.getZunmiUserdetail().getMailSubscribe();
		MessageSubscribe msgSubscribe = currentUser.getZunmiUserdetail().getMsgSubscribe();
		if (mailSubscribe == MailSubscribe.NONE) {
			setEmailAuction(false);
			setEmailTransaction(false);
		} else if (mailSubscribe == MailSubscribe.AUCTION) {
			setEmailAuction(true);
			setEmailTransaction(false);
		} else if (mailSubscribe == MailSubscribe.TRANSACTION) {
			setEmailAuction(false);
			setEmailTransaction(true);
		} else {
			setEmailAuction(true);
			setEmailTransaction(true);
		}
		
		if (msgSubscribe == MessageSubscribe.NONE) {
			setMsgAuction(false);
			setMsgTransaction(false);
		} else if (msgSubscribe == MessageSubscribe.AUCTION) {
			setMsgAuction(true);
			setMsgTransaction(false);
		} else if (msgSubscribe == MessageSubscribe.TRANSACTION) {
			setMsgAuction(false);
			setMsgTransaction(true);
		} else {
			setMsgAuction(true);
			setMsgTransaction(true);
		}
		return "updateUserInfo";	
	}
	//复制用户完善内容　
	private ZunmiUser copyToCompleteZunmiUser(ZunmiUser from,ZunmiUser to){	
		if(from.getZunmiUserdetail().getUserType()!=null && !from.getZunmiUserdetail().getUserType().equals(to.getZunmiUserdetail().getUserType())) {
			to.getZunmiUserdetail().setUserType(from.getZunmiUserdetail().getUserType());
		}
		if(from.getZunmiUserdetail().getEnterpriseName()!=null && !from.getZunmiUserdetail().getEnterpriseName().equals(to.getZunmiUserdetail().getEnterpriseName())){
			to.getZunmiUserdetail().setEnterpriseName(from.getZunmiUserdetail().getEnterpriseName());
		}
		if(from.getZunmiUserdetail().getName() != null && !from.getZunmiUserdetail().getName().equals(to.getZunmiUserdetail().getName())){
			to.getZunmiUserdetail().setName(from.getZunmiUserdetail().getName());
		}
		if(from.getZunmiUserdetail().getIdentityType()!=null && !from.getZunmiUserdetail().getIdentityType().equals(to.getZunmiUserdetail().getIdentityType())) {
			to.getZunmiUserdetail().setIdentityType(from.getZunmiUserdetail().getIdentityType());
		}
		if(from.getZunmiUserdetail().getIdentity()!=null && !from.getZunmiUserdetail().getIdentity().equals(to.getZunmiUserdetail().getIdentity())) {
			to.getZunmiUserdetail().setIdentity(from.getZunmiUserdetail().getIdentity());
		}
		if(from.getZunmiSecurity().getQuestion()!=null && !from.getZunmiSecurity().getQuestion().equals(to.getZunmiSecurity().getQuestion()) ) {
			to.getZunmiSecurity().setQuestion(from.getZunmiSecurity().getQuestion());
		}
		if(from.getZunmiSecurity().getAnswer()!=null && !from.getZunmiSecurity().getAnswer().equals(to.getZunmiSecurity().getAnswer()) ) {
			to.getZunmiSecurity().setAnswer(from.getZunmiSecurity().getAnswer());
		}		
		return to;		
	}
	//复制用户更新的内容　
	private ZunmiUser copyToUpdateZunmiUser(ZunmiUser from,ZunmiUser to){
		if(StringUtils.isNotBlank(from.getMobile()) && !from.getMobile().equals(to.getMobile())) {
			to.setMobile(from.getMobile());
		}	
		if(StringUtils.isNotBlank(from.getZunmiUserdetail().getSex()) && !from.getZunmiUserdetail().getSex().equals(to.getZunmiUserdetail().getSex())){
			to.getZunmiUserdetail().setSex(from.getZunmiUserdetail().getSex());
		}
		if(StringUtils.isNotBlank(from.getZunmiUserdetail().getAlterEmail()) && !from.getZunmiUserdetail().getAlterEmail().equals(to.getZunmiUserdetail().getAlterEmail())) {
			to.getZunmiUserdetail().setAlterEmail(from.getZunmiUserdetail().getAlterEmail());
		}
		if(StringUtils.isNotBlank(from.getZunmiUserdetail().getQq()) && !from.getZunmiUserdetail().getQq().equals(to.getZunmiUserdetail().getQq())){
			to.getZunmiUserdetail().setQq(from.getZunmiUserdetail().getQq());
		}
		if(StringUtils.isNotBlank(from.getZunmiUserdetail().getMsn()) && !from.getZunmiUserdetail().getMsn().equals(to.getZunmiUserdetail().getMsn())){
			to.getZunmiUserdetail().setMsn(from.getZunmiUserdetail().getMsn());
		}
		if(StringUtils.isNotBlank(from.getZunmiUserdetail().getGtalk()) && !from.getZunmiUserdetail().getGtalk().equals(to.getZunmiUserdetail().getGtalk())){
			to.getZunmiUserdetail().setGtalk(from.getZunmiUserdetail().getGtalk());
		}
		if(StringUtils.isNotBlank(from.getZunmiUserdetail().getPostal()) && !from.getZunmiUserdetail().getPostal().equals(to.getZunmiUserdetail().getPostal())){
			to.getZunmiUserdetail().setPostal(from.getZunmiUserdetail().getPostal());
		}
		if(StringUtils.isNotBlank(from.getZunmiUserdetail().getStreet()) && !from.getZunmiUserdetail().getStreet().equals(to.getZunmiUserdetail().getStreet())){
			to.getZunmiUserdetail().setStreet(from.getZunmiUserdetail().getStreet());
		}
		if(StringUtils.isNotBlank(from.getZunmiUserdetail().getQq()) && !from.getZunmiUserdetail().getQq().equals(to.getZunmiUserdetail().getQq())){
			to.getZunmiUserdetail().setQq(from.getZunmiUserdetail().getQq());
		}
		if(from.getZunmiUserdetail().getMsgSubscribe()!=null && !from.getZunmiUserdetail().getMsgSubscribe().equals(to.getZunmiUserdetail().getMsgSubscribe())){
			to.getZunmiUserdetail().setMsgSubscribe(from.getZunmiUserdetail().getMsgSubscribe());
		}
		if(from.getZunmiUserdetail().getMailSubscribe()!=null && !from.getZunmiUserdetail().getMailSubscribe().equals(to.getZunmiUserdetail().getMailSubscribe())){
			to.getZunmiUserdetail().setMailSubscribe(from.getZunmiUserdetail().getMailSubscribe());
		}
		if (isEmailAuction()) {
			if (isEmailTransaction()) {
				to.getZunmiUserdetail().setMailSubscribe(MailSubscribe.ALL);
			} else {
				to.getZunmiUserdetail().setMailSubscribe(MailSubscribe.AUCTION);
			}
		} else {
			if (isEmailTransaction()) {
				to.getZunmiUserdetail().setMailSubscribe(MailSubscribe.TRANSACTION);
			} else {
				to.getZunmiUserdetail().setMailSubscribe(MailSubscribe.NONE);
			}
		}
		if (isMsgAuction()) {
			if (isMsgTransaction()) {
				to.getZunmiUserdetail().setMsgSubscribe(MessageSubscribe.ALL);
			} else {
				to.getZunmiUserdetail().setMsgSubscribe(MessageSubscribe.AUCTION);
			}
		} else {
			if (isMsgTransaction()) {
				to.getZunmiUserdetail().setMsgSubscribe(MessageSubscribe.TRANSACTION);
			} else {
				to.getZunmiUserdetail().setMsgSubscribe(MessageSubscribe.NONE);
			}
		}
	
		return to;
	}
	public boolean checkComplete(ZunmiUser user){
		boolean flag = true;
		flag = StringUtils.isNotBlank(user.getZunmiUserdetail().getName())
				&& StringUtils.isNotBlank(user.getZunmiUserdetail().getIdentity())
				&& StringUtils.isNotBlank(user.getZunmiSecurity().getQuestion())
				&& StringUtils.isNotBlank(user.getZunmiSecurity().getAnswer());
		return flag;	
	}
}
