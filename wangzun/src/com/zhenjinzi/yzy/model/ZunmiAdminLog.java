package com.zhenjinzi.yzy.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "zunmi_adminlog", catalog = "zunmi")
public class ZunmiAdminLog implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	private Integer id;
	private YzyAdmin zunmiAdmin;
	private Integer ipaddress;
	private Timestamp logTime;
	private Boolean success;
	private String type;


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
	@JoinColumn(name = "adminid", nullable = false)
	public YzyAdmin getZunmiAdmin() {
		return this.zunmiAdmin;
	}

	public void setZunmiAdmin(YzyAdmin zunmiAdmin) {
		this.zunmiAdmin = zunmiAdmin;
	}

	@Column(name = "ipaddress")
	public Integer getIpaddress() {
		return this.ipaddress;
	}

	public void setIpaddress(Integer ipaddress) {
		this.ipaddress = ipaddress;
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

	@Column(name = "type", length = 21)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}