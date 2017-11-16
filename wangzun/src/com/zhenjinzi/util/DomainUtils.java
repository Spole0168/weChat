package com.zhenjinzi.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DomainUtils {
	private static final Log log=LogFactory.getLog(DomainUtils.class);
	
	public static  int seed=0;
	public static final String NS_DELIMITERS="\r\n|\n|\r|,";
	public static final char DOMAIN_DELIMITER=';';
	public static final int WHOIS_SERVER_PORT=43;
	public static final int DEFAULT_CODE_LENGTH=20;
	public static final String enReg = "(?i)[0-9a-z](([0-9a-z]|\\-?)[0-9a-z])*((\\.)(com|cn|net|org|ac|asia|ah|bj|sh|cq|he|sx|nm|ln|jl|hl|js|zj|fj|jx|sd|ha|hb|hn|gd|gx|hi|sc|gz|yn|xz|sn|gs|qh|nx|xj|tj|tw|hk|mo|gov))";
	public static final String cdnReg = "(?i)[0-9a-z]*([\u4e00-\u9fa5])(([0-9a-z\u4e00-\u9fa5]|\\-?)[0-9a-z\u4e00-\u9fa5]){0,10}((\\.)(cn|中国|中國))";
	public static final String domainReg ="(?i)#ym\\s*([0-9a-z\u4e00-\u9fa5\\-\\.]{1,60})\\s*ym#";
	public static final String regexEmail="\\w{1,}[@][\\w\\-]{1,}([.]([\\w\\-]{1,})){1,3}";

	/**
	 * 返回EPP协议要求的E.164格式电话号码，如果不带国际区号则默认为+86。
	 */
	public static  String getE164Number(String number){
		if(StringUtils.isEmpty(number)){
			return null;
		}
		if(number.charAt(0)=='+'){
			return number.replace('-','.');
		}
		return null;
	}
	/**
	 * 返回16位的唯一事务编号。
	 */
	public static synchronized String getTransactionId(){
		return 16+Long.toString(System.currentTimeMillis(), 36)+'-'+StringUtils.leftPad(Integer.toString(seed++,36), 7,'0');
	}
	public static Set<String> splitStringToSet(String str,String regex){
		if(str!=null){
			Set result=new LinkedHashSet();
			String[] a=str.split(regex);
			for(String s:a){
				if(StringUtils.isNotEmpty(s)){
				result.add(s.trim().toLowerCase());
			   }
			}
			return result;
		}
		return null;
	} 
	/**
	 * 集合set根据插入分隔符转换成字符串
	 * @param set
	 * @param delim
	 * @return
	 */
	public static String mergeSetToString(Set set ,char delim){
		if((set==null)||(set.isEmpty())){
			return null;
		}
		StringBuilder sb=new StringBuilder();
		Iterator it=set.iterator();
		while(it.hasNext()){
			Object object=it.next();
			sb.append(object.toString()).append(delim);
		}
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();
		
	}
	/**
	 * 把字符串content根据regex正则解析成list
	 * @param content
	 * @param regex
	 * @return
	 */
	public static List<String> splitStringToList(String content,String regex){
		if(content!=null){
			List<String> list=new ArrayList<String>();
			String[] strs=content.split(regex);
			for (String s : strs) {
				if(StringUtils.isNotEmpty(s)){
					list.add(s.trim().toLowerCase());
				}
			}
			return list;
		}
		return null;
	}
	/**
	 * 把list作用分隔符转换成字符串
	 * @param list 
	 * @param delim 分隔符
	 * @return
	 */
	public static String mergeListToString(List<String> list,char delim){
		if((list==null)||(list.isEmpty())){
			return null;
		}
		StringBuilder sb=new StringBuilder();
		Iterator<String> it=list.iterator();
		while(it.hasNext()){
			Object object=it.next();
			sb.append(object.toString()).append(delim);
		}
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}

    /**
     * 在zunmi.com上查找whois
     * @param domain
     * @return
     */
	public static String whoisBak(String domain) {
		try {
			domain = PunycodeUtils.encode(domain).toString();
			String ul = "http://www.zunmi.com/whois/?d=" + domain;
			URL url = new URL(ul);
			URLConnection con = url.openConnection();
			con.connect();
			con.setConnectTimeout(5000);
			InputStream is = con.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line).append('\n');
			}
			if (sb.length() > 0) {
				sb.deleteCharAt(sb.length() - 1);
			}
			return sb.toString();
		} catch (Exception e) {
		}
		return "";
	}
	/**
	 * 过滤文章中的域名，加入链接<a/>标签
	 * @param content　内容
	 * @return
	 */
	public static StringBuffer filterDomain(StringBuffer content) {

		Pattern pattern = Pattern.compile(domainReg);
		Matcher matcher = pattern.matcher(content);
		StringBuffer en = new StringBuffer();
		while (matcher.find()) {
			String p1 = matcher.group(1);
			matcher.appendReplacement(en, "<a  class = \"tip\" onMouseOver=\"over(this,'"+p1+"')\" onMouseOut=\"out(this,'"+p1+"')\">"+p1+"</a>");
		}
		matcher.appendTail(en);
		return en;
	
	} 

		
	/**
	 *  过滤邮箱
	 */
	public static Set<String> filterEmail(StringBuffer content) {
		Set<String>  sets=new HashSet<String>();
		Pattern pattern = Pattern.compile(regexEmail);
		Matcher matcher = pattern.matcher(content);
		StringBuffer en = new StringBuffer();
		while (matcher.find()) {
			String p0 = matcher.group();
			sets.add(p0);
		}
		return sets;
	
	} 
