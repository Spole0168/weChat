<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>


<div class="pageHeader">
	<form id="pagerForm" onsubmit="return navTabSearch(this)" action="${ctx}/manager/question!adminQuestionSearch.action" method="post">
	 <input type="hidden" name="pageNum" value="${pageNum}" />
	 <input type="hidden" name="numPerPage" id="numPerPageHd" value="${numPerPage}" />
	 <div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					<label>用户名称：</label><input type="text" name="domainStore.zunmiUser.userName" value="${domainStore.zunmiUser.userName }" />
				</td>
			</tr>
			
		</table>
		 
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit" >检索</button></div></div></li>
				<li><a class="button" href="${ctx}/manager/question!search.action" target="dialog" rel="question_search" title="查询框"><span>高级检索</span></a></li>				
				<!-- 高级查询提供n多条件，可以查询回复时间等的条件 -->
			</ul>
		</div>
	  </div>
	</form>
</div>
<div class="pageContent">
      
	<div class="panelBar">
		<ul class="toolBar">
		<!--<li><a class="add" href="${ctx}/manager/admin!input.action" target="dialog" rel="admin_input_dialog" height="350" width="600" title="管理员添加"><span>添加</span></a></li>
			<li><a class="delete" href="${ctx}/manager/admin!del.action?adminId={sid_question}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
	
		 	<li><a class="delete" href="${ctx}/manager/domain-store!delStore.action?domainStoreId={sid_domainStore}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
				 -->
			<li><a class="edit" href="${ctx}/manager/domain-store!getUserDetail.action?domainStoreId={sid_domainStore}" rel="domain_store_input_dialog" target="dialog" height="450" width="740"><span>编辑</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="demo/common/dwz-team.xls" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table id="domainStoreList" class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="30"></th>
				<th style="display:none"></th>
				<th width="180" align="center">旺铺名称</th>
				<th width="160">用户账号</th>
				<th width="150">创建时间</th>
				<th width="160">旺铺地址</th>
				<th width="100" align="center">推荐</th>
				<th width="100">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${list}" varStatus="index">
				<tr target="sid_domainStore" rel="${item.id}">
				<td>${index.count}</td>
				<td style="display:none">${item.id}</td>
				<td>${item.storeName}</td>
				<td>${item.zunmiUser.userName }</td>
				<td>${item.creationTime }</td>
				<td>${item.storeUrl }</td>
				<td>
				<c:if test="${item.recommend=='true'}">是</c:if>
				<c:if test="${item.recommend=='false'}">否</c:if>
				</td>
		          <td>操作*******</td> 
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

