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
@Table(name = "zunmi_node", catalog = "zunmi")
public class ZunmiNode implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	private Integer id;
	private YzyAdmin zunmiAdmin;
	private String nodeName;
	private Integer parentId;
	private String parentsPath;
	private Integer parentsCount;
	private Integer childrenCount;
	private Boolean isLastNode;
	private String nodeIndexName;
	private Integer taxis;
	private Timestamp addDate;
	private String imageUrl;
	private String content;
	private Integer contentNum;
	private String filePath;
	private String channleFilePathRule;
	private String contentFilePathRule;
	private String extendValues;
	private Integer contentTemplate;
	private Integer channelTemplate;
	private String linkUrl;
	private String linkType;
	private String keywords;
	private String description;
	private Set<ZunmiContent> zunmiContents = new HashSet<ZunmiContent>(0);
	private Set<ZunmiUserBoard> zunmiUserBoards = new HashSet<ZunmiUserBoard>(0);


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
	@JoinColumn(name = "adduser")
	public YzyAdmin getZunmiAdmin() {
		return this.zunmiAdmin;
	}

	public void setZunmiAdmin(YzyAdmin zunmiAdmin) {
		this.zunmiAdmin = zunmiAdmin;
	}

	@Column(name = "nodename", nullable = false, length = 100)
	public String getNodeName() {
		return this.nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	@Column(name = "parentid")
	public Integer getParentId() {
		return this.parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	@Column(name = "parentspath", length = 200)
	public String getParentsPath() {
		return this.parentsPath;
	}

	public void setParentsPath(String parentsPath) {
		this.parentsPath = parentsPath;
	}

	@Column(name = "parentscount")
	public Integer getParentsCount() {
		return this.parentsCount;
	}

	public void setParentsCount(Integer parentsCount) {
		this.parentsCount = parentsCount;
	}

	@Column(name = "childrencount")
	public Integer getChildrenCount() {
		return this.childrenCount;
	}

	public void setChildrenCount(Integer childrenCount) {
		this.childrenCount = childrenCount;
	}

	@Column(name = "islastnode", nullable = false)
	public Boolean getIsLastNode() {
		return this.isLastNode;
	}

	public void setIsLastNode(Boolean isLastNode) {
		this.isLastNode = isLastNode;
	}

	@Column(name = "nodeindexname", length = 500)
	public String getNodeIndexName() {
		return this.nodeIndexName;
	}

	public void setNodeIndexName(String nodeIndexName) {
		this.nodeIndexName = nodeIndexName;
	}

	@Column(name = "taxis")
	public Integer getTaxis() {
		return this.taxis;
	}

	public void setTaxis(Integer taxis) {
		this.taxis = taxis;
	}

	@Column(name = "adddate", nullable = false, length = 19)
	public Timestamp getAddDate() {
		return this.addDate;
	}

	public void setAddDate(Timestamp addDate) {
		this.addDate = addDate;
	}

	@Column(name = "imageurl", length = 200)
	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Column(name = "content", length = 65535)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "contentnum")
	public Integer getContentNum() {
		return this.contentNum;
	}

	public void setContentNum(Integer contentNum) {
		this.contentNum = contentNum;
	}

	@Column(name = "filepath", length = 200)
	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Column(name = "channlefilepathrule", length = 200)
	public String getChannleFilePathRule() {
		return this.channleFilePathRule;
	}

	public void setChannleFilePathRule(String channleFilePathRule) {
		this.channleFilePathRule = channleFilePathRule;
	}

	@Column(name = "contentfilepathrule", length = 200)
	public String getContentFilePathRule() {
		return this.contentFilePathRule;
	}

	public void setContentFilePathRule(String contentFilePathRule) {
		this.contentFilePathRule = contentFilePathRule;
	}

	@Column(name = "extendvalues", length = 65535)
	public String getExtendValues() {
		return this.extendValues;
	}

	public void setExtendValues(String extendValues) {
		this.extendValues = extendValues;
	}

	@Column(name = "contenttemplate")
	public Integer getContentTemplate() {
		return this.contentTemplate;
	}

	public void setContentTemplate(Integer contentTemplate) {
		this.contentTemplate = contentTemplate;
	}

	@Column(name = "channeltemplate")
	public Integer getChannelTemplate() {
		return this.channelTemplate;
	}

	public void setChannelTemplate(Integer channelTemplate) {
		this.channelTemplate = channelTemplate;
	}

	@Column(name = "linkurl", length = 200)
	public String getLinkUrl() {
		return this.linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	@Column(name = "linktype", length = 200)
	public String getLinkType() {
		return this.linkType;
	}

	public void setLinkType(String linkType) {
		this.linkType = linkType;
	}

	@Column(name = "keywords", length = 500)
	public String getKeywords() {
		return this.keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	@Column(name = "description", length = 500)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "zunmiNode")
	public Set<ZunmiContent> getZunmiContents() {
		return this.zunmiContents;
	}

	public void setZunmiContents(Set<ZunmiContent> zunmiContents) {
		this.zunmiContents = zunmiContents;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "zunmiNode")
	public Set<ZunmiUserBoard> getZunmiUserboards() {
		return this.zunmiUserBoards;
	}

	public void setZunmiUserboards(Set<ZunmiUserBoard> zunmiUserboards) {
		this.zunmiUserBoards = zunmiUserboards;
	}

}