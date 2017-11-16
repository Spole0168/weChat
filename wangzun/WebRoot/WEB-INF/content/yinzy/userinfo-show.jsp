<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>

<div class="pageContent">
	<form  method="post" action="${ctx}/outeract/zhuanjijh!jihuo.action" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
		  <table  style="padding-left: 20px">
		  		<tr>
		              <td><label>饮水机id：</label></td>
		                 <td>${userInfo.machineid} </td>
		  		</tr>
		  		<tr>
		              <td><label>装机人员id：</label></td>
		                <td>${userInfo.text1} </td>
		  		</tr>
		  		<tr>
		              <td><label>名称：</label></td>
		               <td>${userInfo.contact_name} </td>
		  		</tr>
		  		<tr>
		              <td><label>电话：</label></td>
		              <td>${userInfo.telphone} </td>
		  		</tr>
		  			<tr>
		              <td><label>身份证号：</label></td>
		                 <td>${userInfo.cardno} </td>
		  		</tr>
		  			<tr>
		              <td><label>地址：</label></td>
		                <td>${userInfo.instak_address} </td>
		  		</tr>
		  			<tr>
		              <td><label>tds_in：</label></td>
		               <td>${userInfo.inputtds} </td>
		  		</tr>
		  			<tr>
		              <td><label>tds_out：</label></td>
		                 <td>${userInfo.outputtds} </td>
		  		</tr>
		  			<tr>
		              <td><label>付款金额：</label></td>
		               <td>${userInfo.text2} </td>
		  		</tr>
		  			<tr>
		              <td><label>付款方式：</label></td>
		                <td>${userInfo.text3} </td>
		  		</tr>
		  </table>
		</div>
			<div class="formBar">
			<ul>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>





