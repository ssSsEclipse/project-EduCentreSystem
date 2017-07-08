package com.hkta.educentresystem.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.hkta.educentresystem.entity.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
	
	User findByUsername(String username);
   
	Page<User> findAll(Pageable p);
}
