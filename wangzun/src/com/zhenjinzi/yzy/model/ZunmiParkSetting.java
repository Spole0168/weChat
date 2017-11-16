package com.zhenjinzi.yzy.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "zunmi_parksetting", catalog = "zunmi")
public class ZunmiParkSetting implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String title;
	private String template;
	private String keywords;
	private String bannerTitle;
	private String bannerUrl;
	private String bannerImageUrl;
	private String adTitle1;
	private String adUrl1;
	private String adTitle2;
	private String adUrl2;
	private String customText;
	private String customStat;
	private Boolean showOnSale;
	private Boolean showPopUp;
	private Set<ZunmiDomainParking> zunmiDomainparkings = new HashSet<ZunmiDomainParking>(
			0);

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

	@Column(name = "title", length = 100)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "template", length = 50)
	public String getTemplate() {
		return this.template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	@Column(name = "keywords", length = 50)
	public String getKeywords() {
		return this.keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	@Column(name = "bannertitle", length = 50)
	public String getBannerTitle() {
		return this.bannerTitle;
	}

	public void setBannerTitle(String bannerTitle) {
		this.bannerTitle = bannerTitle;
	}

	@Column(name = "bannerurl", length = 200)
	public String getBannerUrl() {
		return this.bannerUrl;
	}

	public void setBannerUrl(String bannerUrl) {
		this.bannerUrl = bannerUrl;
	}

	@Column(name = "bannerimageurl", length = 200)
	public String getBannerImageUrl() {
		return this.bannerImageUrl;
	}

	public void setBannerImageUrl(String bannerImageUrl) {
		this.bannerImageUrl = bannerImageUrl;
	}

	@Column(name = "adtitle1", length = 50)
	public String getAdTitle1() {
		return this.adTitle1;
	}

	public void setAdTitle1(String adTitle1) {
		this.adTitle1 = adTitle1;
	}

	@Column(name = "adurl1", length = 200)
	public String getAdUrl1() {
		return this.adUrl1;
	}

	public void setAdUrl1(String adUrl1) {
		this.adUrl1 = adUrl1;
	}

	@Column(name = "adtitle2", length = 50)
	public String getAdTitle2() {
		return this.adTitle2;
	}

	public void setAdTitle2(String adTitle2) {
		this.adTitle2 = adTitle2;
	}

	@Column(name = "adurl2", length = 200)
	public String getAdUrl2() {
		return this.adUrl2;
	}

	public void setAdUrl2(String adUrl2) {
		this.adUrl2 = adUrl2;
	}

	@Column(name = "customtext", length = 500)
	public String getCustomText() {
		return this.customText;
	}

	public void setCustomText(String customText) {
		this.customText = customText;
	}

	@Column(name = "customstat", length = 500)
	public String getCustomStat() {
		return this.customStat;
	}

	public void setCustomStat(String customStat) {
		this.customStat = customStat;
	}

	@Column(name = "showonsale", nullable = false)
	public Boolean getShowOnSale() {
		return this.showOnSale;
	}

	public void setShowOnSale(Boolean showOnSale) {
		this.showOnSale = showOnSale;
	}

	@Column(name = "showpopup", nullable = false)
	public Boolean getShowPopUp() {
		return this.showPopUp;
	}

	public void setShowPopUp(Boolean showPopUp) {
		this.showPopUp = showPopUp;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "zunmiParksetting")
	public Set<ZunmiDomainParking> getZunmiDomainparkings() {
		return this.zunmiDomainparkings;
	}

	public void setZunmiDomainparkings(
			Set<ZunmiDomainParking> zunmiDomainparkings) {
		this.zunmiDomainparkings = zunmiDomainparkings;
	}

}