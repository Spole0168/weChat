<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

</div>

<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<div class="pageContent">
	<form  method="post" action="${ctx}/yinzy/userinfo!updatejifen.action" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
		  <table  style="padding-left: 20px">
		  		<tr>
		              <td><label>饮水机id：</label></td>
		              <input type="hidden" name="userInfo.id" value="${userInfo.id}"/>
		               <td><input type="radio"  name="type" value="2" checked="checked"/> 增加<input type="radio"  name="type" value="1"/>减少</td>
		  		</tr>
		  		<tr>
		              <td><label>积分（增加或减少）：</label></td>
		               <td><input id="jifen" name="jifen"     class="required" size="50" alt="请输入装机人员id"></td>
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





