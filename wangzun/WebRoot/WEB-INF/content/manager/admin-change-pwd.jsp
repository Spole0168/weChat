<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<div class="pageContent">
	<form  method="post" action="${ctx}/manager/admin-change-pwd!changePassword.action" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>原密码：</label>
				<input id="oldPassword" name="oldPassword" value="" type="password" class="required" size="30"/>
			</p>
			<p>
				<label>新密码：</label>
				<input id="newPassword1" name="newPassword1" value="" type="password" class="required" size="30"/>
			</p>
			<p>
				<label>重复新密码：</label>
				<input id="newPassword2" name="newPassword2" value="" type="password" class="required" size="30"/>
			</p>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>








