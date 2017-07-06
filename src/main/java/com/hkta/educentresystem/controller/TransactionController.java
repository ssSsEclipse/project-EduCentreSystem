package com.hkta.educentresystem.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hkta.educentresystem.entity.Transaction;
import com.hkta.educentresystem.exception.ResourceNotFoundException;
import com.hkta.educentresystem.service.TransactionService;

@Controller
@RequestMapping(value = "/transactions")
public class TransactionController {

	private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

	private static String VIEW_TRANSACTIONS = "transactions";

	@Autowired
	private TransactionService transactionService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getPage() {
		ModelAndView model = new ModelAndView();
		model.setViewName(VIEW_TRANSACTIONS);
		return model;
	}
	
	@RequestMapping(value={"/list"}, method = RequestMethod.GET)
	@ResponseBody
	public Page<Transaction> listTransactions(
			@RequestParam(value = "month", required = false) String month,
			@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "page") int page, 
			@RequestParam(value = "size") int size) {

        Page<Transaction> resultPage = transactionService.findByMonthYear(month, year, page, size);
        if (page > resultPage.getTotalPages()) {
            throw new ResourceNotFoundException();
        }
 
        return resultPage;
	}

}
