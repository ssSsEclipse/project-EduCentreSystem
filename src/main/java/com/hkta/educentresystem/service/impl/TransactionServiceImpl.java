package com.hkta.educentresystem.service.impl;

import java.util.Date;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hkta.educentresystem.dto.TransactionDTO;
import com.hkta.educentresystem.entity.Transaction;
import com.hkta.educentresystem.repository.TransactionRepository;
import com.hkta.educentresystem.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private DozerBeanMapper dozerMapper;
	
	@Override
	@Transactional(readOnly = true)
	public Page<TransactionDTO> findByRecordTime(Date from, Date to, Pageable pageRequest) {
		Page<Transaction> searchResultPage = transactionRepository.findByRecordTime(from, to, pageRequest);
		//TODO
		return null;
	}
	

}
