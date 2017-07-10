package com.hkta.educentresystem.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hkta.educentresystem.dto.ResponseMessage;
import com.hkta.educentresystem.entity.Centre;
import com.hkta.educentresystem.entity.Transaction;
import com.hkta.educentresystem.service.CentreService;
import com.hkta.educentresystem.service.TransactionService;

@RestController
@RequestMapping(value = "/api")
public class RestApiController {

	private static final Logger logger = LoggerFactory.getLogger(RestApiController.class);
	
	@Autowired
	private CentreService centreService;
	@Autowired
	private TransactionService transactionService;

	@RequestMapping(value = "/centre", method = RequestMethod.GET)
	public Centre getCentre(@RequestParam(value="id", required=false) Long id, @RequestParam(value="couponCode", required=false) String couponCode) {
		if (id != null) {
			return centreService.findOne(id);
		}else if (!StringUtils.isEmpty(couponCode)) {
			return centreService.findByCouponCode(couponCode);
		}
		return null;
	}

	@RequestMapping(value="/transaction/{id}", method = RequestMethod.DELETE )
	@ResponseBody
	public ResponseEntity<ResponseMessage> deleteTransaction(@PathVariable("id") Long id) {
		Transaction transaction = transactionService.findOne(id);
		if (transaction != null) {
			transactionService.delete(transaction);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResponseMessage(id, Transaction.class.getSimpleName(), "Transaction deleted!"));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage("Unable to delete transaction"));
	} 

	@RequestMapping(value="/transaction", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ResponseMessage> addTransaction(@RequestBody Transaction transaction) {
		Transaction newTransaction = transactionService.save(transaction);
		if (newTransaction == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage("Unable to create transaction"));
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(newTransaction.getId(), Transaction.class.getSimpleName(),  "Transaction created!"));
	}
}