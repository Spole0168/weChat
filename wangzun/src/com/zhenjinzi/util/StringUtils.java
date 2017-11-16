package com.zhenjinzi.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	
	//判断某字符串是否为空
	public static boolean Empty(String str){
		return str==null || str.trim().equals("");
	}
	
	//判断一个字符串是否为整形数字
	public static boolean isNumeric(String str){
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		return isNum.matches();
	} 
	/**
	 * 获取指定长度字符串
	 * @param str初始字符串
	 * @param startindex开始位置
	 * @param endindex结束位置
	 * @return
	 */
	public String subString(String str,int startindex,int endindex)
	{
		return str.substring(startindex, endindex);	
	}
	/**
	 * 获取指定长度字符串
	 * @param str初始字符串
	 * @param beginindex开始位置
	 * @return
	 */
	public String subString(String str,int beginindex)
	{
		return str.substring(beginindex);
	}
	/**
	 * 连接两个字符串并返回
	 * @param str1字符串1
	 * @param str2字符串2
	 * @return
	 */
	public String conCat(String str1,String str2)
	{
		return str1.concat(str2);
	}
	/**
	 * 替换字符串中的指定字符
	 * @param str初始字符串
	 * @param original指定字符
	 * @param replacement替换字符
	 * @return
	 */
	public String rePlace(String str,char original,char replacement)
	{
		return str.replace(original, replacement);
	}
	 /**
     * 替换字符串
     * 
     * @since 1.1
     * @param strSc
     *            需要进行替换的字符串
     * @param oldStr
     *            源字符串
     * @param newStr
     *            替换后的字符串
     * @return 替换后对应的字符串
     */
    public static String replace(String strSc, String oldStr, String newStr) {
        String ret = strSc;
        if (ret != null && oldStr != null && newStr != null) {
            ret = strSc.replaceAll(oldStr, newStr);
        }
        return ret;
    }

    /**
     * 替换字符串，修复java.lang.String类的replaceAll方法时第一参数是字符串常量正则时(如："address".
     * replaceAll("dd","$");)的抛出异常：java.lang.StringIndexOutOfBoundsException:
     * String index out of range: 1的问题。
     * 
     * @since 1.2
     * @param strSc
     *            需要进行替换的字符串
     * @param oldStr
     *            源字符串
     * @param newStr
     *            替换后的字符串
     * @return 替换后对应的字符串
     */
    public static String replaceAll(String strSc, String oldStr, String newStr) {
        int i = -1;
        while ((i = strSc.indexOf(oldStr)) != -1) {
            strSc = new StringBuffer(strSc.substring(0, i)).append(newStr)
                    .append(strSc.substring(i + oldStr.length())).toString();
        }
        return strSc;
    }

    /**
     * 将字符串转换成HTML格式的字符串
     * 
     * @since 1.1
     * @param str
     *            需要进行转换的字符串
     * @return 转换后的字符串
     */
    public static String toHtml(String str) {
        String html = str;
        if (str == null || str.length() == 0) {
            return "";
        } else {
            html = replace(html, "&", "&amp;");
            html = replace(html, "<", "&lt;");
            html = replace(html, ">", "&gt;");
            html = replace(html, "\r\n", "\n");
            html = replace(html, "\n", "<br>\n");
            html = replace(html, "\"", "&quot;");
            html = replace(html, " ", "&nbsp;");
            return html;
        }
    }

    /**
     * 将HTML格式的字符串转换成常规显示的字符串
     * 
     * @since 1.1
     * @param str
     *            需要进行转换的字符串
     * @return 转换后的字符串
     */
    public static String toText(String str) {
        String text = str;
        if (str == null || str.length() == 0) {
            return "";
        } else {
            text = replace(text, "&amp;", "&");
            text = replace(text, "&lt;", "<");
            text = replace(text, "&gt;", ">");
            text = replace(text, "<br>\n", "\n");
            text = replace(text, "<br>", "\n");
            text = replace(text, "&quot;", "\"");
            text = replace(text, "&nbsp;", " ");
            return text;
        }
    }

    /**
     * 将一字符串数组以某特定的字符串作为分隔来变成字符串
     * 
     * @since 1.0
     * @param strs
     *            字符串数组
     * @param token
     *            分隔字符串
     * @return 以token为分隔的字符串
     */
    public static String join(String[] strs, String token) {
        if (strs == null)
            return null;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < strs.length; i++) {
            if (i != 0)
                sb.append(token);
            sb.append(strs[i]);
        }
        return sb.toString();
    }

    /**
     * 将一字符串以某特定的字符串作为分隔来变成字符串数组
     * 
     * @since 1.0
     * @param str
     *            需要拆分的字符串("@12@34@56")
     * @param token
     *            分隔字符串("@")
     * @return 以token为分隔的拆分开的字符串数组
     */
    public static String[] split(String str, String token) {
        String temp = str.substring(1, str.length());
        return temp.split(token);
    }

    /**
     * 验证字符串合法性
     * 
     * @since 1.0
     * @param str
     *            需要验证的字符串
     * @param test
     *            非法字符串（如："~!#$%^&*()',;:?"）
     * @return true:非法;false:合法
     */
    public static boolean check(String str, String test) {
        if (str == null || str.equals(""))
            return true;
        boolean flag = false;
        for (int i = 0; i < test.length(); i++) {
            if (str.indexOf(test.charAt(i)) != -1) {
                flag = true;
                break;
            }
        }
        return flag;
    }
    /**
     * 将数值型字符串转换成Integer型
     * 
     * @since 1.0
     * @param str
     *            需要转换的字符型字符串
     * @param ret
     *            转换失败时返回的值
     * @return 成功则返回转换后的Integer型值；失败则返回ret
     */
    public static Integer String2Integer(String str, Integer ret) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return ret;
        }
    }

    /**
     * 将数值型转换成字符串
     * 
     * @since 1.0
     * @param it
     *            需要转换的Integer型值
     * @param ret
     *            转换失败的返回值
     * @return 成功则返回转换后的字符串；失败则返回ret
     */
    public static String Integer2String(Integer it, String ret) {
        try {
            return Integer.toString(it);
        } catch (NumberFormatException e) {
            return ret;
        }
    }

    /**
     * 比较两字符串大小(ASCII码顺序)
     * 
     * @since 1.1
     * @param str1
     *            参与比较的字符串1
     * @param str2
     *            参与比较的字符串2
     * @return str1>str2:1;str1<str2:-1;str1=str2:0
     */
    public static int compare(String str1, String str2) {//
        if (str1.equals(str2)) {
            return 0;
        }
        int str1Length = str1.length();
        int str2Length = str2.length();
        int length = 0;
        if (str1Length > str2Length) {
            length = str2Length;
        } else {
            length = str1Length;
        }
        for (int i = 0; i < length; i++) {
            if (str1.charAt(i) > str2.charAt(i)) {
                return 1;
            }
        }
        return -1;
    }


    /**
     * 将字符串的首字母改为大写
     * 
     * @since 1.2
     * @param str
     *            需要改写的字符串
     * @return 改写后的字符串
     */
    public static String firstToUpper(String str){
        return str.substring(0,1).toUpperCase()+str.substring(1);
    }
	/**
	 * 解析逗号和冒号分割的字符串，转成哈希对象
	 * @param str  源串，示例："name:peter,age:20"
	 * @return HashMap<String, String>
	 */
	public static HashMap<String, String> Str2Hash(String str){
		HashMap<String, String> map = new HashMap<String, String>();
		if(str == null || str.trim().equals("") )return map;
		
		String[] parts = str.trim().split(",");
		for (int i = 0; i < parts.length; i++) {
			String part = parts[i];
			String[] item = part.split(":");
			map.put(item[0], item[1]);
		}
		return map;
	}
	public static String Hash2Str(HashMap<String, String> map){
		String str="";
		LinkedList<String> list = new LinkedList<String>(map.keySet());
		Collections.sort(list);
		for (String key : list) {
			 if(str.equals("")){
			    	str = key+":"+map.get(key);
			    }else{
			    	str += ","+key+":"+map.get(key);
			    }
		}
		return str;
	}
	
	/**
	 * utf8字符串的长度
	 * @param str
	 * @return 出错时返回-1
	 */
	public static int uLength(String str){
		try {
			String b = new String(str.getBytes("UTF-8"), "ISO8859_1");
			return b.length();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public static String applyRelativePath(String htmlFolder,String htmlFileName){
		if(htmlFolder.charAt(htmlFolder.length()-1)!='/'){
			htmlFolder+="/";
		}
		return htmlFolder+htmlFileName;
	}
	
	/**
	 * 输入info（whois信息），返回一个时间的字符串String的 Set集合
	 * @param info
	 * @return
	 */
	public static Set<String> getDateString(String info){
		Set<String> dates=new HashSet<String>();
		if(info==null){return null;}
		String[] ss=info.split("\\|");
		for (String da : ss) {
			try{
			     String s=da.substring(da.indexOf("Date:"));
			     String date=s.substring(s.indexOf(":")+1).trim();
			     if(date!=null&&!date.equals("")){
				     dates.add(date);
			     }
			}catch(Exception e){
			}
		}
		return dates;
	}
	public static void main(String[] args) throws ParseException {
		String info=WhoisServer.whois("nihao.me");
//		String[] ss=info.split("\\|");
//		for (String string : ss) {
//			System.out.println(string);
//		}
		Set<String> strs=getDateString(info);
		for (String string : strs) {
			System.out.println(string);
		}
	}
	
	
}
