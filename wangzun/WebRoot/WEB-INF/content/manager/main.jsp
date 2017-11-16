<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="org.springframework.security.ui.AbstractProcessingFilter"%>
<%@ page import="org.springframework.security.ui.webapp.AuthenticationProcessingFilter"%>
<%@ page import="org.springframework.security.AuthenticationException"%>
<%@ page import="org.springframework.security.context.SecurityContextHolder"%>
<%@ page import="org.property.core.modules.security.springsecurity.SpringSecurityUtils" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>银之源管理平台</title>

<link href="${ctx}/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="${ctx}/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="${ctx}/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
<link href="${ctx}/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>
<!--[if IE]>
<link href="${ctx}/themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
<![endif]-->

<script src="${ctx}/js/speedup.js" type="text/javascript"></script>
<script src="${ctx}/js/jquery-1.7.1.js" type="text/javascript"></script>
<script src="${ctx}/js/jquery.cookie.js" type="text/javascript"></script>
<script src="${ctx}/js/jquery.validate.js" type="text/javascript"></script>
<script src="${ctx}/js/jquery.bgiframe.js" type="text/javascript"></script>
<script src="${ctx}/xheditor/xheditor-1.1.12-zh-cn.min.js" type="text/javascript"></script>
<script src="${ctx}/uploadify/scripts/swfobject.js" type="text/javascript"></script>
<script src="${ctx}/uploadify/scripts/jquery.uploadify.v2.1.0.js" type="text/javascript"></script>

<script src="${ctx}/js/dwz.core.js" type="text/javascript"></script>
<script src="${ctx}/js/dwz.util.date.js" type="text/javascript"></script>
<script src="${ctx}/js/dwz.validate.method.js" type="text/javascript"></script>
<script src="${ctx}/js/dwz.regional.zh.js" type="text/javascript"></script>
<script src="${ctx}/js/dwz.barDrag.js" type="text/javascript"></script>
<script src="${ctx}/js/dwz.drag.js" type="text/javascript"></script>
<script src="${ctx}/js/dwz.tree.js" type="text/javascript"></script>
<script src="${ctx}/js/dwz.accordion.js" type="text/javascript"></script>
<script src="${ctx}/js/dwz.ui.js" type="text/javascript"></script>
<script src="${ctx}/js/dwz.theme.js" type="text/javascript"></script>
<script src="${ctx}/js/dwz.switchEnv.js" type="text/javascript"></script>
<script src="${ctx}/js/dwz.alertMsg.js" type="text/javascript"></script>
<script src="${ctx}/js/dwz.contextmenu.js" type="text/javascript"></script>
<script src="${ctx}/js/dwz.navTab.js" type="text/javascript"></script>
<script src="${ctx}/js/dwz.tab.js" type="text/javascript"></script>
<script src="${ctx}/js/dwz.resize.js" type="text/javascript"></script>
<script src="${ctx}/js/dwz.dialog.js" type="text/javascript"></script>
<script src="${ctx}/js/dwz.dialogDrag.js" type="text/javascript"></script>
<script src="${ctx}/js/dwz.sortDrag.js" type="text/javascript"></script>
<script src="${ctx}/js/dwz.cssTable.js" type="text/javascript"></script>
<script src="${ctx}/js/dwz.stable.js" type="text/javascript"></script>
<script src="${ctx}/js/dwz.taskBar.js" type="text/javascript"></script>
<script src="${ctx}/js/dwz.ajax.js" type="text/javascript"></script>
<script src="${ctx}/js/dwz.pagination.js" type="text/javascript"></script>
<script src="${ctx}/js/dwz.database.js" type="text/javascript"></script>
<script src="${ctx}/js/dwz.datepicker.js" type="text/javascript"></script>
<script src="${ctx}/js/dwz.effects.js" type="text/javascript"></script>
<script src="${ctx}/js/dwz.panel.js" type="text/javascript"></script>
<script src="${ctx}/js/dwz.checkbox.js" type="text/javascript"></script>
<script src="${ctx}/js/dwz.history.js" type="text/javascript"></script>
<script src="${ctx}/js/dwz.combox.js" type="text/javascript"></script>
<script src="${ctx}/js/dwz.print.js" type="text/javascript"></script>
<!--
<script src="bin/dwz.min.js" type="text/javascript"></script>
-->
<script src="${ctx}/js/dwz.regional.zh.js" type="text/javascript"></script>

<script type="text/javascript">
$(function(){
	DWZ.init("${ctx}/dwz.frag.xml", {
		//loginUrl:"${ctx}/login-dialog.action", loginTitle:"用户登录",	// 弹出登录对话框
		loginUrl:"${ctx}/manager/login.action",	// 跳到登录页面
		statusCode:{ok:200, error:300, timeout:301}, //【可选】
		pageInfo:{pageNum:"pageNum", numPerPage:"numPerPage", orderField:"orderField", orderDirection:"orderDirection"}, //【可选】
		debug:false,	// 调试模式 【true|false】
		callback:function(){
			initEnv();
			$("#themeList").theme({themeBase:"themes"}); // themeBase 相对于index页面的主题base路径
		}
	});
});
  //登录结果回调
  function callback(code){
     if(code==null || code!="200") { $('#ts_div').html("用户名、密码错误！");return; }                
     window.location.href="${ctx}/manager/main.action";
  }
</script>
</head>

