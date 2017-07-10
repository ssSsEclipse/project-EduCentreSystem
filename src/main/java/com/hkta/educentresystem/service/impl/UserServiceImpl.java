package com.hkta.educentresystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import com.hkta.educentresystem.dto.UserDto;
import com.hkta.educentresystem.entity.Centre;
import com.hkta.educentresystem.entity.User;
import com.hkta.educentresystem.mapper.CustomDozerMapper;
import com.hkta.educentresystem.repository.CentreRepository;
import com.hkta.educentresystem.repository.UserRepository;
import com.hkta.educentresystem.service.UserService;

@Service
public class UserServiceImpl extends AbstractBaseCrudService<User, Long> implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CentreRepository centreRepository;
	
	@Autowired
	private CustomDozerMapper dozerMapper;

	@Override
	public PagingAndSortingRepository<User, Long> getRepository() {
		return this.userRepository;
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User saveDto(UserDto userDto) {
		User newUser = dozerMapper.map(userDto, User.class);
		if (userDto.getTutorialCentreId() != null) {
			Centre tutorialCentre = centreRepository.findOne(userDto.getTutorialCentreId());
			newUser.setTutorialCentre(tutorialCentre);
		}
		return userRepository.save(newUser);
	}
}
