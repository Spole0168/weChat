<!doctype html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${node.nodeName}_尊米网</title>
<meta charset="utf-8"/>
<meta name="description" content="<#if node.description??>${node.description}</#if>" />
<meta name="keywords" content="<#if node.description??>${node.keywords}</#if>" />
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
  <!--位置开始-->
  <div class="gk">
    <div class="gk2">
       <div class="gkl"><span class="fEB6C00">您的位置：</span><a href="#" target="_blank">尊米首页</a> > <a href="${contextPath}${parentNode.linkUrl}" target="_blank">${parentNode.nodeName}</a> > ${node.nodeName}</div>
       <div class="gkr"></div>
       <!--位置结束--> 
    </div>
  </div>
  <!--主体部分开始-->
  <div class="main">
    <!--新闻列表开始-->
    <div class="mainlist">
      <div class="newslist">
        <h1> ${node.nodeName}</h1>
        <#if ImagesContentsFirst??>
        <div class="picnews">
        <#list ImagesContentsFirst as images>
          <dl>
            <dt><a href="${contextPath}${images.linkUrl}" target="_blank"><img src="${contextPath}${images.imageUrl}" width="160" height="100" title="${images.subTitle}"></a></dt>
          </dl>
         </#list>
          <div class="clear"></div>
        </div>
        </#if>
       		<#assign num=domainNewsContents?size>
       		<#assign perNum=20>
        	<#list domainNewsContents as news1>
        	<#assign dateStr="${news1.addDate}">
        	<#assign dateStr=dateStr?substring(0,dateStr?index_of(' '))>
            <#if (news1_index%perNum==0)&&(news1_index!=0)>
        	<div class="clear"></div>  
        	 </ul>   
        	</#if>   
        	<#if (news1_index%perNum==0)>
        	<ul>
        	</#if>
        	<li><span class="dateright">${dateStr}</span>&middot;<a href="${contextPath}${news1.linkUrl}" target="_blank">${news1.subTitle}</a></li>
        	<#if news1_index==num-1>
        	<div class="clear"></div>  
        	 </ul>   
        	</#if>
        	</#list>
        <#--
        <div class="picnews">
         <#list ImagesContentsSecond as imagesSecond>
          <dl>
            <dt><img src="${contextPath}/images/tem1.jpg"" width="160" height="100"></dt>
            <dd><a href="#">${imagesSecond.subTitle}</a></dd>
          </dl>
         </#list>
          <div class="clear"></div>
        </div>
        -->
        <div class="page">
       
        <a href="${contextPath}${firstPage}">首页</a>|<a href="${contextPath}${prev}">上一页</a>
        <#assign firstPosition=nowPage-5><!-- 第一循环位置变量声明,5很重要 int a=5-->
        <#if firstPosition<=0>           <!--改变一页显示1 2 3 4 5 6 7 8 9-->
        <#assign firstPosition=1>        <!--就改变-->
        </#if>
        <#assign lastPosition=firstPosition+10><!--循环变量的最后声明,10很重要 int b=10 ；b=2a-->
        <#if totalPageNum<lastPosition>
        <#assign lastPosition=totalPageNum>
        </#if>
        <#list allUrl as item>
        	<#assign index=item_index+1>
		     <#if (item_index+1>=firstPosition)>
		        <#if (item_index+1==nowPage)>
		       		 ${nowPage}&nbsp;
		        <#else>
		     		 <a href="${contextPath}${item}">${item_index+1}</a>&nbsp;
		     	</#if>
		     </#if>
		     <#if (item_index+1>=lastPosition)> <#break>
		     </#if>
        </#list>
        <a href="${contextPath}${next}">下一页</a>|<a href="${contextPath}${lastPage}">末页</a> 
        <select name='sldd' style='width:54px' onchange='location.href=this.options[this.selectedIndex].value;'>
         	<#list allUrl as per>
        		<#if per_index==nowPage-1>
        			<option value="${contextPath}${per}" selected="selected">${nowPage}</option>
        		<#else>
        			<option value="${contextPath}${per}">${per_index+1}</option>
        		</#if>
        	</#list>
        </select>
                      共${totalPageNum}页${totalNewsNum}条
        </div>
      </div>
      <!--右侧栏开始-->
      <div class="sider">
        <div class="right">
          <h1 class="infoh1"><span class="more"><img src="${contextPath}/images/more.gif" width="33" height="5" /></span>域名行业新闻</h1>
          <ul>
            <li>&middot;<a href="#">百度“百伯”两年历程 域名频频</a></li>
          </ul>
        </div>
        <div class="ad2"><img src="${contextPath}/images/ad2.jpg" width="268" height="170"></div>
        <div class="right">
          <h1 class="infoh1"><span class="more"><img src="${contextPath}/images/more.gif" width="33" height="5" /></span>域名行业新闻</h1>
          <ul>
            <li>&middot;<a href="#">百度“百伯”两年历程 域名频频</a></li>
          </ul>
        </div>
        <div class="right">
          <h1 class="infoh1"><span class="more"><img src="${contextPath}/images/more.gif" width="33" height="5" /></span>域名行业新闻</h1>
          <ul>
            <li>&middot;<a href="#">百度“百伯”两年历程 域名频频</a></li>
          </ul>
        </div>
        <div class="ad2"><img src="${contextPath}/images/ad2.jpg" width="268" height="170"></div>
        <div class="right">
          <h1 class="infoh1"><span class="more"><img src="${contextPath}/images/more.gif" width="33" height="5" /></span>域名行业新闻</h1>
          <ul>
            <li>&middot;<a href="#">百度“百伯”两年历程 域名频频</a></li>
          </ul>
        </div>
      </div>
      <!--右侧栏结束-->
      <div class="clear"></div>
    </div>
    <!--新闻列表结束-->
    <#include "common/blogroll.ftl">
  </div>
  <!--主体部分结束--> 
 <#include "common/footer.ftl">
  
</div>
</body>
</html>