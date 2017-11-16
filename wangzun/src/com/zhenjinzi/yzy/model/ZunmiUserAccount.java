package com.zhenjinzi.yzy.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.zhenjinzi.yzy.model.enums.UserAccountStatus;


@Entity
@Table(name = "zunmi_useraccount", catalog = "zunmi")
public class ZunmiUserAccount implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Integer id;
	private ZunmiUser zunmiUser;
	private Set<ZunmiTransactionLog> zunmiTransactionLogs;
	private Double balance;
	private Double freezedCash;
	private Integer buyCredit;
	private Integer saleCredit;
	private UserAccountStatus status;
	private String paymentPassword;

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

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userid",nullable = false)
	public ZunmiUser getZunmiUser() {
		return this.zunmiUser;
	}

	public void setZunmiUser(ZunmiUser zunmiUser) {
		this.zunmiUser = zunmiUser;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "zunmiUserAccount")
	public Set<ZunmiTransactionLog> getZunmiTransactionLogs() {
		return zunmiTransactionLogs;
	}

	public void setZunmiTransactionLogs(Set<ZunmiTransactionLog> zunmiTransactionLogs) {
		this.zunmiTransactionLogs = zunmiTransactionLogs;
	}

	@Column(name = "balance", nullable = false, precision = 22, scale = 0)
	public Double getBalance() {
		return this.balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	@Column(name = "freezedcash", nullable = false, precision = 22, scale = 0)
	public Double getFreezedCash() {
		return this.freezedCash;
	}

	public void setFreezedCash(Double freezedCash) {
		this.freezedCash = freezedCash;
	}

	@Column(name = "buycredit", nullable = false)
	public Integer getBuyCredit() {
		return this.buyCredit;
	}

	public void setBuyCredit(Integer buyCredit) {
		this.buyCredit = buyCredit;
	}

	@Column(name = "salecredit", nullable = false)
	public Integer getSaleCredit() {
		return this.saleCredit;
	}

	public void setSaleCredit(Integer saleCredit) {
		this.saleCredit = saleCredit;
	}

	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
	public UserAccountStatus getStatus() {
		return this.status;
	}

	public void setStatus(UserAccountStatus status) {
		this.status = status;
	}

	@Column(name = "paymentpassword", nullable = true, length = 50)
	public String getPaymentPassword() {
		return paymentPassword;
	}

	public void setPaymentPassword(String paymentPassword) {
		this.paymentPassword = paymentPassword;
	}

}