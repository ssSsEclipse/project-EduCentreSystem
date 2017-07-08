package com.hkta.educentresystem.service;

import com.hkta.educentresystem.entity.User;

public interface UserService {
	User findByUsername(String username);
}
