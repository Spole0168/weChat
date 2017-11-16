<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!-- 管理员添加和修改用户信息的界面 -->
<div class="pageContent sortDrag" selector="h1" layoutH="42">
	<form  method="post" action="${ctx}/manager/user-manager!addUser.action" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div  class="pageFormContent" layoutH="100">
		<h1>用户基本资料</h1>
				<p>
					<label>用户名：</label>
					<input type="hidden" name="user.id" value="${user.id}"/>
					<input id="userName" name="user.userName" value="${user.userName}" class="required alphanumeric" size="30" alt="请输入用户名"/>
				</p>
				<p>
					<label>用户邮箱：</label>
					<input name="user.email" class="required email" type="text" size="30" value="${user.email }" alt="请输入用户邮箱"/>
				</p>
		   		 <p>
					<label>密码：</label>
					<input id="password" name="user.password" value="" type="password" class="required" size="30" minlength="6" maxlength="20" alt="请输入密码">
				</p>
				<p>
					<label>重复密码：</label>
					<input id="password2" name="password2" value="" type="password" class="required" size="30" minlength="6" maxlength="20" equalto="#password" alt="请再次输入密码">
				</p>
				<p>
					<label>手机号码：</label>
					<input id="mobile" name="user.mobile" value="${user.mobile }" type="text" class="phone" size="30"  alt="请您的手机号码">
				</p>
				<p>
					<label>状态：</label>
					<select class="combox" name="user.status">
						<option value="NOMAL">正常</option>
						<option value="LOCKED">锁定</option>
						<option value="DISABLED">删除</option>
					</select>
				</p>
			<div class="divider"></div>		
				<h1>用户详细资料</h1>
						<p>
						<label>备用邮箱：</label>
						<input name="userDetail.alterEmail" class="email" type="text" size="30" value="${userDetail.alterEmail }" alt="请输入用户备用邮箱"/>
						</p>
						<p>
						<label>生日：</label>
						<input name="userDetail.birthday" class="date" type="text" size="30" value="${userDetail.birthday }" alt=""/>
						</p>
						<p>
						<label>用户类型：</label><!-- 这用单选框也不错 -->
						个人<input type="radio" name="userDetail.userType" value="INDIVIDUAL"/>
						企业	<input type="radio" name="userDetail.userType" value="ENTERPRISE"/> *
						</p>
						<p>
						<label>企业名称：</label>
						<input name="userDetail.enterpriseName" class="" type="text" size="30" value="${userDetail.enterpriseName }" alt=""/>
						</p>
						<p>
						<label>真实姓名：</label>
						<input name="userDetail.name" class="" type="text" size="30" value="${userDetail.name }" alt=""/>
						</p>
						<p>
						<label>证件类型：</label>
						<select name="userDetail.identityType" class="required">
							<option value="IDENTITY_CARD">身份证</option>
							<option value="PASSPORT">护照</option>
							<option value="BUSINESS_LICENSE"></option>
						</select>
						
						</p>
						
						<p>
						<label>证件号：</label>
						<input name="userDetail.identity" class="" type="text" size="30" value="${userDetail.country }" alt=""/>
						</p>
						
						<p>
						<label>电话号（座机）：</label>
						<input name="userDetail.telphone" class=""phone type="text" size="30" value="${userDetail.telphone }" alt=""/>
						</p>
						
						<p>
						<label>国家：</label>
						<input name="userDetail.country" class="" type="text" size="30" value="${userDetail.country }" alt=""/>
						</p>
						<p>
						<label>省份：</label>
						<input name="userDetail.province" class="" type="text" size="30" value="${userDetail.province }" alt=""/>
						</p>
						<p>
						<label>城市：</label>
						<input name="userDetail.city" class="" type="text" size="30" value="${userDetail.city }" alt=""/>
						</p>
						<p>
						<label>街道：</label>
						<input name="userDetail.street" class="" type="text" size="30" value="${userDetail.street }" alt=""/>
						</p>
						<p>
						<label>邮编：</label>
						<input name="userDetail.postal" class="" type="text" size="30" value="${userDetail.postal }" alt=""/>
						</p>	
		<div class="divider" ></div>
				<h1>用户密保问题</h1>
		    <p>
				<label>安全问题：</label>
				<input id="securityQuestion" name="security.question" class="required" value="${security.question}" size="30" alt="请输入安全问题"/>
			</p>
			<p>
				<label>答   案：</label>
				<input id="securityAnswer" name="security.answer" class="required" value="${security.email}" size="30" alt="请输入答案"/>
			</p>
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








