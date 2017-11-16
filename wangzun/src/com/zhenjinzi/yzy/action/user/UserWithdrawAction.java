package com.zhenjinzi.yzy.action.user;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Controller;

import com.zhenjinzi.yzy.action.UserBaseAction;
import com.zhenjinzi.yzy.model.ZunmiBank;
import com.zhenjinzi.yzy.model.ZunmiUser;
import com.zhenjinzi.yzy.service.ZunmiWithdrawService;

@Controller
@Results({
	@Result(name="withdraw",location="/WEB-INF/content/user/accountpages/withdraw.jsp")
})
public class UserWithdrawAction extends UserBaseAction{

	private static final long serialVersionUID = 1L;
	
	@Resource
	private ZunmiWithdrawService withdrawService;
	
	private ZunmiUser user ;

	private ZunmiBank bank;
	
	private Double userCash;
	
	private String paymentPassword;
	
	private BigDecimal balance;
	public String execute(){
		user = checkUser();
		if(user!=null){
			this.bank= user.getZunmiBank();
			BigDecimal   bd   =   new   BigDecimal(user.getZunmiUseraccount().getBalance());   
		    balance   =   bd.setScale(2,BigDecimal.ROUND_HALF_UP);  
			return "withdraw";
		}
		return null;
	}
	
	public void doWithdraw(){
		user = checkUser();
		if(user!=null){
			ZunmiBank bank = user.getZunmiBank();
			if(bank==null){bank = new ZunmiBank();}
			bank.setBankName(this.bank.getBankName());
			bank.setBranchName(this.bank.getBranchName());
			bank.setCardNo(this.bank.getCardNo());
			user.setZunmiBank(bank);
			try {
				withdrawService.doWithdraw(userCash, paymentPassword, user);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public ZunmiBank getBank() {
		return bank;
	}

	public void setBank(ZunmiBank bank) {
		this.bank = bank;
	}
	
	public ZunmiUser getUser() {
		return user;
	}

	public void setUser(ZunmiUser user) {
		this.user = user;
	}

	public Double getUserCash() {
		return userCash;
	}

	public void setUserCash(Double userCash) {
		this.userCash = userCash;
	}

	public String getPaymentPassword() {
		return paymentPassword;
	}

	public void setPaymentPassword(String paymentPassword) {
		this.paymentPassword = paymentPassword;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

}
