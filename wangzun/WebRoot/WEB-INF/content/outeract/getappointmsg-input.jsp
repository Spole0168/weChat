
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
    <script type="text/javascript">
    $(function(){
    });
    
    function doClick(){
  		 debugger
  		var machine_id = $("#machine_id").val();
  		//alert("userName"+userName+"  password"+password);
  		if(!machine_id){
  			alert("请输入饮水机id，进行查询！");
  			return;
  		}
  		$.post(
  				"${ctx}/outeract/getappointmsg!getappointmsg.action", 
  				{"machine_id": machine_id },
  				function(data){
  					 debugger
  					 //alert(data.statusCode);
  					 if(data.statusCode==200||data.statusCode=='200'){
  						 alert("machineid:"+data.machineid);
  					 }else{
  						 alert(data.message);
  					 }
  	       },"json");
  	}
    
    function doLogin(){
    	return true;
    }
	</script>
<div class="pageContent">

<div class="pageContent">
	<form  method="post" action="${ctx}/outeract/zhuanjijh!jihuo.action" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
		  <table  style="padding-left: 20px">
		  		<tr>
		              <td><label>饮水机id：</label></td>
		               <td><input id="machine_id" name="machine_id" value="${machine_id}" class="required alphanumeric" size="50" value="" alt="请输入饮水机id"/></td>
		  		</tr>
		  			<tr>
		              <td><label>地址：</label></td>
		               <td><label>outeract/getappointmsg!getappointmsg.action</label></td>
		  		</tr>
		  			<tr>
		              <td colspan="2" align="center"><button type="button" onclick="doClick()">查询</button></td>
		  		</tr>
		  </table>
		</div>
	</form>
</div>





