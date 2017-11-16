package org.liufeng.course.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.liufeng.course.constant.Constants;

/***
 * 请求微信公共的API的公共类
 * @author ly
 *
 */
public class WXSendRequestUtil {
	/**
	 * 
	 * @param url  请求地址 
	 * @param method  请求方式
	 * @param params 请求参数
	 * @return
	 */
	private static  String sendRequestByJson(String url,String method,Map<String,Object> params){
		BufferedReader in = null;  
		InputStreamReader isr = null;
		URL u =null ;
		HttpURLConnection huc = null;
		/**返回的消息内容*/
		StringBuffer sb = new StringBuffer();
		try{
			u= new URL(url);
			huc= (HttpURLConnection) u.openConnection();  
			huc.setRequestMethod(method.toUpperCase());  /**请求方式*/
			huc.setRequestProperty("Content-type", "application/json");
			/**设置请求参数*/
			if(params!=null&&params.size()>0){
				Iterator<String> it=params.keySet().iterator();
				while(it.hasNext()){
					String key=it.next();
					huc.addRequestProperty(key, params.get(key)+"");
				}
			}
			isr=new InputStreamReader(huc.getInputStream());
			in = new BufferedReader(isr);  
			String line;  
			
			while ((line = in.readLine()) != null) {  
				sb.append(line);
				System.out.println(line);  
			}  
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			/**关闭流处理，以及关闭连接处理*/
			try{
				if(in != null){
				    in.close();
				}
				if(isr != null){
				    isr.close();
				}
				if(huc!=null){
					huc.disconnect();
				}
			}catch(Exception ee){
				ee.printStackTrace();
				System.out.println(ee.getMessage());
			}
		}
		return sb.toString();
	}
	
	/***
	 * 发送获得二维码事件
	 * @param flag LS临时二维码，YJ永久二维码                 默认是临时二维码
	 * @param mechineid   32位的无符号整型，饮水机id
	 * @param  604800 过期的秒数   默认7天
	 * @return
	 */
	public static JSONArray getDimCodeBy(String flag,String mechineid){
		JSONArray array=null;
		try{
			String url     = Constants.WX_CREATE_DIM.replaceAll("TOKEN", SendRequestUtil.getWXAccessToken("weixin"));
			Map<String,Object> params = new HashMap<String, Object>();
			
			if(flag!=null && flag.equals("YJ")){
				params.put("action_name", "QR_LIMIT_SCENE");
			}else{
				params.put("action_name", "QR_SCENE");
			}
			Map<String, String> key11 = new HashMap<String, String>(); 
			key11.put("scene_id", mechineid);
			Map<String, Object> key12 = new HashMap<String, Object>(); 
			key12.put("scene", key11);
			params.put("action_info", key12);
		
			String temp = sendRequestByJson(url,"post",params);
			System.out.println(temp);
			array = new JSONArray(temp);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return array;
	}
	
	public static void main(String[] args) {
		getDimCodeBy("LS","222");
	}
	
}
