package com.zhenjinzi.yzy.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class ZunmiAdminPermissionId  implements java.io.Serializable {


    // Fields    

	private static final long serialVersionUID = 1L;
	private Integer adminId;
    private Integer permissionId;

   
    // Property accessors

    @Column(name="adminid", nullable=false)

    public Integer getAdminId() {
        return this.adminId;
    }
    
    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    @Column(name="permissionid", nullable=false)

    public Integer getPermissionId() {
        return this.permissionId;
    }
    
    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof ZunmiAdminPermissionId) ) return false;
		 ZunmiAdminPermissionId castOther = ( ZunmiAdminPermissionId ) other; 
         
		 return ( (this.getAdminId()==castOther.getAdminId()) || ( this.getAdminId()!=null && castOther.getAdminId()!=null && this.getAdminId().equals(castOther.getAdminId()) ) )
 && ( (this.getPermissionId()==castOther.getPermissionId()) || ( this.getPermissionId()!=null && castOther.getPermissionId()!=null && this.getPermissionId().equals(castOther.getPermissionId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getAdminId() == null ? 0 : this.getAdminId().hashCode() );
         result = 37 * result + ( getPermissionId() == null ? 0 : this.getPermissionId().hashCode() );
         return result;
   }   





}