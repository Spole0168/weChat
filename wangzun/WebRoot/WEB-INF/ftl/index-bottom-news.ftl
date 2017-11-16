<div class="ad"><img src="images/ad1.jpg" width="1002" height="90" /></div>
<div class="domain">
  <div class="weibo">
    <h1><span class="more"></span>微博</h1>
    <iframe width="100%" height="550" class="share_self"  frameborder="0" scrolling="no" src="http://widget.weibo.com/weiboshow/index.php?language=&width=0&height=550&fansRow=2&ptype=1&speed=0&skin=1&isTitle=0&noborder=1&isWeibo=1&isFans=0&uid=2302316577&verifier=fe26ef74&dpc=1"></iframe>
  </div>
  <div class="mygz">
    <h1><a href="${contextPath}/focus.html" target="_blank"><span class="more"><img src="images/more.gif" width="33" height="5" /></span></a>米友关注</h1>
    <div class="shown">
      <div class="mypic">
        <ul>
          <#list focusImageContents as x>
           <li><a href="${contextPath}${x.linkUrl}" target="_blank"><img src="${contextPath}${x.imageUrl}" title="${x.subTitle}" width="100" height="70" /></a></li>
          </#list>
          <div class="clear"></div>
        </ul>
      </div>
      <div class="mynews">
        <ul>
          <#list focusContents as x>
          <#assign dateStr=x.addDate>
	 	  <#assign dateStr=dateStr?substring(0,dateStr?length-3)>
          <li><span class="dateright">${dateStr}</span>&middot;[<span class="biaoti"><a href="${contextPath}${x.zunmiNode.linkUrl} target="_blank">${x.zunmiNode.nodeName}</a></span>]<a href="${contextPath}${x.linkUrl}" target="_blank">${x.subTitle}</a></li>
          </#list>
        </ul>
      </div>
      <div class="clear"></div>
    </div>
  </div>
  <div class="clear"></div>
</div>