package com.zhenjinzi.util;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class EncryptionHelper {
	public static void main(String args[]){
		System.out.println(Enc("123456", "MD5"));
	}
	/**  
	 * BASE64解密  
	*   
	* @param key  
	* @return  
	* @throws Exception  
	*/  
	public static byte[] decryptBASE64(String key) throws Exception {   
	    return (new BASE64Decoder()).decodeBuffer(key);   
		}   	  
	/**  
	* BASE64加密  
	*   
	* @param key  
	* @return  
	* @throws Exception  
	*/  
	public static String encryptBASE64(byte[] key) throws Exception {   
	   return (new BASE64Encoder()).encodeBuffer(key);   
	}  

	/***
	 * @param strSrc
	 * 加密串
	 * @param encName
	 * 算法名称 encName:MD5,SHA-1,SHA-256
	 * 
	 */
	public static String Enc(String strSrc, String encName) {
		MessageDigest md = null;
		String strDes = null;

		
		try {
			byte[] bt = strSrc.getBytes("utf-8");
			if (encName == null || encName.equals("")) {
				encName = "MD5";
			}
			md = MessageDigest.getInstance(encName);
			md.update(bt);
			strDes = bytes2Hex(md.digest()); // to HexString
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Invalid algorithm.");
			return null;
		} catch (UnsupportedEncodingException e) {
			System.out.println("Invalid encoding.");
			return null;
		}
		return strDes;
	}
	
	private static String bytes2Hex(byte[] bts) {
		String des = "";
		String tmp = null;
		for (int i = 0; i < bts.length; i++) {
			tmp = (Integer.toHexString(bts[i] & 0xFF));
			if (tmp.length() == 1) {
				des += "0";
			}
			des += tmp;
		}
		return des;
	}
}
