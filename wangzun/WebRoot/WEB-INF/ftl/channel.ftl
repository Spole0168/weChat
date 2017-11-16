<!doctype html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${channelNode.nodeName} _尊米网</title>
<meta name="description" content="${channelNode.description}" />
<meta name="keywords" content="${channelNode.keywords}" />
<link href="${contextPath}/style/global.css" rel="stylesheet" type="text/css" />
<link href="${contextPath}/style/othercss.css" rel="stylesheet" type="text/css">
<link href="${contextPath}/style/tabcss.css" rel="stylesheet" type="text/css" />
<script src="${contextPath}/scripts/jquery-1.7.2.min.js" type="text/javascript"></script>
<script src="${contextPath}/scripts/slide.js" type="text/javascript" ></script>
<script src="${contextPath}/scripts/jquery.cookie.js" type="text/javascript" ></script>
<!--[if lte IE 6]>
<script src="${contextPath}/scripts/DD_belatedPNG_0.0.8a.js" type="text/javascript"></script>
    <script type="text/javascript">
        DD_belatedPNG.fix('div, ul, img, li, input , a , h1 , h2');
    </script>
<![endif]-->
</head>

<body>
<div class="wrapper"> 
  <#include "common/header.ftl">
  <!--所在位置开始-->
  <div class="gk">
    <div class="gk2">
      <div class="gkl"><span class="fEB6C00">您的位置：</span><a href="${contextPath}">尊米首页</a> > <a href="${contextPath}${channelNode.linkUrl}">${channelNode.nodeName}</a></div>
      <div class="gkr"></div>
    </div>
  </div>
  <!--所在位置结束--> 
  <!--主要部分开始-->
  <div class="main">
    <div class="maintop">
      <div class="maintopnews">
        <div class="ads"><img src="images/tem5.jpg" width="300" height="240"></div>
        <!--头条新闻开始-->
        <div class="topnews">
           <#if topContent??>
           <h1><a href="${contextPath}${topContent.linkUrl}">${topContent.title}</a></h1>
            <#assign summary="${topContent.summary?replace(' ','')}">
			<#assign summary="${summary?replace('　','')}">
			<#assign summary="${summary?replace('&nbsp;','')}">
			<#assign summary="${summary?replace('<.*?>','','r')}">
           <h2><a href="${contextPath}${topContent.linkUrl}">
           <#if (summary?length > 50) >
      					${summary?substring(0,50)}...
      				<#else>
      					${summary}
      				</#if></a>[<a href="${contextPath}${topContent.linkUrl}">查看全文</a>]</h2>
           </#if>
          
          <ul>
              <#list channelContent as x>
                  <li><a href="${contextPath}${x.linkUrl}">${x.subTitle}</a></li>
                  <#if x_index == 7> <#break></#if>
              </#list>
              <#if (channelContent?size==0) ><li>栏目文章列表为空</li> </#if>
          </ul>
          <div class="clear"></div>
        </div>
        <!--头条新闻结束--> 
        <!--推荐阅读-->
        <div class="topnewsr">
          <ul>
            <#list recommendContent as x>
                <li><a href="${contextPath}${x.linkUrl}">${x.subTitle}</a></li>
            <#if x_index == 8> <#break></#if>
           </#list> 
          </ul>
        </div>
        <!--推荐阅读--> 
      </div>
      <div class="clear"></div>
      <!--图文资讯开始-->
      <div class="maintopimg">
        <div id="Scroll">
          <div id="ScrollMe" style="OVERFLOW: hidden; height:164px;">
          <#list imageContentList as x>
            <#if (x_index == 5)><#break></#if>
            <dl>
              <dt><a href="${contextPath}${x.linkUrl}"><img src="${contextPath}${x.imageUrl}" width="180" height="120" title="${x.title}"></a></dt>
            </dl>
          </#list>
            <div class="clear"></div>
          </div>
        </div>
        <SCRIPT>new srcMarquee("ScrollMe",0,2,808,164,30,3000,3000,164)</SCRIPT> 
      </div>
      <!--图文资讯结束--> 
    </div>
    <!--资讯开始-->
    <div class="newsinfo">
      <div class="newsinfol">
        <div class="newsmain">
          <div class="newsmainl">
            <#if channelNode0??>
            <h1 class="infoh1"><span class="more"><a href="${contextPath}${channelNode0.linkUrl}"><img src="images/more.gif" width="33" height="5" /></a>
            </span>${channelNode0.nodeName}</h1>
           <#if topContent0??>
            <h2><a href="${contextPath}${topContent0.linkUrl}">${topContent0.subTitle}</a></h2>
            <#assign summary="${topContent0.summary?replace(' ','')}">
			<#assign summary="${summary?replace('　','')}">
			<#assign summary="${summary?replace('&nbsp;','')}">
			<#assign summary="${summary?replace('<.*?>','','r')}">
            <h3><span class="f999"><a href="${contextPath}${topContent0.linkUrl}"> <#if (summary?length > 50) >
      					${summary?substring(0,50)}...
      				<#else>
      					${summary}
      				</#if></a></span>[<span class="fccc"><a href="${contextPath}${topContent0.linkUrl}">查看详细</a></span>]</h3>
      		</#if>      
            <ul>
            <#if channelContent0??>
              <#list channelContent0 as x>
                  <li><span class="dateright">${x.addDate?string("MM-dd")} 
                  </span>&middot;<a href="${contextPath}${x.linkUrl}"><a href="${contextPath}${x.linkUrl}">${x.subTitle}</a></li>
                  <#if x_index == 9> <#break></#if>
              </#list>     
            </#if>  
            </ul>
            </#if>  
          </div>
          <div class="newsmainl">
           <#if channelNode1??>
            <h1 class="infoh1"><span class="more"><a href="${contextPath}${channelNode1.linkUrl}"><img src="images/more.gif" width="33" height="5" /></a></span>${channelNode1.nodeName}</h1>
            <#if topContent1??>
               <h2><a href="${contextPath}${topContent1.linkUrl}">${topContent1.subTitle}</a></h2>
            <#assign summary="${topContent1.summary?replace(' ','')}">
			<#assign summary="${summary?replace('　','')}">
			<#assign summary="${summary?replace('&nbsp;','')}">
			<#assign summary="${summary?replace('<.*?>','','r')}">
               <h3><span class="f999"><a href="${contextPath}${topContent1.linkUrl}"> 
                    <#if (summary?length > 50) >
      					${summary?substring(0,50)}...
      				<#else>
      					${summary}
      				</#if></a></span>[<span class="fccc"><a href="${contextPath}${topContent1.linkUrl}">查看详细</a></span>]</h3>
           </#if>        
            <ul>
              <#if channelContent1??>
                 <#list channelContent1 as x>
                    <li><span class="dateright">${x.addDate?string("MM-dd")} </span>&middot;<a href="${contextPath}${x.linkUrl}">${x.subTitle}</a></li>
                    <#if x_index == 9> <#break></#if>
                </#list>          
              </#if>
            </ul>
            </#if>
          </div>
        </div>
        <div class="clear"></div>
        <div class="newsmain">
          <div class="newsmainl">
            <#if channelNode2??>
            <h1 class="infoh1"><span class="more"><a href="${contextPath}${channelNode2.linkUrl}"><img src="images/more.gif" width="33" height="5" /></a></span>${channelNode2.nodeName}</h1>
            <#if topContent2??>
              <h2><a href="${contextPath}${topContent2.linkUrl}">${topContent2.subTitle}</a></h2>
             <#assign summary="${topContent2.summary?replace(' ','')}">
			<#assign summary="${summary?replace('　','')}">
			<#assign summary="${summary?replace('&nbsp;','')}">
			<#assign summary="${summary?replace('<.*?>','','r')}">
              <h3><span class="f999"><a href="${contextPath}${topContent2.linkUrl}">
                   <#if (summary?length > 50) >
      					${summary?substring(0,50)}...
      				<#else>
      					${summary}
      				</#if></a></span>[<span class="fccc"><a href="${contextPath}${topContent2.linkUrl}">查看详细</a></span>]</h3>
            </#if>           
            <ul>
             <#if channelContent2??>
                 <#list channelContent2 as x>
                    <li><span class="dateright">${x.addDate?string("MM-dd")}</span>&middot;<a href="${contextPath}${x.linkUrl}">${x.subTitle}</a></li>
                    <#if x_index == 9> <#break></#if>
              </#list>            
             </#if>
            </ul>
            </#if>
          </div>
          <div class="newsmainl">
          <#if channelNode3??>
            <h1 class="infoh1"><span class="more"><a href="${contextPath}${channelNode3.linkUrl}"><img src="images/more.gif" width="33" height="5" /></a></span>${channelNode3.nodeName}</h1>
            <#if topContent3??>
              <h2><a href="${contextPath}${topContent3.linkUrl}">${topContent3.subTitle}</a></h2>
            <#assign summary="${topContent3.summary?replace(' ','')}">
			<#assign summary="${summary?replace('　','')}">
			<#assign summary="${summary?replace('&nbsp;','')}">
			<#assign summary="${summary?replace('<.*?>','','r')}">
              <h3><span class="f999"><a href="#"> <#if (summary?length > 50) >
      					${summary?substring(0,50)}...
      				<#else>
      					${summary}
      				</#if></a></span>[<span class="fccc"><a href="${contextPath}${topContent3.linkUrl}">查看详细</a></span>]</h3>
            </#if>              
            <ul>
              <#if channelContent3??>
                 <#list channelContent3 as x>
                    <li><span class="dateright">${x.addDate?string("MM-dd")}</span>&middot;<a href="${contextPath}${x.linkUrl}">${x.subTitle}</a></li>
                    <#if x_index == 9> <#break></#if>
              </#list>         
            </#if>
            </ul>
            </#if>
          </div>
        </div>
        <div class="clear"></div>
      </div>
      <!--右侧栏开始-->
      <div class="newsinfor">
        <div class="right">
          <h1 class="infoh1"><span class="more"><img src="images/more.gif" width="33" height="5" /></span>域名行业新闻</h1>
          <ul>
            <li>&middot;<a href="#">百度“百伯”两年历程 域名频频11</a></li>
          </ul>
        </div>
        <div class="ad2"><img src="images/ad2.jpg" width="268" height="170"></div>
        <div class="right">
          <h1 class="infoh1"><span class="more"><img src="images/more.gif" width="33" height="5" /></span>域名行业新闻</h1>
          <ul>
            <li>&middot;<a href="#">百度“百伯”两年历程 域名频频12</a></li>
          </ul>
        </div>
      </div>
      <div class="clear"></div>
    </div>
   <!--正文二开始-->
    <div class="newsinfo">
      <div class="newsinfol">
        <div class="newsmain">
          <div class="newsmainl">
            <#if channelNode4??>
            <h1 class="infoh1"><span class="more"><a href="${contextPath}${channelNode4.linkUrl}"><img src="images/more.gif" width="33" height="5" /></a></span>${channelNode4.nodeName}</h1>
            <#if topContent4??>
            <h2><a href="#">${topContent4.subTitle}</a></h2>
            <#assign summary="${topContent4.summary?replace(' ','')}">
			<#assign summary="${summary?replace('　','')}">
			<#assign summary="${summary?replace('&nbsp;','')}">
			<#assign summary="${summary?replace('<.*?>','','r')}">
            <h3><span class="f999"><a href="${contextPath}${topContent4.linkUrl}">
                   <#if (summary?length > 50) >
      					${summary?substring(0,50)}...
      				<#else>
      					${summary}
      				</#if></a></span>[<span class="fccc"><a href="${contextPath}${topContent4.linkUrl}">查看详细</a></span>]</h3>
                   </#if>   
            <ul>
               <#if channelContent4??>
                 <#list channelContent4 as x>
                    <li><span class="dateright">${x.addDate?string("MM-dd")}</span>&middot;<a href="${contextPath}${x.linkUrl}"><a href="${contextPath}${x.linkUrl}">${x.subTitle}</a></li>
                    <#if x_index == 9> <#break></#if>
              </#list> 
             </#if>
            </ul>
            </#if>
          </div>
          <div class="newsmainl">
          <#if channelNode5??>
            <h1 class="infoh1"><span class="more"><a href="${contextPath}${channelNode5.linkUrl}"><img src="images/more.gif" width="33" height="5" /></a></span>${channelNode5.nodeName}</h1>
            <#if topContent5??>
            <h2><a href="#">${topContent5.subTitle}</a></h2>
            <#assign summary="${topContent5.summary?replace(' ','')}">
			<#assign summary="${summary?replace('　','')}">
			<#assign summary="${summary?replace('&nbsp;','')}">
			<#assign summary="${summary?replace('<.*?>','','r')}">
            <h3><span class="f999"><a href="${contextPath}${topContent5.linkUrl}">
                   <#if (summary?length > 50) >
      					${summary?substring(0,50)}...
      				<#else>
      					${summary}
      				</#if></a></span>[<span class="fccc"><a href="${contextPath}${topContent5.linkUrl}">查看详细</a></span>]</h3>
      		       </#if>  
            <ul>
              <#if channelContent5??>
                 <#list channelContent5 as x>
                    <li><span class="dateright">${x.addDate?string("MM-dd")}</span>&middot;<a href="${contextPath}${x.linkUrl}">${x.subTitle}</a></li>
                    <#if x_index == 9> <#break></#if>
              </#list>  
              </#if>  
            </ul>
            </#if>  
          </div>
          </div>

        <div class="clear"></div> 
        <div class="newsmain">
          <div class="newsmainl">
           <#if channelNode6??>
            <h1 class="infoh1"><span class="more"><a href="${contextPath}${channelNode6.linkUrl}"><img src="images/more.gif" width="33" height="5" /></a></span>${channelNode6.nodeName}</h1>
            <#if topContent6??>
            <h2><a href="${contextPath}${topContent6.linkUrl}">${topContent6.subTitle}</a></h2>
            <#assign summary="${topContent6.summary?replace(' ','')}">
			<#assign summary="${summary?replace('　','')}">
			<#assign summary="${summary?replace('&nbsp;','')}">
			<#assign summary="${summary?replace('<.*?>','','r')}">
            <h3><span class="f999"><a href="${contextPath}${topContent6.linkUrl}">
                   <#if (summary?length > 50) >
      					${summary?substring(0,50)}...
      				<#else>
      					${summary}
      				</#if></a></span>[<span class="fccc"><a href="${contextPath}${topContent6.linkUrl}">查看详细</a></span>]</h3>
           </#if> 
            <ul>
              <#if channelContent6??>
                 <#list channelContent6 as x>
                    <li><span class="dateright">${x.addDate?string("MM-dd")}</span>&middot;<a href="${contextPath}${x.linkUrl}">${x.subTitle}</a></li>
                    <#if x_index == 9> <#break></#if>
                 </#list> 
             </#if>
            </ul>
            </#if>
          </div>
          <div class="newsmainl">
            <#if channelNode7??>
            <h1 class="infoh1"><span class="more"><a href="${contextPath}${channelNode0.linkUrl}"><img src="images/more.gif" width="33" height="5" /></a></span>${channelNode7.nodeName}</h1>
            <#if topContent7??>
            <h2><a href="${contextPath}${topContent7.linkUrl}">${topContent7.subTitle}</a></h2>
            <#assign summary="${topContent7.summary?replace(' ','')}">
			<#assign summary="${summary?replace('　','')}">
			<#assign summary="${summary?replace('&nbsp;','')}">
			<#assign summary="${summary?replace('<.*?>','','r')}">
            <h3><span class="f999"><a href="${contextPath}${topContent7.linkUrl}">
                   <#if (summary?length > 50) >
      					${summary?substring(0,50)}...
      				<#else>
      					${summary}
      				</#if></a></span>[<span class="fccc"><a href="${contextPath}${topContent7.linkUrl}">查看详细</a></span>]</h3>
           </#if> 
            <ul>
              <#if channelContent7??>
                 <#list channelContent7 as x>
                    <li><span class="dateright">${x.addDate?string("MM-dd")}</span>&middot;<a href="${contextPath}${x.linkUrl}">${x.subTitle}</a></li>
                    <#if x_index == 9> <#break></#if>
                 </#list> 
             </#if>
            </ul>  
           </#if>
          </div>
        </div>
        <div class="clear"></div>
      </div>
      <!--右侧栏开始-->
      <div class="newsinfor">
        <div class="right">
          <h1 class="infoh1"><span class="more"><img src="images/more.gif" width="33" height="5" /></span>域名行业新闻</h1>
          <ul>
            <li>&middot;<a href="#">百度“百伯”两年历程 域名频频</a></li>
          </ul>
        </div>
        <div class="ad2"><img src="images/ad3.jpg" width="268" height="170"></div>
        <div class="right">
          <h1 class="infoh1"><span class="more"><img src="images/more.gif" width="33" height="5" /></span>域名行业新闻</h1>
          <ul>
            <li>&middot;<a href="#">百度“百伯”两年历程 域名频频</a></li>
          </ul>
        </div>
      </div>
      <div class="clear"></div>
    </div>
    <!--资讯结束--> 
    <!--友情链接开始-->
    <div class="links">
      <h1><span class="more"><img src="images/more.gif" width="33" height="5" /></span>域名新闻</h1>
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
  <!--主要部分结束--> 
 <#include "common/footer.ftl">
</div>
</body>
</html>