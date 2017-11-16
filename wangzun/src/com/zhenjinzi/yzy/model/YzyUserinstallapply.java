package com.zhenjinzi.yzy.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户申请安装  实体类
 * @author ly
 *
 */
@Entity
@Table(name = "yzy_userinstallapply", catalog = "")
public class YzyUserinstallapply implements java.io.Serializable{
	
        	private static final long serialVersionUID = 1L;
        	
			private Integer  id ;//int(11) unsigned NOT NULL   auto_increment primary key,
			private String   userName;// varchar(300) DEFAULT NULL,
			private String  telphone;// varchar(40) DEFAULT NULL,
			private String  instak_address;// varchar(500) DEFAULT NULL,
			private String   openid;// varchar(40) DEFAULT NULL,
			private Timestamp   apply_Date;// datetime DEFAULT NULL,
			private String  text1;//varchar(300) DEFAULT NULL,
			private String  text2;// varchar(300) DEFAULT NULL,
			private String text3;// varchar(300) DEFAULT NULL,
			private String text4;// varchar(300) DEFAULT NULL,
			private String text5;//   20160313统一用来做 逻辑删除   1 代表正常   2代表删除
			@Id
			@GeneratedValue(strategy = IDENTITY)
			@Column(name = "id", unique = true, nullable = false)
			public Integer getId() {
				return id;
			}
			public void setId(Integer id) {
				this.id = id;
			}
			@Column(name = "user_name", length = 300)
			public String getUserName() {
				return userName;
			}
			public void setUserName(String userName) {
				this.userName = userName;
			}
			@Column(name = "telphone", length = 40)
			public String getTelphone() {
				return telphone;
			}
			public void setTelphone(String telphone) {
				this.telphone = telphone;
			}
			@Column(name = "instak_address", length = 500)
			public String getInstak_address() {
				return instak_address;
			}
			public void setInstak_address(String instak_address) {
				this.instak_address = instak_address;
			}
			@Column(name = "openid", length = 40)
			public String getOpenid() {
				return openid;
			}
			public void setOpenid(String openid) {
				this.openid = openid;
			}
			@Column(name = "apply_date", length = 20)
			public Timestamp getApply_Date() {
				return apply_Date;
			}
			public void setApply_Date(Timestamp apply_Date) {
				this.apply_Date = apply_Date;
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
