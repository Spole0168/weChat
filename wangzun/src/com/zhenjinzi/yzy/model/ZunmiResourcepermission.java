package com.zhenjinzi.yzy.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * ZunmiResourcepermission entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "zunmi_resourcepermission", catalog = "zunmi")
public class ZunmiResourcepermission implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	private ZunmiResourcepermissionId id;

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "resourceId", column = @Column(name = "resourceid", nullable = false)),
			@AttributeOverride(name = "permissionId", column = @Column(name = "permissionid", nullable = false)) })
	public ZunmiResourcepermissionId getId() {
		return this.id;
	}

	public void setId(ZunmiResourcepermissionId id) {
		this.id = id;
	}

}