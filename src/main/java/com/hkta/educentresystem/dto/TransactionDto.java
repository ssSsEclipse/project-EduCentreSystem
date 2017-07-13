package com.hkta.educentresystem.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TransactionDto implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3020581250247422779L;

	private Long tutorialCentreId;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date recordTime;

	private String customerName;

	private String content;

	private BigDecimal amount;

	private BigDecimal commission;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="GMT+8")
	private Date chequeIssuedDate;

	private String chequeId;
	
	
	public Long getTutorialCentreId() {
		return tutorialCentreId;
	}

	public void setTutorialCentreId(Long tutorialCentreId) {
		this.tutorialCentreId = tutorialCentreId;
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
