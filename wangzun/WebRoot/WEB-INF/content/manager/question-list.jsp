<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>


<script type="text/javascript"> 
	//展示和隐藏高级查询的div
    function toggleDebug(searchGaoji) {
       var debugDiv = document.getElementById(searchGaoji);
       var buttonGaoji = document.getElementById('buttonGaoji');
            var display = debugDiv.style.display;
            if (display == 'none') {
                debugDiv.style.display = 'block';
                buttonGaoji.innerHTML="<span>隐藏取消</span>";
            } else if (display == 'block') {
                debugDiv.style.display = 'none';
                buttonGaoji.innerHTML="<span>高级查询</span>";
           }
 }
 
 //检索之后的高级查询展示与隐藏的
 function blockOrNone(){
 	var dDiv = document.getElementById('searchGaoji');
    var buttonGaoji = document.getElementById('buttonGaoji');
    var dDivDisplay=dDiv.style.display;
 	alert(dDivDisplay);
 	$.ajax({
   			type: "POST",
   			url: "${ctx}/manager/question!ajaxDisplay.action",
   			data: "dDivStyle="+dDivDisplay,
   			success: function(data){
   			 alert(data);
   			 alert(this.valueOf());
    	//	 alert( "Data Saved: " + msg );
    	//	 dDivDisplay=msg;
  			 }
		});
 }
</script>

<div class="pageHeader">
	<form id="pagerForm" onsubmit="return navTabSearch(this)" action="${ctx}/manager/question!adminQuestionSearch.action" method="post">
	 <input type="hidden" name="pageNum" value="${pageNum}" />
	 <input type="hidden" name="numPerPage" id="numPerPageHd" value="${numPerPage}" />
	 <div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					<label>标题：</label><input type="text" name="question.question" value="${question.question}"/>
				</td>
				<td>
				<label>问题状态：</label>
					<c:choose>
					   <c:when test="${question.status.toString()=='RESOLVED'}">
					   	<select class="combox" name="question.status">
						<option value="" >所有状态</option>
						<option value="RESOLVED" selected="selected">已解决</option>
						<option value="UNRESOLVED" >未解决</option>
						</select>
					   </c:when>
					   <c:when test="${question.status.toString()=='UNRESOLVED'}">
				  	    <select class="combox" name="question.status">
						<option value="" >所有状态</option>
						<option value="RESOLVED" >已解决</option>
						<option value="UNRESOLVED" selected="selected">未解决</option>
						</select>
					   </c:when>
					   <c:otherwise>
					    <select class="combox" name="question.status">
						<option value="" selected="selected">所有状态</option>
						<option value="RESOLVED" >已解决</option>
						<option value="UNRESOLVED" >未解决</option>
						</select>
					   </c:otherwise>
					</c:choose>
				</td>
				<td>
					<label>反馈日期：</label><input type="text" name="littleTime" class="date" readonly="true" format="yyyy-MM-dd HH:mm:ss" value="${littleTime}"/>
						&nbsp;&nbsp;到&nbsp;&nbsp;<input type="text" name="greatTime" class="date" readonly="true" format="yyyy-MM-dd HH:mm:ss" value="${greatTime}"/>
						之间
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
		 	<li><a class="delete" href="${ctx}/manager/question!delQuestion.action?questionId={sid_question}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
			<li><a class="edit" href="${ctx}/manager/question!getReplyQuestion.action?questionId={sid_question}" rel="question_input_dialog" target="dialog" height="450" width="600"><span>回复</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="demo/common/dwz-team.xls" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table id="questionList" class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="30"></th>
				<th style="display:none"></th>
				<th width="120">反馈问题</th>
				<th width="120">反馈问题的用户</th>
				<th width="150">提问类别</th>
				<th width="150">提问时间</th>
				<th width="150">回复时间</th>
				<th width="80" align="center">操作员</th>
				<th width="80">状态</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${list}" varStatus="index">
				<tr target="sid_question" rel="${item.id}">
				<td>${index.count}</td>
				<td style="display:none">${item.id}</td>
				<td>${item.question}</td>
				<td>${item.zunmiUser.userName }</td>
				<td>
					<c:choose>
						<c:when test="${item.questionType.toString()=='DOMAIN_REGISTRATION'}">域名注册</c:when>
						<c:when test="${item.questionType.toString()=='DOMAIN_MANAGEMENT'}">域名管理</c:when>
						<c:when test="${item.questionType.toString()=='FINANCE'}">财务相关</c:when>
						<c:when test="${item.questionType.toString()=='OTHER'}">其他</c:when>
						<c:otherwise>未知类型</c:otherwise>
					</c:choose>
				</td>
				<td>${item.questionTime }</td>
				<td>${item.answerTime }</td>
				<td>${item.zunmiAdmin.userName }</td>
		        <td>
		        	<c:if test="${item.status.toString()=='RESOLVED' }">已解决</c:if>
		        	<c:if test="${item.status.toString()=='UNRESOLVED' }">待解决</c:if>
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

