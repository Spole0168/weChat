<!--TOP开始-->
<div id="top">
  <div class="top">
   <div class="huiyuan" id="welcome"></div>
  </div>
</div>
<script type="text/javascript">
	$(function(){
	  if ($.cookie("UserName")) {
	    $("#welcome").html('<ul class="userul"><li class="userout"><a href="logout.action">安全退出</a></li><li class="userxiaoxi">信息<a href="#"><strong>(2)</strong></a></li><li class="username"> 账户名称:<a href="#"><strong>'+$.cookie("UserName")+'</strong></a></li><li>欢迎您登录尊米网！</li></ul>');
      }else{
      	$("#welcome").html('<span style="color:#FF0000;">您还未登录!</span>&nbsp;<a href="user/login.action">登录</a>&nbsp;|&nbsp;<a href="user/regist.action">注册</a>');
      }
	});
</script>
<!--TOP结束--> 
<!--HEAD开始-->
<div id="header">
  <div class="header">
    <div class="logo"><img src="${contextPath}/images/logo.png" width="163" height="60" /></div>
  </div>
</div>
<!--HEAD结束-->
<!--导航开始-->
<div id="mainNav">
    <div class="mainNav" >
      <ul id="nav" >
        <li class="mainlevel"><a href="${contextPath}" class="ok">尊米首页</a></li>
        <li class="mainlevel" id="mainlevel_01"><a href="${contextPath}/news.html">域名新闻</a>
          <ul id="sub_01" style="z-index:9999;">
            <li><a href="${contextPath}/news/6.html">域名行业新闻</a></li>
            <li><a href="${contextPath}/news/7.html">域名应用/周边</a></li>
            <li><a href="${contextPath}/news/8.html">抢注/争议报道</a></li>
            <li><a href="${contextPath}/news/9.html">成功交易报道</a></li>
            <li><a href="${contextPath}/news/10.html">拍卖叫价新闻</a></li>
            <li><a href="${contextPath}/news/11.html">域名时事评析</a></li>
            <li><a href="${contextPath}/news/12.html">域名商家动态</a></li>
          </ul>
        </li>
        <li class="mainlevel" id="mainlevel_02"><a href="${contextPath}/study.html">域名知识</a>
          <ul id="sub_02" style="z-index:9999;">
            <li><a href="${contextPath}/study/13.html">域名基础知识</a></li>
            <li><a href="${contextPath}/study/14.html">域名相关法规</a></li>
            <li><a href="${contextPath}/study/15.html">域名停放赚钱</a></li>
            <li><a href="${contextPath}/study/16.html">域名选择/应用</a></li>
            <li><a href="${contextPath}/study/17.html">域名价值分享</a></li>
            <li><a href="${contextPath}/study/18.html">域名权威数据</a></li>
          </ul>
          
        </li>
        <li class="mainlevel" id="mainlevel_03"><a href="${contextPath}/focus.html">米友关注</a>
        <ul id="sub_03" style="z-index:9999;">
            <li><a href="${contextPath}/focus/31.html">时事与社会</a></li>
            <li><a href="${contextPath}/focus/32.html">科技与数码</a></li>
            <li><a href="${contextPath}/focus/33.html">体育与娱乐</a></li>
            <li><a href="${contextPath}/focus/34.html">汽车与房产</a></li>
            <li><a href="${contextPath}/focus/35.html">时尚与健康</a></li>	
            <li><a href="${contextPath}/focus/36.html">游戏与趣闻</a></li>
          </ul>
        </li>
        <li class="mainlevel" id="mainlevel_04"><a href="${contextPath}/domain/ykj.action">一口价</a></li>
        <li class="mainlevel" id="mainlevel_05"><a href="#">域名求购</a></li>
        <li class="mainlevel" id="mainlevel_06"><a href="#">域名查询</a></li>
        <li class="mainlevel" id="mainlevel_08"><a href="#">尊米服务</a></li>
        <li class="mainlevel" id="mainlevel_08"><a href="${contextPath }/user/account.action">我的账户</a></li>
        <div class="clear"></div>
      </ul>
    </div>
 </div>
<!--导航结束--> 