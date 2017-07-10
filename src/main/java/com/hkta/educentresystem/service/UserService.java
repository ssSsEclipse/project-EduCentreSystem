package com.hkta.educentresystem.service;

import com.hkta.educentresystem.dto.UserDto;
import com.hkta.educentresystem.entity.User;

public interface UserService extends BaseCrudService<User, Long>{
	User findByUsername(String username);
	User saveDto(UserDto userDto);
}
