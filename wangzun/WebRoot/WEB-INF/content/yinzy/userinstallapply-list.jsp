<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<div class="pageHeader">
	<form id="pagerForm" onsubmit="return navTabSearch(this)" action="${ctx}/yinzy/userinstallapply!search.action" method="post">
	 <input type="hidden" name="pageNum" value="${pageNum}" />
	 <input type="hidden" name="numPerPage" id="numPerPageHd" value="${numPerPage}" />
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					<label>姓名：</label><input type="text" name="userName"  value="${userName }"/>
				</td>
				<td>
					<label>电话：</label><input type="text" name="tel"  value="${tel }"/>
				</td>
				<td>
					<label>申请日期：</label><input type="text" name="apply_Date"  value ="${apply_Date }"  class="date" readonly="true" />
				</td>
			</tr>
		</table>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
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
			<li><a class="edit" href="${ctx}/manager/admin!input.action?adminId={sid_user}" target="dialog" height="350" width="600"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="demo/common/dwz-team.xls" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		
		    <li><a class="delete" href="${ctx}/yinzy/userinstallapply!del.action?serviceId={sid_user}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
		 -->
		</ul>
	</div>
	<table id="adminList" class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="30"></th>
				<th style="display:none"></th>
				<th width="120">姓名</th>
				<th width="120">电话</th>
				<th width="150">地址</th>
				<th width="150">申请时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${list}" varStatus="index">
				<tr target="sid_user" rel="${item.id}">
				<td>${index.count}</td>
				<td style="display:none">${item.id}</td>
				<td>${item.userName}</td>
				<td>${item.telphone}</td>
				<td>${item.instak_address }</td>
				<td>${item.apply_Date }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox" id="numPerPage" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})"  value="${numPerPage}">
				<option value="20">20</option>
				<option value="50">50</option>
				<option value="100">100</option>
				<option value="200">200</option>
			</select>
			<span>条，共${totalCount}条</span>
		</div>
		<div class="pagination" targetType="navTab" totalCount="${totalCount}" numPerPage="${numPerPage}" pageNumShown="5" currentPage="${pageNum}"></div>
	</div>
</div>
