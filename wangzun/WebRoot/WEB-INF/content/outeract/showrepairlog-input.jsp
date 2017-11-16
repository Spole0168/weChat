
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
    <script type="text/javascript">
    $(function(){
    });
    
    function doClick(){
  		 debugger
  		var machine_id = $("#machine_id").val();
  		//alert("userName"+userName+"  password"+password);
  		alert("machine_id:"+machine_id);
  		if(machine_id == null || machine_id==""){
  			alert("请输入饮水机id，进行查询！");
  			return;
  		}
  		//$.post(
  	//			"${ctx}/outeract/showrepairlog!getrepairlog.action", 
  	//			{"machine_id": machine_id },
  //				function(data){
  //					 debugger
  					 //alert(data.statusCode);
 // 					 if(data.statusCode==200||data.statusCode=='200'){
  //					 }else{
  //					 }
  //					 alert("tds_in:"+data.tds_in+"   tds_out"+data.tds_out);
  						//window.location.href="${ctx}/manager/main.action";
  //	       },"json");
  		
  		$.ajax({
  			 type:"POST",
  			 url:"${ctx}/outeract/showrepairlog!getrepairlog.action",
  			data:{"machine_id": machine_id },
  			 success:function(data){
  				 debugger
  				alert(data.statusCode);
  				 alert(data);
  				 alert(data.message);
  	          // $("#msg").html(decodeURI(data));            
  	            },
  	          complete: function(XMLHttpRequest, textStatus){
  	            //   alert(XMLHttpRequest.responseText);
  	             //  alert(textStatus);
  	                //HideLoading();
  	            },
  	            //调用出错执行的函数
  	            error: function(){
  	            	//alert("失败了啊");
  	                //请求出错处理
  	            } 
  		});
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
		               <td><label>outeract/showrepairlog!getrepairlog.action</label></td>
		  		</tr>
		  			<tr>
		              <td colspan="2" align="center"><button type="button" onclick="doClick()">查询</button></td>
		  		</tr>
		  </table>
		</div>
	</form>
</div>





