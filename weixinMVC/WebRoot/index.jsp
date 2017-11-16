<%@ page language="java" import="java.util.*" pageEncoding="utf8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	
	<c:set var="stx" value="${pageContext.request.contextPath }"></c:set>
  <head>
   
  </head>
  
  <body>
    模拟微信接入GET请求:
    <form action="${stx }/xxt/api" method = "get">
    	signature:<input type="text" name="signature"><br>
    	timestamp:<input type="text" name="timestamp"><br>
    	nonce:<input type="text" name="nonce"><br>
    	echostr:<input type="text" name="echostr"><br>
    	<input type="submit" value="提交">
    </form>
  </body>
</html>
