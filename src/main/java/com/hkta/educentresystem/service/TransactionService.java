package com.hkta.educentresystem.service;

import org.springframework.data.domain.Page;

import com.hkta.educentresystem.entity.Transaction;

public interface TransactionService {
	Page<Transaction> findByMonthYear(String month, String year, int page, int size);
}
