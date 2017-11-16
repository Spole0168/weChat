package com.zhenjinzi.yzy.action.user;

import java.sql.Timestamp;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Controller;

import com.fire.modules.web.struts2.Struts2Utils;
import com.zhenjinzi.util.Sequence;
import com.zhenjinzi.yzy.action.UserBaseAction;
import com.zhenjinzi.yzy.model.ZunmiRecharge;
import com.zhenjinzi.yzy.model.ZunmiUser;
import com.zhenjinzi.yzy.model.enums.ChargeStatus;
import com.zhenjinzi.yzy.model.enums.ChargeWay;
import com.zhenjinzi.yzy.model.enums.MoneyType;
import com.zhenjinzi.yzy.service.ZunmiPaymentService;
import com.zhenjinzi.yzy.service.ZunmiRechargeService;
import com.zhenjinzi.yzy.service.ZunmiTransactionLogService;
import com.zhenjinzi.yzy.service.ZunmiUserAccountService;
import com.zhenjinzi.yzy.service.ZunmiUserService;

@Controller
@Results({
	@Result(name="deposit",location="/WEB-INF/content/user/accountpages/deposit.jsp")
})
public class DepositAction extends UserBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Resource
	private ZunmiUserService userService;
	
	@Resource
	private ZunmiRechargeService rechargeService;
	
	@Resource
	private ZunmiTransactionLogService transactionLogService;
	
	@Resource
	private ZunmiUserAccountService accountService;
	
	@Resource
	private ZunmiPaymentService paymentService;
	
	private ZunmiRecharge recharge;
	private ZunmiUser user ;

	@Override
	public String execute(){
		user = checkUser();
		return user==null?null:"deposit";
	}
	
	public void doDeposit(){
		user = checkUser(); 	
		//生成订单
		String orderNo=Sequence.getPK("");
		MoneyType moneyType = MoneyType.valueOf(request.getParameter("moneyType"));
		String depositMoney = request.getParameter("depositMoney");
		ChargeWay chargeWay = ChargeWay.valueOf(request.getParameter("payType"));
		recharge = new ZunmiRecharge();
		recharge.setOrderId(orderNo);
		recharge.setZunmiUser(user);
		recharge.setMoneyType(moneyType);
		recharge.setRechargeCash(Double.parseDouble(depositMoney));
		recharge.setChargeWay(chargeWay);
		recharge.setStatus(ChargeStatus.PROCESSING);
		recharge.setRequestDate(new Timestamp(System.currentTimeMillis()));
		
		//保存充值订单
		boolean b=rechargeService.save(recharge);
		
		if(ChargeWay.CUP.equals(chargeWay)){
			String sbHtml = paymentService.getPaymentParam(recharge.getOrderId(), recharge.getRechargeCash().toString(), moneyType, chargeWay);
	        Struts2Utils.renderHtml(sbHtml);
		}else if(ChargeWay.ALIPAY.equals(chargeWay))
		{
			String sHtmlText = paymentService.getPaymentParam(recharge.getOrderId(),recharge.getRechargeCash().toString(),moneyType, chargeWay);
			Struts2Utils.renderHtml(sHtmlText);
		}
	}
	
	

	//接收支付宝通知
	public void alipayNotify(){
		Map<String, String[]> requestParams = request.getParameterMap();
		String notifyResult = paymentService.alipayNotify(requestParams);
		Struts2Utils.renderHtml(notifyResult);
	}
	
	public void chinaBankNotify(){
		Map<String ,String[]> requestParams = request.getParameterMap();
		String notifyResult = paymentService.chinaBankNotify(requestParams);
		out.print(notifyResult);
	}
	
	/*接收并验证
	 * notify_time   通知时间    不可空   yyyy-MM-dd HH:mm:ss
	 * notify_type   通知类型    不可空
	 * notify_id     通知校验ID
	 * sign_type     签名方式                     DSA   RSA    MD5
	 * sign          签名   
	 * 业务参数
	 * out_trade_no  订单号 （根据订单号来处理）比如说更新这个定的状态
	 * subject     暂时不用            商品名称
	 * trade_status      交易状态             trade_status=TRADE_SUCCESS
	 * gmt_create        交易创建时间
	 * gmt_payment       交易付款时间
	 * gmt_close         交易关闭时间
	 * refund_status     退款状态                
	 * gmt_refund        退款时间                      
	 * total_fee         交易金额
	 * body              商品描述
	 * trade_no          支付宝交易号
	*/
