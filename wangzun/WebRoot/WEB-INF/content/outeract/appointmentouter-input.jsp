<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="pageContent">
	<form  method="post" action="${ctx}/outeract/appointmentouter!yuyue.action" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
		  <table  style="padding-left: 20px">
		  			<tr>
		              <td><label>饮水机Id：</label></td>
		               <td><input id="machine_id" name="machine_id" value="${machine_id}" class="required alphanumeric" size="50" value="" alt="请输入饮水机ID"/></td>
		  		</tr>
		  		<tr>
		              <td><label>姓名：</label></td>
		               <td><input id="name" name="name" value="${name}" class="required" size="50" alt="请输入姓名"></td>
		  		</tr>
		  		<tr>
		              <td><label>电话：</label></td>
		               <td><input id="tel" name="tel" value="${tel}" class="required" size="50" alt="请输入电话"></td>
		  		</tr>
		  		<tr>
		              <td><label>预约时间：</label></td>
		               <td><input id="appointmentdate" name="appointmentdate"  class="date" value="${appointmentdate}" size="50“  minlength="1" maxlength="110" alt="请输入地址"></td>
		  		</tr>
	 			<tr>
		              <td colspan="2" align="center"><button type="submit">保存</button></td>
		  		</tr>
		  </table>
		</div>
	</form>
</div>





