package com.hkta.educentresystem.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hkta.educentresystem.entity.Transaction;
import com.hkta.educentresystem.repository.TransactionRepository;
import com.hkta.educentresystem.service.TransactionService;

@Service
public class TransactionServiceImpl extends AbstractBaseCrudService<Transaction, Long> implements TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;
	
	@Override
	@Transactional(readOnly = true)
	public Page<Transaction> findByCentreIdMonthYear(String centreId, String month, String year, int page, int size) {
		month = !StringUtils.isNumeric(month) ? "" : month;
		year = !StringUtils.isNumeric(year) ? "" : year;
		
		Page<Transaction> result = transactionRepository.findByCentreIdMonthYear(
				StringUtils.isEmpty(centreId) ? null : Long.parseLong(centreId),
				StringUtils.isEmpty(month) ? null : Integer.parseInt(month),
				StringUtils.isEmpty(year) ? null : Integer.parseInt(year),
				new PageRequest(page, size));
		return result;
	}

	@Override
	PagingAndSortingRepository<Transaction, Long> getRepository() {
		return this.transactionRepository;
	}
	
	@Override
	public BigDecimal getGrandTotal(List<Transaction> results) {
		BigDecimal grandTotal = BigDecimal.ZERO;
		
		for (Transaction transaction : results) {
			grandTotal = grandTotal.add(transaction.getCommission());
		}
		return grandTotal;
	}
}
