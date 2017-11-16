package com.zhenjinzi.yzy.action.user;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.Format;
import java.util.Date;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;

import org.apache.commons.lang.time.FastDateFormat;
import org.apache.commons.lang.xwork.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.fire.modules.web.struts2.Struts2Utils;
import com.zhenjinzi.util.Desed3;
import com.zhenjinzi.util.EncryptionHelper;
import com.zhenjinzi.yzy.action.UserBaseAction;
import com.zhenjinzi.yzy.action.manager.CheckCodeAction;
import com.zhenjinzi.yzy.model.ZunmiSecurity;
import com.zhenjinzi.yzy.model.ZunmiUser;
import com.zhenjinzi.yzy.model.ZunmiUserAccount;
import com.zhenjinzi.yzy.model.ZunmiUserLog;
import com.zhenjinzi.yzy.model.enums.MailSubscribe;
import com.zhenjinzi.yzy.model.enums.MessageSubscribe;
import com.zhenjinzi.yzy.model.enums.UserAccountStatus;
import com.zhenjinzi.yzy.model.enums.UserLogType;
import com.zhenjinzi.yzy.model.enums.UserStatus;
import com.zhenjinzi.yzy.service.ZunmiMailService;
import com.zhenjinzi.yzy.service.ZunmiUserAccountService;
import com.zhenjinzi.yzy.service.ZunmiUserLogService;
import com.zhenjinzi.yzy.service.ZunmiUserService;

@Controller
@Results({ @Result(name = "error", location = "/error.html") })
public class AccountAction extends UserBaseAction {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private static final long serialVersionUID = 1L;
	@Resource
	private ZunmiUserService userService;
	@Resource
	private ZunmiMailService mailService;
	@Resource
	private ZunmiUserLogService userLogService;
	@Resource
	private ZunmiUserAccountService accountService;
	
	private ZunmiUser user;
	private static final String key = "beijingwangzunhdy";
	private String token;
	private String customQuestion;
    private String status;//LY
	private String question;

	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public ZunmiUser getUser() {
		return user;
	}

