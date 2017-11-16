<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<h2 class="contentTitle">点击节点生成内容页</h2>
<div style=" float:left; display:block; margin:10px; overflow:auto; width:98%; height:450px; border:solid 1px #CCC; line-height:21px; background:#FFF;">
<ul class="tree treeFolder expand">
<c:out value="${contentCreationList}" escapeXml="false"></c:out>
</ul>
</div>


