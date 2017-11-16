package com.zhenjinzi.yzy.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;
import java.util.HashSet;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.zhenjinzi.yzy.model.enums.TransactionAction;

@Entity
@Table(name = "zunmi_transactionlog", catalog = "zunmi")
public class ZunmiTransactionLog implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	private Integer id;
	private ZunmiUser zunmiUser;
	private ZunmiUserAccount zunmiUserAccount;
	private Double transactionAmount;
	private String inOut;
	private Double currentAmount;
	private TransactionAction action;
	private Timestamp actionDate;
	private String description;
	private Set<ZunmiWithdraw> zunmiWithdraws = new HashSet<ZunmiWithdraw>(0);
	private Set<ZunmiRecharge> zunmiRecharges = new HashSet<ZunmiRecharge>(0);
//	private Set<ZunmiDomainOnePrize> zunmiDomainOnePrizes=new HashSet<ZunmiDomainOnePrize>(0);
	
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
	@JoinColumn(name = "userid", nullable = false)
	public ZunmiUser getZunmiUser() {
		return this.zunmiUser;
	}

	public void setZunmiUser(ZunmiUser zunmiUser) {
		this.zunmiUser = zunmiUser;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "useraccountid", nullable = false)
	public ZunmiUserAccount getZunmiUserAccount() {
		return zunmiUserAccount;
	}

	public void setZunmiUserAccount(ZunmiUserAccount zunmiUserAccount) {
		this.zunmiUserAccount = zunmiUserAccount;
	}

	@Column(name = "transactionamount", nullable = false, precision = 22, scale = 0)
	public Double getTransactionAmount() {
		return this.transactionAmount;
	}

	public void setTransactionAmount(Double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	@Column(name = "`inout`", nullable = false, length = 3)
	public String getInOut() {
		return this.inOut;
	}

	public void setInOut(String inOut) {
		this.inOut = inOut;
	}

	@Column(name = "currentamount", nullable = false, precision = 22, scale = 0)
	public Double getCurrentAmount() {
		return this.currentAmount;
	}

	public void setCurrentAmount(Double currentAmount) {
		this.currentAmount = currentAmount;
	}

	@Column(name = "`action`", nullable = false)
	@Enumerated(EnumType.STRING)
	public TransactionAction getAction() {
		return this.action;
	}

	public void setAction(TransactionAction action) {
		this.action = action;
	}

	@Column(name = "actiondate", nullable = false, length = 19)
	public Timestamp getActionDate() {
		return this.actionDate;
	}

	public void setActionDate(Timestamp actionDate) {
		this.actionDate = actionDate;
	}

	@Column(name = "description", length = 200)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "zunmiTransactionlog")
	public Set<ZunmiWithdraw> getZunmiWithdraws() {
		return this.zunmiWithdraws;
	}

	public void setZunmiWithdraws(Set<ZunmiWithdraw> zunmiWithdraws) {
		this.zunmiWithdraws = zunmiWithdraws;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "zunmiTransactionlog")
	public Set<ZunmiRecharge> getZunmiRecharges() {
		return this.zunmiRecharges;
	}

	public void setZunmiRecharges(Set<ZunmiRecharge> zunmiRecharges) {
		this.zunmiRecharges = zunmiRecharges;
	}
	
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "zunmiTransactionlog")
//	public Set<ZunmiDomainOnePrize> getZunmiDomainOnePrizes() {
//		return this.zunmiDomainOnePrizes;
//	}
//
//	public void setZunmiDomainOnePrizes(
//			Set<ZunmiDomainOnePrize> zunmiDomainOnePrizes) {
//		this.zunmiDomainOnePrizes = zunmiDomainOnePrizes;
//	}
//	
	
}