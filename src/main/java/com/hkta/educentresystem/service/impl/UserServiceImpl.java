package com.hkta.educentresystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import com.hkta.educentresystem.entity.User;
import com.hkta.educentresystem.repository.UserRepository;
import com.hkta.educentresystem.service.UserService;

@Service
public class UserServiceImpl extends AbstractBaseCrudService<User, Long> implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public PagingAndSortingRepository<User, Long> getRepository() {
		return this.userRepository;
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
}
