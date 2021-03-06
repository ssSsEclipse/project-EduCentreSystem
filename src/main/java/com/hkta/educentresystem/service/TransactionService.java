package com.hkta.educentresystem.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;

import com.hkta.educentresystem.entity.Transaction;

public interface TransactionService extends BaseCrudService<Transaction, Long>{
	Page<Transaction> findByCentreIdMonthYear(String centreId, String month, String year, int page, int size);

	BigDecimal getGrandTotal(List<Transaction> results);
}
