package com.zhenjinzi.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.ResourceUtils;


public class WhoisServer {
	private static final Log log = LogFactory.getLog(WhoisServer.class);
    private static Properties whoisServers;
    private static Properties whoisUrls;
    private static final String WHOIS_SERVER_MARK = "Whois Server:";
    private static String DOMAIN_REG;

    static {
    	Date date1 = new Date();
    	buildProperties();
    	Date date2 = new Date();
		System.out.println("static time:"+(date2.getTime()-date1.getTime()));
    }
    
    public static synchronized void buildProperties() {
    }
    
    public static String getDomainRegex() {
		return DOMAIN_REG;
	}

    /**
     * whois查询
     * 
     * @param domain
     * @return 全部whois信息
     * @throws ParseException
     */
	public static String whois(String domain) throws ParseException {
		return whois(domain, true);
	}
	
    /**
     * whois查询
     * 
     * @param domain 域名 
     * @param fully 是否获得全部whois信息
     * @return
     * @throws ParseException
     */
    public static String whois(String domain, boolean fully) throws ParseException {
    	domain = domain.toLowerCase();
    	int index = domain.indexOf(".");
     	String tld = domain.substring(index + 1);
    	if (domain.endsWith("中国")) {
    		domain = domain.substring(0, domain.lastIndexOf("中国")) + "cn";
    	} else if (domain.endsWith("网络") || domain.endsWith("公司")) {
    		domain += ".cn";
    	}
    	String realDomain = PunycodeUtils.encode(domain).toString();
    	boolean transfer = !domain.equals(realDomain);
    	if (transfer && (tld.equals("cn"))) {
    		tld = "中国";
    	}
    	String server = whoisServers.getProperty(tld);
    	System.out.println("server:"+server);
    	while(server == null) {
    		index = domain.indexOf(".", index + 1);
    		if (index == -1) {
    			break;
    		}
    		tld = domain.substring(index + 1);
    		server = whoisServers.getProperty(tld);
    	}
        if (server == null) {
        	log.error("this endwith is not support yet: " + tld);
        	return "";
        }
        String result = null;
        if (server.equals("#")) {
        	log.info("this tld" + tld + " is unused for some reason, use zunmi server replaced.");
        	result = DomainUtils.whoisBak(domain);
        } else {
        	result = basic(server, 43, realDomain);
        	if (fully) {
        		result += moreInfo(result, 43, realDomain);
        	}
        }
        return result;
    }
    
    public static String basic(String whoisServers, int port, String domain) {
    	String[] servers = whoisServers.split("\\|");
    	if (servers.length > 1) {
    		domain = servers[1] + domain;
    	}
    	return socket(servers[0], port, domain);
    }
    
    public static String moreInfo(String basic, int port, String domain) {
    	String server = null;
    	if (basic != null) {
    		int index = basic.lastIndexOf(WHOIS_SERVER_MARK);
    		if (index != -1) {
    			server = basic.substring(index + WHOIS_SERVER_MARK.length(), basic.indexOf("|", index)).trim();
    			String bak = whoisUrls.getProperty(server);
    			if (bak != null) {
    				server = bak;
    			}
    		}
    	}
    	if (server == null) 
    		return "";
    	else 
    		return socket(server, port, domain);
    }
    
    private static String socket(String whoisServer, int port, String domain) {
    	StringBuffer sb = new StringBuffer();
    	Socket socket = null;
    	PrintStream ps = null;
    	BufferedReader br = null;
    	
    	try {
			socket = new Socket();
			socket.setSoTimeout(30000);
			socket.connect(new InetSocketAddress(whoisServer, port));
			
			ps = new PrintStream(socket.getOutputStream());
			ps.print(domain + "\r\n");
			ps.flush();
			br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			String line = null;
			while((line = br.readLine()) != null) {
				sb.append(line).append('|');
			}
		} catch (IOException e) {
		} finally {
			IOUtils.closeQuietly(br);
			IOUtils.closeQuietly(ps);
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
				}
			}
		}
    	return sb.toString();
    }
    
    public static String readWhoisInfo(String source, String[] propertyValues) {
    	int start = source.indexOf(propertyValues[2]);
    	if (start == -1) {
    		return "";
    	}
    	return source.substring(source.indexOf(propertyValues[3], start) + propertyValues[3].length(), source.indexOf(propertyValues[4], start));
    }
    
    public static String loadUrlResource(String resource, String charset) {
    	String result = null;
    	Reader reader = null;
    	StringBuilder out = new StringBuilder();
		try {
			URL url = new URL(resource);
			URLConnection con = url.openConnection();
			con.setConnectTimeout(5000);
			con.connect();
			reader = new InputStreamReader(con.getInputStream(), charset);
			List<?> list = IOUtils.readLines(reader);
			for (Object obj: list) {
				out.append(obj.toString()).append("|");
			}
			if (out.length() > 0) {
				out.deleteCharAt(out.length() - 1);
			}
			result = out.toString();
		} catch (IOException e) {
			log.error("no resource found, location: " + resource, e);
		} finally {
			IOUtils.closeQuietly(reader);
		}
    	return result;
    }
    
	public static void main(String[] args) throws ParseException{
		Date date1 = new Date();
		String result = whois("baidu.com", false);
//	    String[] array = result.split("\\|+");
		//过滤NOTICE减少传输数据量
		 result= result.split("NOTICE:")[0];
		//过滤Whois Server Version 2.0
	     String[] result2 = result.split("information\\.");
	    result = (result2.length == 1)?result2[0]:result2[1];
	    String[] array = result.split("\\|+");
		for (String temp : array) {
			System.out.println("temp:"+temp);
		}
		System.out.println("length:"+array.length);
		System.out.println("result length:"+result.length());
		Date date2 = new Date();
		System.out.println("time:"+(date2.getTime()-date1.getTime()));
	}
  
}
