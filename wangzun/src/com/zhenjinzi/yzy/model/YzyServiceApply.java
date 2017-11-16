package com.zhenjinzi.yzy.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "yzy_serviceapplyinfo", catalog = "")
public class YzyServiceApply  implements java.io.Serializable{
	private Integer id;//	主键	int(11)unsigned
	private String communityName;//	申请社区名称	varchar(500)
	private String communityAddress;//	地址	varchar(300)
	private Integer communitPersonNum;//	社区业主数量	int(11)unsigned
	private String communitRate;//	入住率	varchar(30)
	private String waterSource;//	居民直饮水主要来源	varchar(300)
	private Integer machineNum;//	社区直饮水机数量	int(11)unsigned
	private String mechinePrice;//	直饮水机的价格	varchar(30)
	private String waterQuality;// 	本社区水质情况	varchar(300)
	private String waterTDS;//	TDS	varchar(30)
	private String other;//	其他	varchar(300)
	private String applyBusineeslic;//	申请者有无营业执照	varchar(30)
	private String applyOrgName;//	申请者单位姓名	varchar(300)
	private String applyProject;//	申请者主要经营项目	varchar(500)
	private String applyContactsource;//	申请者主要客户资源	varchar(500)
	private Integer applyStaffnum;//	申请者员工人数	int(11)unsigned
	private String isIstallOK	;//经过培训是否可以完成安装服务	varchar(30)
	private String applyOrgPerson;//	申请人单位联系人	varchar(100)
	private String applyTelphone;//	电话	varchar(40)
	private String applyEmail;//	邮箱	varchar(300)
	private Timestamp applyDate;//申请时间
	private String applyeName;//	申请人	varchar(100)
	private String text1;//		varchar(300);
	private String text2;//		varchar(300);
	private String text3;//		varchar(300);
	private String text4;//		varchar(300);
	private String text5;//		20160313统一用来做 逻辑删除   1 代表正常   2代表删除
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "communityname", length = 500)
	public String getCommunityName() {
		return communityName;
	}
	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}
	@Column(name = "communityaddress", length = 300)
	public String getCommunityAddress() {
		return communityAddress;
	}
	public void setCommunityAddress(String communityAddress) {
		this.communityAddress = communityAddress;
	}
	@Column(name = "communitpersonnum", length = 11)
	public Integer getCommunitPersonNum() {
		return communitPersonNum;
	}
	public void setCommunitPersonNum(Integer communitPersonNum) {
		this.communitPersonNum = communitPersonNum;
	}
	@Column(name = "communitrate", length = 30)
	public String getCommunitRate() {
		return communitRate;
	}
	public void setCommunitRate(String communitRate) {
		this.communitRate = communitRate;
	}
	@Column(name = "watersource", length = 300)
	public String getWaterSource() {
		return waterSource;
	}
	public void setWaterSource(String waterSource) {
		this.waterSource = waterSource;
	}
	@Column(name = "machinenum", length = 11)
	public Integer getMachineNum() {
		return machineNum;
	}
	public void setMachineNum(Integer machineNum) {
		this.machineNum = machineNum;
	}
	@Column(name = "mechineprice", length = 30)
	public String getMechinePrice() {
		return mechinePrice;
	}
	public void setMechinePrice(String mechinePrice) {
		this.mechinePrice = mechinePrice;
	}
	@Column(name = "waterquality", length = 300)
	public String getWaterQuality() {
		return waterQuality;
	}
	public void setWaterQuality(String waterQuality) {
		this.waterQuality = waterQuality;
	}
	@Column(name = "watertds", length = 30)
	public String getWaterTDS() {
		return waterTDS;
	}
	public void setWaterTDS(String waterTDS) {
		this.waterTDS = waterTDS;
	}
	@Column(name = "other", length = 300)
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	@Column(name = "applybusineeslic", length = 30)
	public String getApplyBusineeslic() {
		return applyBusineeslic;
	}
	public void setApplyBusineeslic(String applyBusineeslic) {
		this.applyBusineeslic = applyBusineeslic;
	}
	@Column(name = "applyorgname", length = 300)
	public String getApplyOrgName() {
		return applyOrgName;
	}
	public void setApplyOrgName(String applyOrgName) {
		this.applyOrgName = applyOrgName;
	}
	@Column(name = "applyproject", length = 500)
	public String getApplyProject() {
		return applyProject;
	}
	public void setApplyProject(String applyProject) {
		this.applyProject = applyProject;
	}
	@Column(name = "applycontactsource", length = 500)
	public String getApplyContactsource() {
		return applyContactsource;
	}
	public void setApplyContactsource(String applyContactsource) {
		this.applyContactsource = applyContactsource;
	}
	@Column(name = "applystaffnum", length = 11)
	public Integer getApplyStaffnum() {
		return applyStaffnum;
	}
	public void setApplyStaffnum(Integer applyStaffnum) {
		this.applyStaffnum = applyStaffnum;
	}
	@Column(name = "isistallok", length = 30)
	public String getIsIstallOK() {
		return isIstallOK;
	}
	public void setIsIstallOK(String isIstallOK) {
		this.isIstallOK = isIstallOK;
	}
	@Column(name = "applyorgperson", length = 100)
	public String getApplyOrgPerson() {
		return applyOrgPerson;
	}
	public void setApplyOrgPerson(String applyOrgPerson) {
		this.applyOrgPerson = applyOrgPerson;
	}
	@Column(name = "applytelphone", length = 40)
	public String getApplyTelphone() {
		return applyTelphone;
	}
	public void setApplyTelphone(String applyTelphone) {
		this.applyTelphone = applyTelphone;
	}
	@Column(name = "applyemail", length = 300)
	public String getApplyEmail() {
		return applyEmail;
	}
	public void setApplyEmail(String applyEmail) {
		this.applyEmail = applyEmail;
	}
	@Column(name = "applydate", length = 20)
	public Timestamp getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(Timestamp applyDate) {
		this.applyDate = applyDate;
	}
	@Column(name = "applyename", length = 100)
	public String getApplyeName() {
		return applyeName;
	}
	public void setApplyeName(String applyeName) {
		this.applyeName = applyeName;
	}
	@Column(name = "text1", length = 300)
	public String getText1() {
		return text1;
	}
	public void setText1(String text1) {
		this.text1 = text1;
	}
	@Column(name = "text2", length = 300)
	public String getText2() {
		return text2;
	}
	public void setText2(String text2) {
		this.text2 = text2;
	}
	@Column(name = "text3", length = 300)
	public String getText3() {
		return text3;
	}
	
	public void setText3(String text3) {
		this.text3 = text3;
	}
	@Column(name = "text4", length = 300)
	public String getText4() {
		return text4;
	}
	public void setText4(String text4) {
		this.text4 = text4;
	}
	@Column(name = "text5", length = 300)
	public String getText5() {
		return text5;
	}
	public void setText5(String text5) {
		this.text5 = text5;
	}
	


	
}
