package com.zhenjinzi.yzy.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.List;

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
import javax.persistence.Table;
import javax.persistence.Transient;

import org.property.core.modules.utils.ReflectionUtils;

import com.google.common.collect.Lists;
import com.zhenjinzi.yzy.model.enums.ResourceType;

@Entity
@Table(name = "zunmi_resource", catalog = "zunmi")
public class ZunmiResource implements java.io.Serializable {
	
	// Fields

	private static final long serialVersionUID = 1L;
	private Integer resourceId;
	private String position;
	private ResourceType type;
	private String value;
	private List<ZunmiPermission> permissions = Lists.newArrayList();

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "resourceid", unique = true, nullable = false)
	public Integer getResourceId() {
		return this.resourceId;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}

	@Column(name = "position", length = 50)
	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Column(name = "type", nullable=false)
	@Enumerated(EnumType.STRING)
	public ResourceType getType() {
		return this.type;
	}

	public void setType(ResourceType type) {
		this.type = type;
	}

	@Column(name = "value", length = 50)
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "zunmi_resourcepermission", joinColumns = { @JoinColumn(name = "resourceid", updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "permissionid", updatable = false) })
	public List<ZunmiPermission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<ZunmiPermission> permissions) {
		this.permissions = permissions;
	}
	
	public void addPermission(ZunmiPermission permission) {
		this.permissions.add(permission);
	}
	
	@Transient
	public String getAuthNames() {
		return ReflectionUtils.convertElementPropertyToString(permissions,
				"authorityName", ",");
	}
	@Transient
	public String getPrefixedAuthNames() {
		return ReflectionUtils.convertElementPropertyToString(permissions,
				"prefixedName", ",");
	}

}