package com.zhenjinzi.yzy.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *yzy_userInfo 用户表
 */
@Entity
@Table(name = "yzy_userinfo", catalog = "")
public class YzyUserinfo implements Serializable{

	private Integer  id ;//int(11) unsigned NOT NULL   auto_increment primary key,
	private String contact_name;// varchar(300),
	private String telphone;// varchar(40)	,
	private String instak_address;// varchar(500),	
	private String mechinename;// varchar(300),
	private String machineid;// varchar(300),
	private String cardno;// varchar(40),
	private String machetype;// 饮水机型号varchar(500),
	private String openid;// varchar(100),
	private String inputtds;// varchar(100),
	private String outputtds;// varchar(100),
	private String scaleflag;// varchar(10),
	private String judge;// varchar(10),
	private String taste;// varchar(10),
	private Timestamp active_Date;// datetime,
	private Timestamp create_Date;// datetime,
	private Integer servicetimes;// int(11)unsigned,
	private String text1;// varchar(300) zhuangji_id  
	private String text2;// varchar(300) pay,
	private String text3;// varchar(300) pay_by
	private String text4;//  varchar(300) DEFAULT NULL,2016-03-20  添加总的服务天数  365，允许向其增加天数和减少天数  
	private String text5;//  varchar(300)   20160313统一用来做 逻辑删除   1 代表正常   2代表删除
	private String text6;//仅仅用于显示
	private Set<YzyRepairlog> yzyRepairlogs=new HashSet<YzyRepairlog>();/**维修记录*/
	private Set<YzyAppoitmentcheck> yzyAppoitmentchecks=new HashSet<YzyAppoitmentcheck>();
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "contact_name", length = 300)
	public String getContact_name() {
		return contact_name;
	}
	public void setContact_name(String contact_name) {
		this.contact_name = contact_name;
	}
	@Column(name = "telphone", length = 40)
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	@Column(name = "cardno", length = 40)
	public String getCardno() {
		return cardno;
	}
	public void setCardno(String cardno) {
		this.cardno = cardno;
	}
	@Column(name = "instak_address", length = 500)
	public String getInstak_address() {
		return instak_address;
	}
	public void setInstak_address(String instak_address) {
		this.instak_address = instak_address;
	}
//	@Column(name = "openid", length = 100)
//	public String getOpenid() {
//		return openid;
//	}
//	public void setOpenid(String openid) {
//		this.openid = openid;
//	}
//	@Column(name = "active_Date", length = 20)
//	public Timestamp getActive_Date() {
//		return active_Date;
//	}
//	public void setActive_Date(Timestamp active_Date) {
//		this.active_Date = active_Date;
//	}
	@Column(name = "mechinename", length = 300)
	public String getMechinename() {
		return mechinename;
	}
	public void setMechinename(String mechinename) {
		this.mechinename = mechinename;
	}
	@Column(name = "machineid", length = 300)
	public String getMachineid() {
		return machineid;
	}
	public void setMachineid(String machineid) {
		this.machineid = machineid;
	}
//	@Column(name = "cardno", length = 40)
//	public String getCardno() {
//		return cardno;
//	}
//	public void setCardno(String cardno) {
//		this.cardno = cardno;
//	}
	@Column(name = "machetype", length = 500)
	public String getMachetype() {
		return machetype;
	}
	public void setMachetype(String machetype) {
		this.machetype = machetype;
	}
	@Column(name = "openid", length = 100)
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	@Column(name = "inputTDS", length = 100)
	public String getInputtds() {
		return inputtds;
	}
	public void setInputtds(String inputtds ) {
		this.inputtds = inputtds ;
	}
	@Column(name = "outputTDS", length = 100)
	public String getOutputtds () {
		return outputtds ;
	}
	public void setOutputtds(String outputtds) {
		this.outputtds  = outputtds ;
	}
	@Column(name = "scaleflag", length = 10)
	public String getScaleflag() {
		return scaleflag;
	}
	public void setScaleflag(String scaleflag) {
		this.scaleflag = scaleflag;
	}
	@Column(name = "judge", length = 10)
	public String getJudge() {
		return judge;
	}
	
	public void setJudge(String judge) {
		this.judge = judge;
	}
	@Column(name = "taste", length = 10)
	public String getTaste() {
		return taste;
	}
	public void setTaste(String taste) {
		this.taste = taste;
	}
	@Column(name = "active_Date", length = 20)
	public Timestamp getActive_Date() {
		return active_Date;
	}
	public void setActive_Date(Timestamp active_Date) {
		this.active_Date = active_Date;
	}
	@Column(name = "create_Date", length = 20)
	public Timestamp getCreate_Date() {
		return create_Date;
	}
	public void setCreate_Date(Timestamp create_Date) {
		this.create_Date = create_Date;
	}
	@Column(name = "servicetimes", length = 11)
	public Integer getServicetimes() {
		return servicetimes;
	}
	public void setServicetimes(Integer servicetimes) {
		this.servicetimes = servicetimes;
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
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "waterMachine")
	public Set<YzyRepairlog> getYzyRepairlogs() {
		return yzyRepairlogs;
	}
	public void setYzyRepairlogs(Set<YzyRepairlog> yzyRepairlogs) {
		this.yzyRepairlogs = yzyRepairlogs;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userInfo")
	public Set<YzyAppoitmentcheck> getYzyAppoitmentchecks() {
		return yzyAppoitmentchecks;
	}
	public void setYzyAppoitmentchecks(Set<YzyAppoitmentcheck> yzyAppoitmentchecks) {
		this.yzyAppoitmentchecks = yzyAppoitmentchecks;
	}
	@Column(name = "text6", length = 300)
	public String getText6() {
		return text6;
	}
	public void setText6(String text6) {
		this.text6 = text6;
	}

	
}
