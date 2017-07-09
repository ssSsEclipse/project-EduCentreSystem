package com.hkta.educentresystem.service;

import com.hkta.educentresystem.entity.Centre;

public interface CentreService {
	Centre findByCouponCode(String couponCode);
}
