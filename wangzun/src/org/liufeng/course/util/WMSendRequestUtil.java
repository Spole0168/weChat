package org.liufeng.course.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.liufeng.course.constant.Constants;
import org.json.JSONObject;



/***
 * 请求微盟的API的公共类
 * @author ly
 *
 */
public class WMSendRequestUtil {
	
	public static Logger logger = Logger.getLogger(WMSendRequestUtil.class.getName());
	/**
	 * @param url  请求地址 
	 * @param method  请求方式
	 * @param params 请求参数
	 * @return
	 */
	private static  String sendRequest(String url,String method,Map<String,Object> params){
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
			 // 设置是否向connection输出，因为这个是post请求，参数要放在
	        // http正文内，因此需要设为true
			huc.setDoOutput(true);
	        // Read from the connection. Default is true.
			huc.setDoInput(true);
			/**POST 不适用缓存*/
			huc.setUseCaches(false);
			/**设置请求参数*/
			huc.setInstanceFollowRedirects(true);
		    // 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
		    // 意思是正文是urlencoded编码过的form参数，下面我们可以看到我们对正文内容使用URLEncoder.encode
		        // 进行编码
			huc.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
			 // 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，
	        // 要注意的是connection.getOutputStream会隐含的进行connect。
			huc.connect();
	        DataOutputStream out = new DataOutputStream(huc .getOutputStream());
	        // The URL-encoded contend
	        // 正文，正文内容其实跟get的URL中 '? '后的参数字符串一致
			StringBuffer sbb= new StringBuffer();
			if(params!=null&&params.size()>0){
				Iterator<String> it=params.keySet().iterator();
				int i=0;
				while(it.hasNext()){
					String key=it.next();
					sbb.append(key).append("=").append(params.get(key)).append("&");
//					huc.addRequestProperty(key, params.get(key)+"");
				}
			}
			String strParam="";
			if(sbb.length()>0){
				strParam=sbb.toString().substring(0, sbb.length()-1);
			}
			System.out.println(strParam);
		    out.writeBytes(strParam);
	        out.flush();
	        out.close(); 
	        
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
	 * GetScoreBill (获取微盟积分流水)
	 * @param offset 微盟积分流水序号
	 * @param size    读取条数
	 * @return
	 */
	public static JSONArray getMenbersCard(String offset,String size){
		JSONArray array=null;
		try{
			String url     = Constants.WM_GETMEMBER_CARDS.replaceAll("ACCESS_TOKEN", SendRequestUtil.getWMAccessToken("weimob"));
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("offset", offset);
			params.put("size", size);
		
			String temp = sendRequest(url,"post",params);
			System.out.println(temp);
			array = new JSONArray(temp);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return array;
	}
	private static String getCurrentDate(){
		Date now  = new Date(System.currentTimeMillis());
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(now);
	}
	/***
	 *   通过微盟提供的接口，获取openid
	 *   grant_type=authorization_code&client_id=CLIENT_ID&client_secret=CLIENT_SECRET&code=CODE
	 * @param code 
	 * @return
	 */
	public static void getOpenIdbyCode(String code){
		JSONObject array=null;
		try{//.replaceAll("CLIENT_ID",Constants.WM_APP_ID).replaceAll("CLIENT_SECRET", Constants.WM_SECRET).replaceAll("CODE", code)
			String url     = Constants.WM_GET_OPENIDS;
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("grant_type", "authorization_code");
			params.put("client_id", Constants.WM_APP_ID);
			params.put("client_secret",  Constants.WM_SECRET);
			params.put("code", code);
			logger.error(url);
			String temp = sendRequest(url,"post",params);
			System.out.println(getCurrentDate()+"WMSendRequestUtil.getOpenIdbyCode"+temp);
			logger.info(temp);
			array = new JSONObject(temp);
			saveOpenidAndcode(array,code);
		}catch(Exception e){
			e.printStackTrace();
		}
//		return array;
	}
	
	
	
	private static int saveOpenidAndcode(JSONObject object,String code) {
		String sql = "insert into yzy_openidcode(opencode,  openid,create_date) values(?, ?,?)";
		MySQLUtil mysqlUtil = new MySQLUtil();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = mysqlUtil.getConn(null);
			ps = conn.prepareStatement(sql);
			ps.setString(1,code );
			ps.setString(2, object.getString("openid"));
			ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			int num = ps.executeUpdate();
			return num;
		}catch(Exception e){
			e.printStackTrace();
//			try {
//				conn.rollback();
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
		}finally{
//			try {
//				conn.commit();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
			MySQLUtil.releaseResources(conn, ps, rs);
		}
		return 0;
	}

	public static void main(String[] args) {
//		getMenbersCard("1","11");
//		String code="22343086839b783f76a64b80166e7311f47b2377";
//		
//		String code="a192bca3af4d570def78a689565dccd770820e23";
//		getOpenIdbyCode(code);
		String jsonStr="{\"access_token\":\"d448081e039e3312e51264a6fb5d82cb4718e710\",\"expires_in\":7200,\"scope\":\"snsapi_base\",\"refresh_token\":\"a428fadf85125db0d703daa575efcf77ce98548a\",\"openid\":\"ofuotwYhqsiiUYReKzdtD_KzlxRE\"}";
		JSONObject array =null;
		try {
			array= new JSONObject(jsonStr);
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		try {
			Object  obj = array.get("openid");
			System.out.println(obj);
		} catch (JSONException e) {
			e.printStackTrace();
		}
//		HashMap<String,String> params = new HashMap<String, String>();
//		params.put("anc", "qwer");
//		params.put("an1c", "qwer1");
//		params.put("anc2", "qwer2");
//		StringBuffer sbb= new StringBuffer();
//		if(params!=null&&params.size()>0){
//			Iterator<String> it=params.keySet().iterator();
//			int i=0;
//			while(it.hasNext()){
//				String key=it.next();
//				sbb.append(key).append("=").append(params.get(key)).append("&");
////				huc.addRequestProperty(key, params.get(key)+"");
//			}
//		}
//		System.out.println(sbb.toString().substring(0, sbb.length()-1));
	}
	
}
