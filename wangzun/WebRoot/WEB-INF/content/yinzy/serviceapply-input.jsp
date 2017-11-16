<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<div class="pageContent">
	<form  method="post" action="${ctx}/manager/serviceApply!save.action" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>申请社区名称：</label>
				<input type="hidden" name="serviceApply.id" value="${serviceApply.id}"/>
				<input id="communityName" name="serviceApply.communityName" value="${serviceApply.communityName}" class="required alphanumeric" size="50" value="" alt="请输入申请社区名称"/>
			</p>
			<p><label>地址：</label>
				<input id="communityAddress" name="serviceApply.communityAddress" value="${serviceApply.communityAddress}" class="required" size="50" alt="请输入地址">
			</p>
			<p><label>社区业主数量：</label>
				<input id="communitPersonNum" name="serviceApply.communitPersonNum" value="${serviceApply.communitPersonNum}"" class="number" size="11" minlength="1" maxlength="11" alt="请输入业主数量">
			</p>
			<p><label>入住率%：</label>
				<input id="communitRate" name="serviceApply.communitRate" value="${serviceApply.communitRate} }"  class="required" size="50"  maxlength="2"  alt="请输入入住率">
			</p>
			<p><label>居民直饮水主要来源：</label>
				<input id="waterSource" name="serviceApply.waterSource" value="${serviceApply.waterSource}"   size="50" alt="请输入居民直饮水主要来源"/>
			</p>
			<p><label>社区直饮水机数量：</label>
				<input id="machineNum" name="serviceApply.machineNum" value="${serviceApply.machineNum}"  class="number" size="50" minlength="1" maxlength="11"  alt="请输入社区直饮水机数量"/>
			</p>
			<p><label>直饮水机的价格（元）：</label>
				<input id="mechinePrice" name="serviceApply.mechinePrice" value="${serviceApply.mechinePrice}"  size="30" alt="请输入直饮水机的价格"/>
			</p>
			<p><label>本社区水质情况：</label>
				<input id="waterQuality" name="serviceApply.waterQuality" value="${serviceApply.waterQuality}"  size="30" alt="请输入本社区水质情况"/>
			</p>
			<p><label>TDS：</label>
				<input id="waterTDS" name="serviceApply.waterTDS" value="${serviceApply.waterTDS}"  size="30" alt="请输入TDS"/>
			</p>
			<p><label>其他：</label>
				<input id="other" name="serviceApply.other" value="${serviceApply.other}"  size="50" alt="请输入其他"/>
			</p>
			<p><label>申请单位资质：</label>
				<input id="applyBusineeslic" name="serviceApply.applyBusineeslic" value="${serviceApply.applyBusineeslic}"  size="30" alt="请输入申请者有无营业执照"/>
			</p>
			<p><label>申请者单位姓名：</label>
				<input id="applyOrgName" name="serviceApply.applyOrgName" value="${serviceApply.applyOrgName}"  size="30" alt="请输入申请者单位姓名"/>
			</p>
			<p><label>主要经营项目：</label>
				<input id="applyProject" name="serviceApply.applyProject" value="${serviceApply.applyProject}"  size="50" alt="请输入申请者主要经营项目"/>
			</p>
			<p><label>申请者主要客户资源：</label>
				<input id="applyContactsource" name="serviceApply.applyContactsource" value="${serviceApply.applyContactsource}"  size="50" alt="请输入申请者主要客户资源"/>
			</p>
			<p><label>申请者员工人数：</label>
				<input id="applyStaffnum" name="serviceApply.applyStaffnum" value="${serviceApply.applyStaffnum}"  class="number" size="50" minlength="1" maxlength="11"  alt="请输入申请者员工人数"/>
			</p>
			<p><label>经过培训是否可以完成安装服务：</label>
				<input id="isIstallOK" name="serviceApply.isIstallOK" value="${serviceApply.isIstallOK}"  size="50" alt="请输入经过培训是否可以完成安装服务"/>
			</p>
			<p><label>申请人单位联系人：</label>
				<input id="applyOrgPerson" name="serviceApply.applyOrgPerson" value="${serviceApply.applyOrgPerson}"  size="50" alt="请输入申请人单位联系人"/>
			</p>
			<p><label>联系人电话：</label>
				<input id="applyTelphone" name="serviceApply.applyTelphone" value="${serviceApply.applyTelphone}"  size="50" alt="请输入联系人电话"/>
			</p>
			<p><label>邮箱：</label>
				<input id="applyEmail" name="serviceApply.applyEmail" value="${serviceApply.applyEmail}"  size="50" alt="请输入邮箱"/>
			</p>
			<p><label>申请时间：</label>
				<label>${serviceApply.applyDate}</label>
			</p>
			<p>	<label>申请人名称：</label>
				<input id="applyeName" name="serviceApply.applyeName" value="${serviceApply.applyeName}"  size="30" alt="请输入申请人名称"/>
			</p>
			<p> </p>
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








