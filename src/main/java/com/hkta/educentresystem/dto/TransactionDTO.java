package com.hkta.educentresystem.dto;

import java.math.BigDecimal;
import java.util.Date;

public class TransactionDTO {
		private Long id;

		private Date recordTime;

		private String customerName;

		private String content;

		private BigDecimal amount;
		
		private BigDecimal commission;

		public Long getId() {
			return id;
		}
		
		public void setId(Long id) {
			this.id = id;
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
	}
