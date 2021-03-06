package com.hkta.educentresystem.service;

import com.hkta.educentresystem.entity.Centre;

public interface CentreService extends BaseCrudService<Centre, Long>{
	
	Centre findByCouponCode(String couponCode);
	boolean isRequestAllowed(Long requestingCentreId);
	Centre getCurrentUserCentre();
	boolean canDeleteCentre(Centre centre);
}
