package com.zhenjinzi.yzy.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zhenjinzi.yzy.model.enums.ChargeStatus;
import com.zhenjinzi.yzy.model.enums.ChargeWay;
import com.zhenjinzi.yzy.model.enums.MoneyType;

@Entity
@Table(name = "zunmi_recharge", catalog = "zunmi")
public class ZunmiRecharge implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	private String orderId;
	private ZunmiUser zunmiUser;
	private ZunmiTransactionLog zunmiTransactionLog;
	private ChargeWay chargeWay;
	private MoneyType moneyType;
	private Double rechargeCash;
	private Timestamp requestDate;
	private Timestamp responseDate;
	private ChargeStatus status;


	// Property accessors
	
	@Id
	@Column(name = "orderid", unique = true, nullable = false, length = 50)
	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userid", nullable = false)
	public ZunmiUser getZunmiUser() {
		return this.zunmiUser;
	}

	public void setZunmiUser(ZunmiUser zunmiUser) {
		this.zunmiUser = zunmiUser;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "detailid")
	public ZunmiTransactionLog getZunmiTransactionlog() {
		return this.zunmiTransactionLog;
	}

	public void setZunmiTransactionlog(ZunmiTransactionLog zunmiTransactionlog) {
		this.zunmiTransactionLog = zunmiTransactionlog;
	}

	@Column(name = "chargeway", nullable = false)
	@Enumerated(EnumType.STRING)
	public ChargeWay getChargeWay() {
		return this.chargeWay;
	}

	public void setChargeWay(ChargeWay chargeWay) {
		this.chargeWay = chargeWay;
	}

	@Column(name = "moneytype", nullable = false)
	@Enumerated(EnumType.STRING)
	public MoneyType getMoneyType() {
		return this.moneyType;
	}

	public void setMoneyType(MoneyType moneyType) {
		this.moneyType = moneyType;
	}

	@Column(name = "rechargecash", nullable = false, precision = 22, scale = 0)
	public Double getRechargeCash() {
		return this.rechargeCash;
	}

	public void setRechargeCash(Double rechargeCash) {
		this.rechargeCash = rechargeCash;
	}

	@Column(name = "requestdate", nullable = false, length = 19)
	public Timestamp getRequestDate() {
		return this.requestDate;
	}

	public void setRequestDate(Timestamp requestDate) {
		this.requestDate = requestDate;
	}

	@Column(name = "responsedate", length = 19)
	public Timestamp getResponseDate() {
		return this.responseDate;
	}

	public void setResponseDate(Timestamp responseDate) {
		this.responseDate = responseDate;
	}

	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
	public ChargeStatus getStatus() {
		return this.status;
	}

	public void setStatus(ChargeStatus status) {
		this.status = status;
	}

}