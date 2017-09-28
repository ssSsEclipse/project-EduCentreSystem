package com.hkta.educentresystem.controller;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hkta.educentresystem.dto.CustomUserDetails;
import com.hkta.educentresystem.dto.TransactionListDto;
import com.hkta.educentresystem.entity.Centre;
import com.hkta.educentresystem.entity.Transaction;
import com.hkta.educentresystem.exception.ResourceNotFoundException;
import com.hkta.educentresystem.service.CentreService;
import com.hkta.educentresystem.service.TransactionService;

@Controller
@RequestMapping(value = "/transactions")
public class TransactionController {

	private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private CentreService centreService;
	
	@RequestMapping(value={"/list"}, method = RequestMethod.GET)
	@ResponseBody
	public TransactionListDto listTransactions(
			@RequestParam(value = "centreId", required = false) String centreId,
			@RequestParam(value = "month", required = false) String month,
			@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "page") int page, 
			@RequestParam(value = "size") int size) {

		Centre centre = centreService.getCurrentUserCentre();
		
		TransactionListDto result = new TransactionListDto();
		
		if (centre != null) {
	        Page<Transaction> resultPage = transactionService.findByCentreIdMonthYear(centre.getId(), month, year, page, size);
	        if (page > resultPage.getTotalPages()) {
	            throw new ResourceNotFoundException();
	        }
	        result.setResult(resultPage);
	        
	        Page<Transaction> allResults = transactionService.findByCentreIdMonthYear(centre.getId(), month, year, 0, Integer.MAX_VALUE);
	        result.setGrandTotal(transactionService.getGrandTotal(allResults.getContent()));
		}else {
			result.setGrandTotal(BigDecimal.ZERO);
		}
        return result;
	}

}
