package com.hkta.educentresystem.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class DisabledException extends UsernameNotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8066098881064791800L;

	public DisabledException(String msg) {
		super(msg);
	}
}