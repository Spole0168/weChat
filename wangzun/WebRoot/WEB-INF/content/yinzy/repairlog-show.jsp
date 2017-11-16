<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<div class="pageContent">
	<form  method="post" action="${ctx}/manager/serviceApply!save.action" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
		<table>
		   <tr>
		        <td><label>饮水机ID：</label></td>
		          <td><label>${rlog.text4}</label></td>
		   </tr>
		   <tr>
		        <td><label>维修时间：</label></td>
		          <td><label>${rlog.repairtime}</label></td>
		   </tr>
		   <tr>
		        <td><label>维修人员工号：</label></td>
		          <td><label>${rlog.serviceuserid}</label></td>
		   </tr>
		      <tr>
		        <td><label>口感：</label></td>
		          <td><label>${rlog.text1} </label></td>
		   </tr>
		    <tr>
		        <td><label>水垢：</label></td>
		          <td><label>${rlog.scaleflag} </label></td>
		   </tr>
		    <tr>
		        <td><label>异味：</label></td>
		          <td><label>${rlog.text2} </label></td>
		   </tr>
		    <tr>
		        <td><label>输入TDS：</label></td>
		          <td><label>${rlog.watertds} </label></td>
		   </tr>
		    <tr>
		        <td><label>输出TDS：</label></td>
		          <td><label>${rlog.outputtds} </label></td>
		   </tr>
		    <tr>
		        <td><label>维修内容：</label></td>
		          <td><label>${rlog.servicepoint}</label></td>
		   </tr>
		</table>
			<p> </p>
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