	public void setUser(ZunmiUser user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	public String getCustomQuestion() {
		return customQuestion;
	}

	public void setCustomQuestion(String customQuestion) {
		this.customQuestion = customQuestion;
	}

	@Action(value = "login")
	public String login() {
		return SUCCESS;
	}

	@Action(value = "regist")
	public String regist() {
		// 注册令牌防止用户刷新时多次提交表单
		httpSession.setAttribute("token",
				new Date().getTime() + httpSession.getId());
		return SUCCESS;
	}

	@Action(value = "/user/checkCaptcha")
	// 校验验证码
	public String checkCaptcha() {
		String originalCaptcha = (String) httpSession.getAttribute("checkCode");
		String captcha = request.getParameter("captcha");
		if (captcha == null) {
			Struts2Utils.renderJson("false");
			return null;
		}
		if (!captcha.trim().equalsIgnoreCase(originalCaptcha)) {
			Struts2Utils.renderJson("false");
			return null;
		}
		Struts2Utils.renderJson("true");
		return null;
	}

	@Action(value = "/user/checkUserName")
	// 校验用户名
	public String checkUserName() {
		String userName = request.getParameter("userName");
		if (userName == null || userName.equals("")) {
			Struts2Utils.renderJson("false");
			return null;
		}
		ZunmiUser user = userService.findByName(userName);
		if (user == null) {
			Struts2Utils.renderJson("true");
			return null;
		} else {
			Struts2Utils.renderJson("false");
			return null;
		}

	}

	@Action(value = "/user/checkEmail")
	// 校验邮箱
	public String checkEmail() {
		String email = request.getParameter("email");
		System.out.println("emain:" + email);
		if (email == null || email.equals("")) {
			Struts2Utils.renderJson("false");
			return null;
		}
		ZunmiUser user = userService.findByEmail(email);
		if (user == null) {
			Struts2Utils.renderJson("true");
			return null;
		} else {
			Struts2Utils.renderJson("false");
			return null;
		}

	}

	@Action(value="/user/doRegist",
			 results={@Result(name="success", location="registStep2", type="redirectAction"),
			          @Result(name="input", location="/WEB-INF/content/user/regist.jsp")}
			)
	public String doRegist(){
	    if(!token.equals((String)httpSession.getAttribute("token"))){
	    	return ERROR;
	    } else {
	    	httpSession.removeAttribute("token");
	    }
	    //校验必填的信息是否为空
	    if(StringUtils.isBlank(user.getEmail()) || StringUtils.isBlank(user.getUserName()) 
	    		|| StringUtils.isBlank(user.getMobile()) || StringUtils.isBlank(user.getPassword()) ){
	    	return INPUT;
	    }
	    user.setPassword(EncryptionHelper.Enc(user.getPassword(), "SHA-1"));
	    user.setStatus(UserStatus.DISABLED);
	    user.setCreationDate(new Timestamp(new Date().getTime()));
	    user.getZunmiUserdetail().setMailSubscribe(MailSubscribe.ALL);
	    user.getZunmiUserdetail().setMsgSubscribe(MessageSubscribe.ALL);
	    user.getZunmiUserdetail().setZunmiUser(user);
	    user.getZunmiUseraccount().setZunmiUser(user);
	    user.getZunmiUseraccount().setBalance(0.0);
	    user.getZunmiUseraccount().setSaleCredit(0);
	    user.getZunmiUseraccount().setBuyCredit(0);
	    user.getZunmiUseraccount().setFreezedCash(0.0);
	    user.getZunmiUseraccount().setStatus(UserAccountStatus.NOMAL);
	    user.getZunmiSecurity().setZunmiUser(user);
	    user.getZunmiBank().setZunmiUser(user);
	    if(StringUtils.isNotBlank(getCustomQuestion()) && getCustomQuestion().equals("-1")) {
	    	user.getZunmiSecurity().setQuestion(getCustomQuestion());
	    }
	   
	    try {
			userService.save(user);
			user.getUserName();
			String activationUrl = getActivationUrl(user);
			// send Activation Email
			sendActivationEmail(user.getEmail(), user.getUserName(),activationUrl);
			String email = user.getEmail();
			String emailUrl = email.substring(email.indexOf('@')+1);
			httpSession.setAttribute("email",email);
			httpSession.setAttribute("emailUrl",emailUrl);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	
	@Action(value="registStep2",
			   results={@Result(name="success", location="/WEB-INF/content/user/verifica.jsp")}
    )
	//注册成功后跳转到另一个action,防止刷新提交表单
	public String  trunToActivateAccountUrl(){
		return SUCCESS;
	}

	@Action(value = "/user/activateAccount", results = {
			@Result(name = "success", location = "/WEB-INF/content/user/complete.jsp"),
			@Result(name = "failure", location = "/WEB-INF/content/user/verifica.jsp") })
	public String activateAccount() throws UnsupportedEncodingException {
		String code = request.getParameter("code");
	    code = URLEncoder.encode(code, "utf-8");
		String userName = getDesUserName(code);
		if (userName == null) {
			return ERROR;
		}
		ZunmiUser user = userService.findByName(userName);
		if (user == null) {
			return ERROR;
		} else {
			try {
				user.setStatus(UserStatus.NOMAL);
				userService.save(user);
				return SUCCESS;
			} catch (Exception e) {
				return "failure";
			}
		}

	}
	
	@Action(value="account")
	public String account(){
		if(checkUser()==null){
			try {
				response.sendRedirect("login.action");
			} catch (IOException e) {
			}
		}
		return SUCCESS;
	}

	@Action(value = "/doLogin")
	public void doLogin(){
		String userName = request.getParameter("u");
		String password = request.getParameter("p");
		String remember = request.getParameter("remember");

		ZunmiUserLog userLog = null;
		ZunmiUser muser = userService.findByName(userName);
		if (muser == null) {
			muser = userService.findByEmail(userName);
		}
		if (muser == null) {
			return;
		}
		if (!muser.getPassword()
				.equals(EncryptionHelper.Enc(password, "SHA-1"))) {
			userLog = new ZunmiUserLog(getIpAddr(), false, UserLogType.LOGIN,
					muser);
			userLogService.save(userLog);
			Struts2Utils.renderJson("{\"status\":false,\"error\":\"用户名或密码错误\",\"data\":{}}");
		}

		int maxAge = -1;
		if ("1".equals(remember)) {
			maxAge = 7 * 24 * 60 * 60;
		}

		String toEncString = "userId=" + muser.getId() + ",userName="
				+ muser.getUserName() + ",password=" + muser.getPassword()
				+ ",email=" + muser.getEmail();
		Desed3.getKey("beijingwangzun");
		String userInfo = Desed3.getEncString(toEncString);

		try {
			setCookie("UserName", muser.getUserName(), maxAge);
			setCookie("UserInfo", URLEncoder.encode(userInfo, "utf-8"), maxAge);
			setCookie("UN", muser.getUserName(), 365 * 24 * 60 * 60);
			userLog = new ZunmiUserLog(getIpAddr(), true, UserLogType.LOGIN,muser);
			userLogService.save(userLog);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Struts2Utils.renderJson("{\"status\":true,\"error\":\"\",\"data\":{\"password\":\"0\"}}");
	}

	@Action(value = "/ssoindex")
	public void ssoindex() throws Exception {
		String logout = request.getParameter("logout");
		String exp = request.getParameter("exp");

		Cookie userName = getCookie("UserName");
		Cookie userInfo = getCookie("UserInfo");

		if (userName == null || userInfo == null || "1".equals(logout)) {
			deleteCookie(getCookie("UserName"));
			deleteCookie(getCookie("UserInfo"));
			return;
		}
		if ("rememberLogin".equals(exp)) {
			ZunmiUser user = userService.validCheckCode(userName.getValue(),
					userInfo.getValue());
			if (user == null) {
				deleteCookie(getCookie("UserName"));
				deleteCookie(getCookie("UserInfo"));
			}
			Struts2Utils.renderJson("{\"status\":true,\"error\":\"\"}");
			return;
		}
	}

	@Action(value = "/logout")
	public void logout() {
		deleteCookie(getCookie("UserName"));
		deleteCookie(getCookie("UserInfo"));
		try {
			response.sendRedirect("index.jsp");
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}
	
	@Action(value="setPayPassword",results={@Result(name="success",location="/WEB-INF/content/user/accountpages/setPaymentPassword.jsp")})
	public String setPayPassword(){
		user = checkUser();
		if(user==null){
		   return "";	
		}
		if(user.getZunmiUseraccount().getPaymentPassword()!=null){
			return "error";
		}
		return SUCCESS;
	}
	
	@Action(value="doSetPayPassword",results={@Result(name="success",location="/WEB-INF/content/user/accountpages/setPaymentPassword.jsp")})
	public void doSetPayPassword(){
		String loginPwd = request.getParameter("password");
		String payPwd = request.getParameter("paypassword");
		String payPwd2 = request.getParameter("paypassword2");
		user = checkUser();
		if(user==null){
			return;
		}
		if(payPwd==null||payPwd2==null||!payPwd2.equals(payPwd)){
			return;
		}
		if(!EncryptionHelper.Enc(loginPwd, "SHA1").equals(user.getPassword())){
			return;
		}
		ZunmiUserAccount account = user.getZunmiUseraccount();
		account.setPaymentPassword(EncryptionHelper.Enc(payPwd, "SHA1"));
		accountService.save(account);
	}
	@Action(value="getBackPassword",results={@Result(name="success",location="/WEB-INF/content/user/accountpages/getBackPassword.jsp")})
	public String getBackPassword(){
	       return "getBackPassword";
		}
	//用户找回密码
	public String doGetBackPassword(){
		String email = request.getParameter("email");
		String question = request.getParameter("question");
		String answer  = request.getParameter("answer");
		if(StringUtils.isEmpty(email) || StringUtils.isEmpty(question) || StringUtils.isEmpty(answer)) {
			Struts2Utils.renderJson("{\"status\":false,\"message\":\"输入内容不能为空\",\"data\":{}}");	
			return null;
		}
		ZunmiUser user = userService.findByEmail(email);
		if(user == null) {
			Struts2Utils.renderJson("{\"status\":false,\"message\":\"用户名不存在\",\"data\":{}}");
			return null;
		}
		if(user.getZunmiSecurity().getQuestion().equals(question) && user.getZunmiSecurity().getAnswer().equals(answer) ){
			String password = CheckCodeAction.getRandomPassword();
			user.setPassword(EncryptionHelper.Enc(password, "SHA-1"));
			userService.save(user);
			 //send get password mail
			sendGetPaaswordEmail(user.getEmail(), user.getUserName(), password);
			Struts2Utils.renderJson("{\"status\":false,\"message\":\"已发送邮件，请注意查收\",\"data\":{}}");
			return null;
		} else {
			Struts2Utils.renderJson("{\"status\":false,\"message\":\"问题或者答案不正确\",\"data\":{}}");
			return null;
		}
	}
	/**以上getBackPassword和doGetBackPassword是已经离职的hdy所写的找回密码的流程*/
	/**下面是LY写的找回密码的流程*/
	//1 进入“输入想要找回密码的邮箱”页面
	@Action(value="/user/lostPassWord",results={
			@Result(name="success",location="/WEB-INF/content/user/lostpwd.jsp")})
	public String accessEmail(){
		System.out.println("sssxxx");
		return "success";
	}
	//2 根据邮箱获得安全问题
	@Action(value="/user/lostPwd",results={
			@Result(name="success",location="/WEB-INF/content/user/password_lost.jsp"),
			@Result(name="failed",location="/WEB-INF/content/user/lostpwd.jsp"),
			@Result(name="notSetSecurity",location="/WEB-INF/content/user/lostpwd.jsp")})
	public String getSecurityProblem(){
		String checkCode=request.getParameter("captcha");
		String scheck=(String)httpSession.getAttribute("checkCode");
		String email=request.getParameter("email");		
		if(StringUtils.isEmpty(checkCode)||!checkCode.equalsIgnoreCase(scheck)){
			request.setAttribute("errorMsg","验证码没写或是验证码不正确！");
			request.setAttribute("email", email);
			return "failed";
		}
		user=userService.findByEmail(email);
		if(StringUtils.isEmpty(email)||user==null){
			request.setAttribute("errorMsg","用户不存在或是没您填写用户信息！");
			request.setAttribute("email", email);
			return "failed";
		}
		if(user.getZunmiSecurity()==null){
			request.setAttribute("adminNeeded", "您还没设置密保问题请联系管理员吧！kefu@wangzun.com或qq:16540000");
			return "notSetSecurity";
		}
		question=user.getZunmiSecurity().getQuestion();//
		String now=((Long)System.currentTimeMillis()).toString();
		httpSession.setAttribute("now", now);
		request.setAttribute("now", now);
		return "success";
	}

	//3 密保问题回答正确发送邮件
	@Action(value="/user/confirmAnswer",results={
			@Result(name="success",location="/WEB-INF/content/user/password_lost.jsp"),
			@Result(name="failed",location="/WEB-INF/content/user/password_lost.jsp"),
			@Result(name="timeout",location="/WEB-INF/content/user/lostpwd.jsp")
	})
	public String doConfirmSecrity(){
		String now=request.getParameter("now");
		String sNow=(String)httpSession.getAttribute("now");
		if(StringUtils.isEmpty(now)||StringUtils.isEmpty(sNow)||!now.equals(sNow)){
			//请求超时，请重新加载
			return "timeout";
		}
		httpSession.setAttribute("now", null);
		String answer=request.getParameter("answer");
		String question=request.getParameter("question");
		String email=request.getParameter("userEmail");
		user=userService.findByEmail(email);
		if(StringUtils.isEmpty(email)||StringUtils.isEmpty(question)||StringUtils.isEmpty(answer)||user==null){
			status="FAILED";
			return "failed";
		}
		ZunmiSecurity security=user.getZunmiSecurity();
		if(question.equals(security.getQuestion())&&answer.equals(security.getAnswer())){
			status="SUCCESS";
			String password = CheckCodeAction.getRandomPassword();
			user.setPassword(EncryptionHelper.Enc(password, "SHA-1"));
			userService.save(user);
			 //send get password mail
			request.setAttribute("subEmail", user.getEmail().substring(user.getEmail().indexOf('@')));
			sendGetPaaswordEmail(user.getEmail(), user.getUserName(), password);
			return "success";
		}else{
			status="FAILED";
			return "failed";
		}
	}


	private  String getActivationUrl(ZunmiUser user) {
		String str = user.getEmail() + "|" + user.getUserName() + "|"
				+ user.getPassword();
		Desed3.getKey(key);
		String enc = Desed3.getEncString(str);
		String url = null;
		try {
			url = request.getContextPath()
					+ "/user/activateAccount.action?code="
					+ URLEncoder.encode(enc, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return url;
	}

	private static String getDesUserName(String code) {
		if (code == null) { 
			return null;
		}
		Desed3.getKey(key);
		String desStr = null;
		try {	
			String des = URLDecoder.decode(code, "utf-8");
			desStr = Desed3.getDesString(des);
			System.out.println("des:"+des);
			System.out.println("desStr:"+desStr);
		} catch (Exception e) {
			return null;
		}
		if (desStr == null) {
			return null;
		}
		String[] array = desStr.split("\\|");
		if (array.length >= 1) {
			return array[1];
		} else {
			return null;
		}
	}
	//发送激活邮件
	private void sendActivationEmail(String email, String username, String activationUrl) {
		HashMap<String, Object> model = new HashMap<String, Object>();
		model.put("username", username);
		model.put("email", email);
		model.put("activationUrl", activationUrl);

		try {
			mailService.send(email, "registration.html", model, null);
		} catch (UnsupportedEncodingException e) {
			logger.error("Unable to send push notification email.", e);
		} catch (Exception e) {
			logger.error("Unable to send push notification email.", e);
		}
	}
	//发送找回密码邮件
	private void sendGetPaaswordEmail(String email, String username, String password) {
		HashMap<String, Object> model = new HashMap<String, Object>();
		Format df = FastDateFormat.getInstance("yyyy年MM月dd日HH:mm:ss");
		model.put("date",df.format(new Date()));
		model.put("username", username);
		model.put("email", email);
		model.put("password", password);

		try {
			mailService.send(email, "lostpassword.html", model, null);
		} catch (UnsupportedEncodingException e) {
			logger.error("Unable to send push notification email.", e);
		} catch (Exception e) {
			logger.error("Unable to send push notification email.", e);
		}
	}
//	public static void main(String[] args) throws UnsupportedEncodingException {
//		String str = "abc@163.com|hdy|7c4a8d09ca3762af61e59520943dc26494f8941b";
//		//String code = "DqYDMgbwMkOSCJ3dlZG5gS7T%2B5TZjez33VLEvaIWQp9sqJKmeSIMnuqB9DwIYlfo7tTA%2BEx3wf%2BK%0D%0ANqDCnbm6sg%3D%3D";
//		String receiveCode = "DqYDMgbwMkOSCJ3dlZG5gS7T+5TZjez33VLEvaIWQp9sqJKmeSIMnuqB9DwIYlfo7tTA+Ex3wf+KNqDCnbm6sg==";
//		String s1 = URLEncoder.encode(receiveCode, "utf-8");
//		System.out.println("eeee:"+URLDecoder.decode(s1, "utf-8"));
//		System.out.println("dddd:"+s1);
////		String s2 = URLDecoder.decode(s1, "utf-8");
////		System.out.println("ddd2:"+s2);
//		System.out.println("ss:"+getDesUserName(s1));
//	}


}
