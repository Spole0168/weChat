<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String wxno = request.getParameter("wxno");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    
   <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
	<base href="<%=basePath%>">

    <title>首页</title>
    <c:set var="ctx" value="${pageContext.request.contextPath}" />
    <script type="text/javascript" src="<c:url value="/js/jquery-1.7.2.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/js/alert.js" />"></script>
    <script type="text/javascript" src="<c:url value="/js/alertify.min.js" />"></script>
    <link href="<c:url value="/css/alertify.css" />" rel="stylesheet">

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/signin_n.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy this line! -->
    <!--[if lt IE 9]><script src="../../docs-assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
  </head>
  
  <script type="text/javascript">
    Alertify.dialog.labels.ok = "确定";
  
  	$(function(){
		$("#xxt_submit").live("click",function(){
		
			if($("#account").val() == '') {
				Alertify.dialog.alert("请输入用户名!");
				return false;
			}
			if($("#pwd").val() == '') {
				Alertify.dialog.alert("请输入密码!");
				return false;
			}
		
			var param = {
				account:$("#account").val(),
				pwd:$("#pwd").val(),
				wxno:"<%=wxno %>" 
			};
			$.ajax({
				type:"POST",
				url: "${ctx}/bindServlet",
				data:param,
				cache:false,		            
				dataType: "text",
				success:function(data){
					console.log(data);
					if(data == -10){
						//alert("用户名或密码错误！");
						Alertify.dialog.alert("用户名或密码错误！");
					} else if(data == 0){
						//alert("绑定成功！");
						Alertify.dialog.alert("您的校讯通帐号已经与微信绑定成功，请点击屏幕左上方的返回按键，返回主菜单!");
					} else {
						//alert("失败！");
						Alertify.dialog.alert("亲，服务器繁忙，请稍后重试!");
					}
				}
			});
		})
	});
  </script>
  
  
  <body>
    <div class="container">
    	<div class="form_box_dv box-shadow">
    		<h3 class="signin-h3">微信账号绑定</h3>
			<div class="form-signin">
				<p><input type="text" class="form-control" placeholder="请输入校讯通账号" name="account" id="account"></p>
				<p><input type="password" class="form-control" placeholder="请输入校讯通密码" name="pwd" id="pwd"></p>
				<!-- 
				<label class="yzm-label">
					<input type="text" class="form-control" placeholder="验证码" maxLength="4" ><img src="image/yzm.gif" alt="验证码"/>
				</label>
				 -->
				<button class="btn btn-lg btn-primary btn-block" type="button" id="xxt_submit">确认提交</button>
			</div>
  		</div>
    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
  </body>
</html>
