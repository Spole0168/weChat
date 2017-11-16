package com.zhenjinzi.yzy.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zhenjinzi.yzy.model.enums.MoneyType;
import com.zhenjinzi.yzy.model.enums.WithdrawStatus;

@Entity
@Table(name = "zunmi_withdraw", catalog = "zunmi")
public class ZunmiWithdraw implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	private Integer id;
	private ZunmiBank zunmiBank;
	private YzyAdmin zunmiAdmin;
	private ZunmiUser zunmiUser;
	private ZunmiTransactionLog zunmiTransactionlog;
	private Timestamp addTime;
	private MoneyType moneyType;
	private Double applicationAmount;
	private Double poundage;
	private Timestamp auditingTime;
	private WithdrawStatus status;
	private String description;


	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bankid")
	public ZunmiBank getZunmiBank() {
		return this.zunmiBank;
	}

	public void setZunmiBank(ZunmiBank zunmiBank) {
		this.zunmiBank = zunmiBank;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "adminid")
	public YzyAdmin getZunmiAdmin() {
		return this.zunmiAdmin;
	}

	public void setZunmiAdmin(YzyAdmin zunmiAdmin) {
		this.zunmiAdmin = zunmiAdmin;
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
		return this.zunmiTransactionlog;
	}

	public void setZunmiTransactionlog(ZunmiTransactionLog zunmiTransactionlog) {
		this.zunmiTransactionlog = zunmiTransactionlog;
	}

	@Column(name = "addtime", nullable = false, length = 19)
	public Timestamp getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

	@Column(name = "moneytype",nullable=false)
	@Enumerated(EnumType.STRING)
	public MoneyType getMoneyType() {
		return this.moneyType;
	}

	public void setMoneyType(MoneyType moneyType) {
		this.moneyType = moneyType;
	}

	@Column(name = "applicationamount", precision = 22, scale = 0)
	public Double getApplicationAmount() {
		return this.applicationAmount;
	}

	public void setApplicationAmount(Double applicationAmount) {
		this.applicationAmount = applicationAmount;
	}

	@Column(name = "poundage", precision = 22, scale = 0)
	public Double getPoundage() {
		return this.poundage;
	}

	public void setPoundage(Double poundage) {
		this.poundage = poundage;
	}

	@Column(name = "auditingtime", length = 19)
	public Timestamp getAuditingTime() {
		return this.auditingTime;
	}

	public void setAuditingTime(Timestamp auditingTime) {
		this.auditingTime = auditingTime;
	}

	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
	public WithdrawStatus getStatus() {
		return this.status;
	}

	public void setStatus(WithdrawStatus status) {
		this.status = status;
	}

	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}