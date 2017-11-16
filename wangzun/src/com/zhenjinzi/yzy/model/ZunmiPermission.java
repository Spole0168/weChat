package com.zhenjinzi.yzy.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "zunmi_permission", catalog = "zunmi")
public class ZunmiPermission implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields
	/**
	 * SpringSecurity中默认的角色/授权名前缀.
	 */
	public static final String AUTHORITY_PREFIX = "ROLE_";
	private Integer permissionId;
	private String permissionName;


	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "permissionid", unique = true, nullable = false)
	public Integer getPermissionId() {
		return this.permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}

	@Column(name = "permissionname", nullable = false, length = 100)
	public String getPermissionName() {
		return this.permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}
	
	@Transient
	public String getPrefixedName() {
		return AUTHORITY_PREFIX + permissionName;
	}
	

}