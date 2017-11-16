<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

</div>

<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<div class="pageContent">
	<form  method="post" action="${ctx}/yinzy/userinfo!save.action" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
		  <table  style="padding-left: 20px">
		  		<tr>
		              <td><label>饮水机id：</label></td>
		              <input type="hidden" name="userInfo.id" value="${userInfo.id}"/>
		               <td><input id="userInfo.machineid" name="userInfo.machineid" value="${userInfo.machineid}" class="required alphanumeric" size="50" value="" alt="请输入饮水机id"/></td>
		  		</tr>
		  		<tr>
		              <td><label>装机人员id：</label></td>
		               <td><input id="userInfo.text1" name="userInfo.text1" value="${userInfo.text1}" class="required" size="50" alt="请输入装机人员id"></td>
		  		</tr>
		  		<tr>
		              <td><label>名称：</label></td>
		               <td><input id="userInfo.contact_name" name="userInfo.contact_name" value="${userInfo.contact_name}" size="50“  minlength="1" maxlength="110" alt="请输入业主名称"></td>
		  		</tr>
		  		<tr>
		              <td><label>电话：</label></td>
		               <td><input id="userInfo.telphone" name="userInfo.telphone" value="${userInfo.telphone} "  class="required" size="50"  maxlength="14"  alt="请输入电话"></td>
		  		</tr>
		  			<tr>
		              <td><label>身份证号：</label></td>
		               <td><input id="userInfo.cardno" name="userInfo.cardno" value="${userInfo.cardno}"   size="50" alt="请输入居民身份证号"/></td>
		  		</tr>
		  			<tr>
		              <td><label>地址：</label></td>
		               <td><input id="userInfo.instak_address" name="userInfo.instak_address" value="${userInfo.instak_address}"  size="50" minlength="1" maxlength="110"  alt="请输入地址"/></td>
		  		</tr>
		  			<tr>
		              <td><label>tds_in：</label></td>
		               <td><input id="userInfo.inputtds" name="userInfo.inputtds" value="${userInfo.inputtds}"  size="50" alt="请输入tds_in"/></td>
		  		</tr>
		  			<tr>
		              <td><label>tds_out：</label></td>
		               <td><input id="userInfo.outputtds" name="userInfo.outputtds" value="${userInfo.outputtds}"  size="50" alt="请输入tds_out"/></td>
		  		</tr>
		  			<tr>
		              <td><label>付款金额：</label></td>
		               <td><input id="userInfo.text2" name="userInfo.text2" value="${userInfo.text2}"  size="50" alt="请输入pay"/></td>
		  		</tr>
		  			<tr>
		              <td><label>付款方式：</label></td>
		               <td><input id="userInfo.text3" name="userInfo.text3" value="${userInfo.text3 }"  size="50" alt="请输入pay_by"/></td>
		  		</tr>
		  </table>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>





