package com.lianchuang.util;

/**
 * 常量
 * @author herosky
 *
 */
public class Constants {
	/**
	 * APPID
	 */
	public static String APPID = "wx52e8ee1150fe3a42";
	/**
	 * SECRET
	 */
	public static String SECRET = "3b69f4a0443558fae7c088bb784c2e5e";
	/**
	 * 获取ACCESS_TOKEN接口
	 */
	public static String GET_ACCESSTOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	/**
	 * ACCESS_TOKEN有效时间(单位：ms)
	 */
	public static int EFFECTIVE_TIME = 700000;
	/**
	 * conf.properties文件路径
	 */
	public static String CONF_PROPERTIES_PATH = "src/conf/config.properties";
	/**
	 * 微信接入token ，用于验证微信接口
	 */
	public static String TOKEN = "abc123";
}
