<%@ page language="java" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>Student 列表</title>
		<script type="text/javascript" language="javascript">
			function addStu(){
				document.getElementById('tempData').style.display="block";//显示 myForm
				document.getElementById('myForm').action = "insert.do";
			}
			function checkform(){
				var obj = document.myForm.elements["name"]; 
				if (obj.value==""){
					alert("用户名不能为空");
					obj.focus();
					return false;
				}
				obj = document.myForm.elements["sex"]; 
				if (obj.value==""){
					alert("用户性别不能为空");
					obj.focus();
					return false;
				}
				obj = document.myForm.elements["age"];
				if (obj.value==""){
					alert("用户年龄不能为空");
					obj.focus();
					return false;
				}
				var obj = document.getElementById('tempData'); 	//div对象
				obj.style.display="none";					//显示 myForm
				return true;
			}
		</script>
	</head>
	<body >
		<form name="searchForm" action="listStudent.do" method="post" >
					<table align="center">
						<tr>
							<td width="60">
								<label>name</label>
							</td>
							<td width="60">
								<input type="text" id="name" name="name"  width="50"/>
							</td>
							<td width="60">
								<label>sex</label>
							</td>
							<td width="60">
								<input type="text" id="sex" name="sex"   width="50"/>
							</td>
							<td width="60">
								<label>age</label>
							</td>
							<td width="60">
								<input type="text" id="age" name="age"   width="50"/>
							</td>
						</tr>
						<tr>
							<td width="180" colspan="3">
								<input type="submit" value="查询">
							</td>
							<td width="180" colspan="3">
								<input type="reset" value="重置" >
							</td>
						</tr>
					</table>
				</form>
		<table width="50%" border="1">
			<tr>
				<td>
					id
				</td>
				<td>
					姓名
				</td>
				<td>
					性别
				</td>
				<td>
					年龄
				</td>
				<td>
					操作
				</td>
			</tr>
			
			<c:forEach items="${stuList}" var="stu">
				<tr>
					<td>
						${stu.id}
					</td>
					<td>
						${stu.name}
					</td>
					<td>
						${stu.sex}
					</td>
					<td>
						${stu.age}
					</td>
					<td>
					<%-- <a onclick="javascript:update('${stu}')" href="javascript:void(0)"> 娶不到对象值--%>
						<a  href="pre4Update/${stu.id}.do">修改</a>||<a href="delete/${stu.id}.do"> 删除</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		<a href="javascript:void(0)" onclick="javascript:addStu()">添加</a>
		
		<center>
			<div id="tempData"  style="display: none;width: 230px;height: 300px;border: 1px black;background-color: yellow;">
				<form name="myForm" action="insert.do" method="post" >
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
			</div>
		</center>
	</body>
</html>