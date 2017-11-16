package com.zhenjinzi.yzy.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zhenjinzi.yzy.model.enums.QuestionStatus;

@Entity
@Table(name = "zunmi_question", catalog = "zunmi")
public class ZunmiQuestion implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	private Integer id;
	private YzyAdmin zunmiAdmin;
	private ZunmiUser zunmiUser;
	private String questionType;
	private String question;
	private String description;
	private Timestamp questionTime;
	private String answer;
	private Timestamp answerTime;
	private QuestionStatus status;

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
	@JoinColumn(name = "adminid")
	public YzyAdmin getZunmiAdmin() {
		return this.zunmiAdmin;
	}

	public void setZunmiAdmin(YzyAdmin zunmiAdmin) {
		this.zunmiAdmin = zunmiAdmin;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userid", nullable = false)
	public ZunmiUser getZunmiUser() {
		return this.zunmiUser;
	}

	public void setZunmiUser(ZunmiUser zunmiUser) {
		this.zunmiUser = zunmiUser;
	}

	@Column(name = "questiontype", nullable = false, length = 20)
	public String getQuestionType() {
		return this.questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	@Column(name = "question", nullable = false, length = 100)
	public String getQuestion() {
		return this.question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	@Column(name = "description", nullable = false, length = 65535)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "questiontime", nullable = false, length = 19)
	public Timestamp getQuestionTime() {
		return this.questionTime;
	}

	public void setQuestionTime(Timestamp questionTime) {
		this.questionTime = questionTime;
	}

	@Column(name = "answer", length = 65535)
	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Column(name = "answertime", length = 19)
	public Timestamp getAnswerTime() {
		return this.answerTime;
	}

	public void setAnswerTime(Timestamp answerTime) {
		this.answerTime = answerTime;
	}

	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
	public QuestionStatus getStatus() {
		return this.status;
	}

	public void setStatus(QuestionStatus status) {
		this.status = status;
	}

}