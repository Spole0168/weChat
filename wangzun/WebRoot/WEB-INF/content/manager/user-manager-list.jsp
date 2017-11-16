<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>


<div class="pageHeader">
	<form id="pagerForm" onsubmit="return navTabSearch(this)" action="${ctx}/manager/user-manager!search.action" method="post">
	 <input type="hidden" name="pageNum" value="${pageNum}" />
	 <input type="hidden" name="numPerPage" id="numPerPageHd" value="${numPerPage}" />
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					<label>用户名：</label><input type="text" name="user.userName" value="${user.userName }"/>
				</td>
				<td>
					<select class="combox" name="user.status">
						<option value="ALL">所有状态</option>
						<option value="NOMAL">正&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;常</option>
						<option value="LOCKED">锁&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;定</option>
						<option value="DISABLED">已删除的</option>
					</select>
				</td>
				<td>
					<label>创建日期：</label><input type="text" name="littleTime" class="date" format="yyyy-MM-dd HH:mm:ss" readonly="true" value="${littleTime }"/>
						&nbsp;&nbsp;到&nbsp;&nbsp;<input type="text" name="greatTime" class="date" readonly="true" format="yyyy-MM-dd HH:mm:ss" value="${greatTime}"/>
						之间
				</td>
			</tr>
		</table>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
				<li><a class="button" href="demo_page6.html" target="dialog" mask="true" title="查询框"><span>高级检索</span></a></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="${ctx}/manager/user-manager!input.action" target="dialog" rel="user_manager_input_dialog" height="450" width="750" title="用户信息添加"><span>添加</span></a></li>
			<li><a class="delete" href="${ctx}/manager/user-manager!delUser.action?userId={sid_user}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
			<li><a class="edit" href="${ctx}/manager/user-manager!input.action?userId={sid_user}" target="dialog" height="350" width="600"><span>修改</span></a></li>
						<li><a class="edit" href="${ctx}/manager/user-manager!resetPassword.action?userId={sid_user}" target="dialog" height="350" width="600" title="重设用户密码"><span>重设密码</span></a></li>
			<!--  
			<li><a class="edit" href="${ctx}/manager/user-manager!input.action?userId={sid_user}" target="dialog" height="350" width="600"><span>锁定</span></a></li>
			-->
			<li class="line">line</li>
			<li><a class="icon" href="demo/common/dwz-team.xls" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table id="userManagerList" class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="45"></th>
				<th style="display:none"></th>
				<th width="200">用户账号</th>
				<th width="160">用户昵称</th>
				<th width="165">注册时间</th>
				<th width="160" align="center">状态</th>
				<th width="150">域名旺铺</th>
				<!-- 经过计算，他们的width总和必须是880 -->
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${list}" varStatus="index">
				<tr target="sid_user" rel="${item.id}">
				<td>${index.count}</td>
				<td style="display:none">${item.id}</td>
				<td>${item.email}</td>
				<td>${item.userName }</td>
				<td>${item.creationDate }</td>
				<td>
					<!-- 初步想发是弄一个下拉列表，在失去焦点事件的时候改变user的status 从而实现解锁和-->
					<c:if test="${item.status=='NOMAL'}">正常</c:if>
					<c:if test="${item.status=='LOCKED'}">被锁定</c:if>
					<c:if test="${item.status=='DISABLED'}">被删除</c:if>
				</td>
				<td>
					<c:if test="${item.zunmiDomainstores.size()!=0}">已开通<a href="#">【再次开通】</a></c:if>
					<c:if test="${item.zunmiDomainstores.size()==0}">
					未开通<a href="${ctx}/manager/domain-store!getUserDetail.action?userId=${item.id}"
					                      target="dialog" rel="domain_store_input_dialog" height="450" width="800" title="开通旺铺页面">【开通】</a>
					                      <!-- target="navTab"  -->
					</c:if>
				</td>
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
