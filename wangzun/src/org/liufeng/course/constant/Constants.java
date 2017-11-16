package org.liufeng.course.constant;
/**
 * 定义常量的地方
 * @author ly
 *
 */
public class Constants {
	/**微信的appid和secret 值*/
//	public static final String APP_ID="w***309";
//	public static final String SECRET="465***3b9aa";
	public static final String APP_ID="wx96***0a48a9f";
	public static final String SECRET="572a0f1***13a6a7f2";
	/**微盟的 appid*/
	public static final String WM_APP_ID="4ddc3b8e***568bbee7959";
	public static final String WM_SECRET="5536e4***4e83550";
	/**获取 微信公众账号 的access_token*/
	public static final String ACCEES_TOKEN_WXURL="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	/**微信  创建菜单的API接口*/
	public static final String MENU_CREATE="https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	
	/**微信 永久创建二维码：{"expire_seconds": 604800, "action_name": "QR_SCENE", "action_info": {"scene": {"scene_id": 123}}}*/
	public static final String WX_CREATE_DIM=" https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN";
	
	/**获取微盟weimob的access*/
	public static final String ACCESS_TOKEN_WEIMODURL="https://open.weimob.com/common/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	
	public static final String WM_ACCESS_TOKEN="https://open.weimob.com/common/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	/**获得成员的积分相关列表接口*/
	public static final String WM_GETMEMBER_CARDS="https://open.weimob.com/api/mname/WE_MEMBERS_CARD/cname/getScoreBill?accesstoken=ACCESS_TOKEN";
	
	/**微盟获得openid的方式：POST:grant_type=authorization_code&client_id=CLIENT_ID&client_secret=CLIENT_SECRET&code=CODE */
	public static final String WM_GET_OPENIDS="https://open.weimob.com/oauth2/openid/access_token"; 
}
