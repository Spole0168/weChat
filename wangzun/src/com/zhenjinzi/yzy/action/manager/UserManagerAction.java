package com.zhenjinzi.yzy.action.manager;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.fire.modules.web.struts2.Struts2Utils;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.zhenjinzi.util.EncryptionHelper;
import com.zhenjinzi.util.RandomController;
import com.zhenjinzi.yzy.action.BaseAction;
import com.zhenjinzi.yzy.model.YzyAdmin;
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
public class UserManagerAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Resource
	private ZunmiUserService userService;
	@Resource
	private ZunmiUserDetailService userDetailService;
	@Resource
	private ZunmiUserAccountService userAccountService;
	@Resource
	private ZunmiSecurityService securityService;
	
	private List<ZunmiUser> list;
	private ZunmiUser user;//页面传过来被封装的将被查询的客户
	private Integer userId;
	private ZunmiUserDetail userDetail;
	private ZunmiSecurity security;//密保问题对象
	
	private Timestamp littleTime;//查询时间变量的小者
	private Timestamp greatTime;//查询时间变量的大者

	
	public String execute(){
		Search search=new Search();
		
		search.setFirstResult((pageNum-1)*numPerPage);
		search.setMaxResults(pageNum*numPerPage);
		SearchResult<ZunmiUser> searchResult=userService.searchAndCount(search);
		list = searchResult.getResult();
		totalCount = searchResult.getTotalCount();
		return "list";
	}
	
	
	public String input(){
		if(userId==null){
			return INPUT;
		}else{
//			System.out.println("input*****8888888888888888888888888888888888888888888888888888888888888888888888888");
			user=userService.findById(userId);
		}
		return INPUT;
	}
	
	
	//管理员修改用户资料 1:获得用户资料
	public String getUserDetailForU(){
		Integer userId=Integer.parseInt(request.getParameter("userId"));
		userDetail=userDetailService.findUserByUserId(userId);
		return "";
	}
	
	//管理员修改用户资料 2:存储修改过的用户资料   存在问题 修改成功
	//显示名、用户状态、       证件类型、证件号码、用户类型、企业名称、真实姓名、
	public String updateUserDetail(){
		ZunmiUser user2=userService.findById(userId);
		boolean hasEmail=userService.hasEmail(user.getEmail());
		if(hasEmail){
			Struts2Utils.renderJson("{\"statusCode\":\"300\",\"message\":\"邮箱已被占用!\"}");
			return null;
		}
		boolean hasUsername=userService.hasUserName(user.getUserName());
		if(hasUsername){
			Struts2Utils.renderJson("{\"statusCode\":\"300\",\"message\":\"用户名已被占用!\"}");
			return null;
		}
		user2.setUserName(user.getUserName());
		user2.setEmail(user.getEmail());
		user2.setStatus(user.getStatus());
		
		
		ZunmiUserDetail userDetail2=user2.getZunmiUserdetail();
//		身份证号唯一性判断
		boolean hasIndentity=userDetailService.hasIdentity(userDetail.getIdentity());
		if(hasIndentity){
			Struts2Utils.renderJson("{\"statusCode\":\"300\",\"message\":\"证件号已被占用！！\"}");
			return null;
		}
		userDetail2.setIdentity(userDetail.getIdentity());
		userDetail2.setTelphone(userDetail.getTelphone());
		userDetail2.setQq(userDetail.getQq());
		userDetail2.setSex(userDetail.getSex());
		userDetail2.setAlterEmail(userDetail.getAlterEmail());
		userDetail2.setMsn(userDetail.getMsn());
		userDetail2.setGtalk(userDetail.getGtalk());
		userDetail2.setPostal(userDetail.getPostal());
		userDetail2.setUserType(userDetail.getUserType());
		userDetail2.setEnterpriseName(userDetail.getEnterpriseName());
		userDetail2.setName(userDetail.getName());
		userDetail2.setProvince(userDetail.getProvince());
		userDetail2.setCity(userDetail.getCity());
		userDetail2.setStreet(userDetail.getStreet());

		boolean b=userService.save(user2);
		if(b){
			Struts2Utils.renderJson("{\"statusCode\":\"300\",\"message\":\"修改失败！！\"}");
			return null;
		}
		return null;
	}
	
	//添加用户信息
	public String addUser(){
		boolean hasUsername=userService.hasUserName(user.getUserName());
		boolean hasIndentity=userDetailService.hasIdentity(userDetail.getIdentity());
		boolean hasEmail=userService.hasEmail(user.getEmail());
		String password=user.getPassword();
		String confirmpassword=request.getParameter("password2");//确etp认密码
		String customQuestion=request.getParameter("customQuestion");//自己编辑的密保问题
		if(hasEmail){
			Struts2Utils.renderJson("{\"statusCode\":\"300\",\"message\":\"邮箱已被占用！!\"}");
			return null;
		}
        if(hasUsername){
			Struts2Utils.renderJson("{\"statusCode\":\"300\",\"message\":\"用户名已被占用！!\"}");
        	return null;
		}
		if(hasIndentity){
			Struts2Utils.renderJson("{\"statusCode\":\"300\",\"message\":\"该证件号已被占用！!\"}");
			return null;
		}
		if(password==null||confirmpassword==null||!password.equals(confirmpassword)){
			Struts2Utils.renderJson("{\"statusCode\":\"300\",\"message\":\"两次输入的密码不一致!\"}");
			return null;
		}
		if(security.getQuestion().equals("-1")){//如果传来的值是-1把自己编辑的问题赋给question
			security.setQuestion(customQuestion);
		}
		System.out.println("1");
		user.setPassword(EncryptionHelper.Enc(user.getPassword(), "SHA-1"));
		user.setStatus(UserStatus.NOMAL);
		user.setCreationDate(new Timestamp(System.currentTimeMillis()));
		
		ZunmiUserDetail userDetail2=user.getZunmiUserdetail();
		userDetail2.setMailSubscribe(MailSubscribe.ALL);
		userDetail2.setMsgSubscribe(MessageSubscribe.ALL);
		userDetail2.setUserType(userDetail.getUserType());//用户类型：个人用户&企业用户
		userDetail2.setEnterpriseName(userDetail.getEnterpriseName());
		userDetail2.setName(userDetail.getName());//真实姓名和联系人
		userDetail2.setIdentityType(userDetail.getIdentityType());
		userDetail2.setIdentity(userDetail.getIdentity());
		userDetail2.setTelphone(userDetail.getTelphone());
		//TODO 还有很多属性没有set进来
		userDetail2.setZunmiUser(user);
		
		try{
			userService.save(user);
			security.setZunmiUser(user);
			securityService.save(security);
			Struts2Utils.renderJson("{\"statusCode\":\"200\",\"message\":\"添加成功!\"," +
					               "\"navTabId\":\"user_manager_list\",\"callbackType\":\"closeCurrent\"}");
		}catch(Exception e){                       
			Struts2Utils.renderJson("{\"statusCode\":\"300\",\"message\":\"添加失败\"}");
		}
		userAccountService.init(user.getId());
		return null;
	}

	public String changePassword() {
		String oldPassword = request.getParameter("oldPassword");
		String newPassword1 = request.getParameter("newPassword1");
		String newPassword2 = request.getParameter("newPassword2");
		if (!newPassword1.equals(newPassword2)) {
			Struts2Utils.renderJson("{\"statusCode\":\"300\",\"message\":\"两次密码不一致!\"}");
			return null;
		}
		ZunmiUser userChangePw = userService.findById(Integer.parseInt(httpSession
				.getAttribute("userId") + ""));
		if (!EncryptionHelper.Enc(oldPassword, "SHA-1").equals(
				userChangePw.getPassword())) {
			Struts2Utils.renderJson("{\"statusCode\":\"300\",\"message\":\"原密码输入有误!\"}");
			return null;
		}
		userChangePw.setPassword(EncryptionHelper.Enc(newPassword1, "SHA-1"));
//		userChangePw.setLastActivityDate(new Timestamp(System.currentTimeMillis()));
		try {
			userService.save(userChangePw);
			Struts2Utils.renderJson("{\"statusCode\":\"200\",\"message\":\"修改成功!\",\"callbackType\":\"closeCurrent\"}");
		} catch (Exception e) {
			e.printStackTrace();
			Struts2Utils.renderJson("{\"statusCode\":\"300\",\"message\":\"操作失败!\"}");
		}
		return null;
	}
	
	//删除客户信息
	public String delUser(){
		ZunmiUser user=userService.findById(userId);
		user.setStatus(UserStatus.DISABLED);
		
		try {
			userService.delete(user);
			Struts2Utils.renderJson("{\"statusCode\":\"200\",\"message\":\"删除成功!\",\"navTabId\":\"user_manager_list\"}");
		} catch (Exception e) {
			Struts2Utils.renderJson("{\"statusCode\":\"300\",\"message\":\"删除失败!\"}");
		}
		return null;
	}
	
	//客户锁定
	public String lockedUser(){
		ZunmiUser user=userService.findById(userId);
		if(user==null||user.getStatus().equals("LOCKED")){
			Struts2Utils.renderJson("{\"statusCode\":\"300\",\"message\":\"用户不存在或是用户已被锁定\"}");
//			return "用户不存在或是用户已被锁定";
		}
		user.setStatus(UserStatus.LOCKED);
		
		try {
			userService.save(user);
			Struts2Utils.renderJson("{\"statusCode\":\"200\",\"message\":\"客户锁定成功!\",\"navTabId\":\"user_list\"}");
		} catch (Exception e) {
			Struts2Utils.renderJson("{\"statusCode\":\"300\",\"message\":\"客户锁定失败!\"}");
		}
		return null;
	}
	
	//客户解锁
	public String unLockedUser(){
		ZunmiUser user=userService.findById(userId);
		if(user==null||user.getStatus().equals("NOMAL")){
			Struts2Utils.renderJson("{\"statusCode\":\"300\",\"message\":\"用户不存在或是用户已被锁定!\"}");
			return "用户不存在或是用户已被锁定";
		}
		user.setStatus(UserStatus.NOMAL);
		
		try {
			userService.save(user);
			Struts2Utils.renderJson("{\"statusCode\":\"200\",\"message\":\"客户解锁成功!\",\"navTabId\":\"user_list\"}");
		} catch (Exception e) {
			Struts2Utils.renderJson("{\"statusCode\":\"300\",\"message\":\"客户解锁失败!\"}");
		}
		return null;
	}
	
	//查询客户信息
	public String search(){
		Search search=new Search();
		if(user!=null&&user.getUserName()!=null&&user.getUserName().length()>0){
			search.addFilterLike("userName", "%"+user.getUserName()+"%");
		}
		if(user.getStatus()!=null&&!user.getStatus().toString().equals("ALL")){
			search.addFilterEqual("status", user.getStatus());
		}
		
		if(littleTime!=null&&greatTime!=null&&littleTime.before(greatTime)){
			System.out.println("1");
			search.addFilterGreaterOrEqual("creationDate", littleTime);
			search.addFilterLessOrEqual("creationDate", greatTime);  
		}else{
			System.out.println("2");
			search.addFilterGreaterOrEqual("creationDate", greatTime);
			search.addFilterLessOrEqual("creationDate",littleTime);  
		}
		
		search.addFilterNotEqual("status", UserStatus.DISABLED);
		search.setFirstResult((pageNum-1)*numPerPage);
		search.setMaxResults(pageNum*numPerPage);
		SearchResult<ZunmiUser> searchResult=userService.searchAndCount(search);
		list = searchResult.getResult();
		for (ZunmiUser use : list) {
			System.out.println(use.getCreationDate());
		}
		totalCount = searchResult.getTotalCount();
		return "list";
	}

	
