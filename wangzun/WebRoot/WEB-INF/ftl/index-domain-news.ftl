	<div class="newstop">
		
      <div class="newstopl">
        <div class="newstoplt">
          <div id="loop" class="slideBox">
		    <ul class="items">
		      <#list slideShowContentList as x>
		      <li><a href="${contextPath}${x.linkUrl}" target="_blank" title="${x.subTitle}"><img src="${x.imageUrl}"></a></li>
		      </#list>
		    </ul>
		  </div>
		</div>
        <div class="lastcontent">
       		<h1 class="newh1">最新更新</h1>
	        <ul>
	    	 <#list lastContents as x>
			 <li>&middot;<a href="${contextPath}${x.linkUrl}" target="_blank">${x.subTitle}</a></li>
			 </#list>
	        </ul>
        </div>
      </div>
      <div class="newstopm">
        <div class="headnews">
          <#list topContents as x>
          	<#if x_index==0>
			<#assign summary="${x.summary?replace(' ','')}">
			<#assign summary="${summary?replace('　','')}">
			<#assign summary="${summary?replace('&nbsp;','')}">
			<#assign summary="${summary?replace('<.*?>','','r')}">
      		<h1><a href="${contextPath}${x.linkUrl}" target="_blank">${x.subTitle}</a></h1>
      		<h2><a href="${contextPath}${x.linkUrl}" target="_blank">&nbsp;&nbsp;&nbsp;&nbsp;
      				<#if (summary?length > 100) >
      					${summary?substring(0,100)}...
      				<#else>
      					${summary}
      				</#if>
      			</a>[<a href="${contextPath}${x.linkUrl}" target="_blank">查看全文</a>]
      		</h2>
      		
          	<#else>
      		   	<#if x_index==1><ul class="ultj"></#if>
	            <li><a href="${contextPath}${x.linkUrl}" target="_blank">${x.subTitle}</a></li>
	         	<#if x_index==topContents?size-1></ul></#if>
          	</#if>
		  </#list>    
          <div class="clear"></div>
        </div>
        <h1 class="tjh1">特别推荐</h1>
        <ul class="ulpic">
          <#list specialRecommendHasImgList as x>
          	 <li><a href="${contextPath}${x.linkUrl}" target="_blank"><img src="${contextPath}${x.imageUrl}" width="120" height="80" title="${x.subTitle}" /></a></li>
          </#list>
        </ul>
        <div class="clear"></div>
        <ul class="ultj">
          <#list specialRecommendContents as x>
          	<li>&middot;<a href="${contextPath}${x.linkUrl}" target="_blank">${x.subTitle}</a></li>
          </#list>
        </ul>
        <div class="clear"></div>
      </div>
      <div class="newstopr">
        <div class="ymcx">
          <h1>域名查询</h1>
          <form id="form1" name="form1" method="post" action="${contextPath}/content/whois/domainWhois.action" target="_blank" >
            <table width="100%">
              <tr>
                <td width="21%" align="right"><span class="www">www.</span></td>
                <td width="59%" align="center"><input name="domain" type="text" class="ym" id="textfield" size="20" /></td>
                <td width="20%"><input name="button" type="submit" class="cxbt" id="button" value="查询" /></td>
              </tr>
            </table>
            <script type="text/javascript">
	        function switchTab(ProTag, ProBox) {
	            for (i = 1; i < 5; i++) {
	                if ("tab" + i == ProTag) {
	                    document.getElementById(ProTag).getElementsByTagName("a")[0].className = "on";
	                } else {
	                    document.getElementById("tab" + i).getElementsByTagName("a")[0].className = "";
	                }
	                if ("con" + i == ProBox) {
	                    document.getElementById(ProBox).style.display = "";
	                } else {
	                    document.getElementById("con" + i).style.display = "none";
	                }
	            }
	        }
    		</script>
            <div id="tabContainer">
              <ul>
                <li id="tab1"><a href="#" class="on" onclick="switchTab('tab1','con1');this.blur();return false;"> 英文域名</a></li>
                <li id="tab2"><a href="#" onclick="switchTab('tab2','con2');this.blur();return false;"> 中文域名</a></li>
              </ul>
              <div class="clear"></div>
              <div id="con1">
                <table width="100%">
                  <tr>
                    <td><input name="checkbox" type="checkbox" id="checkbox" checked="checked" />
                      .com</td>
                    <td><input type="checkbox" name="checkbox4" id="checkbox4" />
                      <label for="checkbox4">.net</label></td>
                    <td><input type="checkbox" name="checkbox7" id="checkbox7" />
                      <label for="checkbox7">.cn</label></td>
                  </tr>
                  <tr>
                    <td height="20"><input type="checkbox" name="checkbox2" id="checkbox2" />
                      <label for="checkbox2">.com.cn</label></td>
                    <td><p>
                        <input type="checkbox" name="checkbox5" id="checkbox5" />
                        <label for="checkbox5"></label>
                        .net.cn<br />
                      </p></td>
                    <td><input type="checkbox" name="checkbox8" id="checkbox8" />
                      .org
                      <label for="checkbox8"></label></td>
                  </tr>
                  <tr>
                    <td height="20"><input type="checkbox" name="checkbox3" id="checkbox3" />
                      <label for="checkbox3">.cc</label></td>
                    <td><input type="checkbox" name="checkbox6" id="checkbox6" />
                      <label for="checkbox6">.biz</label></td>
                    <td><input type="checkbox" name="checkbox9" id="checkbox9" />
                      <label for="checkbox9">全选</label></td>
                  </tr>
                </table>
              </div>
              <div id="con2" style="display: none"> 霉运也是一种运气... </div>
            </div>
          </form>
        </div>
        <div class="kjcx">
          <h1>快捷查询</h1>
          <div class="kjc">
            <form id="form2" name="form2" method="post" action="">
              <table width="100%" cellspacing="10">
                <tr>
                  <td height="49" colspan="2"><label for="textfield2"></label>
                    <input name="textfield2" type="text" class="seach" id="textfield2" value="请输入关键字" /></td>
                </tr>
                <tr>
                  <td width="59%"><label for="select"></label>
                    <select name="select" id="select">
                      <option>智能模糊查询 </option>
                    </select></td>
                  <td width="41%"><input type="submit" name="button2" id="button2" value="查询" class="seachbt" /></td>
                </tr>
                <tr>
                  <td colspan="2"><p class="fFF7900">热门搜索：</p>
                    <p><a href="3">到期删除域名</a> <a href="#">过期域名</a> <a href="#">icann 删除列表</a> <a href="#">域名抢注</a> <a href="#">微软</a> <a href="#">CN域名</a> <a href="#">域名到期 </a></p></td>
                </tr>
              </table>
            </form>
          </div>
        </div>
      </div>
      <div class="clear"></div>
    </div>
    <div class="ad"><img src="images/ad1.jpg" width="1002" height="90" /></div>
    <div class="newsm">
      <div class="sider">
        <h1><span class="more"></span>推荐资讯</h1>
        <ul>
        <#list recommendContents as x>
         <li>&middot;<a href="${contextPath}${x.linkUrl}" target="_blank">${x.subTitle}</a></li>
		</#list>
        </ul>
      </div>
      <div class="showinfo">
        <h1><a href="${contextPath}/news.html"><span class="more"><img src="images/more.gif" width="33" height="5" /></span></a>域名新闻</h1>
        <ul>
           <#list domainNewsContents as x>
           <li>&middot;[<span class="biaoti"><a href="${contextPath}${x.zunmiNode.linkUrl}" target="_blank">${x.zunmiNode.nodeName}</a></span>]<a href="${contextPath}${x.linkUrl}" target="_blank">${x.subTitle}</a></li>
		   </#list>
        </ul>
      </div>
      <div class="ranking"> 
        <script type=text/javascript>
		function setTab03Syn ( i )
		{
			selectTab03Syn(i);
		}
	
		function selectTab03Syn ( i )
		{
			switch(i){
				case 1:
				document.getElementById("TabTab03Con1").style.display="block";
				document.getElementById("TabTab03Con2").style.display="none";
				document.getElementById("font1").style.color="#0C96E7";
				document.getElementById("font2").style.color="#0C96E7";
				break;
				case 2:
				document.getElementById("TabTab03Con1").style.display="none";
				document.getElementById("TabTab03Con2").style.display="block";
				document.getElementById("font1").style.color="#0C96E7";
				document.getElementById("font2").style.color="#0C96E7";
				break;
				case 3:
				document.getElementById("TabTab03Con3").style.display="block";
				document.getElementById("TabTab03Con4").style.display="none";
				document.getElementById("font3").style.color="#0C96E7";
				document.getElementById("font4").style.color="#0C96E7";
				break;
				case 4:
				document.getElementById("TabTab03Con3").style.display="none";
				document.getElementById("TabTab03Con4").style.display="block";
				document.getElementById("font3").style.color="#0C96E7";
				document.getElementById("font4").style.color="#0C96E7";
				break;
				
			}
		}
		</script>
        <div id="bg" class="xixi1">
          <div id="font1" class="tab1" onMouseOver="setTab03Syn(1);document.getElementById('bg').className='xixi1'">周排行</div>
          <div id="font2" class="tab2" onMouseOver="setTab03Syn(2);document.getElementById('bg').className='xixi2'">月排行</div>
        </div>
        <div id=TabTab03Con1>
          <ul>
	          <#list hisByWeekContents as x>
			  <li><a href="${contextPath}${x.linkUrl}" target="_blank">${x.subTitle}</a></li>
			  </#list>
          </ul>
        </div>
        <div id=TabTab03Con2 style="display:none">
          <ul>
          <#list  hisByMonthContents as x>
			<li><a href="${contextPath}${x.linkUrl}" target="_blank" >${x.subTitle}</a></li>
		  </#list>
          </ul>
        </div>
      </div>
      <div class="clear"></div>
    </div>
    <div class="newsm">
      <div class="sider">
        <h1><span class="more"><img src="images/more.gif" width="33" height="5" /></span>推荐资讯</h1>
        <ul>
        <#list recommendContents as x>
         <li>&middot;<a href="${contextPath}${x.linkUrl}" target="_blank">${x.subTitle}</a></li>
		</#list>
        </ul>
      </div>
      <div class="showinfo">
        <h1><a href="${contextPath}/study.html" target="_blank"><span class="more"><img src="images/more.gif" width="33" height="5" /></span></a>域名知识</h1>
        <ul>
          <#list domainKnowledges as x>
           <li>&middot;[<span class="biaoti"><a href="${contextPath}${x.zunmiNode.linkUrl}" target="_blank">${x.zunmiNode.nodeName}</a></span>]<a href="${contextPath}${x.linkUrl}" target="_blank">${x.subTitle}</a></li>
          </#list>
        </ul>
      </div>
      <div class="ranking">
        <div id="bg2" class="xixi3">
          <div id="font3" class="tab3" onMouseOver="setTab03Syn(3);document.getElementById('bg2').className='xixi3'">一口价排行</div>
          <div id="font4" class="tab4" onMouseOver="setTab03Syn(4);document.getElementById('bg2').className='xixi4'">求购排行</div>
        </div>
        <div id=TabTab03Con3>
          <ul>
            <li><a href="#"></a></li>
          </ul>
        </div>
        <div id=TabTab03Con4 style="display:none">
          <ul>
            <li><a href="#"></a></li>
          </ul>
        </div>
      </div>
      <div class="clear"></div>
    </div>
    <div class="clear"></div>
    <div class="ad"><img src="images/ad1.jpg" width="1002" height="90" /></div>