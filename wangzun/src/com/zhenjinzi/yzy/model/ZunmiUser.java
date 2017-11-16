package com.zhenjinzi.yzy.model;

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
import javax.persistence.OneToOne;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.zhenjinzi.yzy.model.enums.UserStatus;

@Entity
@Table(name = "zunmi_user", catalog = "zunmi")
public class ZunmiUser implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String email;
	private String userName;
	private String password;
	private String mobile;
	private UserStatus status;
	private Timestamp creationDate;
	private Set<ZunmiDomainGroup> zunmiDomaingroups = new HashSet<ZunmiDomainGroup>(0);
	private Set<ZunmiDomainStore> zunmiDomainstores = new HashSet<ZunmiDomainStore>(0);
	private Set<ZunmiTransactionLog> zunmiTransactionlogs = new HashSet<ZunmiTransactionLog>(0);
	private Set<ZunmiMessage> zunmiMessagesForReceiver = new HashSet<ZunmiMessage>(0);
	private Set<ZunmiUserLog> zunmiUserlogs = new HashSet<ZunmiUserLog>(0);
	private Set<ZunmiDomainParking> zunmiDomainparkings = new HashSet<ZunmiDomainParking>(0);
	private Set<ZunmiRecharge> zunmiRecharges = new HashSet<ZunmiRecharge>(0);
	private ZunmiSecurity zunmiSecurity = new ZunmiSecurity();
	private Set<ZunmiWithdraw> zunmiWithdraws = new HashSet<ZunmiWithdraw>(0);
	private Set<ZunmiDomain> zunmiDomains = new HashSet<ZunmiDomain>(0);
	private Set<ZunmiQuestion> zunmiQuestions = new HashSet<ZunmiQuestion>(0);
	private ZunmiBank bank = new ZunmiBank();
	private ZunmiUserDetail zunmiUserdetail = new ZunmiUserDetail();
	private ZunmiUserAccount zunmiUseraccount = new ZunmiUserAccount();
	private Set<ZunmiUserBoard> zunmiUserBoards = new HashSet<ZunmiUserBoard>(0);
	private Set<ZunmiMessage> zunmiMessagesForSender = new HashSet<ZunmiMessage>(0);
	private Set<ZunmiDomainOnePrize> zunmiDomainOnePrizes=new HashSet<ZunmiDomainOnePrize>(0);

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

	@Column(name = "email", nullable = false, length = 100)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "username", nullable = false, length = 50)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "password", nullable = false, length = 50)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "mobile", length = 20)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
	public UserStatus getStatus() {
		return this.status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	@Column(name = "creationdate", nullable = false, length = 19)
	public Timestamp getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "zunmiUser")
	public Set<ZunmiDomainGroup> getZunmiDomaingroups() {
		return this.zunmiDomaingroups;
	}

	public void setZunmiDomaingroups(Set<ZunmiDomainGroup> zunmiDomaingroups) {
		this.zunmiDomaingroups = zunmiDomaingroups;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "zunmiUser")
	public Set<ZunmiDomainStore> getZunmiDomainstores() {
		return this.zunmiDomainstores;
	}

	public void setZunmiDomainstores(Set<ZunmiDomainStore> zunmiDomainstores) {
		this.zunmiDomainstores = zunmiDomainstores;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "zunmiUser")
	public Set<ZunmiTransactionLog> getZunmiTransactionlogs() {
		return this.zunmiTransactionlogs;
	}

	public void setZunmiTransactionlogs(
			Set<ZunmiTransactionLog> zunmiTransactionlogs) {
		this.zunmiTransactionlogs = zunmiTransactionlogs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "zunmiUserByReceiver")
	public Set<ZunmiMessage> getZunmiMessagesForReceiver() {
		return this.zunmiMessagesForReceiver;
	}

	public void setZunmiMessagesForReceiver(
			Set<ZunmiMessage> zunmiMessagesForReceiver) {
		this.zunmiMessagesForReceiver = zunmiMessagesForReceiver;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "zunmiUser")
	public Set<ZunmiUserLog> getZunmiUserlogs() {
		return this.zunmiUserlogs;
	}

	public void setZunmiUserlogs(Set<ZunmiUserLog> zunmiUserlogs) {
		this.zunmiUserlogs = zunmiUserlogs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "zunmiUser")
	public Set<ZunmiDomainParking> getZunmiDomainparkings() {
		return this.zunmiDomainparkings;
	}

	public void setZunmiDomainparkings(
			Set<ZunmiDomainParking> zunmiDomainparkings) {
		this.zunmiDomainparkings = zunmiDomainparkings;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "zunmiUser")
	public Set<ZunmiRecharge> getZunmiRecharges() {
		return this.zunmiRecharges;
	}

	public void setZunmiRecharges(Set<ZunmiRecharge> zunmiRecharges) {
		this.zunmiRecharges = zunmiRecharges;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "zunmiUser")
	public ZunmiSecurity getZunmiSecurity() {
		return this.zunmiSecurity;
	}

	public void setZunmiSecurity(ZunmiSecurity zunmiSecurity) {
		this.zunmiSecurity = zunmiSecurity;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "zunmiUser")
	public Set<ZunmiWithdraw> getZunmiWithdraws() {
		return this.zunmiWithdraws;
	}

	public void setZunmiWithdraws(Set<ZunmiWithdraw> zunmiWithdraws) {
		this.zunmiWithdraws = zunmiWithdraws;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "zunmiUser")
	public Set<ZunmiDomain> getZunmiDomains() {
		return this.zunmiDomains;
	}

	public void setZunmiDomains(Set<ZunmiDomain> zunmiDomains) {
		this.zunmiDomains = zunmiDomains;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "zunmiUser")
	public Set<ZunmiQuestion> getZunmiQuestions() {
		return this.zunmiQuestions;
	}

	public void setZunmiQuestions(Set<ZunmiQuestion> zunmiQuestions) {
		this.zunmiQuestions = zunmiQuestions;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "zunmiUser")
	public ZunmiBank getZunmiBank() {
		return this.bank;
	}

	public void setZunmiBank(ZunmiBank zunmiBank) {
		this.bank = zunmiBank;
	}


	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "zunmiUser")
	public ZunmiUserDetail getZunmiUserdetail() {
		return this.zunmiUserdetail;
	}

	public void setZunmiUserdetail(ZunmiUserDetail zunmiUserdetail) {
		this.zunmiUserdetail = zunmiUserdetail;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "zunmiUser")
	public ZunmiUserAccount getZunmiUseraccount() {
		return this.zunmiUseraccount;
	}

	public void setZunmiUseraccount(ZunmiUserAccount zunmiUseraccount) {
		this.zunmiUseraccount = zunmiUseraccount;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "zunmiUser")
	public Set<ZunmiUserBoard> getZunmiUserboards() {
		return this.zunmiUserBoards;
	}

	public void setZunmiUserboards(Set<ZunmiUserBoard> zunmiUserboards) {
		this.zunmiUserBoards = zunmiUserboards;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "zunmiUserBySender")
	public Set<ZunmiMessage> getZunmiMessagesForSender() {
		return this.zunmiMessagesForSender;
	}

	public void setZunmiMessagesForSender(
			Set<ZunmiMessage> zunmiMessagesForSender) {
		this.zunmiMessagesForSender = zunmiMessagesForSender;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "zunmiUser")
	public Set<ZunmiDomainOnePrize> getZunmiDomainOnePrizes() {
		return zunmiDomainOnePrizes;
	}

	public void setZunmiDomainOnePrizes(
			Set<ZunmiDomainOnePrize> zunmiDomainOnePrizes) {
		this.zunmiDomainOnePrizes = zunmiDomainOnePrizes;
	}

}