<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<div class="pageContent">
	<form  method="post" action="${ctx}/outeract/repairlogouter!save.action" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
		  <table  style="padding-left: 20px">
		  		<tr>
		              <td><label>饮水机id：</label></td>
		               <td><input id="machine_id" name="machine_id" value="${machine_id}" class="required alphanumeric" size="50" value="" alt="请输入饮水机id"/></td>
		  		</tr>
		  		<tr>
		              <td><label>维修人员id：</label></td>
		               <td><input id="fuwuyuan_id" name="fuwuyuan_id" value="${fuwuyuan_id}" class="required" size="50" alt="请输入维修id"></td>
		  		</tr>
		  		<tr>
		              <td><label>tds_in：</label></td>
		               <td><input id="tds_in" name="tds_in" value="${tds_in}"  size="50" alt="请输入tds_in"/></td>
		  		</tr>
		  			<tr>
		              <td><label>tds_out：</label></td>
		               <td><input id="tds_out" name="tds_out" value="${tds_out}"  size="50" alt="请输入tds_out"/></td>
		  		</tr>
		  		<tr>
		              <td><label>水垢：</label></td>
		               <td><input id="scale" name="scale" value="${scale}" size="50“  minlength="1" maxlength="10" alt="水垢"></td>
		  		</tr>
		  		<tr>
		              <td><label>口感：</label></td>
		               <td><input id="texture" name="texture" value="${texture} "  class="required" size="50"  maxlength="14"  alt="请输入口感"></td>
		  		</tr>
		  			<tr>
		              <td><label>异味：</label></td>
		               <td><input id="smell" name="smell" value="${smell}"   size="50"   maxlength="10"  alt="请输入异味"/></td>
		  		</tr>
		  		<tr>
		              <td><label>维修内容：</label></td>
		               <td><input type="checkbox" name="whats" value="更换水泵"> 更换水泵
                               <input type="checkbox" name="whats" value="更换电源"> 更换电源
                               <input type="checkbox" name="whats" value="更换pp棉">更换pp棉
                              <input type="checkbox" name="whats" value="更换活性炭">更换活性炭
                               <input type="checkbox" name="whats" value="更换水管">更换水管
                                <input type="checkbox" name="whats" value="更换接头">更换接头
		  		</tr>
		  		
		  			<tr>
		              <td><label>地址：</label></td>
		               <td><label>outeract/repairlogouter!save.action</label></td>
		  		</tr>
		  			<tr>
		              <td colspan="2" align="center"><button type="submit">保存</button></td>
		  		</tr>
		  </table>
		</div>
	</form>
</div>





