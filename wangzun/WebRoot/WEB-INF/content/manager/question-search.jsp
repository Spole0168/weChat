<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>


<div class="pageHeader">
	<h1 align="center">反馈问题的高级检索</h1>
</div>
<div class="pageContent">
	<form id="pagerForm"
		action="${ctx}/manager/question!gaoJiSearch.action" method="post"
		class="pageForm required-validate"
		onsubmit="return navTabSearch(this)">
		<input type="hidden" name="pageNum" value="${pageNum}" /> 
		<input type="hidden" name="numPerPage" id="numPerPageHd" value="${numPerPage}" />
		<div class="searchBar" layoutH="56" align="center">
			<table class="searchContent">
				<tr>
					<td><label>标题：</label><input type="text"
						name="question.question" value="${question.question }" /> <input
						type="hidden" name="gaoji" value="gaoji" /></td>
				</tr>
				<tr>
					<td><label>问题状态：</label> <select class="combox"
						name="question.status">
							<option value="ALL">所有状态</option>
							<option value="RESOLVED">已解决</option>
							<option value="UNRESOLVED">未解决</option>
					</select></td>
				</tr>
				<tr>
					<td><label>反馈日期：</label><input type="text" name="littleTime"
						class="date" readonly="true" value="${ littleTime}"/> &nbsp;&nbsp;到&nbsp;&nbsp;<input
						type="text" name="greatTime" class="date" readonly="true" value="${greatTime }" /> 之间</td>
				</tr>

				<tr>
					<td><label>提问者姓名：</label><input type="text"
						name="question.zunmiUser.userName" /></td>
				</tr>
				<tr>
					<td><label>问题类型：</label> <select class="combox"
						name="question.questionType">
							<option value="ALL">所有状态</option>
							<option value="DOMAIN_REGISTRATION">域名注册相关</option>
							<option value="DOMAIN_MANAGEMENT">域名管理相关</option>
							<option value="FINANCE">财&nbsp;务&nbsp;相&nbsp;关</option>
							<option value="OTHER">其&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;它</option>
					</select></td>
				</tr>
				<tr>
					<td><label>反馈日期：</label><input type="text"
						name="replyLittleTime" class="date" readonly="true" />
						&nbsp;&nbsp;到&nbsp;&nbsp;<input type="text" name="replyGreatTime"
						class="date" readonly="true" /> 之间</td>
				</tr>
				<tr>
					<td><label>回复管理员姓名：</label><input type="text"
						name="question.zunmiAdmin.userName" /></td>
				</tr>
			</table>
		</div>
			<div class="formBar">
				<ul>
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">检索</button>
							</div>
						</div></li>
					<li>
						<div class="button">
							<div class="buttonContent">
								<button type="button" class="close">取消</button>
							</div>
						</div>
					</li>
				</ul>
			</div>
		
	</form>
</div>


