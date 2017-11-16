<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>


<div class="pageHeader">
	<form id="pagerForm" onsubmit="return navTabSearch(this)" action="${ctx}/manager/domain-sale!search.action" method="post">
	 <input type="hidden" name="pageNum" value="${pageNum}" />
	 <input type="hidden" name="numPerPage" id="numPerPageHd" value="${numPerPage}" />
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					<label>域&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</label><input type="text" name="domainAuction.domainName" value="${domainAuction.domainName }"/>
				</td>
				<td>
					<label>添加日期：</label><input type="text" name="littleTime" class="date" format="yyyy-MM-dd HH:mm" 
					                                           value="${littleTime }" readonly="true" />到
											<input type="text" name="greatTime" class="date" format="yyyy-MM-dd HH:mm" 
											                   value="${ greatTime}" readonly="true" />之间
				</td>
				<td>
					<label>域名状态：</label>
					<c:choose>
				    <c:when test="${domainOnePrize.status=='PENDING' }">
					<select class="combox" class="required" name="domainOnePrize.status">
						<option value="ALL">所有状态</option>
						<option value="PENDING" selected="selected">未验证</option>
						<option value="WAIT">等待</option>
						<option value="FAILED">失败</option>
						<option value="SUCCESSFUL">成功</option>
						<option value="END">结束</option>
						<option value="ABORTED">ABORTED</option>
					</select>
					</c:when>
					 <c:when test="${domainOnePrize.status=='WAIT' }">
					<select class="combox" class="required" name="domainOnePrize.status">
						<option value="ALL">所有状态</option>
						<option value="PENDING">未验证</option>
						<option value="WAIT" selected="selected">等待</option>
						<option value="FAILED">失败</option>
						<option value="SUCCESSFUL">成功</option>
						<option value="END">结束</option>
						<option value="ABORTED">ABORTED</option>
					</select>
					</c:when>
					<c:when test="${domainOnePrize.status=='FAILED' }">
					<select class="combox" class="required" name="domainOnePrize.status">
						<option value="ALL">所有状态</option>
						<option value="PENDING">未验证</option>
						<option value="WAIT">等待</option>
						<option value="FAILED" selected="selected">失败</option>
						<option value="SUCCESSFUL">成功</option>
						<option value="END">结束</option>
						<option value="ABORTED">ABORTED</option>
					</select>
					</c:when>
					<c:when test="${domainOnePrize.status=='SUCCESSFUL' }">
					<select class="combox" class="required" name="domainOnePrize.status">
						<option value="ALL">所有状态</option>
						<option value="PENDING">未验证</option>
						<option value="WAIT">等待</option>
						<option value="FAILED">失败</option>
						<option value="SUCCESSFUL" selected="selected">成功</option>
						<option value="END">结束</option>
						<option value="ABORTED">ABORTED</option>
					</select>
					</c:when>
					<c:when test="${domainOnePrize.status=='END' }">
					<select class="combox" class="required" name="domainOnePrize.status">
						<option value="ALL">所有状态</option>
						<option value="PENDING">未验证</option>
						<option value="WAIT">等待</option>
						<option value="FAILED">失败</option>
						<option value="SUCCESSFUL">成功</option>
						<option value="END" selected="selected">结束</option>
						<option value="ABORTED">ABORTED</option>
					</select>
					</c:when>
					<c:when test="${domainOnePrize.status=='ABORTED' }">
					<select class="combox" class="required" name="domainOnePrize.status">
						<option value="ALL">所有状态</option>
						<option value="PENDING">未验证</option>
						<option value="WAIT">等待</option>
						<option value="FAILED">失败</option>
						<option value="SUCCESSFUL">成功</option>
						<option value="END">结束</option>
						<option value="ABORTED" selected="selected">ABORTED</option>
					</select>
					</c:when>
					<c:otherwise>
					<select class="combox" class="required" name="domainOnePrize.status">
						<option value="ALL">所有状态</option>
						<option value="PENDING">未验证</option>
						<option value="WAIT">等待</option>
						<option value="FAILED">失败</option>
						<option value="SUCCESSFUL">成功</option>
						<option value="END">结束</option>
						<option value="ABORTED">ABORTED</option>
					</select>
					</c:otherwise>
				</c:choose>
				</td>
				
			</tr>
			<tr>
			    <td>
			    	<label>域名持有人用户名：</label>
			        <input type="text" name="domainAuction.zunmiUser.userName" value="${domainAuction.domainName }"/>
			    </td>
			    <td>
					<label>价钱区间：</label>
					<input type="text" name="onePriceLittle"  value="${onePriceLittle }"/>到
					<input type="text" name="onepriceGreate"  value="${onepriceGreate }"/>之间
				</td>
			    <td>&nbsp;</td>
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
			<!-- 以前的
			<li><a class="add" href="${ctx}/manager/domain-sale!input.action" target="dialog" rel="admin_input_dialog" height="550" width="790" title="域名列表"><span>去域名列表添加</span></a></li>
			 -->
			<li><a class="add" href="${ctx}/manager/domain.action" target="navTab" rel="domain_list" title="域名列表"><span>去域名列表添加</span></a></li>
			<li><a class="delete" href="${ctx}/manager/domain-sale!del.action?domainOnePrizeId={sid_domainOnePrizeId}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
			<li><a class="edit" href="${ctx}/manager/domain-sale!getDomainOPDetail.action?domainOnePrizeId={sid_domainOnePrizeId}" target="dialog" height="450" width="740"><span>修改</span></a></li>
			<li class="line">line</li>
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
				<th width="130">加入时间</th>
				<th width="80">域名特征</th>
				<th width="100">买卖方式</th>
				<th width="80" align="center">是否推荐 </th>
				<th width="80">买卖状态</th>
				<th width="60">一口价价钱</th>
				<th width="90">截止时间</th>     
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${list}" varStatus="index">
				<tr target="sid_domainOnePrizeId" rel="${item.id}">
				<td>${index.count}</td>
				<td style="display:none">${item.id}</td>
				<td>${item.domainName}</td>
				<td>${item.zunmiUser.email}</td>
				<td>${item.addDate}</td>
				<td>${item.domainItems }</td>
				<td>一口价</td>
				<td>
				<c:if test="${item.isRecommend==true }">推荐</c:if>
				<c:if test="${item.isRecommend==false }">不推荐</c:if>
				</td>
				<td>${item.status }</td>
				<td>${item.prize }</td>
				<td id="time_${item.id }">${item.endDate}</td>
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
