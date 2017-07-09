package com.hkta.educentresystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import com.hkta.educentresystem.entity.Centre;
import com.hkta.educentresystem.repository.CentreRepository;
import com.hkta.educentresystem.service.CentreService;

@Service
public class CentreServiceImpl extends AbstractBaseCrudService<Centre, Long> implements CentreService {

	@Autowired
	private CentreRepository centreRepository;

	@Override
	public PagingAndSortingRepository<Centre, Long> getRepository() {
		return this.centreRepository;
	}

	@Override
	public Centre findByCouponCode(String couponCode) {
		return centreRepository.findByCouponCode(couponCode);
	}
}
