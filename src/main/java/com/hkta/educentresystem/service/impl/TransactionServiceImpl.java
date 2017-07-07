package com.hkta.educentresystem.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hkta.educentresystem.entity.Transaction;
import com.hkta.educentresystem.mapper.CustomDozerMapper;
import com.hkta.educentresystem.repository.TransactionRepository;
import com.hkta.educentresystem.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private CustomDozerMapper dozerMapper;
	
	@Override
	@Transactional(readOnly = true)
	public Page<Transaction> findByMonthYear(String month, String year, int page, int size) {
		Page<Transaction> result = transactionRepository.findByMonthYear(
				StringUtils.isEmpty(month) ? null : Integer.parseInt(month),
				StringUtils.isEmpty(year) ? null : Integer.parseInt(year),
				new PageRequest(page, size));
		return result;
	}
	

}
