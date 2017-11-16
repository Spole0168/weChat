<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Insert title here</title>
		<script type="text/javascript" >
			function checkform(){
				var obj = document.getElementById('name'); 
				if (obj.value==""){
					alert("用户名不能为空");
					obj.focus();
					return false;
				}
				obj = document.getElementById('sex'); 
				if (obj.value==""){
					alert("用户性别不能为空");
					obj.focus();
					return false;
				}
				obj = document.getElementById('age'); 
				if (obj.value==""){
					alert("用户年龄不能为空");
					obj.focus();
					return false;
				}
// 				var obj = document.getElementById('tempData'); 	//div对象
// 				obj.style.display="none";					//显示 myForm
				return true;
			}
		</script>
		
	</head>
	<body>
		<center>
			<form name="updForm" action="<%=path%>/student/updateStudent.do" method="post" >
				<table align="center">
					<tr>
						<td width="60">
							<label>id</label>
						</td>
						<td width="60">
							<input type="text" id="id" name="id" value="${stu.id}" readonly="readonly" width="50"/>
						</td>
					</tr>
					<tr>
						<td width="60">
							<label>name</label>
						</td>
						<td width="60">
							<input type="text" id="name" name="name" value="${stu.name}" width="50"/>
						</td>
					</tr>
					<tr>
						<td width="60">
							<label>sex</label>
						</td>
						<td width="60">
							<input type="text" id="sex" name="sex" value="${stu.sex}"  width="50"/>
						</td>
					</tr>
					<tr>
						<td width="60">
							<label>age</label>
						</td>
						<td width="60">
							<input type="text" id="age" name="age" value="${stu.age}"  width="50"/>
						</td>
					</tr>
					<tr>
						<td width="60">
							<input type="submit" value="确定" onClick="return(checkform())">
						</td>
						<td width="60">
							<input type="reset" value="重置" onclick="resetValue();">
						</td>
					</tr>
				</table>
			</form>
		</center>
	</body>
</html>