//	public String alipayReturn() throws UnsupportedEncodingException{
//		String flag="";//标志位、、成功的话赋值SUCCESS失败的话赋值FAILED，已 经充值的话ALREADYSUCC
//		String out_trade_no=request.getParameter("out_trade_no");
//		
//		//trade_status=TRADE_SUCCESS如果交易状态为SUCCESS则交易成功，即充值成功。
//		//首先，根据out_trade_no查找充值记录，更改这个充值记录的状态（同时更改余额），
//		ZunmiRecharge reRecharge=rechargeService.findByOrderId(out_trade_no);
//		ZunmiUser user=reRecharge.getZunmiUser();//这个充值的客户
//		
//		ZunmiTransactionLog transactionLog=new ZunmiTransactionLog();//交易明细
//		ZunmiUserAccount  userAccount= user.getZunmiUseraccount();//客户账户
//
//		Map<String,String> params = new HashMap<String,String>();
//		Map requestParams = request.getParameterMap();
//		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
//			String name = (String) iter.next();
//			String[] values = (String[]) requestParams.get(name);
//			String valueStr = "";
//			for (int i = 0; i < values.length; i++) {
//				valueStr = (i == values.length - 1) ? valueStr + values[i]
//						: valueStr + values[i] + ",";
//			}
//			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
//			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "UTF-8");
//			System.out.println("sss:"+valueStr);//
//			params.put(name, valueStr);
//		}
//
//		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
//		String trade_no = request.getParameter("trade_no");				//支付宝交易号
//		String total_fee = request.getParameter("total_fee");	        //获取总金额
//		String subject = new String(request.getParameter("subject").getBytes("ISO-8859-1"),"UTF-8");//商品名称、订单名称
//		String body = "";
//		if(request.getParameter("body") != null){
//			body = new String(request.getParameter("body").getBytes("ISO-8859-1"), "UTF-8");//商品描述、订单备注、描述
//		}
//		String buyer_email = request.getParameter("buyer_email");		//买家支付宝账号
//		String trade_status = request.getParameter("trade_status");		//交易状态
//
//		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
//		//计算得出通知验证结果
//		boolean verify_result = AlipayNotify.verify(params);
//		
//		if(verify_result){//验证成功
//			//请在这里加上商户的业务逻辑程序代码
//			System.out.println("测试1");
//			if(trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")){
//				System.out.println("测试2");
//				if(reRecharge.getStatus().equals("SUCCESSFUL")){////判断该笔订单是否在商户网站中已经做过处理
//		    	//如果有做过处理，不执行商户的业务程序
//					flag="ALREADYSUCC";
//					return "已经充值成功！！";
//				}else{
//					System.out.println("测试3");
//			   //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
//				double oldBalance=userAccount.getBalance();
//				userAccount.setBalance(oldBalance+Double.parseDouble(total_fee));//设置账户余额
//			  
//				reRecharge.setResponseDate(new Timestamp(System.currentTimeMillis()));
//				reRecharge.setStatus(ChargeStatus.SUCCESSFUL);
//				reRecharge.setRechargeCash(Double.parseDouble(total_fee));
//				
//				transactionLog.setZunmiUser(user);
//				Double currentAmount=Arith.add(userAccount.getBalance(),Double.parseDouble(total_fee));//工具类是处理加法的。
//				transactionLog.setCurrentAmount(currentAmount);
//				transactionLog.setActionDate(new Timestamp(System.currentTimeMillis()));
//				transactionLog.setAction("RECHARGE");
//				transactionLog.setInOut("IN");
//				transactionLog.setZunmiUserAccount(userAccount);
//				transactionLog.setCurrentAmount(userAccount.getBalance());//余额
//				transactionLog.setTransactionAmount(Double.parseDouble(total_fee));
//				
//				Set<ZunmiRecharge> recharges=transactionLog.getZunmiRecharges();
//				recharges.add(reRecharge);
//				transactionLog.setZunmiRecharges(recharges);
//				
////				boolean c=accountService.save(userAccount);
//				boolean b=transactionLogService.save(transactionLog);
//				
//				reRecharge.setZunmiTransactionlog(transactionLog);//充值订单与交易明细关联
//				if(!b){
//					flag="FAILED";
//				}
//				flag="SUCCESS";
//				}
//			}
////			out.println("验证成功<br />");
////			out.println("trade_no=" + trade_no);
//		}else{
//			//该页面可做页面美工编辑
//			reRecharge.setResponseDate(new Timestamp(System.currentTimeMillis()));
//			reRecharge.setStatus(ChargeStatus.FAILED);
//			reRecharge.setRechargeCash(Double.parseDouble(total_fee));
//			out.println("验证失败");
//			rechargeService.save(reRecharge);
//			flag= "FAILED";
//			
//		}
//		
//		rechargeService.save(reRecharge);
//		return flag;//
//	
//	}
	

	public ZunmiRecharge getRecharge() {
		return recharge;
	}
	public void setRecharge(ZunmiRecharge recharge) {
		this.recharge = recharge;
	}
	public ZunmiUser getUser() {
		return user;
	}
	public void setUser(ZunmiUser user) {
		this.user = user;
	}

}
