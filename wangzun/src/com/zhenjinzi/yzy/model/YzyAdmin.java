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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.zhenjinzi.yzy.model.enums.AdminStatus;

@Entity
@Table(name = "yzy_admin", catalog = "")
public class YzyAdmin implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String userName;
	private String displayName;
	private String password;
	private String email;
	private String question;
	private String answer;
	private Timestamp creationDate;
	private String creatorUserName;
	private Timestamp lastActivityDate;
	private Integer countOfLogin;
	private AdminStatus status;
	
	/**多多的关联关系不需要了*/
//	private Set<ZunmiContent> zunmiContentsForOperatorId = new HashSet<ZunmiContent>(
//			0);
//	private Set<ZunmiRole> zunmiRoles = new HashSet<ZunmiRole>(0);
//	private Set<ZunmiContent> zunmiContentsForLastEditUserId = new HashSet<ZunmiContent>(
//			0);
//	private Set<ZunmiWithdraw> zunmiWithdraws = new HashSet<ZunmiWithdraw>(0);
//	private Set<ZunmiNode> zunmiNodes = new HashSet<ZunmiNode>(0);
//	private Set<ZunmiQuestion> zunmiQuestions = new HashSet<ZunmiQuestion>(0);
//	private Set<ZunmiAdminLog> zunmiAdminLogs = new HashSet<ZunmiAdminLog>(0);

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

	@Column(name = "username", nullable = false, length = 50)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "displayname", nullable = false, length = 50)
	public String getDisplayName() {
		return this.displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@Column(name = "password", nullable = false, length = 50)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "email", nullable = false, length = 100)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "question", length = 50)
	public String getQuestion() {
		return this.question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	@Column(name = "answer", length = 50)
	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Column(name = "creationdate", nullable = false, length = 19)
	public Timestamp getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	@Column(name = "creatorusername", length = 50)
	public String getCreatorUserName() {
		return this.creatorUserName;
	}

	public void setCreatorUserName(String creatorUserName) {
		this.creatorUserName = creatorUserName;
	}

	@Column(name = "lastactivitydate", length = 19)
	public Timestamp getLastActivityDate() {
		return this.lastActivityDate;
	}

	public void setLastActivityDate(Timestamp lastActivityDate) {
		this.lastActivityDate = lastActivityDate;
	}

	@Column(name = "countoflogin", nullable = false)
	public Integer getCountOfLogin() {
		return this.countOfLogin;
	}

	public void setCountOfLogin(Integer countOfLogin) {
		this.countOfLogin = countOfLogin;
	}

	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
	public AdminStatus getStatus() {
		return this.status;
	}

	public void setStatus(AdminStatus status) {
		this.status = status;
	}

//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "zunmiAdminByOperatorId")
//	public Set<ZunmiContent> getZunmiContentsForOperatorId() {
//		return this.zunmiContentsForOperatorId;
//	}
//
//	public void setZunmiContentsForOperatorId(Set<ZunmiContent> zunmiContentsForOperatorId) {
//		this.zunmiContentsForOperatorId = zunmiContentsForOperatorId;
//	}

//	// 多对多定义
//	@ManyToMany
//	// 中间表定义,表名采用默认命名规则
//	@JoinTable(name = "zunmi_adminrole", joinColumns = { @JoinColumn(name = "adminid") }, inverseJoinColumns = { @JoinColumn(name = "roleid") })
//	// Fecth策略定义
//	@Fetch(FetchMode.SUBSELECT)
//	// 集合按id排序.
//	@OrderBy("roleId")
//	// 集合中对象id的缓存.
//	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
//	public Set<ZunmiRole> getZunmiRoles() {
//		return this.zunmiRoles;
//	}
//
//	public void setZunmiRoles(Set<ZunmiRole> zunmiRoles) {
//		this.zunmiRoles = zunmiRoles;
//	}
//
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "zunmiAdminByLastEditUserId")
//	public Set<ZunmiContent> getZunmiContentsForLastEditUserId() {
//		return this.zunmiContentsForLastEditUserId;
//	}
//
//	public void setZunmiContentsForLastEditUserId(
//			Set<ZunmiContent> zunmiContentsForLastEditUserId) {
//		this.zunmiContentsForLastEditUserId = zunmiContentsForLastEditUserId;
//	}
//
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "zunmiAdmin")
//	public Set<ZunmiWithdraw> getZunmiWithdraws() {
//		return this.zunmiWithdraws;
//	}
//
//	public void setZunmiWithdraws(Set<ZunmiWithdraw> zunmiWithdraws) {
//		this.zunmiWithdraws = zunmiWithdraws;
//	}
//
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "zunmiAdmin")
//	public Set<ZunmiNode> getZunmiNodes() {
//		return this.zunmiNodes;
//	}
//
//	public void setZunmiNodes(Set<ZunmiNode> zunmiNodes) {
//		this.zunmiNodes = zunmiNodes;
//	}
//
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "zunmiAdmin")
//	public Set<ZunmiQuestion> getZunmiQuestions() {
//		return this.zunmiQuestions;
//	}
//
//	public void setZunmiQuestions(Set<ZunmiQuestion> zunmiQuestions) {
//		this.zunmiQuestions = zunmiQuestions;
//	}
//
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "zunmiAdmin")
//	public Set<ZunmiAdminLog> getZunmiAdminlogs() {
//		return this.zunmiAdminLogs;
//	}
//
//	public void setZunmiAdminlogs(Set<ZunmiAdminLog> zunmiAdminLogs) {
//		this.zunmiAdminLogs = zunmiAdminLogs;
//	}

}