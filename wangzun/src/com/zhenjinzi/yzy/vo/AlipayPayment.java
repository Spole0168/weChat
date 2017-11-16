package com.zhenjinzi.yzy.vo;

public class AlipayPayment {
	private String partner;
	private String key;
	private String service;
	private String returnUrl;
	private String notifyUrl;
	private String notifyVerifyUrl;
	private String sellerEmail;
	private String inputCharset;
	private String subject;
	private String body;
	private String showUrl;
	private String discount;
	private String paygateway;
	private String paymentType;
	private String signType;
	
	public AlipayPayment() {
		super();
	}

	/**
	 * 合作者身份ID
	 * @return
	 */
	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	/**
	 * 支付宝安全校验码 获得sign签名
	 * @return
	 */
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * 接口名称
	 * @return
	 */
	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	/**
	 * 支付宝通知验证地址
	 * @return
	 */
	public String getNotifyVerifyUrl() {
		return notifyVerifyUrl;
	}

	public void setNotifyVerifyUrl(String notifyVerifyUrl) {
		this.notifyVerifyUrl = notifyVerifyUrl;
	}

	/**
	 * 卖家支付宝账号
	 * @return
	 */
	public String getSellerEmail() {
		return sellerEmail;
	}

	public void setSellerEmail(String sellerEmail) {
		this.sellerEmail = sellerEmail;
	}

	/**
	 * 参数编码字符集
	 * @return
	 */
	public String getInputCharset() {
		return inputCharset;
	}

	public void setInputCharset(String inputCharset) {
		this.inputCharset = inputCharset;
	}

	/**
	 * 商品名称
	 * @return
	 */
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * 商品描述
	 * @return
	 */
	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getShowUrl() {
		return showUrl;
	}

	public void setShowUrl(String showUrl) {
		this.showUrl = showUrl;
	}

	/**
	 * 折扣
	 * @return
	 */
	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
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

	/**
	 * 支付类型
	 * @return
	 */
	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	/**
	 * 签名方式
	 * @return
	 */
	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}
}