<body scroll="no">
	<div id="layout">
		<div id="header">
			<div class="headerNav">
				<a class="logo" href="#">标志</a>
				<ul class="nav">
				<!--  
					<li id="switchEnvBox"><a href="javascript:">（<span>北京</span>）切换城市</a>
						<ul>
							<li><a href="sidebar_1.html">北京</a></li>
							<li><a href="sidebar_2.html">上海</a></li>
							<li><a href="sidebar_2.html">南京</a></li>
							<li><a href="sidebar_2.html">深圳</a></li>
							<li><a href="sidebar_2.html">广州</a></li>
							<li><a href="sidebar_2.html">天津</a></li>
							<li><a href="sidebar_2.html">杭州</a></li>
						</ul>
					</li>
					-->
					
					<li><a href="${ctx}/manager/admin-change-pwd.action" target="dialog" width="550" height="250">修改密码</a></li>
					<!--  
					<li><a href="http://weibo.com/zunmiwang" target="_blank">微博</a></li>
				-->
					<li><a href="${ctx}/manager/login!logoff.action" title="确定要退出吗？">退出</a></li>
				</ul>
					<!--  
				<ul class="themeList" id="themeList">
					<li theme="default"><div class="selected">蓝色</div></li>
					<li theme="green"><div>绿色</div></li>
					<li theme="purple"><div>紫色</div></li>
					<li theme="silver"><div>银色</div></li>
					<li theme="azure"><div>天蓝</div></li>
				</ul>
				-->
			</div>

			<!-- navMenu --> 
			
		</div>

		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse"><div></div></div>
				</div>
			</div>
			<div id="sidebar">
				<div class="toggleCollapse"><h2>主菜单</h2><div>收缩</div></div>

				<div class="accordion" fillSpace="sidebar">
				 <div class="accordionHeader">
						<h2><span>Folder</span>用户信息列表</h2>
					 </div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
						     <li><a href="${ctx}/yinzy/userinfo.action"  target="navTab" id="appointmentcheck_list"  rel="userinfo-list">激活用户列表</a></li>
							<li><a href="${ctx}/yinzy/userinstallapply.action"  target="navTab"  rel="userinstallapply_list">新增申请用户列表</a></li>
							<!--
							<li><a href="${ctx}/yinzy/userinstallapply.action"  target="navTab"  rel="userinstallapply_list">用户二维码生成相关</a></li>
						     -->
						</ul>
					</div>	
					<div class="accordionHeader">
						<h2><span>Folder</span>社区服务申请管理</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a href="${ctx}/yinzy/serviceapply.action" target="navTab" rel="content_list">服务商申请列表</a></li>
						</ul>
					</div>	
				   <div class="accordionHeader">
						<h2><span>Folder</span>用户预约</h2>
					 </div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a href="${ctx}/yinzy/appoitmentcheck.action"   target="navTab"  rel="userinstallapply_list">用户预约申请检测列表</a></li>
							<!-- 
							<li><a href="${ctx}/yinzy/userinstallapply.action"  target="navTab"  rel="appointmentcheck-list">用户预约申请列表</a></li>
							 -->
							<li><a href="${ctx}/yinzy/repairlog.action"  target="navTab"  rel="repairlog-list">维修记录表</a></li>
						</ul>
					</div>	
					 <!--  
					<div class="accordionHeader">
						<h2><span>Folder</span>生成管理--测试</h2>
					</div>			
					
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a href="${ctx}/outeract/zhuanjijh.action"   target="navTab"   rel="index_page_creation1">装机人员激活页面1</a></li>
						    <li><a href="${ctx}/outeract/applyinstallouter.action"   target="navTab"   rel="index_page1_creation2">申请安装测试页面2</a></li>
						     <li><a href="${ctx}/outeract/appointmentouter.action"   target="navTab"   rel="index_page2_creation3">申请检测测试页面3</a></li>
						    <li><a href="${ctx}/outeract/showwaterinfo.action"   target="navTab"   rel="index_page2_creation4">查询饮水机信息测试页面</a></li>
						     <li><a href="${ctx}/outeract/showrepairlog.action"   target="navTab"   rel="index_page2_creation5">查询维修记录测试页面</a></li>
						    <li><a href="${ctx}/outeract/repairlogouter.action"   target="navTab"   rel="index_page2_creation6">饮水机维修测试页面</a></li>
						     <li><a href="${ctx}/outeract/getappointmsg.action"   target="navTab"   rel="index_page2_creation7">获取预约检测信息测试页面</a></li>
						</ul>
					</div>		
					-->
				</div>
			</div>
		</div>
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main"><a href="javascript:;"><span><span class="home_icon">银之源</span></span></a></li>
						</ul>
					</div>
					<div class="tabsLeft">left</div><!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div><!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:;">&nbsp;</a></li>
				</ul>
				<div class="navTab-panel tabsPageContent layoutBox">
					<div class="page unitBox">
						<div class="accountInfo">
							<div class="alertInfo">
								<h2><a href="#" target="_blank">&nbsp;</a></h2>
								<a href="#" target="_blank">&nbsp;</a>
							</div>
							<div class="right">
								<p><a href="#" target="_blank" style="line-height:19px">&nbsp;</a></p>
								<p><a href="#" target="_blank" style="line-height:19px">&nbsp;</a></p>
							</div>
							<p><span>&nbsp;</span></p>
							<p>&nbsp;<a href="#" target="_blank">&nbsp;</a></p>
						</div>
						<div class="pageFormContent" layoutH="80" style="margin-right:230px">
							
						
							
						</div>
						
						<div style="width:320px;position: absolute;top:60px;right:0" layoutH="80">
						</div>
					</div>
					
				</div>
			</div>
		</div>

	</div>

	<div id="footer">Copyright &copy; 2012 <a href="demo_page2.html" target="dialog">xxx</a> Tel：010-xxxxxxxx</div>
</body>
</html>
