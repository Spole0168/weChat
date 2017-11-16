<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<div class="pageHeader">
	<form id="pagerForm" onsubmit="return navTabSearch(this)" action="${ctx}/yinzy/repairlog!search.action" method="post">
	 <input type="hidden" name="pageNum" value="${pageNum}" />
	 <input type="hidden" name="numPerPage" id="numPerPageHd" value="${numPerPage}" />
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					<label>申请日期：</label><input type="text" name="repairtime"  value="${repairtime }" class="date" readonly="true" />
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
		 -->
		 	  <li><a class="delete" href="${ctx}/yinzy/repairlog!del.action?serviceId={sid_user}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
			<li><a class="edit" href="${ctx}/yinzy/repairlog!show.action?serviceId={sid_user}" target="dialog" height="350" width="600"><span>展示</span></a></li>
		</ul>
	</div>
	<table id="adminList" class="table" width="100%" layoutH="138" style="table-layout:fixed;">
		<thead>
			<tr>
				<th width="30"></th>
				<th style="display:none"></th>
				<th width="120">饮水机ID</th>
				<th width="120">维修时间</th>
				<th width="100">维修人员工号</th>
				<th width="50">口感</th>
				<th width="50">水垢</th>
				<th width="50">异味</th>
				<th width="50">输入TDS</th>
				<th width="50">输出TDS</th>
				<th width="150">维修内容</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${list}" varStatus="index">
				<tr target="sid_user" rel="${item.id}">
				<td>${index.count}</td>
				<td style="display:none">${item.id}</td>
				<td>${item.text4}</td>
				<td>${item.repairtime}</td>
				<td>${item.serviceuserid}</td>
				<td>${item.text1}</td>
				<td>${item.scaleflag }</td>
				<td>${item.text2 }</td>
				<td>${item.watertds }</td>
				<td>${item.outputtds }</td>
				<td style="word-break:break-all;">${item.servicepoint }</td>
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