//	//重设客户密码
	public String resetPassword(){
		user=userService.findById(userId);
		return "change-pwd";
	}
//	public String resetPassword(){
//		if (httpSession.getAttribute("adminId") == null) {
//			Struts2Utils.renderJson("{\"statusCode\":\"301\"}");
//			return null;
//		}
//		Integer userId=Integer.parseInt(request.getParameter("userId"));
//		ZunmiUser user=userService.findById(userId);
//		String newPassword=RandomController.getCharacterAndNumber(6);
//		user.setPassword(EncryptionHelper.Enc(newPassword, "SHA-1"));
//		
//		try{
//			userService.save(user);
//			Struts2Utils.renderJson("{\"statusCode\":\"200\",\"message\":\"添加成功!密码是：+user.getPassword()+\"," +
//										"\"navTabId\":\"user_manager_list\",\"callbackType\":\"closeCurrent\"}");
//		}catch(Exception e){
//			e.printStackTrace();
//			Struts2Utils.renderJson("{\"statusCode\":\"300\",\"message\":\"添加失败\"}");
//		}
//		
//		request.setAttribute("USER_PASSWORD", newPassword);
//		//新设的密码？ TODO   给客户发一邮件或是短信（手机是经过验证的）之类，通账户密码的变动
//		return null;
//	}
	public ZunmiUser getUser() {
		return user;
	}

	public void setUser(ZunmiUser user) {
		this.user = user;
	}

	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public List<ZunmiUser> getList() {
		return list;
	}

	public void setList(List<ZunmiUser> list) {
		this.list = list;
	}
	public void setUserDetail(ZunmiUserDetail userDetail) {
		this.userDetail = userDetail;
	}
	
    public ZunmiUserDetail getUserDetail() {
		return userDetail;
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

	public ZunmiSecurity getSecurity() {
		return security;
	}

	public void setSecurity(ZunmiSecurity security) {
		this.security = security;
	}

}
