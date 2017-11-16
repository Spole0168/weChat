<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
<!--

$("#domainName").focus(function(){
	  $("#domainName").css("background-color","#FFFFCC");
	});


function searchSaleAccount(){
	var email=$("#zhanghao").val();
	$.post(
			"${ctx}/manager/domain-sale!searchSaleAccount.action", 
			{ "email":email },
			function(data){
			   alert(data.uses);
			   
			 },"json");
}


//-->
</script>

<div class="pageContent">
	<form id="formDomainSale" method="post" action="${ctx}/manager/domain-sale!save.action" enctype="multipart/form-data" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<input type="hidden" name="domainAuction.id" value="${domainAuctionId }"/>
			<dl class="nowrap">		
				<dt>一口价域名：</dt>
				<dd><input id="domainName" class="required"  name="domainOnePrize.domainName" 
				                    value="${domainOnePrize.domainName}"  size="30"/></dd>
			</dl>
			<dl class="nowrap">			
				<dt>卖家账号：</dt>
				<dd><input id="zhanghao" class="required email"  name="domainOnePrize.zunmiUser.email" 
				           type="text"  value="${domainOnePrize.zunmiUser.email }" size="30" alt="请输入卖家邮箱"/>
				           <!-- 
				<span><input type="button" onclick="searchSaleAccount()" value="查询卖家账号"  /></span>
				            -->
				<div id="dd"></div>
				</dd>
			</dl>
			<dl class="nowrap">
				<dt>域名特征：</dt> 
				<dd>
					<select id="dt" class="required" name="domainOnePrize.domainItems">
						<option value="E">纯单词（如：name.com）</option>
						<option value="EN">单词组合（如：namemax.cn）</option>
						<option value="PY" selected="selected">全拼（如：zunmi.com）</option>
						<option value="PYS">拼音缩写（如：gdrc.com）</option>
						<option value="NUM">纯数字（如：265.com）</option>
						<option value="ENUM">字母与数字(如：51job.com)</option>
						<option value="CY">纯创意（如：yahoo.com）</option>
						<option value="ZH">创意组合（如：sohu.com）</option>
						<option value="CN">纯中文（如：尊米.com）</option>
						<option value="CEN">中文与英数（如：粤A.com）</option>
					</select> 
				</dd>
			</dl>
			
			<dl class="nowrap">
				<dt>状&nbsp;&nbsp;&nbsp;&nbsp;态：</dt>
				<dd>
					<select class="combox" class="required" name="domainOnePrize.status">
						<option value="ALL">所有状态</option>
						<option value="PENDING">未验证</option>
						<option value="WAIT">等待</option>
						<option value="FAILED">失败</option>
						<option value="SUCCESSFUL">成功</option>
						<option value="END">结束</option>
						<option value="ABORTED">ABORTED</option>
					</select>
				</dd>
			</dl>
			<dl class="nowrap">
				<dt>货币类型：</dt>
				<dd>
					<select class="combox" class="required" name="domainOnePrize.moneyType">
						<option value="CNY">人民币</option>
						<option value="USD">美元</option>
						<option value="EUR">欧元</option>
					</select>
				</dd>
			</dl>
			<dl class="nowrap">			
				<dt>一口价：</dt>
				<dd>
				<input type="text" class="required number" name = "domainOnePrize.prize" value="${domainOnePrize.prize }" size="30"/>
				</dd>
			</dl>
			
			<dl class="nowrap">			
				<dt>域名描述：</dt>
				<dd>
				<textarea id="recommendDomains" name="domainOnePrize.description" rows="5" cols="50">${domainOnePrize.description }</textarea>
				</dd>
			</dl>
			<dl class="nowrap">
				<dt>是否推荐：</dt>
				<dd>
				  <input id="recommend" type="radio" name="domainOnePrize.isRecommend" value="true"/>是 
				  <input id="recommend1" type="radio" name="domainOnePrize.isRecommend" value="false"/>否
				</dd>
			</dl>
			<dl class="nowrap">
				<dt>起始时间：</dt>
				<dd>
			    <input type="text"  name="domainOnePrize.addDate" class="date required" format="yyyy-MM-dd HH:mm" 
			                                            value="${domainOnePrize.addDate }" readonly="true" />
				</dd>
			</dl>
			<dl class="nowrap">
				<dt>一口价天数：</dt>
				<dd>
				  <input type="text"  name="totalDays" class="required" 
				                     value="${totalDays }" />
				</dd>
			</dl>
			<!-- 编辑的话应该显示出截止日期 -->
			<c:if test="${domainOnePrize.id!=null }">
			<dl class="nowrap">
				<dt>截止日期：</dt>
				<dd>
				${domainOnePrize.endDate }
				</dd>
			</dl>
			</c:if>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">确定</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>





