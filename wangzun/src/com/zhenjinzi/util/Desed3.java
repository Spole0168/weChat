package com.zhenjinzi.util;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.Key;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
/**
 * 使用3DES加密与解密,可对byte[],String类型进行加密与解密 密文可使用String,byte[]存储.
 *
 */
public class Desed3 {
	private static byte[] iv = {8,2,3,4,5,6,7,1}; 
    private static Key key;        //密钥
    
    /**
     * 根据参数生成KEY
     *
     * @param strKey 密钥字符串
     */
    public static void getKey(String strKey) {
        try {
            KeySpec dks = new DESKeySpec(strKey.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            key = keyFactory.generateSecret(dks);
            
        } catch (Exception e) {
        	throw new RuntimeException( " 初始化密钥出现异常 " );  
        }
    }
	public static void main(String[] args) throws UnsupportedEncodingException{
		String str = "mail|username|password";
		Desed3.getKey("beijingwangzun");
		String encStr = Desed3.getEncString(str);
		System.out.println(encStr);
		encStr = Desed3.getDesString(encStr);
		System.out.println("----------");
		System.out.println(encStr);
	}
	
    /**
     * 加密String明文输入,String密文输出
     *
     * @param strMing String明文
     * @return String密文
     */
    public static String getEncString(String strMing) {
        byte[] byteMi = null;
        byte[] byteMing = null;
        String strMi = "";
        BASE64Encoder base64en = new BASE64Encoder();
        try {
            byteMing = strMing.getBytes("UTF8");
            byteMi = getEncCode(byteMing);
            strMi = base64en.encode(byteMi);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            base64en = null;
            byteMing = null;
            byteMi = null;
        }
        return strMi;
    }
    /**
     * 解密 以String密文输入,String明文输出
     *
     * @param strMi String密文
     * @return String明文
     */
    public static String getDesString(String strMi) {
        BASE64Decoder base64De = new BASE64Decoder();
        byte[] byteMing = null;
        byte[] byteMi = null;
        String strMing = null;
        try {
            byteMi = base64De.decodeBuffer(strMi);
            byteMing = getDesCode(byteMi);
            strMing = new String(byteMing, "UTF8");
        } catch (Exception e) {
//            e.printStackTrace();
        } finally {
            base64De = null;
            byteMing = null;
            byteMi = null;
        }
        return strMing;
    }
    /**
     * 加密以byte[]明文输入,byte[]密文输出
     *
     * @param byteS byte[]明文
     * @return byte[]密文
     */
    private static byte[] getEncCode(byte[] byteS) {
        byte[] byteFina = null;
        Cipher cipher;
        try {
            cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            IvParameterSpec zeroIv = new IvParameterSpec(iv);
            cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
            byteFina = cipher.doFinal(byteS);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cipher = null;
        }
        return byteFina;
    }
    /**
     * 解密以byte[]密文输入,以byte[]明文输出
     *
     * @param byteD byte[]密文
     * @return byte[]明文
     */
    private static byte[] getDesCode(byte[] byteD) {
        Cipher cipher;
        byte[] byteFina = null;
        try {
        	 cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
             IvParameterSpec zeroIv = new IvParameterSpec(iv);
             cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
            byteFina = cipher.doFinal(byteD);
        } catch (Exception e) {
//            e.printStackTrace();
        } finally {
            cipher = null;
        }
        return byteFina;
    }
}
