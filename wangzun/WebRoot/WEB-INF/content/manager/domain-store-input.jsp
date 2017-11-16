<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">

$(document).ready(function() {
	reocmmend = '${domainStore.recommend}';
		if(reocmmend == 'true') {
			$("#reco").attr("checked", true);
		} else {
			$("#reco1").attr("checked", true);
		}
	});  

</script>

<div class="pageContent">
	<form method="post" action="${ctx}/manager/domain-store!adminUpdate.action" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent"  layoutH="56">
		<fieldset>
			<dl class="nowrap">
				<dt>用户账号： </dt>
				<dd>
				<input type="hidden" name="user.id" value="${user.id }"/>
				<input type="hidden" name="domainStore.id" value="${domainStore.id }"/>
				<input type="text" name="user.email" value="${user.email }" readonly="readonly" size="30"/>
				</dd>
			</dl>
			<dl class="nowrap">
				<dt>旺铺名称：   </dt>
				<dd>
				<input id="domainStoreName" name="domainStore.storeName" value="${domainStore.storeName }" class="required" size="30"/>
				</dd>
			</dl>
			<dl class="nowrap">
				<dt>旺铺地址：   </dt>
				<dd>
				<input id="domainStoreAddr" name="domainStore.storeUrl" value="${domainStore.storeUrl }" class="" size="30"/>
				</dd>
			</dl>
			<dl class="nowrap">
				<dt>旺铺Logo：     </dt>
				<dd>
				<input id="logo" name="domainStore.logoUrl" value="${domainStore.logoUrl }" type="file" size="30" />
				</dd>
			</dl>
			<dl class="nowrap">
				<dt>店主邮箱：       </dt>
				<dd>
				<input id="domainUserEmail" type="text" name="domainStore.zunmiUser.email" value="${domainStore.zunmiUser.email }" size="30"/>
				</dd>
			</dl>
			<dl class="nowrap">
				<dt>推荐用户：       </dt>
				<dd>
					是<input type="radio" id="reco" name="domainStore.recommend" value="true" />
					否<input type="radio" id="reco1" name="domainStore.recommend" value="false" />
				</dd>
			</dl>
			<dl class="nowrap">
				<dt>联系电话：       </dt>
				<dd>
				<c:if test="${domainStore==null}">
				<input id="phone" name="domainStore.zunmiUser.mobile" value="${user.mobile }" type="text" size="30" />
				</c:if>
				<c:if test="${domainStore!=null}">
				<input id="phone" name="domainStore.zunmiUser.mobile" value="${domainStore.zunmiUser.mobile }" type="text" size="30" />
				</c:if>
				</dd>
			</dl>
			<dl class="nowrap">
				<dt>店主 QQ：       </dt>
				<dd>
				<c:if test="${domainStore==null}">
				<input id="qq" name="domainStore.zunmiUser.zunmiUserdetail.qq" value="${user.zunmiUserdetail.qq }" type="text" size="30"/>
				</c:if>
				<c:if test="${domainStore!=null}">
				<input id="qq" name="domainStore.zunmiUser.zunmiUserdetail.qq" value="${domainStore.zunmiUser.zunmiUserdetail.qq }" type="text" size="30"/>
				</c:if>
				</dd>
			</dl>
			<dl class="nowrap">
				<dt>店主MSN：       </dt>
				<dd>
				<c:if test="${domainStore==null}">
				<input id="msn" name="domainStore.zunmiUser.zunmiUserdetail.msn" value="${user.zunmiUserdetail.msn }"  type="text" size="30"/>
				</c:if>
				<c:if test="${domainStore!=null}">
				<input id="msn" name="domainStore.zunmiUser.zunmiUserdetail.msn" value="${domainStore.zunmiUser.zunmiUserdetail.msn }"  type="text" size="30"/>
				</c:if>
				</dd>
			</dl>
			<dl class="nowrap">
				<dt>旺铺公告：       </dt>
				<dd>
				<textarea id="notice" name="domainStore.notice" rows="5" cols="50">${domainStore.notice }</textarea>
				</dd>
			</dl>
			<dl class="nowrap">
				<dt>域名推荐：       </dt>
				<dd>
				<textarea id="recommendDomains" name="domainStore.recommendDomains" rows="5" cols="50">${domainStore.recommendDomains }</textarea>
				</dd>
			</dl>
			<dl class="nowrap">
				<dt>友情链接：       </dt>
				<dd>
				<textarea id="customConnects" name="domainStore.customConnects" rows="5" cols="50">${domainStore.customConnects }</textarea>
				</dd>
			</dl>
			<!-- 
				友情链接： 
				<input id="email" name="admin.answer" value="${admin.email}" minlength="2" maxleng="20" size="30" alt="请输入答案"/>
		    -->
	    </fieldset>
		</div>
		<div class="formBar">
			<ul>
				<li>
					<div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div>
				</li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>