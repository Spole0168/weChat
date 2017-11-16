package com.zhenjinzi.yzy.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "zunmi_domainoneprize", catalog = "zunmi")
public class ZunmiDomainOnePrize implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	private Integer id;
	private ZunmiUser zunmiUser;
	private ZunmiTransactionLog zunmiTransactionLog;
	private ZunmiDomain zunmiDomain;
	private String domainName;
	private String domainItems;
	private String description;
	private String moneyType;
	private Double prize;
	private Date addDate;
	private Date endDate;
	private Integer hits;
	private Boolean isRecommend;
	private String status;

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
	@JoinColumn(name = "userid")
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "domainid")
	public ZunmiDomain getZunmiDomain() {
		return this.zunmiDomain;
	}

	public void setZunmiDomain(ZunmiDomain zunmiDomain) {
		this.zunmiDomain = zunmiDomain;
	}

	@Column(name = "domainname", nullable = false, length = 100)
	public String getDomainName() {
		return this.domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	@Column(name = "domainitems", length = 200)
	public String getDomainItems() {
		return this.domainItems;
	}

	public void setDomainItems(String domainItems) {
		this.domainItems = domainItems;
	}

	@Column(name = "description", length = 200)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "moneytype", length = 4)
	public String getMoneyType() {
		return this.moneyType;
	}

	public void setMoneyType(String moneyType) {
		this.moneyType = moneyType;
	}

	@Column(name = "prize", precision = 22, scale = 0)
	public Double getPrize() {
		return this.prize;
	}

	public void setPrize(Double prize) {
		this.prize = prize;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "adddate", nullable = false, length = 10)
	public Date getAddDate() {
		return this.addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "enddate", length = 10)
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "hits")
	public Integer getHits() {
		return this.hits;
	}

	public void setHits(Integer hits) {
		this.hits = hits;
	}

	@Column(name = "isrecommend", nullable = false)
	public Boolean getIsRecommend() {
		return this.isRecommend;
	}

	public void setIsRecommend(Boolean isRecommend) {
		this.isRecommend = isRecommend;
	}

	@Column(name = "status", nullable = false, length = 10)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}