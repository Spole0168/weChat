package com.zhenjinzi.yzy.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class ZunmiAdminRoleId  implements java.io.Serializable {


    // Fields    

	private static final long serialVersionUID = 1L;
	private Integer roleId;
    private Integer adminId;


    // Property accessors

    @Column(name="RoleID", nullable=false)

    public Integer getRoleId() {
        return this.roleId;
    }
    
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Column(name="AdminID", nullable=false)

    public Integer getAdminId() {
        return this.adminId;
    }
    
    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof ZunmiAdminRoleId) ) return false;
		 ZunmiAdminRoleId castOther = ( ZunmiAdminRoleId ) other; 
         
		 return ( (this.getRoleId()==castOther.getRoleId()) || ( this.getRoleId()!=null && castOther.getRoleId()!=null && this.getRoleId().equals(castOther.getRoleId()) ) )
 && ( (this.getAdminId()==castOther.getAdminId()) || ( this.getAdminId()!=null && castOther.getAdminId()!=null && this.getAdminId().equals(castOther.getAdminId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getRoleId() == null ? 0 : this.getRoleId().hashCode() );
         result = 37 * result + ( getAdminId() == null ? 0 : this.getAdminId().hashCode() );
         return result;
   }   





}