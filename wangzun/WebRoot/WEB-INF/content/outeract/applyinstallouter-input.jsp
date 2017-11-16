<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>


<div class="pageContent">
	<form  method="post" action="${ctx}/outeract/applyinstallouter!install.action" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
		  <table  style="padding-left: 20px">
		  		<tr>
		              <td><label>姓名：</label></td>
		               <td><input id="name" name="name" value="${name}" class="required alphanumeric" size="50" value="" alt="请输入申请者姓名"/></td>
		  		</tr>
		  		<tr>
		              <td><label>电话：</label></td>
		               <td><input id="tel" name="tel" value="${tel}" class="required" size="50" alt="请输入电话"></td>
		  		</tr>
		  		<tr>
		              <td><label>地址：</label></td>
		               <td><input id="address" name="address" value="${address}" size="50“  minlength="1" maxlength="110" alt="请输入地址"></td>
		  		</tr>
		  		<tr>
		              <td colspan="2" align="center"><button type="submit">保存</button></td>
		  		</tr>
		  </table>
		</div>
	</form>
</div>





