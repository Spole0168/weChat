$(function(){
	var reg=new RegExp(",","g");
	contentId =contentId.replace(reg,"");	
	$.ajax({
   type: "GET",
   url: base+"/content/content-page!searchPreviousAndNextPage.action",
   data: "id="+contentId,
   success: function(msg){
     addDiv(msg.previousUrl,msg.previousSubTitle,msg.nextUrl,msg.nextSubTitle );
   }
  });
 //定义弹出div上的事件mouseover
 $("#box").mouseover(function() {
   jtime();
  return false;
 });
  //定义弹出div上的事件mouseout
 $("#box").mouseout(function() {
   hide();
  return false;
 });
});
function addDiv(url1,st1,url2,st2 ) { 
   var pUrl = base+url1;
   var nUrl = base+url2;
   if(url1=="null" && url2=="null"){
   	return;
   }

   if(url1 == "null"){
   	 $("#pnPage").html("<ul> <li>下一篇：<a href=\""+nUrl+"\">"+st2+"</a></li></ul>");
   	 return;
   }
   if(url2 == "null"){
     $("#pnPage" ).html("<ul> <li>上一篇：<a href=\""+pUrl+"\">"+st1+"</a></li></ul>");
   	 return;
  }
  $("#pnPage" ).html("<ul> <li>上一篇：<a href=\""+pUrl+"\">"+st1+"</a></li> <li>下一篇：<a href=\""+nUrl+"\">"+st2+"</a></li></ul>");

  }
  //移到关键字事件
function over(o,domain){
     flag = 1;
	 showLoading(o);
	 query(o,domain);	
}
//离开关键字事件
function out(){
      flag = 0;
      hiddenLoading();
	  db_gtime=setTimeout(hide,500);  //0.5秒后执行delDiv删除操作
} 
function query(o,domain){
 if(flag == 0 ) {
  hiddenLoading();
 return;
 }
$.ajax({
   type: "GET",
   url: base+"/content/whois/queryContentDomainWhois.action",
   data: "domain="+domain,
   success: function(msg){
       hiddenLoading();
       if(flag == 0 ) return;
	   var result = msg.message;
	   var reg=new RegExp("\\|","g");
	   result =result.replace(reg,"<br>");	 
       showDomainInfo(o,result);
   }
  });
   }
//显示弹出窗口
function showDomainInfo(o,result){
 var h=132;
 var dbt=o.offsetTop;
 var dbl=o.offsetLeft;
 var ofH=o.offsetHeight;    
 while(o=o.offsetParent)                                        
 {    
   dbl+=o.offsetLeft;    
   dbt+=o.offsetTop;       
 } 
 var bt=document.documentElement.scrollTop ? document.documentElement.scrollTop : document.body.scrollTop;
  wh=dbwindowHeight();
  var b=wh-(dbt-bt)-ofH;
  if(b>h) h=dbt+ofH; else h=dbt-h;
// newDiv.style.top = h+"px";　//设置div的位置
// newDiv.style.left = dbl+"px";
$("#box").css({"left":dbl,"top":h});
$("#box").html(result);
$("#box").fadeIn("fast");
 return false;
}
var db_gtime=null;
var jtime=function()
{
  clearTimeout(db_gtime);
};
function hide(){
	setTimeout("$('#box').hide()",1000); 
     return false;
}

function dbwindowHeight() {
		var E = document.documentElement;
		return self.innerHeight || (E && E.clientHeight) || document.body.clientHeight;
	}
	
function showLoading(o){
	 var h=132;
	 var dbt=o.offsetTop;
	 var dbl=o.offsetLeft;
	 var ofH=o.offsetHeight;    
	 while(o=o.offsetParent)                                        
	 {    
	   dbl+=o.offsetLeft;    
	   dbt+=o.offsetTop;       
	 } 
	 var bt=document.documentElement.scrollTop ? document.documentElement.scrollTop : document.body.scrollTop;
	  wh=dbwindowHeight();
	  var b=wh-(dbt-bt)-ofH;
	  if(b>h) h=dbt+ofH; else h=dbt-h;
	// newDiv.style.top = h+"px";　//设置div的位置
	// newDiv.style.left = dbl+"px";
	$("#loading").css({"left":dbl,"top":h});
	$("#loading").fadeIn("fast");
}

function hiddenLoading(){
	$("#loading").hide();
}