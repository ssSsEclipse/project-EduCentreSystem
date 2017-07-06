package com.hkta.educentresystem.service;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hkta.educentresystem.dto.TransactionDTO;

public interface TransactionService {
	Page<TransactionDTO> findByRecordTime(Date from, Date to, Pageable pageRequest);
}
