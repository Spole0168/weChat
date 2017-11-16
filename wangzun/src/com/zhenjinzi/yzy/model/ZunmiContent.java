package com.zhenjinzi.yzy.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zhenjinzi.yzy.model.enums.ContentChecked;

@Entity
@Table(name = "zunmi_content", catalog = "zunmi")
public class ZunmiContent implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	private Integer id;
	private ZunmiNode zunmiNode;
	private YzyAdmin zunmiAdminByOperatorId;
	private YzyAdmin zunmiAdminByLastEditUserId;
	private Timestamp addDate;
	private Timestamp lastEditDate;
	private Integer taxis;
	private ContentChecked isChecked;
	private Integer checkedLevel;
	private String title;
	private String subTitle;
	private String imageUrl;
	private String summary;
	private String linkUrl;
	private String author;
	private String source;
	private String fileUrl;
	private Boolean isRecommend;
	private Boolean isRecommend2;
	private Boolean isHot;
	private Boolean isColor;
	private Boolean isTop;
	private Boolean isRoll;
	private Boolean isPic;
	private Boolean isSlideShow;
	private String content;
	private String tags;
	private String stringXml;
	private Integer hits;
	private Integer hitsByDay;
	private Integer hitsByWeek;
	private Integer hitsByMonth;
	private Timestamp lastHitDate;

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
	@JoinColumn(name = "nodeid", nullable = false)
	public ZunmiNode getZunmiNode() {
		return this.zunmiNode;
	}

	public void setZunmiNode(ZunmiNode zunmiNode) {
		this.zunmiNode = zunmiNode;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "operatorid")
	public YzyAdmin getZunmiAdminByOperatorId() {
		return this.zunmiAdminByOperatorId;
	}

	public void setZunmiAdminByOperatorId(YzyAdmin zunmiAdminByOperatorId) {
		this.zunmiAdminByOperatorId = zunmiAdminByOperatorId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lastedituserid")
	public YzyAdmin getZunmiAdminByLastEditUserId() {
		return this.zunmiAdminByLastEditUserId;
	}

	public void setZunmiAdminByLastEditUserId(
			YzyAdmin zunmiAdminByLastEditUserId) {
		this.zunmiAdminByLastEditUserId = zunmiAdminByLastEditUserId;
	}

	@Column(name = "adddate", length = 19)
	public Timestamp getAddDate() {
		return this.addDate;
	}

	public void setAddDate(Timestamp addDate) {
		this.addDate = addDate;
	}

	@Column(name = "lasteditdate", length = 19)
	public Timestamp getLastEditDate() {
		return this.lastEditDate;
	}

	public void setLastEditDate(Timestamp lastEditDate) {
		this.lastEditDate = lastEditDate;
	}

	@Column(name = "taxis")
	public Integer getTaxis() {
		return this.taxis;
	}

	public void setTaxis(Integer taxis) {
		this.taxis = taxis;
	}

	@Column(name = "ischecked", nullable=false)
	@Enumerated(EnumType.STRING)
	public ContentChecked getIsChecked() {
		return this.isChecked;
	}

	public void setIsChecked(ContentChecked isChecked) {
		this.isChecked = isChecked;
	}

	@Column(name = "checkedlevel")
	public Integer getCheckedLevel() {
		return this.checkedLevel;
	}

	public void setCheckedLevel(Integer checkedLevel) {
		this.checkedLevel = checkedLevel;
	}

	@Column(name = "title", length = 500)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "subtitle", length = 500)
	public String getSubTitle() {
		return this.subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	@Column(name = "imageurl", length = 200)
	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Column(name = "summary", length = 65535)
	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	@Column(name = "linkurl", length = 200)
	public String getLinkUrl() {
		return this.linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	@Column(name = "author", length = 100)
	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Column(name = "source", length = 500)
	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Column(name = "fileurl", length = 200)
	public String getFileUrl() {
		return this.fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	@Column(name = "isrecommend",nullable = false)
	public Boolean getIsRecommend() {
		return this.isRecommend;
	}

	public void setIsRecommend(Boolean isRecommend) {
		this.isRecommend = isRecommend;
	}

	@Column(name = "isrecommend2",nullable = false)
	public Boolean getIsRecommend2() {
		return isRecommend2;
	}

	public void setIsRecommend2(Boolean isRecommend2) {
		this.isRecommend2 = isRecommend2;
	}

	@Column(name = "ishot",nullable = false)
	public Boolean getIsHot() {
		return this.isHot;
	}

	public void setIsHot(Boolean isHot) {
		this.isHot = isHot;
	}

	@Column(name = "iscolor",nullable = false)
	public Boolean getIsColor() {
		return this.isColor;
	}

	public void setIsColor(Boolean isColor) {
		this.isColor = isColor;
	}

	@Column(name = "istop",nullable = false)
	public Boolean getIsTop() {
		return this.isTop;
	}

	public void setIsTop(Boolean isTop) {
		this.isTop = isTop;
	}

	@Column(name = "isroll",nullable = false)
	public Boolean getIsRoll() {
		return isRoll;
	}

	public void setIsRoll(Boolean isRoll) {
		this.isRoll = isRoll;
	}

	@Column(name = "ispic",nullable = false)
	public Boolean getIsPic() {
		return isPic;
	}

	public void setIsPic(Boolean isPic) {
		this.isPic = isPic;
	}

	@Column(name = "isslideshow",nullable = false)
	public Boolean getIsSlideShow() {
		return isSlideShow;
	}

	public void setIsSlideShow(Boolean isSlideShow) {
		this.isSlideShow = isSlideShow;
	}

	@Column(name = "content", length = 65535)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name="tags",length=500)
	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	@Column(name = "stringxml", length = 65535)
	public String getStringXml() {
		return this.stringXml;
	}

	public void setStringXml(String stringXml) {
		this.stringXml = stringXml;
	}

	@Column(name = "hits")
	public Integer getHits() {
		return this.hits;
	}

	public void setHits(Integer hits) {
		this.hits = hits;
	}

	@Column(name = "hitsbyday")
	public Integer getHitsByDay() {
		return this.hitsByDay;
	}

	public void setHitsByDay(Integer hitsByDay) {
		this.hitsByDay = hitsByDay;
	}

	@Column(name = "hitsbyweek")
	public Integer getHitsByWeek() {
		return this.hitsByWeek;
	}

	public void setHitsByWeek(Integer hitsByWeek) {
		this.hitsByWeek = hitsByWeek;
	}

	@Column(name = "hitsbymonth")
	public Integer getHitsByMonth() {
		return this.hitsByMonth;
	}

	public void setHitsByMonth(Integer histByMonth) {
		this.hitsByMonth = histByMonth;
	}

	@Column(name = "lasthitdate", length = 19)
	public Timestamp getLastHitDate() {
		return this.lastHitDate;
	}

	public void setLastHitDate(Timestamp lastHitDate) {
		this.lastHitDate = lastHitDate;
	}

}