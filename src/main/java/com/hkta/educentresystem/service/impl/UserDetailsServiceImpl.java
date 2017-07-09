package com.hkta.educentresystem.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hkta.educentresystem.dto.CustomUserDetails;
import com.hkta.educentresystem.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.hkta.educentresystem.entity.User user = userRepository.findByUsername(username);
		if (user != null) {
			List<GrantedAuthority> authorities = buildUserAuthority(user.getRole());
			return new CustomUserDetails(user.getId(), user.getUsername(), user.getPassword(), user.isActive(), true, true, true, authorities);
		}
		throw new UsernameNotFoundException("User not found");
	}

	private List<GrantedAuthority> buildUserAuthority(String userRole) {
		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
		setAuths.add(new SimpleGrantedAuthority(userRole));
		return new ArrayList<GrantedAuthority>(setAuths);
	}

}
