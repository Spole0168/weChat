package org.liufeng.course.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;

import org.json.JSONException;
import org.json.JSONObject;
import org.liufeng.course.constant.Constants;

public class SendRequestUtil {
	
	private static  String sendRequest(String url){
		BufferedReader in = null;  
		InputStreamReader isr = null;
		URL u =null ;
		HttpURLConnection huc = null;
		/**返回的消息内容*/
		StringBuffer sb = new StringBuffer();
		try{
			u= new URL(url);
			huc= (HttpURLConnection) u.openConnection();  
//			huc.set
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
	 * 获得微新的Access_token
	 * String type="weixin";
	 * @return
	 */
	public static  String getWXAccessToken(String type){
		
		String accessToken=getStrngBySQL(type);
		try {
		if(accessToken == null){
			String url     = Constants.ACCEES_TOKEN_WXURL.replaceAll("APPID", Constants.APP_ID).replaceAll("APPSECRET", Constants.SECRET);
			String temp = sendRequest(url);
			JSONObject object = new JSONObject(temp.trim());
			//保存access_token 
			saveAccessToeken(object,type);
			accessToken = object.getString("access_token");
			System.out.println("accessToken:"+accessToken);
		}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return accessToken;
	}

	/***
	 * 获得  微盟的 的Access_token
	 * String type
	 *                    weimob
	 * @return
	 */
	public static  String getWMAccessToken(String type){
		
		String accessToken=getStrngBySQL(type);
		String url     = Constants.WM_ACCESS_TOKEN.replaceAll("APPID", Constants.WM_APP_ID).replaceAll("APPSECRET", Constants.WM_SECRET);
		System.out.println(url);
		try {
		if(accessToken == null){
			String temp = sendRequest(url);
			JSONObject object = new JSONObject(temp.trim());
			JSONObject temp1   =object.getJSONObject("data");
			//保存access_token 
			saveAccessToeken(temp1,type);
			accessToken = temp1.getString("access_token");
			System.out.println("accessToken:"+accessToken);
		}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return accessToken;
	}
	
	private static int  saveAccessToeken(JSONObject object, String type) throws JSONException {
		long now = System.currentTimeMillis();
		Timestamp createDate  = new Timestamp(now);
		int expires_in=0;
		if(type!=null&&type.equals("weimob")){
			expires_in = object.getInt("expire_in");
		}else{
		    expires_in = object.getInt("expires_in");
		}
		Timestamp expiresDate = new Timestamp(now+expires_in*1000);
		
		String accessToken = object.getString("access_token");
		String sql = "insert into commonAccessToken(access_token, at_type, expires_date, create_date) values(?, ?, ?, ?)";
		MySQLUtil mysqlUtil = new MySQLUtil();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = mysqlUtil.getConn(null);
			ps = conn.prepareStatement(sql);
			ps.setString(1, accessToken);
			ps.setString(2, type);
			ps.setTimestamp(3, expiresDate);
			ps.setTimestamp(4, createDate);
			int num = ps.executeUpdate();
			return num;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MySQLUtil.releaseResources(conn, ps, rs);
		}
		return 0;
	}

	/**
	 * 去数据库表中查询access_token
	 * @param type
	 * @return
	 */
	private static String getStrngBySQL(String type) {
		String sql="select access_token,expires_date from commonAccessToken where at_type = '"+type+"' order by create_date desc";
		Connection conn =null;
		Statement stmt    =null;
		ResultSet rs          =null;
		System.out.println(sql);
		try{
			conn  =  MySQLUtil.getConn(null);
			System.out.println("avx");
			stmt   =  conn.createStatement();
			rs       = stmt.executeQuery(sql);
			if(rs.next()){
				String access_token = rs.getString("access_token");
				Timestamp expires_date    = rs.getTimestamp("expires_date");
				long now = System.currentTimeMillis();
				long expiresDate = expires_date.getTime();
				if(now<expiresDate){
					return access_token;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MySQLUtil.releaseResources(conn, stmt, null, rs);
		}
		return null;
	}
	
	public static void main(String[] args) throws JSONException {
		String token=getWMAccessToken("weimob");
		System.out.println(token);
//		String dd=getWXAccessToken();
//		System.out.println(dd);
//		syso
//		String json="{\"access_token\":\"nISg3tWLsYvf-pohJmKLvkw2kqVpxYlDKsKYsm2Zj117Fn31Xrx9BL6zXwY8xJRr_ABkWtdoE-sPAeUezRvhUNld4sPwwYu_MWWxmGHicOOcU7Im0I4QNj4gqaOtBNXAXXKbABAHHU\",\"expires_in\":7200}";
//		JSONObject jsons= new JSONObject(json);
////		saveAccessToeken(jsons,"wexin");
//		getStrngBySQL("wexin");
	}
}
