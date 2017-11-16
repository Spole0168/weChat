package com.zhenjinzi.yzy.service;

import java.util.Map;

import com.zhenjinzi.yzy.model.enums.ChargeWay;
import com.zhenjinzi.yzy.model.enums.MoneyType;

public interface ZunmiPaymentService {
	public String getPaymentParam(String orderId, String amount, MoneyType moneyType, ChargeWay chargeWay);	
	public String alipayNotify(Map requestParams);
	public String chinaBankNotify(Map requestParams);
}
