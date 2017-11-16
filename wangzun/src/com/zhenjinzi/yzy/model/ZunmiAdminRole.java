package com.zhenjinzi.yzy.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name="zunmi_userrole",catalog="zunmi")
public class ZunmiAdminRole  implements java.io.Serializable {


    // Fields    

	private static final long serialVersionUID = 1L;
	private ZunmiAdminRoleId id;


    // Property accessors
    @EmbeddedId
    @AttributeOverrides( {
    @AttributeOverride(name="roleId", column=@Column(name="RoleID", nullable=false) ), 
    @AttributeOverride(name="adminId", column=@Column(name="AdminID", nullable=false) ) } )
    public ZunmiAdminRoleId getId() {
        return this.id;
    }
    
    public void setId(ZunmiAdminRoleId id) {
        this.id = id;
    }
   








}