package com.hkta.educentresystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.hkta.educentresystem.dto.CustomUserDetails;
import com.hkta.educentresystem.entity.Centre;
import com.hkta.educentresystem.repository.CentreRepository;
import com.hkta.educentresystem.service.CentreService;
import com.hkta.educentresystem.service.UserService;

@Service
public class CentreServiceImpl extends AbstractBaseCrudService<Centre, Long> implements CentreService {

	@Autowired
	private CentreRepository centreRepository;
	@Autowired
	private UserService userService;

	@Override
	public PagingAndSortingRepository<Centre, Long> getRepository() {
		return this.centreRepository;
	}

	@Override
	public Centre findByCouponCode(String couponCode) {
		return centreRepository.findByCouponCode(couponCode);
	}

	@Override
	public boolean isRequestAllowed(Long requestingCentreId) {
		if (requestingCentreId == null) {
			return true;
		}
		CustomUserDetails userDetails = (CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
			return true;
		}else if (userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))) {
			Centre userCentre = userService.findOne(userDetails.getId()).getTutorialCentre();
			if (userCentre != null && userCentre.getId() == requestingCentreId) {
				return true;
			}
		}
		return false;
	}
}
