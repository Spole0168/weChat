package com.zhenjinzi.yzy.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/***
 * 用户预约检测
 * @author ly
 *
 */
@Entity
@Table(name = "yzy_appoitmentcheck", catalog = "")
public class YzyAppoitmentcheck implements Serializable{
	 private Integer id;// int(11) unsigned NOT NULL   auto_increment primary key,
	 private Timestamp create_Date;//申请时间	 datetime,
	 private Timestamp appoit_date;//预约检修时间 datetime,
	 private String openid;// varchar(100),
//	  userid int(11)unsigned,
	 private YzyUserinfo userInfo;
//	  machineid int(11)unsigned,
	 private String status;// varchar(40),
	 private String replymsg;// varchar(600),
	 private String  text1;// varchar(300)    name 
	 private String  text2;// varchar(300)   tel
	 private String  text3;// varchar(300) 预约时间
	 private String  text4;// varchar(300) DEFAULT NULL,
	 private String  text5;// varchar(300);// 统一用来做 逻辑删除   1 代表正常   2代表删除
//	 private YzyUserinfo mechine;
	    @Id
		@GeneratedValue(strategy = IDENTITY)
		@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "create_Date", length = 20)
	public Timestamp getCreate_Date() {
		return create_Date;
	}
	public void setCreate_Date(Timestamp create_Date) {
		this.create_Date = create_Date;
	}
	@Column(name = "appoit_date", length = 20)
	public Timestamp getAppoit_date() {
		return appoit_date;
	}
	public void setAppoit_date(Timestamp appoit_date) {
		this.appoit_date = appoit_date;
	}
	@Column(name = "openid", length = 100)
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userid")
	public YzyUserinfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(YzyUserinfo userInfo) {
		this.userInfo = userInfo;
	}
	@Column(name = "status", length = 20)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name = "replymsg", length = 500)
	public String getReplymsg() {
		return replymsg;
	}
	public void setReplymsg(String replymsg) {
		this.replymsg = replymsg;
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
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "machineid")
//	public YzyUserinfo getMechine() {
//		return mechine;
//	}
//	public void setMechine(YzyUserinfo mechine) {
//		this.mechine = mechine;
//	}
	 
	 
}
