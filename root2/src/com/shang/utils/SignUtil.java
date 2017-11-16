package com.shang.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import org.apache.http.*;
import org.apache.http.client.*;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.*;
import org.apache.http.util.EntityUtils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/** 
 * 请求校验工具类 
 *  
 * @author jiangyin 
 */  
public class SignUtil {  
    // 与接口配置信息中的Token要一致  
	//URL: http://shangzhuzi.duapp.com/
    private static String token = "spole_168";
  
    /** 
     * 验证签名 
     *  
     * @param signature 
     * @param timestamp 
     * @param nonce 
     * @return 
     */  
    public static boolean checkSignature(String signature, String timestamp, String nonce) {  
        String[] arr = new String[] { token, timestamp, nonce };  
        // 将token、timestamp、nonce三个参数进行字典序排序  
        Arrays.sort(arr);  
        StringBuilder content = new StringBuilder();  
        for (int i = 0; i < arr.length; i++) {  
            content.append(arr[i]);  
        }  
        MessageDigest md = null;  
        String tmpStr = null;  
  
        try {  
            md = MessageDigest.getInstance("SHA-1");  
            // 将三个参数字符串拼接成一个字符串进行sha1加密  
            byte[] digest = md.digest(content.toString().getBytes());  
            tmpStr = byteToStr(digest);  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
        }  
  
        content = null;  
        System.out.println("加密排序后的字符串："+tmpStr);
        // 将sha1加密后的字符串可与signature对比，标识该请求来源于微信  
        return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;  
    }  
  
    /** 
     * 将字节数组转换为十六进制字符串 
     *  
     * @param byteArray 
     * @return 
     */  
    private static String byteToStr(byte[] byteArray) {  
        String strDigest = "";  
        for (int i = 0; i < byteArray.length; i++) {  
            strDigest += byteToHexStr(byteArray[i]);  
        }  
        return strDigest;  
    }  
  
    /** 
     * 将字节转换为十六进制字符串 
     *  
     * @param mByte 
     * @return 
     */  
    private static String byteToHexStr(byte mByte) {  
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };  
        char[] tempArr = new char[2];  
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];  
        tempArr[1] = Digit[mByte & 0X0F];  
  
        String s = new String(tempArr);  
        return s;  
    }  
    public static String getAccess_token(String appId,String appSecret){
    	String result = "";
    	String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appId+"&secret="+appSecret;
    	HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(url);
		JsonParser jsonparer = new JsonParser();// 初始化解析json格式的对象
		try{
		    HttpResponse res = client.execute(get);
		    String responseContent = null; // 响应内容
			HttpEntity entity = res.getEntity();
			responseContent = EntityUtils.toString(entity, "UTF-8");
			JsonObject json = jsonparer.parse(responseContent)
			        .getAsJsonObject();
			// 将json字符串转换为json对象
			if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
			    if (json.get("errcode") != null){// 错误时微信会返回错误码等信息，{"errcode":40013,"errmsg":"invalid appid"}
			    	}else{// 正常情况下{"access_token":"ACCESS_TOKEN","expires_in":7200}
			    	result = json.get("access_token").getAsString();
			    	}
			    }
			}catch (Exception e){
				e.printStackTrace();
			}finally{
				// 关闭连接 ,释放资源
				client.getConnectionManager().shutdown();
				return result;
			}
   }
    public static void main(String[] a){
    	String r = getAccess_token("wx69628781ba352b2d", "2b31906b796d11930de59b70fc3dc661");
    	System.out.print(r);
    	
    }
}  

