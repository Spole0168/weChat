<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>

<div class="panelBar">
	<ul class="toolBar">
		<li><a class="add" href="${ctx}/manager/node!input.action" target="dialog" rel="node_input_dialog" height="600" width="800" title="栏目添加"><span>添加栏目</span></a></li>
		<li><a class="delete" href="${ctx}/manager/node!del.action?nodeId={sid_node}" target="ajaxTodo" title="确定要删除吗?"><span>删除栏目</span></a></li>
		<li><a class="edit" href="${ctx}/manager/node!input.action?nodeId={sid_node}" target="dialog" height="600" width="800"><span>编辑</span></a></li>		
	</ul>
</div>
<div style=" float:left; display:block; margin:10px; overflow:auto; width:98%; height:450px; border:solid 1px #CCC; line-height:21px; background:#FFF;">
<ul class="tree treeFolder expand">
<c:out value="${channelCreationList}" escapeXml="false"></c:out>
</ul>
</div>


