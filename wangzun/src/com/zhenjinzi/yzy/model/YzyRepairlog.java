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

/**
 *yzy_repairlog  维修记录
 */
@Entity
@Table(name = "yzy_repairlog", catalog = "")
public class YzyRepairlog implements Serializable{

	private Integer  id ;//int(11) unsigned NOT NULL   auto_increment primary key,
	private String serviceuserid;// varchar(100), 服务人员工号
	private String servicepoint;// varchar(500), 服务维修意见  whats;//维修内容
	private String servicejudge;// varchar(500)	, 服务评价
	private String watertds;// varchar(100),	    tds_in
	private String outputtds;// varchar(100),	
	private String scaleflag;// varchar(10)	,   scale;//水垢，    有  无
	private Timestamp repairtime;//
	private String text1;// varchar(300)   texture;//口感，
	private String text2 ;//varchar(300) smell;//异味，
	private String text3;// varchar(300) whats;//维修内容   以冒号分割
	private String text4;// varchar(300)                                                   饮水机id,
	private String text5;// varchar(300)              20160313统一用来做 逻辑删除   1 代表正常   2代表删除
    private YzyUserinfo waterMachine;//	  machineid	 int(11)unsigned,
    @Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "serviceuserid", length = 100)
	public String getServiceuserid() {
		return serviceuserid;
	}
	public void setServiceuserid(String serviceuserid) {
		this.serviceuserid= serviceuserid;
	}
	@Column(name = "servicepoint", length = 500)
	public String getServicepoint() {
		return servicepoint;
	}
	public void setServicepoint(String servicepoint) {
		this.servicepoint = servicepoint;
	}
	@Column(name = "servicejudge", length = 500)
	public String getServicejudge() {
		return servicejudge;
	}
	public void setServicejudge(String servicejudge) {
		this.servicejudge = servicejudge;
	}
	@Column(name = "watertds", length = 100)
	public String getWatertds() {
		return watertds;
	}
	public void setWatertds(String watertds) {
		this.watertds = watertds;
	}
	@Column(name = "outputtds", length = 100)
	public String getOutputtds() {
		return outputtds;
	}
	public void setOutputtds(String outputtds) {
		this.outputtds = outputtds;
	}
	@Column(name = "scaleflag", length = 100)
	public String getScaleflag() {
		return scaleflag;
	}
	public void setScaleflag(String scaleflag) {
		this.scaleflag = scaleflag;
	}
	@Column(name = "repairtime", length = 20)
	public Timestamp getRepairtime() {
		return repairtime;
	}
	public void setRepairtime(Timestamp repairtime) {
		this.repairtime = repairtime;
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
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "machineid")
	public YzyUserinfo getWaterMachine() {
		return waterMachine;
	}
	public void setWaterMachine(YzyUserinfo waterMachine) {
		this.waterMachine = waterMachine;
	}
    
}
