<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<div class="pageContent">
	<form  method="post" action="${ctx}/manager/admin!save.action" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>用户名：</label>
				<input type="hidden" name="admin.id" value="${admin.id}"/>
				<input id="userName" name="admin.userName" value="${admin.userName}" class="required alphanumeric" size="30" value="" alt="请输入用户名"/>
			</p>
			<p>
				<label>显示名：</label>
				<input id="displayName" name="admin.displayName" value="${admin.displayName}" class="required" size="30" alt="请输入显示名">
			</p>
			<p>
				<label>密码：</label>
				<input id="password" name="admin.password" value="" type="password" class="required" size="30" minlength="6" maxlength="20" alt="请输入密码">
			</p>
			<p>
				<label>重复密码：</label>
				<input id="password2" name="password2" value="" type="password" class="required" size="30" minlength="6" maxlength="20" equalTo="#password" alt="请再次输入密码">
			</p>
			<p>
				<label>邮   箱：</label>
				<input id="email" name="admin.email" value="${admin.email}"  class="required email" size="30" alt="请输入邮箱"/>
			</p>
			<p>
				<label>安全问题：</label>
				<input id="email" name="admin.question" value="${admin.question}" minlength="2" maxleng="20" size="30" alt="请输入安全问题"/>
			</p>
			<p>
				<label>答   案：</label>
				<input id="email" name="admin.answer" value="${admin.email}" minlength="2" maxleng="20" size="30" alt="请输入答案"/>
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








