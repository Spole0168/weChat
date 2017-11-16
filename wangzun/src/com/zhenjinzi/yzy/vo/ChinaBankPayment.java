package com.zhenjinzi.yzy.vo;

public class ChinaBankPayment {
	private String vid;
	private String key;
	private String vurl;
	private String remark1;
	private String remark2;
	private String paygateway;
	
	public ChinaBankPayment(){
		super();
	}
	/**
	 * 商户编号
	 * @return
	 */
	public String getVid() {
		return vid;
	}
	public void setVid(String vid) {
		this.vid = vid;
	}
	
	/**
	 * 安全校验码
	 * @return
	 */
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
	/**
	 * 通知地址
	 * @return
	 */
	public String getVurl() {
		return vurl;
	}
	public void setVurl(String vurl) {
		this.vurl = vurl;
	}
	
	/**
	 * 商品名称
	 * @return
	 */
	public String getRemark1() {
		return remark1;
	}
	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}
	
	/**
	 * 商品描述
	 * @return
	 */
	public String getRemark2() {
		return remark2;
	}
	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}
	
	/**
	 * 接口请求地址
	 * @return
	 */
	public String getPaygateway() {
		return paygateway;
	}
	public void setPaygateway(String paygateway) {
		this.paygateway = paygateway;
	}
	
}
