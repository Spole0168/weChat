package com.zhenjinzi.yzy.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.zhenjinzi.yzy.model.enums.IdentityType;
import com.zhenjinzi.yzy.model.enums.MailSubscribe;
import com.zhenjinzi.yzy.model.enums.MessageSubscribe;
import com.zhenjinzi.yzy.model.enums.UserType;

@Entity
@Table(name = "zunmi_userdetail", catalog = "zunmi")
public class ZunmiUserDetail implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	private Integer id;
	private ZunmiUser zunmiUser;
	private String sex;
	private String alterEmail;
	private Date birthday;
	private String country;
	private UserType userType;
	private String enterpriseName;
	private String province;
	private String city;
	private String street;
	private String name;
	private String postal;
	private IdentityType identityType;
	private String identity;
	private String telphone;
	private String fax;
	private String gtalk;
	private String qq;
	private String msn;
	private String website;
	private MessageSubscribe msgSubscribe;
	private MailSubscribe mailSubscribe;

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

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userid", nullable = false)
	public ZunmiUser getZunmiUser() {
		return this.zunmiUser;
	}

	public void setZunmiUser(ZunmiUser zunmiUser) {
		this.zunmiUser = zunmiUser;
	}

	@Column(name = "sex", length = 6)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "alteremail", length = 100)
	public String getAlterEmail() {
		return this.alterEmail;
	}

	public void setAlterEmail(String alterEmail) {
		this.alterEmail = alterEmail;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "birthday", length = 10)
	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Column(name = "country", length = 50)
	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Column(name = "usertype", nullable = false, length = 11)
	@Enumerated(EnumType.STRING)
	public UserType getUserType() {
		return this.userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	@Column(name = "enterprisename", length = 100)
	public String getEnterpriseName() {
		return this.enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	@Column(name = "province", length = 50)
	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(name = "city", length = 50)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "street", length = 100)
	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	@Column(name = "name", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "postal", length = 10)
	public String getPostal() {
		return this.postal;
	}

	public void setPostal(String postal) {
		this.postal = postal;
	}

	@Column(name = "identitytype", length = 16)
	@Enumerated(EnumType.STRING)
	public IdentityType getIdentityType() {
		return this.identityType;
	}

	public void setIdentityType(IdentityType identityType) {
		this.identityType = identityType;
	}

	@Column(name = "identity", length = 30)
	public String getIdentity() {
		return this.identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	@Column(name = "telphone", length = 20)
	public String getTelphone() {
		return this.telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	@Column(name = "fax", length = 20)
	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "gtalk", length = 20)
	public String getGtalk() {
		return this.gtalk;
	}

	public void setGtalk(String gtalk) {
		this.gtalk = gtalk;
	}

	@Column(name = "qq", length = 20)
	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	@Column(name = "msn", length = 100)
	public String getMsn() {
		return this.msn;
	}

	public void setMsn(String msn) {
		this.msn = msn;
	}

	@Column(name = "website", length = 100)
	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	@Column(name = "msgsubscribe", nullable = false, length = 11)
	@Enumerated(EnumType.STRING)
	public MessageSubscribe getMsgSubscribe() {
		return this.msgSubscribe;
	}

	public void setMsgSubscribe(MessageSubscribe msgSubscribe) {
		this.msgSubscribe = msgSubscribe;
	}

	@Column(name = "mailsubscribe", nullable = false, length = 11)
	@Enumerated(EnumType.STRING)
	public MailSubscribe getMailSubscribe() {
		return this.mailSubscribe;
	}

	public void setMailSubscribe(MailSubscribe mailSubscribe) {
		this.mailSubscribe = mailSubscribe;
	}

}