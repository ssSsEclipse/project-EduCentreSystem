package com.hkta.educentresystem.dao;

import com.hkta.educentresystem.entity.User;

public interface UserDao {

	User getUserByUsername(String username);
}
