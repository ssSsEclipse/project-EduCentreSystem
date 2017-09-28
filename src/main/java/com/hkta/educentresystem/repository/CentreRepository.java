package com.hkta.educentresystem.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.hkta.educentresystem.entity.Centre;

@Repository
public interface CentreRepository extends PagingAndSortingRepository<Centre, Long> {
	
	Centre findByCouponCodeIgnoreCase(String couponCode);
   
	Page<Centre> findAll(Pageable p);
}
