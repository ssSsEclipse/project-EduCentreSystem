package com.hkta.educentresystem.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.data.domain.Page;

import com.hkta.educentresystem.entity.Transaction;

public class TransactionListDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 159399809125306469L;

	private Page<Transaction> result;
	
	private BigDecimal grandTotal;

	public Page<Transaction> getResult() {
		return result;
	}

	public void setResult(Page<Transaction> result) {
		this.result = result;
	}

	public BigDecimal getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(BigDecimal grandTotal) {
		this.grandTotal = grandTotal;
	}
	
	
}
