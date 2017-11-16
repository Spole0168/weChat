package com.zhenjinzi.yzy.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "zunmi_domainparking", catalog = "zunmi")
public class ZunmiDomainParking implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	private Integer id;
	private ZunmiUser zunmiUser;
	private ZunmiParkSetting zunmiParksetting;
	private String domainName;
	private Timestamp joinTime;
	private Timestamp deleteTime;
	private Boolean useDomainGroup;

	// Property accessors
	@Id
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
	@JoinColumn(name = "settingid")
	public ZunmiParkSetting getZunmiParksetting() {
		return this.zunmiParksetting;
	}

	public void setZunmiParksetting(ZunmiParkSetting zunmiParksetting) {
		this.zunmiParksetting = zunmiParksetting;
	}

	@Column(name = "domainname", nullable = false, length = 100)
	public String getDomainName() {
		return this.domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	@Column(name = "jointime", nullable = false, length = 19)
	public Timestamp getJoinTime() {
		return this.joinTime;
	}

	public void setJoinTime(Timestamp joinTime) {
		this.joinTime = joinTime;
	}

	@Column(name = "deletetime", length = 19)
	public Timestamp getDeleteTime() {
		return this.deleteTime;
	}

	public void setDeleteTime(Timestamp deleteTime) {
		this.deleteTime = deleteTime;
	}

	@Column(name = "usedomaingroup", nullable = false)
	public Boolean getUseDomainGroup() {
		return this.useDomainGroup;
	}

	public void setUseDomainGroup(Boolean useDomainGroup) {
		this.useDomainGroup = useDomainGroup;
	}

}