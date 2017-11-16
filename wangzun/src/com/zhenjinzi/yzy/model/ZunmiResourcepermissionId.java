package com.zhenjinzi.yzy.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ZunmiResourcepermissionId implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	private Integer resourceId;
	private Integer permissionId;

	// Property accessors

	@Column(name = "resourceid", nullable = false)
	public Integer getResourceId() {
		return this.resourceId;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}

	@Column(name = "permissionid", nullable = false)
	public Integer getAuthorityId() {
		return this.permissionId;
	}

	public void setAuthorityId(Integer permissionId) {
		this.permissionId = permissionId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ZunmiResourcepermissionId))
			return false;
		ZunmiResourcepermissionId castOther = (ZunmiResourcepermissionId) other;

		return ((this.getResourceId() == castOther.getResourceId()) || (this
				.getResourceId() != null && castOther.getResourceId() != null && this
				.getResourceId().equals(castOther.getResourceId())))
				&& ((this.getAuthorityId() == castOther.getAuthorityId()) || (this
						.getAuthorityId() != null
						&& castOther.getAuthorityId() != null && this
						.getAuthorityId().equals(castOther.getAuthorityId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getResourceId() == null ? 0 : this.getResourceId()
						.hashCode());
		result = 37
				* result
				+ (getAuthorityId() == null ? 0 : this.getAuthorityId()
						.hashCode());
		return result;
	}

}