package com.zhenjinzi.yzy.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "zunmi_domain", catalog = "zunmi")
public class ZunmiDomain implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	private Integer id;
	private ZunmiDomainGroup zunmiDomaingroup;
	private ZunmiUser zunmiUser;
	private Boolean isCn;
	private String domain;
	private Timestamp creationTime;
	private Timestamp expirationTime;
	private Timestamp addTime;
	private String domainType;
	private String status;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "groupid", nullable = false)
	public ZunmiDomainGroup getZunmiDomaingroup() {
		return this.zunmiDomaingroup;
	}

	public void setZunmiDomaingroup(ZunmiDomainGroup zunmiDomaingroup) {
		this.zunmiDomaingroup = zunmiDomaingroup;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userid", nullable = false)
	public ZunmiUser getZunmiUser() {
		return this.zunmiUser;
	}

	public void setZunmiUser(ZunmiUser zunmiUser) {
		this.zunmiUser = zunmiUser;
	}

	@Column(name = "iscn")
	public Boolean getIsCn() {
		return this.isCn;
	}

	public void setIsCn(Boolean isCn) {
		this.isCn = isCn;
	}

	@Column(name = "domain", nullable = false, length = 100)
	public String getDomain() {
		return this.domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	@Column(name = "creationtime", length = 19)
	public Timestamp getCreationTime() {
		return this.creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

	@Column(name = "expirationtime", length = 19)
	public Timestamp getExpirationTime() {
		return this.expirationTime;
	}
	
	@Column(name = "addtime", nullable = false, length = 19)
	public Timestamp getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

	public void setExpirationTime(Timestamp expirationTime) {
		this.expirationTime = expirationTime;
	}

	@Column(name = "domaintype", length = 17)
	public String getDomainType() {
		return this.domainType;
	}

	public void setDomainType(String domainType) {
		this.domainType = domainType;
	}

	@Column(name = "status", nullable = false, length = 11)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "zunmiDomain")
	public Set<ZunmiDomainOnePrize> getZunmiDomainOnePrizes() {
		return zunmiDomainOnePrizes;
	}

	public void setZunmiDomainOnePrizes(
			Set<ZunmiDomainOnePrize> zunmiDomainOnePrizes) {
		this.zunmiDomainOnePrizes = zunmiDomainOnePrizes;
	}

}