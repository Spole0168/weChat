package com.zhenjinzi.yzy.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.property.core.modules.utils.ReflectionUtils;

@Entity
@Table(name = "zunmi_role", catalog = "zunmi")
public class ZunmiRole implements java.io.Serializable {

	
	// Fields
	
	private static final long serialVersionUID = 1L;
	private Integer roleId;
	private String roleName;
	private Set<ZunmiPermission> permissions = new HashSet<ZunmiPermission>(0);

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "roleid", unique = true, nullable = false)
	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Column(name = "rolename", length = 30)
	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	@ManyToMany(targetEntity = ZunmiPermission.class, cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@JoinTable(name = "zunmi_rolepermission", joinColumns = @JoinColumn(name = "roleid"), inverseJoinColumns = @JoinColumn(name = "permissionid", updatable = false))
	public Set<ZunmiPermission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<ZunmiPermission> permissionList) {
		this.permissions = permissionList;
	}

	public void addPermissions(ZunmiPermission permission) {
		this.permissions.add(permission);
	}
	
	@Transient
	public String getAuthNames() {
		return ReflectionUtils.convertElementPropertyToString(permissions,
				"permissionName", ", ");
	}

	@Transient
	@SuppressWarnings("unchecked")
	public List<Long> getAuthIds() {
		return ReflectionUtils.convertElementPropertyToList(permissions,
				"permissionId");
	}
}