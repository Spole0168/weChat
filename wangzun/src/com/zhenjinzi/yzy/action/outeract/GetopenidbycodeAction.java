package com.zhenjinzi.yzy.action.outeract;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.liufeng.course.util.MySQLUtil;
import org.liufeng.course.util.WMSendRequestUtil;

import com.fire.modules.web.struts2.Struts2Utils;
import com.zhenjinzi.yzy.action.BaseAction;

/***
 * 根据code获取用户的openid
 * @author ly
 *
 */

public class GetopenidbycodeAction extends BaseAction{

	public static Logger logger = Logger.getLogger(GetopenidbycodeAction.class.getName());
	
	private String code;
	
	public String getOpenid(){
//		JSONObject json = WMSendRequestUtil.getOpenIdbyCode(code);
		WMSendRequestUtil.getOpenIdbyCode(code);
		String openid = null;
		try {
//			logger.info("GetopenidbycodeAction:"+json.toString());
			System.out.println("code:"+code);
			MySQLUtil mysqlUtil = new MySQLUtil();
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sql="select openid from yzy_openidcode where opencode=?";
			try {
				conn = mysqlUtil.getConn(null);
				ps = conn.prepareStatement(sql);
				ps.setString(1, code);
				rs=ps.executeQuery();
				if(rs.next()){
					openid = rs.getString("openid").trim();
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				MySQLUtil.releaseResources(conn, ps, rs);
			}
			System.out.println("GetopenidbycodeAction  syso::"+openid);
			
			Struts2Utils.renderJson("{\"statusCode\":\"300\",\"openid\":\""+openid+"\"}");
			return null;
//			if(json != null && json.get("openid") !=null ){
//				openid= json.get("openid")+"";
////				logger.info("GetopenidbycodeAction  openid:"+openid);
//			System.out.println("openid:"+"{\"statusCode\":\"300\",\"openid\":\""+openid.trim()+"\"}");
//			throw new RuntimeException("openid"+openid);
////			Struts2Utils.renderJson("{\"statusCode\":\"300\",\"openid\":\""+openid.trim()+"\"}");
////			    return null;
//			}else{
//				 openid = json.get("error")+"";
//				logger.info("GetopenidbycodeAction  error:"+openid);
//				System.out.println("error:"+openid);
//			    Struts2Utils.renderJson("{\"statusCode\":\"300\",\"message\":\""+openid+"\"}");
//			    return null;
//			}
////			  Struts2Utils.renderJson(json.toString());
////			  Struts2Utils.renderJson("{\"statusCode\":\"200\",\"messages\":"+json.toString()+"}");
		} catch(RuntimeException w){
//			Struts2Utils.renderJson("{\"statusCode\":\"300\",\"message\":\""+openid+"\"}");
		}catch (Exception e) {
//			  logger.error("GetopenidbycodeAction:"+e.getMessage(), e);
			  String error=e.getMessage();
				logger.info("JSONException  :"+error);
			  Struts2Utils.renderJson("{\"statusCode\":\"300\",\"message\":\""+openid+"\"}");
		}
		
		return null;
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public static void main(String[] args) {
		String dd="openid";
		try{
			dd="abc";
			throw new RuntimeException("abc");
		}catch(RuntimeException e){
			System.out.println("runtime Exception"+dd);
			System.out.println(e.getMessage());
		}catch(Exception e){
			System.out.println("runtimsssse Exception"+dd);
			System.out.println("mes:"+e.getMessage());
		}
	}
}
