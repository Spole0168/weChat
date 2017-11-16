<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>

<%@ page import="org.springframework.security.ui.AbstractProcessingFilter" %>  
<%@ page import="org.springframework.security.ui.webapp.AuthenticationProcessingFilter" %>  
<%@ page import="org.springframework.security.AuthenticationException" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title></title>
    <script src="${ctx}/scripts/jquery-1.7.2.min.js" type="text/javascript"></script>
    <link href="${ctx}/themes/css/login.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript">
    $(function(){
    });
    
    function doClick(){
  		 debugger
  		var userName = $("#userName").val();
  		var password = $("#password").val();
  		//alert("userName"+userName+"  password"+password);
  		$.post(
  				"${ctx}/manager/login!login.action", 
  				{"j_username": userName,
  				 "j_password": password },
  				function(data){
  					 debugger
  					 //alert(data.statusCode);
  					 if(data.statusCode==200||data.statusCode=='200'){
  					//	 alert(data.statusCode+"ddd");
  						window.location.href="${ctx}/manager/main.action";
  					 }else{
  						 $("#ts_div1").text(data.message);
  						 //alert(""+data.message);
  					 }
  						
  	       },"json");
  	}
    
    function doLogin(){
    	return true;
    }
	</script>
  </head>
  <body>
  <div id="login">
		<div id="login_header">
			<h1 class="login_logo">
				<a href=""><img src="${ctx}/themes/default/images/login_yzy_logo.png" /></a>
			</h1>
			<div class="login_headerContent">
				<div class="navList">
					<ul>
						<li><a href="#">设为首页</a></li>
						<li><a href="#">反馈</a></li>
						<li><a href="#" target="_blank">帮助</a></li>
					</ul>
				</div>
				<h2 class="login_title"><img src="${ctx}/themes/default/images/login_yzy_title.png" /></h2>
			</div>
		</div>
		<div id="login_content">
			<div class="loginForm">
			    <iframe id="hidden_iframe" name="hidden_iframe" style="display:none"></iframe>
			    <!-- action="${ctx}/loginServlet.ajax" method="post" target="hidden_iframe" onSubmit="return doLogin()" -->
				<div id="loginForm"    > 
					<p>
						<label>用户名：</label>
						<input type='text' id='userName' name='j_username' class="login_input" value=""
									<s:if test="not empty param.error">	value='<%=session.getAttribute(AuthenticationProcessingFilter.SPRING_SECURITY_LAST_USERNAME_KEY)%>'</s:if>/>
					</p>
					<p>
						<label>密&nbsp;&nbsp;&nbsp;&nbsp;码：</label>
						<input type='password' id='password' name='j_password' class="login_input" value=""/>
					</p>
					<!-- 
					<p>
						<label>验证码：</label>
						<input class="code" type="text" size="5" />
						<span><img src="${ctx}/themes/default/images/header_bg.png" alt="" width="75" height="24" /></span>
					</p>
					 -->
					<div class="login_bar">
						<input class="sub" type="button"  onclick="doClick()"      value=" " />
					</div>
					<div style="padding-top:20px;padding-left:20px;">
							<%
							if (session.getAttribute(AbstractProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY) != null) {
							%>
								<div style="color:red;">
								${sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message}登录失败，请重试.
								</div>
							<%
							}
							else
							{
							%>
								<div id="ts_div" style="color:red;display: none;">请用您的用户名和密码登录.</div>
							<%
								}
							%>
							<div id="ts_div1" style="color:red;"></div>
					</div>
				</div>
			</div>
			<div class="login_banner"><img src="${ctx}/themes/default/images/login_banner.jpg" /></div>
			<div class="login_main">
				<ul class="helpList">
					<!--<li><a href="#">##############</a></li>
					<li><a href="#">##############</a></li>
					<li><a href="#">##############</a></li>
					<li><a href="#">##############</a></li>  -->
				</ul>
				<div class="login_inner">
					<!--<p>###############################</p>
					<p>###############################</p>
					<p>###############################</p>-->
				</div>
			</div>
		</div>
		<div id="login_footer">
			Copyright &copy; 2012 . All Rights Reserved.
		</div>
		<script src="${ctx}/scripts/jquery-1.7.2.min.js" type="text/javascript"></script>
    	<script type="text/javascript">
             //登录结果回调
             function callback(code){
             //	alert();
                if(code==null || code!="200") { $('#ts_div').html("用户名、密码错误！");return; }                
                location.href="${ctx}/manager/main.action";
             }
	    </script>
	</div>
  </body>
</html>
