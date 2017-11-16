<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

</div>



<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<div class="pageHeader">
	<form id="pagerForm" onsubmit="return navTabSearch(this)" action="${ctx}/yinzy/serviceapply!search.action" method="post">
	 <input type="hidden" name="pageNum" value="${pageNum}" />
	 <input type="hidden" name="numPerPage" id="numPerPageHd" value="${numPerPage}" />
	<div class="searchBar">
	<!--
		<table class="searchContent">
			<tr>
				<td>
					<label>客户名称：</label><input type="text" name="serviceApply.communityName" />
				</td>
				<td>
					<label>申请人：</label><input type="text" name="serviceApply.applyeName" />
				</td>
				<td>
					<label>激活日期：</label><input type="text" name="serviceApply.creationDate" class="date" readonly="true" />
				</td>
			</tr>
		</table>  -->
		<div class="subBar">
			<ul>
			<!--	<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
				 <li><a class="button" href="demo_page6.html" target="dialog" mask="true" title="查询框"><span>高级检索</span></a></li> -->
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
		    <!--  
			<li><a class="add" href="${ctx}/manager/admin!input.action" target="dialog" rel="admin_input_dialog" height="350" width="600" title="管理员添加"><span>添加</span></a></li>
			<li><a class="delete" href="${ctx}/manager/admin!del.action?adminId={sid_user}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
		
			<li><a class="edit" href="${ctx}/yinzy/serviceapply!show.action?serviceId={sid_user}" target="dialog" height="350" width="600"><span>展示</span></a></li>
			<li><a class="edit" href="${ctx}/yinzy/serviceapply!input.action?serviceId={sid_user}" target="dialog" height="350" width="600"><span>修改</span></a></li>
				--><!-- <li class="line">line</li>
			<li><a class="icon" href="demo/common/dwz-team.xls" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
			 -->
		</ul>
	</div>
<div class="pageContent">
	<form  method="post" action="${ctx}/outeract/zhuanjijh!jihuo.action" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
		  <table  style="padding-left: 20px">
		  		<tr>
		              <td><label>饮水机id：</label></td>
		               <td><input id="machine_id" name="machine_id" value="${machine_id}" class="required alphanumeric" size="50" value="" alt="请输入饮水机id"/></td>
		  		</tr>
		  		<tr>
		              <td><label>装机人员id：</label></td>
		               <td><input id="zhuangji_id" name="zhuangji_id" value="${zhuangji_id}" class="required" size="50" alt="请输入装机人员id"></td>
		  		</tr>
		  		<tr>
		              <td><label>名称：</label></td>
		               <td><input id="name" name="name" value="${name}" size="50“  minlength="1" maxlength="110" alt="请输入业主名称"></td>
		  		</tr>
		  		<tr>
		              <td><label>电话：</label></td>
		               <td><input id="tel" name="tel" value="${tel} "  class="required" size="50"  maxlength="14"  alt="请输入电话"></td>
		  		</tr>
		  			<tr>
		              <td><label>身份证号：</label></td>
		               <td><input id="personid" name="personid" value="${personid}"   size="50" alt="请输入居民身份证号"/></td>
		  		</tr>
		  			<tr>
		              <td><label>地址：</label></td>
		               <td><input id="address" name="address" value="${address}"  size="50" minlength="1" maxlength="110"  alt="请输入地址"/></td>
		  		</tr>
		  			<tr>
		              <td><label>tds_in：</label></td>
		               <td><input id="tds_in" name="tds_in" value="${tds_in}"  size="50" alt="请输入tds_in"/></td>
		  		</tr>
		  			<tr>
		              <td><label>tds_out：</label></td>
		               <td><input id="tds_out" name="tds_out" value="${tds_out}"  size="50" alt="请输入tds_out"/></td>
		  		</tr>
		  			<tr>
		              <td><label>pay：</label></td>
		               <td><input id="pay" name="pay" value="${pay}"  size="50" alt="请输入pay"/></td>
		  		</tr>
		  			<tr>
		              <td><label>pay_by：</label></td>
		               <td><input id="pay_by" name="pay_by" value="${pay_by}"  size="50" alt="请输入pay_by"/></td>
		  		</tr>
		  			<tr>
		              <td colspan="2" align="center"><button type="submit">保存</button></td>
		  		</tr>
		  </table>
		</div>
	</form>
</div>





