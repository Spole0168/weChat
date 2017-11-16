<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!doctype html>
  <head>
    <title>尊米网</title>
    <meta charset="utf-8"/>
    <meta name="description" content="本站提供域名新闻、域名知识、域名拍卖、域名人物档案、域名投资、域名停放、域名法律法规、域名交易中介、域名经纪、域名交流社区等服务。" />
	<meta name="keywords" content="domainname,domainpark,domainparking,域名新闻,域名资讯,域名知识,域名投资,域名故事,域名论坛,域名停放,域名法律,域名争议,域名仲裁,域名经纪,域名拍卖,域名评估,域名应用" />
	<link href="${contextPath}/style/jquery.slideBox.css" rel="stylesheet" type="text/css" />
	<link href="${contextPath}/style/global.css" rel="stylesheet" type="text/css" />
	<link href="${contextPath}/style/maincss.css" rel="stylesheet" type="text/css" />
	<link href="${contextPath}/style/tabcss.css" rel="stylesheet" type="text/css" />
	
	<script type="text/javascript" src="${contextPath}/scripts/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="${contextPath}/scripts/jquery.slideBox.min.js"></script>
	<script type="text/javascript" src="${contextPath}/scripts/slide.js"></script>
	<script type="text/javascript" src="${contextPath}/scripts/jquery.cookie.js"></script>
	
	<script>
	 	jQuery(function($){
			$('#loop').slideBox({
				direction : 'left',//left,top#方向
				duration : 0.6,//滚动持续时间，单位：秒
				easing : 'linear',//swing,linear//滚动特效
				delay : 5,//滚动延迟时间，单位：秒
				startIndex : 1,//初始焦点顺序
				hideClickBar : true,//不自动隐藏点选按键
				clickBarRadius : 3,
				hideBottomBar : false//隐藏底栏
			});
		});
	</script>
  </head>
  
  <body>
  	<div class="wrapper">
  	  <!--##### 头部 ：包含Logo、菜单、通知 #####-->
  	  <#include "common/header.ftl"/>
	  <div class="gk">
	    <div class="gk2">
	      <div class="gkl"><span class="fEB6C00">公告通知：</span>尊米网全新改版升级，敬请期待... ...</div>
	      <div class="gkr">
	          <div class="bshare-custom" style="float:right;margin-top:5px;"><a title="分享到QQ空间" class="bshare-qzone"></a><a title="分享到新浪微博" class="bshare-sinaminiblog"></a><a title="分享到人人网" class="bshare-renren"></a><a title="分享到腾讯微博" class="bshare-qqmb"></a><a title="分享到豆瓣" class="bshare-douban"></a><a title="分享到Google+" class="bshare-gplus" href="javascript:void(0);"></a><a title="分享到Facebook" class="bshare-facebook" href="javascript:void(0);"></a><a title="分享到Twitter" class="bshare-twitter" href="javascript:void(0);"></a><a title="更多平台" class="bshare-more bshare-more-icon more-style-addthis"></a></div>
	          <script type="text/javascript" charset="utf-8" src="http://static.bshare.cn/b/buttonLite.js#style=-1&amp;uuid=767ee739-9494-46c1-adf4-9f21b30a3826&amp;pophcol=2&amp;lang=zh"></script>
	          <script type="text/javascript" charset="utf-8" src="http://static.bshare.cn/b/bshareC0.js"></script>
	      </div>
	    </div>
	  </div>	
	  
	  <div class="main">
	    <!-- 新闻栏 --><!-- 广告横幅 -->
	  	<#include "index-domain-news.ftl"/>
	  	<!-- 一口价、求购 --><!-- 旺铺 -->
	  	<#include "index-domain-trade.ftl"/>
	  	 <!-- 微博营销、米友关注 -->
	  	<#include "index-bottom-news.ftl"/>
	  	
	  	<!-- 微博推荐 -->
	  	<div class="domain"><script type="text/javascript">document.write('<iframe width="1002" height="239" frameborder="0" scrolling="no" src="http://widget.weibo.com/relationship/bulkfollow.php?language=zh_cn&uids=1642909335,1782515283&wide=1&color=C2D9F2,FFFFFF,0082CB,666666&showtitle=1&showinfo=1&sense=1&verified=1&count=9&refer='+encodeURIComponent(location.href)+'&dpc=1"></iframe>')</script></div>
	  	<!-- 友情链接 -->
	    <#include "common/blogroll.ftl">
  	  </div>
	  <#include "common/footer.ftl"/>
    </div>
  </body>
</html>
