<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>


<div class="pageHeader">
	<form id="pagerForm" onsubmit="return navTabSearch(this)" action="${ctx}/manager/domain!search.action" method="post">
	 <input type="hidden" name="pageNum" value="${pageNum}" />
	 <input type="hidden" name="numPerPage" id="numPerPageHd" value="${numPerPage}" />
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					<label>域&nbsp;&nbsp;&nbsp;&nbsp;
					         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</label><input type="text" name="domain.domain" value="${domain.domain }"/>
				</td>
				<td>
					<label>域名状态：</label><select class="combox" name="domain.status">
						<option value="ALL">所有状态</option>
						<option value="PENDING">未验证</option>
						<option value="WAIT">等待</option>
						<option value="FAILED">失败</option>
						<option value="SUCCESSFUL">成功</option>
						<option value="END">结束</option>
						<option value="ABORTED">ABORTED</option>
					</select>
				</td>
			    <td>
			    	<label>域名持有人昵称：</label>&nbsp;
			        <input type="text" name="domain.zunmiUser.userName" value="${domain.zunmiUser.userName }"/>
			    </td>
			</tr>
		</table>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
					<!--  
				<li><a class="button" href="demo_page6.html" target="dialog" mask="true" title="查询框"><span>高级检索</span></a></li>
					-->
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="icon" href="demo/common/dwz-team.xls" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table id="domainSaleList" class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="30"></th>
				<th style="display:none"></th>
				<th width="110">出售域名</th>
				<th width="120">所属用户</th>
				<th width="130">创建时间</th>
				<th width="100">域名特征</th>
				<th width="100">买卖状态</th>
				<th width="130">截止时间</th>   
				<th width="160">操作</th>  
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${list}" varStatus="index">
				<tr target="sid_domain" rel="${item.id}">
				<td>${index.count}</td>
				<td style="display:none">${item.id}</td>
				<td>${item.domain}</td>
				<td>${item.zunmiUser.email}</td>
				<td>${item.creationTime}</td>
				<td>${item.domainType }</td>
				<td>${item.status }</td>
				<td>${item.expirationTime}</td>
				<td>
				<a href="${ctx}/manager/domain-sale!addDomainOP.action?domainId=${item.id}" target="dialog" height="450" width="740" title="出售此域名">
				<font style="color: green">出售</font>
				</a>
				</td>
				                   <!-- 剩余时间就用finisTime减去当前时间（FinishTime必须大于当前时间（否则输出已结束）） -->
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
