package com.hkta.educentresystem.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "transaction_record")
public class Transaction implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4420799561915526445L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JoinColumn(name = "tutorial_centre_id")
	@ManyToOne(optional = true, fetch = FetchType.EAGER)
	private Centre tutorialCentre;

	@Column(name = "record_time")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date recordTime;

	@Column(name = "customer_name")
	private String customerName;

	@Column(name = "content")
	private String content;

	@Column(name = "amount")
	private BigDecimal amount;

	@Column(name = "commission")
	private BigDecimal commission;

	@Column(name = "cheque_issued_date")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="GMT+8")
	private Date chequeIssuedDate;

	@Column(name = "cheque_id")
	private String chequeId;
	
	public Long getId() {
		return id;
	}

	public Centre getTutorialCentre() {
		return tutorialCentre;
	}

	public void setTutorialCentre(Centre tutorialCentre) {
		this.tutorialCentre = tutorialCentre;
	}

	public Date getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getCommission() {
		return commission;
	}

	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}

	public Date getChequeIssuedDate() {
		return chequeIssuedDate;
	}

	public void setChequeIssuedDate(Date chequeIssuedDate) {
		this.chequeIssuedDate = chequeIssuedDate;
	}

	public String getChequeId() {
		return chequeId;
	}

	public void setChequeId(String chequeId) {
		this.chequeId = chequeId;
	}

	
}
