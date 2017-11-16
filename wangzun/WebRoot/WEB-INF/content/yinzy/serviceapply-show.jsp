<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<div class="pageContent">
	<form  method="post" action="${ctx}/manager/serviceApply!save.action" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
		<table>
		   <tr>
		        <td><label>申请社区名称：</label></td>
		          <td><label>${serviceApply.communityName}</label></td>
		   </tr>
		   <tr>
		        <td><label>地址：</label></td>
		          <td><label>${serviceApply.communityAddress}</label></td>
		   </tr>
		   <tr>
		        <td><label>社区业主数量：</label></td>
		          <td><label>${serviceApply.communitPersonNum}</label></td>
		   </tr>
		      <tr>
		        <td><label>入住率%：</label></td>
		          <td><label>${serviceApply.communitRate} </label></td>
		   </tr>
		    <tr>
		        <td><label>居民直饮水主要来源：</label></td>
		          <td><label>${serviceApply.waterSource} </label></td>
		   </tr>
		    <tr>
		        <td><label>社区直饮水机数量：</label></td>
		          <td><label>${serviceApply.machineNum} </label></td>
		   </tr>
		    <tr>
		        <td><label>直饮水机的价格（元）：</label></td>
		          <td><label>${serviceApply.mechinePrice} </label></td>
		   </tr>
		    <tr>
		        <td><label>本社区水质情况：</label></td>
		          <td><label>${serviceApply.waterQuality} </label></td>
		   </tr>
		    <tr>
		        <td><label>TDS：</label></td>
		          <td><label>${serviceApply.waterTDS}</label></td>
		   </tr>
		     <tr>
		        <td><label>其他：</label></td>
		          <td><label>${serviceApply.other}</label></td>
		   </tr>
		     <tr>
		        <td><label>申请者单位资质及名称：</label></td>
		          <td><label>${serviceApply.applyBusineeslic}</label></td>
		   </tr>
		   <!--  
		    <tr>
		        <td><label>申请者单位姓名：</label></td>
		          <td><label>${serviceApply.applyOrgName}</label></td>
		   </tr>
		   -->
		    <tr>
		        <td><label>主要经营项目：</label></td>
		          <td><label>${serviceApply.applyProject}</label></td>
		   </tr>
		     <tr>
		        <td><label>主要客户资源：</label></td>
		          <td><label>${serviceApply.applyContactsource}</label></td>
		   </tr>
		    <tr>
		        <td><label>申请者员工人数：</label></td>
		          <td><label>${serviceApply.applyStaffnum}</label></td>
		   </tr>
		      <tr>
		        <td><label>经过培训是否可以完成安装服务：</label></td>
		          <td><label>${serviceApply.isIstallOK}</label></td>
		   </tr>
		       <tr>
		        <td><label>申请人单位联系人：</label></td>
		          <td><label>${serviceApply.applyOrgPerson}</label></td>
		   </tr>
		    <tr>
		        <td><label>联系人电话：</label></td>
		          <td><label>${serviceApply.applyTelphone}</label></td>
		   </tr>
		   <tr>
		        <td><label>邮箱：</label></td>
		          <td><label>${serviceApply.applyEmail}</label></td>
		   </tr>
		   <tr>
		        <td><label>申请时间：</label></td>
		          <td><label>${serviceApply.applyDate}</label></td>
		   </tr>
		    <tr>
		        <td><label>申请人名称：</label></td>
		          <td><label>${serviceApply.applyeName}</label></td>
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








