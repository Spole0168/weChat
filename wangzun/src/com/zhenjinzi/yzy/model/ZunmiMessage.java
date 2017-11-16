package com.zhenjinzi.yzy.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zhenjinzi.yzy.model.enums.MessageStatus;

@Entity
@Table(name = "zunmi_message", catalog = "zunmi")
public class ZunmiMessage implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	private Integer id;
	private ZunmiUser zunmiUserByReceiver;
	private ZunmiUser zunmiUserBySender;
	private String title;
	private String content;
	private Timestamp sendTime;
	private MessageStatus status;


	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "receiver", nullable = false)
	public ZunmiUser getZunmiUserByReceiver() {
		return this.zunmiUserByReceiver;
	}

	public void setZunmiUserByReceiver(ZunmiUser zunmiUserByReceiver) {
		this.zunmiUserByReceiver = zunmiUserByReceiver;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sender", nullable = false)
	public ZunmiUser getZunmiUserBySender() {
		return this.zunmiUserBySender;
	}

	public void setZunmiUserBySender(ZunmiUser zunmiUserBySender) {
		this.zunmiUserBySender = zunmiUserBySender;
	}

	@Column(name = "title", nullable = false, length = 100)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "content", nullable = false, length = 65535)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "sendtime", length = 19)
	public Timestamp getSendTime() {
		return this.sendTime;
	}

	public void setSendTime(Timestamp sendTime) {
		this.sendTime = sendTime;
	}

	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
	public MessageStatus getStatus() {
		return this.status;
	}

	public void setStatus(MessageStatus status) {
		this.status = status;
	}

}