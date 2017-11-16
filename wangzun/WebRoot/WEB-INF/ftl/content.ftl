<!doctype html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${content.title}_尊米网</title>
<meta name="description" content="${content.summary}" />
<meta name="keywords" content="${content.tags}" />
<link href="${contextPath}/style/global.css" rel="stylesheet" type="text/css" />
<link href="${contextPath}/style/othercss.css" rel="stylesheet" type="text/css">
<link href="${contextPath}/style/queryWhois.css" rel="stylesheet" type="text/css">
<script src="${contextPath}/scripts/jquery-1.7.2.min.js" type="text/javascript"></script>
<script src="${contextPath}/scripts/slide.js" type="text/javascript" ></script>
<script src="${contextPath}/scripts/jquery.cookie.js" type="text/javascript" ></script>
<script src="${contextPath}/scripts/contentWhois.js" type="text/javascript" ></script>
<!--[if lte IE 6]>
<script src="${contextPath}/scripts/DD_belatedPNG_0.0.8a.js" type="text/javascript"></script>
    <script type="text/javascript">
        DD_belatedPNG.fix('div, ul, img, li, input , a , h1 , h2');
    </script>
<![endif]-->
<script type="text/javascript">
var contentId = "${content.id}";
var base = "${contextPath}";
</script>

</head>

