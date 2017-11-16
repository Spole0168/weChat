package com.zhenjinzi.yzy.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name="zunmi_rolepermission"
    ,catalog="zunmi"
)

public class ZunmiRolePermission  implements java.io.Serializable {


    // Fields    

	private static final long serialVersionUID = 1L;
	private ZunmiRolePermissionId id;

    // Property accessors
    @EmbeddedId
    @AttributeOverrides( {
        @AttributeOverride(name="roleId", column=@Column(name="roleid", nullable=false) ), 
        @AttributeOverride(name="permissionId", column=@Column(name="permissionid", nullable=false) ) } )

    public ZunmiRolePermissionId getId() {
        return this.id;
    }
    
    public void setId(ZunmiRolePermissionId id) {
        this.id = id;
    }
   








}