//	public static void main(String args[]){
////		 String s ="<p>工信部电信研究院院长曹淑敏aab.com日前透露，在IPV6标准化方面，中国紧跟世界步伐，目前中国通信标准化协会（CCSA）已有超过70项IPV6标准的研究，其中49项已经发布。通过对IPV6标准的确立，我国将结束IP地址匮乏的现状，从IPV4时代向IPV6时代过渡。</p><p>据了解，目前我国采用的是第二代互联网IPV4技术，地址总量在40亿左右。过去IPv4的地址量完全可以满足全球互联网用户及整个应用产业链的需 求，但随着整个互联网的飞速发展，尤其是移动互联网的兴起，使IPv4地址资源呈现枯竭态势。为了更好的解决IP地址分配问题，下一代互联网IPV6技术 应运而生。</p><p>相比IPv4，IPv6的地址容量巨大，其庞大的地址资源能给任何一个物体都能赋予一个IP地址，让其成为互联网的终端，最大限度地推动物联网、泛在网络的发展，从目前的人人交互、人机交互，发展到未来的物物交互。</p><p>按照计划，我国IPv6推进将分三步走。其中2011年-2013年为试商用阶段：启动网络和平台支持IPv6的改造，确定网络及业务过渡方案、现网 商业化试点，基本具备引入IPv6业务的网络条件；2014-2015年为规模商用阶段：IPv4/IPv6网络和业务共存，网络和平台规模改造，业务逐 步迁移，新型应用和用户规模持续扩大；2016-2020年为全面商用阶段：新型应用占据主导，IPv4网络和业务平台逐步退出。</p><p>在标准化方面，中国紧跟世界步伐。据工信部电信研究院院长曹淑敏透露，目前中国通信标准化协会（CCSA）已有致力于超过70项标准的研究，其中49 项已经发布，17项正在研究，6项待发布，涉及安全、网络、资源、应用、传输等方面的标准，&ldquo;尤其是在传输和安全方面有较大创新 （IVI，BIH/PNAT,SAVI），但在传输和应用方面的研究还有待更进一步的突破&rdquo;。</p><p>此前的5月24日，工信部就批准了5项通信行业标准，并于2012年6月1日起开始实施。其中发布的YD/T2395-2012《基于IPV6的下一 代互联网体系架构》以IETF国际标准化组织及国内IPv6相关标准为基础，结合我国运营商网络的具体情况制定，规定了基于IPV6的下一代互联网组网架 构及总体技术要求，包括下一代互联网应具备的服务质量保障、网络可靠性、移动性、安全管理等能力要求。从此，我国将结束IP地址匮乏的现状，从IPV4时 代向IPV6时代过渡。</p><p>网络服务提供商Akamai数据显示，在2011年&ldquo;世界IPv6日&rdquo;，IPv6地址数量为28万，到今年6月6日的&ldquo;世界IPv6日&rdquo;，IPv6地址数量已超过1800万。与之相应的是，IPv6地址的申请数量从2011年的300万激增至2012年的30多亿。</p><p>在中国，根据中国互联网络信息中心(CNNIC)最新数据显示，截至2012年6月26日，三大运营商已完成海量IPv6地址的申请。其中，中国电信 获得了4099/32块地址(&ldquo;块&rdquo;为国际IPv6地址数量单位，1/32块为2的96次方个地址，大约为7.9乘10的28次方个)，中国移动和中国联 通则分别获得了4098/32块地址。</p>";
////		 String s ="<p>工信部电信研究院 abc.cn院长曹淑敏日前透露，在IPV6标准化方面工信部电信研究院院长曹淑敏日前透露.";
//		 String s ="<p>工信部电#ym abc.cn  ym#   ab-c.cn   院长曹淑敏日ym#前透露，在IPV6标准化方面工信部电信研究院院长曹淑敏日前透露.";
//
////	     for(int i=0;i<=10;i++){
////	    	  s=s+s;
////	      }
//	      System.out.println("匹配字数:"+s.length());
//	      Date  d1 = new Date();
//	      System.out.println(d1);
////      DomainUtils.filterDomain(new StringBuffer(s));
//	     System.out.println( "main:"+DomainUtils.filterDomain(new StringBuffer(s)));   
//	     Date  d2 = new Date();
//	      System.out.println("匹配字数:"+s.length());
//	     System.out.println("耗时:"+(d2.getTime()-d1.getTime()));
//		
//	}

	
	
}
