package com.zhenjinzi.yzy.action.manager;


import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.springframework.stereotype.Controller;

import com.zhenjinzi.util.EncryptionHelper;
import com.zhenjinzi.yzy.action.BaseAction;
import com.zhenjinzi.yzy.model.ZunmiSecurity;
import com.zhenjinzi.yzy.model.ZunmiUser;
import com.zhenjinzi.yzy.model.ZunmiUserDetail;
import com.zhenjinzi.yzy.model.enums.MailSubscribe;
import com.zhenjinzi.yzy.model.enums.MessageSubscribe;
import com.zhenjinzi.yzy.model.enums.UserStatus;
import com.zhenjinzi.yzy.service.ZunmiSecurityService;
import com.zhenjinzi.yzy.service.ZunmiUserAccountService;
import com.zhenjinzi.yzy.service.ZunmiUserDetailService;
import com.zhenjinzi.yzy.service.ZunmiUserService;

@Controller
public class UserAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	
	@Resource
	private ZunmiUserService userService;
	@Resource
	private ZunmiUserDetailService userDetailService;
	@Resource
	private ZunmiSecurityService securityService;
	@Resource
	private ZunmiUserAccountService userAccountService;
	
	private ZunmiUser user;
	private String customQuestion;
	private String inVerifCode;//用户输入的验证码
	private String service;//服务条款勾选没，不勾为null
	private String confirmpassword;
	private ZunmiUserDetail userDetail;
	private ZunmiSecurity security;
	
	public String login(){
		
		return null;
	}
	
	
	public String regist(){
		String verifCode=(String)httpSession.getAttribute("verifCode");//从session中获取验证码
		user.setStatus(UserStatus.NOMAL);
		user.setCreationDate(new Timestamp(System.currentTimeMillis()));
		boolean hasEmail=userService.hasEmail(user.getEmail());
		boolean hasUserName=userService.hasUserName(user.getUserName());
		boolean hasIdentity=userDetailService.hasIdentity(userDetail.getIdentity());
		String password=user.getPassword();
		if(hasEmail){
			return "邮箱不唯一错";
		}
		if(hasUserName){
			return "用户名不唯一错";
		}
		if(hasIdentity){
			return "证件号已被占用";
		}
//		if(inVerifCode==null||!inVerifCode.equals(verifCode)){
//			return "验证码错误";
//		}
		if(password==null||confirmpassword==null||!password.equals(confirmpassword)){
			return "两次输入的密码不一致";
		}
		user.setPassword(EncryptionHelper.Enc(user.getPassword(), "SHA-1"));//加密
		if(service==null){
			return "服务条款必须勾选";
		}
		if(security==null){
			return "密保问题没有填写";
		}
		if(security.getQuestion().equals("-1")){//如果传来的值是-1把自己编辑的问题赋给question
			security.setQuestion(customQuestion);
		}
		ZunmiUserDetail userDetail2=user.getZunmiUserdetail(); 
		userDetail2.setMailSubscribe(MailSubscribe.ALL);
		userDetail2.setMsgSubscribe(MessageSubscribe.ALL);
		userDetail2.setUserType(userDetail.getUserType());
		userDetail2.setEnterpriseName(userDetail.getEnterpriseName());
		userDetail2.setName(userDetail.getName());
		userDetail2.setIdentityType(userDetail.getIdentityType());
		userDetail2.setIdentity(userDetail.getIdentity());
		userDetail2.setTelphone(userDetail.getTelphone());
		userDetail2.setZunmiUser(user);
		
//		userDetail2.setZunmiUser(user);
		security.setZunmiUser(user);
		
		
		boolean saveUser=userService.save(user);
		boolean saveSecurity=securityService.save(security);
//		boolean saveUDetail=userDetailService.save(userDetail2);
		if(!saveUser&&!saveSecurity){
			return "注册失败";
		}
		System.out.println(user.getId());
		userAccountService.init(user.getId());
		return SUCCESS;
	}
	
	public String yanZheng(){
		if(httpSession.getAttribute("userId")==null){
			return "用户未登录";
		}
		ZunmiUser user=userService.findById(Integer.parseInt(httpSession.getAttribute("userId")+""));
		
		String oldPassWord=request.getParameter("oldPassWord");//
		String answerSecurity=request.getParameter("answer");
		ZunmiSecurity security=securityService.findSecurityByuserId(user.getId());
		//String answer=httpServletRequest.getParameter("answer");//密保答案 
		System.out.println(security.getAnswer());
		if(!answerSecurity.equals(security.getAnswer())||!(user.getPassword().equals(EncryptionHelper.Enc(oldPassWord, "SHA-1")))){
			return "安全问题答案或者登陆密码错误";
		}
		return SUCCESS;
	}
	
	public String modify(){
		if(httpSession.getAttribute("userId")==null){
			return "用户未登录";
		}
		String newPassword1 = request.getParameter("newPassword1");
		String newPassword2 = request.getParameter("newPassword2");
		if (!newPassword1.equals(newPassword2)) {
			return "两次密码不一致";
		}
		ZunmiUser user=userService.findById(Integer.parseInt(httpSession.getAttribute("userId")+""));
		user.setPassword(EncryptionHelper.Enc(newPassword1, "SHA-1"));
		boolean b=userService.save(user);
		if(!b){
			return "修改成功！！";
		}
		return SUCCESS;
	}
	
	//通过客户信息ID获得用户详细信息传到页面上
	public String findUserDetail(){
		Integer userId=Integer.parseInt(httpSession.getAttribute("userId").toString());
		ZunmiUserDetail userDetail=userDetailService.findUserByUserId(userId);
		request.setAttribute("useDetail", userDetail);
		return SUCCESS;
	}
	
	//修改用户资料
	public String modifyDetail(){
		if(httpSession.getAttribute("userId")==null){
			return "用户未登录";
		}
		Integer userId=Integer.parseInt(httpSession.getAttribute("userId").toString());
		ZunmiUser user2=userService.findById(userId);

		
		ZunmiUserDetail userDetail2=user2.getZunmiUserdetail();
		userDetail2.setAlterEmail(userDetail.getAlterEmail());
		userDetail2.setTelphone(userDetail.getTelphone());
		userDetail2.setQq(userDetail.getQq());
		userDetail2.setGtalk(userDetail.getGtalk());
		userDetail2.setMsn(userDetail.getMsn());
		userDetail2.setMailSubscribe(toMailSubscribe(request.getParameter("emailAuction"),request.getParameter("emailTransaction")));
		userDetail2.setMsgSubscribe(toMessageSubscribe(request.getParameter("msgAuction"),request.getParameter("msgTransaction")));
		userDetail2.setPostal(userDetail.getPostal());
		userDetail2.setSex(userDetail.getSex());
		
		boolean b=userService.save(user2);
		if(b){
			System.out.println("失败！！");
			return "修改不成功";
		}
		System.out.println("成功！！");
		return SUCCESS;
	}
	//'NONE','AUCTION','TRANSACTION','ALL'
	private MessageSubscribe toMessageSubscribe(String auction, String transaction) {
		MessageSubscribe flag;
		if(auction==null&&transaction==null){
			flag=MessageSubscribe.NONE;
		}else if(auction!=null&&transaction==null){
			flag=MessageSubscribe.AUCTION;
		}else if(auction==null&&transaction!=null){
			flag=MessageSubscribe.TRANSACTION;
		}else{
			flag=MessageSubscribe.ALL;
		}
		return flag;
	}
	private MailSubscribe toMailSubscribe(String auction, String transaction) {
		MailSubscribe flag;
		if(auction==null&&transaction==null){
			flag=MailSubscribe.NONE;
		}else if(auction!=null&&transaction==null){
			flag=MailSubscribe.AUCTION;
		}else if(auction==null&&transaction!=null){
			flag=MailSubscribe.TRANSACTION;
		}else{
			flag=MailSubscribe.ALL;
		}
		return flag;
	}

	
	public ZunmiUser getUser() {
		return user;
	}
	public void setUser(ZunmiUser user) {
		this.user = user;
	}
	public String getConfirmpassword() {
		return confirmpassword;
	}
	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}
	public ZunmiUserDetail getUserDetail() {
		return userDetail;
	}
	public void setUserDetail(ZunmiUserDetail userDetail) {
		this.userDetail = userDetail;
	}
	public ZunmiSecurity getSecurity() {
		return security;
	}
	public void setSecurity(ZunmiSecurity security) {
		this.security = security;
	}
	public String getCustomQuestion() {
		return customQuestion;
	}
	public void setCustomQuestion(String customQuestion) {
		this.customQuestion = customQuestion;
	}
	public String getInVerifCode() {
		return inVerifCode;
	}
	public void setInVerifCode(String inVerifCode) {
		this.inVerifCode = inVerifCode;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	
}