<body>
<div id="box"></div>
<div id="loading"><img alt="" src="${contextPath}/images/zhuan.gif" width="15" height="15">正在查询域名Whois...</div>
<div class="wrapper"> 
  <#include "common/header.ftl">
  <!--位置开始-->
  <div class="gk">
	 <div class="gk2">
	   <div class="gkl"><span class="fEB6C00">您的位置：</span><a href="${contextPath}">尊米首页</a> > <a href="${contextPath}${content.zunmiNode.linkUrl}">${content.zunmiNode.nodeName}</a></div>
	   <div class="gkr"></div>
	 </div>
  </div>
  <!--位置结束--> 
  <!--主体部分开始-->
  <div class="main">
    <div class="ad"><img src="${contextPath}/images/ad1.jpg" width="1002" height="90"></div>
    <div class="mainlist">
      <div class="maininfo">
        <h1><span class="listnav"><a href="#">域名周边新闻</a> | <a href="#">域名交易</a> | <a href="#">域名新闻</a> | <a href="#">返回首页</a></span>域名新闻</h1>
        <!--新闻内容正文开始-->
        <div id="content">
          <h2>${content.title}</h2>
          <h3>时间:${content.addDate}&nbsp;&nbsp;&nbsp;来源:${content.source}&nbsp;&nbsp;&nbsp;作者:${content.author}</h3>
          <div class="abstract">内容摘要：${content.summary}</div>
          <div class="content">
             ${content.content}
            <div class="edit">
                                           责任编辑： ${content.zunmiAdminByOperatorId.userName}</div>
          </div>
          <!--上下文开始-->
          <div id="context">
            <div class="context" id = "pnPage">
            </div>
            <div class="clear"></div>
          </div>
          <div class="bshare-custom" style="float:right;">
           		<a title="分享到" href="http://www.bshare.cn/share" id="bshare-shareto" class="bshare-more">分享到:</a>
	            <a title="分享到QQ空间" class="bshare-qzone"></a>
	            <a title="分享到新浪微博" class="bshare-sinaminiblog"></a>
	            <a title="分享到人人网" class="bshare-renren"></a>
	            <a title="分享到腾讯微博" class="bshare-qqmb"></a>
	            <a title="分享到豆瓣" class="bshare-douban"></a>
	            <a title="分享到Google+" class="bshare-gplus" href="javascript:void(0);"></a>
	            <a title="分享到Facebook" class="bshare-facebook" href="javascript:void(0);"></a>
	            <a title="分享到Twitter" class="bshare-twitter" href="javascript:void(0);"></a>
	            <a title="更多平台" class="bshare-more bshare-more-icon more-style-addthis"></a>
	            <span class="BSHARE_COUNT bshare-share-count" style="float: none; ">6.84K</span>
          </div>
          <br/>
          <script type="text/javascript" charset="utf-8" src="http://static.bshare.cn/b/buttonLite.js#style=-1&amp;uuid=564d2554-2f85-4951-b0d1-18b6a7177759&amp;pophcol=2&amp;lang=zh"></script>
          <script type="text/javascript" charset="utf-8" src="http://static.bshare.cn/b/bshareC0.js"></script>
          <!--上下文结束--> 
          <!--微博一键关注-->
          <div class="weibo"><script type="text/javascript">document.write('<iframe width="660" height="204" frameborder="0" scrolling="no" src="http://widget.weibo.com/relationship/bulkfollow.php?language=zh_cn&uids=1642909335,1782515283&wide=0&color=FFFFFF,FFFFFF,0082CB,666666&showtitle=1&showinfo=0&sense=1&verified=1&count=6&refer='+encodeURIComponent(location.href)+'&dpc=1"></iframe>')</script></div>
          <!--评论部分--><!--
          <div class="comment">
            <form name="form1" method="post" action="">
              <h4>我要评论</h4>
              <h5>用户名:Zunmi.com</h5>
              <p class="textarea">
                <textarea cols="75" rows="6">需要登陆后才能发表评论，请您先登录！</textarea>
              </p>
              <p class="pbt">
                <input name="" type="checkbox" value="">
                匿名发布
                <label>
                  <input type="submit" name="button" id="button" value="发表评论">
                </label>
              </p>
            </form>
            <h4><span class="dateright">已有12位米友评论</span>米友评论</h4>
            <div class="pinglun">
              <h6><span class="dateright">2012-05-23 05:23:02 发表</span>米友：<strong>*Brad</strong></h6>
              <div class="pinfo">
                <p>尊米网新闻页面！！！！尊米网新闻页面！！！！ </p>
                <p> 尊米网新闻页面！！！！尊米网新闻页面！！！！ </p>
                <p>尊米网新闻页面！！！！
                  尊米网新闻页面！！！！</p>
                <p>尊米网新闻页面！！！！ </p>
                <div class="review"> <a href="#">定</a>[<span style="color:#F00">2</span>]&nbsp;&nbsp;<a href="#">回复</a>&nbsp;&nbsp;<a href="#">复制</a> </div>
              </div>
            </div>
            <div class="pinglun">
              <h6><span class="dateright">2012-05-23 05:23:02 发表</span>米友：<strong>*Brad</strong></h6>
              <div class="pinfo">
                <p>尊米网新闻页面！！！！尊米网新闻页面！！！！ </p>
                <p> 尊米网新闻页面！！！！尊米网新闻页面！！！！ </p>
                <p>尊米网新闻页面！！！！
                  尊米网新闻页面！！！！</p>
                <p>尊米网新闻页面！！！！ </p>
                <div class="review"> <a href="#">定</a>[<span style="color:#F00">2</span>]&nbsp;&nbsp;<a href="#">回复</a>&nbsp;&nbsp;<a href="#">复制</a> </div>
              </div>
            </div>
            <div class="pinglun">
              <h6><span class="dateright">2012-05-23 05:23:02 发表</span>米友：<strong>*Brad</strong></h6>
              <div class="pinfo">
                <p>尊米网新闻页面！！！！尊米网新闻页面！！！！ </p>
                <p> 尊米网新闻页面！！！！尊米网新闻页面！！！！ </p>
                <p>尊米网新闻页面！！！！
                  尊米网新闻页面！！！！</p>
                <p>尊米网新闻页面！！！！ </p>
                <div class="review"> <a href="#">定</a>[<span style="color:#F00">2</span>]&nbsp;&nbsp;<a href="#">回复</a>&nbsp;&nbsp;<a href="#">复制</a> </div>
              </div>
            </div>
            <div class="see"><a href="#">查看所有12条评论>></a></div>
          </div>-->
          <!--评论部分--> 
        </div>
        <!--新闻内容正文结束--> 
      </div>
      <!--右侧栏部分开始-->
      <div class="sider">
        <div class="right">
          <h1 class="infoh1"><span class="more"><img src="${contextPath}/images/more.gif" width="33" height="5" /></span>${domainNewsNode.nodeName}</h1>
          <ul>
             <#if domainNews??>
                 <#list domainNews as news>
                  <li>&middot;<a href="#">${news.subTitle}</a></li>
                 </#list>            
             <#else>该栏目现在还没有记录
             </#if>
          </ul>
        </div>
        <div class="ad2"><img src="${contextPath}/images/ad2.jpg" width="268" height="170"></div>
        <div class="right">
          <h1 class="infoh1"><span class="more"><img src="${contextPath}/images/more.gif" width="33" height="5" /></span>域名行业新闻</h1>
          <ul>
            <li>&middot;<a href="#">百度“百伯”两年历程 域名频频</a></li>
            <li>&middot;<a href="#">百度“百伯”两年历程 域名频频</a></li>
            <li>&middot;<a href="#">百度“百伯”两年历程 域名频频</a></li>
            <li>&middot;<a href="#">百度“百伯”两年历程 域名频频</a></li>
            <li>&middot;<a href="#">百度“百伯”两年历程 域名频频</a></li>
            <li>&middot;<a href="#">百度“百伯”两年历程 域名频频</a></li>
            <li>&middot;<a href="#">百度“百伯”两年历程 域名频频</a></li>
            <li>&middot;<a href="#">百度“百伯”两年历程 域名频频</a></li>
            <li>&middot;<a href="#">百度“百伯”两年历程 域名频频</a></li>
            <li>&middot;<a href="#">百度“百伯”两年历程 域名频频</a></li>
          </ul>
        </div>
        <div class="right">
          <h1 class="infoh1"><span class="more"><img src="${contextPath}/images/more.gif" width="33" height="5" /></span>域名行业新闻</h1>
          <ul>
            <li>&middot;<a href="#">百度“百伯”两年历程 域名频频</a></li>
            <li>&middot;<a href="#">百度“百伯”两年历程 域名频频</a></li>
            <li>&middot;<a href="#">百度“百伯”两年历程 域名频频</a></li>
            <li>&middot;<a href="#">百度“百伯”两年历程 域名频频</a></li>
            <li>&middot;<a href="#">百度“百伯”两年历程 域名频频</a></li>
            <li>&middot;<a href="#">百度“百伯”两年历程 域名频频</a></li>
            <li>&middot;<a href="#">百度“百伯”两年历程 域名频频</a></li>
            <li>&middot;<a href="#">百度“百伯”两年历程 域名频频</a></li>
            <li>&middot;<a href="#">百度“百伯”两年历程 域名频频</a></li>
            <li>&middot;<a href="#">百度“百伯”两年历程 域名频频</a></li>
          </ul>
        </div>
        <div class="ad2"><img src="${contextPath}/images/ad2.jpg" width="268" height="170"></div>
        <div class="right">
          <h1 class="infoh1"><span class="more"><img src="${contextPath}/images/more.gif" width="33" height="5" /></span>域名行业新闻</h1>
          <ul>
            <li>&middot;<a href="#">百度“百伯”两年历程 域名频频</a></li>
            <li>&middot;<a href="#">百度“百伯”两年历程 域名频频</a></li>
            <li>&middot;<a href="#">百度“百伯”两年历程 域名频频</a></li>
            <li>&middot;<a href="#">百度“百伯”两年历程 域名频频</a></li>
            <li>&middot;<a href="#">百度“百伯”两年历程 域名频频</a></li>
            <li>&middot;<a href="#">百度“百伯”两年历程 域名频频</a></li>
            <li>&middot;<a href="#">百度“百伯”两年历程 域名频频</a></li>
            <li>&middot;<a href="#">百度“百伯”两年历程 域名频频</a></li>
            <li>&middot;<a href="#">百度“百伯”两年历程 域名频频</a></li>
            <li>&middot;<a href="#">百度“百伯”两年历程 域名频频</a></li>
          </ul>
        </div>
      </div>
      <!--右侧栏部分结束-->
      <div class="clear"></div>
    </div>
    <!--友情链接开始-->
    <div class="links">
      <h1><span class="more"><img src="${contextPath}/images/more.gif" width="33" height="5" /></span>域名新闻</h1>
      <ul>
        <li><a href="#">Winindomain.com</a></li>
        <li><a href="#">创域者</a></li>
        <li><a href="#">孕婴童商务网</a> </li>
        <li><a href="#">郝鹏视线</a></li>
        <li><a href="#">IB资讯</a></li>
        <li><a href="#">淘米网</a></li>
        <li><a href="#">挖贝网</a></li>
        <li><a href="#">好名网</a></li>
        <li><a href="#">虚拟主机评测网</a> </li>
        <li><a href="#">哇嘎播播</a> </li>
        <li><a href="#">长春网站建设</a> </li>
        <li><a href="#">@一口价域名</a></li>
        <li><a href="#">八厘米</a> </li>
        <li><a href="#">X.cm</a></li>
        <li><a href="#">293网站估价</a></li>
      </ul>
      <div class="clear"></div>
    </div>
    <!--友情链接结束--> 
  </div>
  <!--主题部分结束--> 
  <#include "common/footer.ftl">
</div>
</body>
</html>