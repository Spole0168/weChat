package com.zhenjinzi.yzy.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name="zunmi_userpermission",catalog="zunmi")

public class ZunmiAdminPermission  implements java.io.Serializable {


    // Fields    

	private static final long serialVersionUID = 1L;
	private ZunmiAdminPermissionId id;
    private Boolean has;

   
    // Property accessors
    @EmbeddedId
    @AttributeOverrides( {
    @AttributeOverride(name="adminId", column=@Column(name="adminid", nullable=false) ), 
    @AttributeOverride(name="permissionId", column=@Column(name="permissionid", nullable=false) ) } )
    public ZunmiAdminPermissionId getId() {
        return this.id;
    }
    
    public void setId(ZunmiAdminPermissionId id) {
        this.id = id;
    }
    
    @Column(name="has", nullable=false)

    public Boolean getHas() {
        return this.has;
    }
    
    public void setHas(Boolean has) {
        this.has = has;
    }
   








}