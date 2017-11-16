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

import com.zhenjinzi.yzy.model.enums.UserLogType;

@Entity
@Table(name = "zunmi_userlog", catalog = "zunmi")
public class ZunmiUserLog implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	private Integer id;
	private ZunmiUser zunmiUser;
	private String ipAddress;
	private Timestamp logTime;
	private Boolean success;
	private UserLogType type;

	//Constructor
	public ZunmiUserLog(){}
	public ZunmiUserLog(String ipAddress,Boolean success,UserLogType type,ZunmiUser zunmiUser){
		this.ipAddress = ipAddress;
		this.success = success;
		this.type = type;
		this.zunmiUser =  zunmiUser;
		this.logTime = new Timestamp(System.currentTimeMillis());
	}
	
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
	@JoinColumn(name = "UserID", nullable = false)
	public ZunmiUser getZunmiUser() {
		return this.zunmiUser;
	}

	public void setZunmiUser(ZunmiUser zunmiUser) {
		this.zunmiUser = zunmiUser;
	}

	@Column(name = "ipaddress", length = 100)
	public String getIpAddress() {
		return this.ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	@Column(name = "logtime", nullable = false, length = 19)
	public Timestamp getLogTime() {
		return this.logTime;
	}

	public void setLogTime(Timestamp logTime) {
		this.logTime = logTime;
	}

	@Column(name = "success", nullable = false)
	public Boolean getSuccess() {
		return this.success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	@Column(name = "type", nullable = false)
	@Enumerated(EnumType.STRING)
	public UserLogType getType() {
		return this.type;
	}

	public void setType(UserLogType type) {
		this.type = type;
	}

}