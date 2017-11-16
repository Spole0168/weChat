package com.zhenjinzi.yzy.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "zunmi_domainstore", catalog = "zunmi")
public class ZunmiDomainStore implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Integer id;
	private ZunmiUser zunmiUser;
	private String storeName;
	private Timestamp creationTime;
	private String storeUrl;
	private String customStoreUrl;
	private String logoUrl;
	private String notice;
	private Integer browseCount;
	private String recommendDomains;
	private String customConnects;
	private Boolean recommend;
	private Timestamp recommendTime;

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

	@Column(name = "storename", nullable = false, length = 50)
	public String getStoreName() {
		return this.storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	@Column(name = "creationtime", nullable = false, length = 19)
	public Timestamp getCreationTime() {
		return this.creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

	@Column(name = "storeurl", length = 100)
	public String getStoreUrl() {
		return this.storeUrl;
	}

	public void setStoreUrl(String storeUrl) {
		this.storeUrl = storeUrl;
	}

	@Column(name = "customstoreurl", length = 100)
	public String getCustomStoreUrl() {
		return this.customStoreUrl;
	}

	public void setCustomStoreUrl(String customStoreUrl) {
		this.customStoreUrl = customStoreUrl;
	}

	@Column(name = "logourl", length = 100)
	public String getLogoUrl() {
		return this.logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	@Column(name = "notice", length = 16777215)
	public String getNotice() {
		return this.notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	@Column(name = "browsecount")
	public Integer getBrowseCount() {
		return this.browseCount;
	}

	public void setBrowseCount(Integer browseCount) {
		this.browseCount = browseCount;
	}

	@Column(name = "recommenddomains", length = 200)
	public String getRecommendDomains() {
		return this.recommendDomains;
	}

	public void setRecommendDomains(String recommendDomains) {
		this.recommendDomains = recommendDomains;
	}

	@Column(name = "customconnects", length = 16777215)
	public String getCustomConnects() {
		return this.customConnects;
	}

	public void setCustomConnects(String customConnects) {
		this.customConnects = customConnects;
	}

	@Column(name = "recommend", nullable = false)
	public Boolean getRecommend() {
		return this.recommend;
	}

	public void setRecommend(Boolean recommend) {
		this.recommend = recommend;
	}

	@Column(name = "recommendtime", nullable = false, length = 19)
	public Timestamp getRecommendTime() {
		return this.recommendTime;
	}

	public void setRecommendTime(Timestamp recommendTime) {
		this.recommendTime = recommendTime;
	}

}