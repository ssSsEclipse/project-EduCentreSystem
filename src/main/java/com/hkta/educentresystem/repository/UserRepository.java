package com.hkta.educentresystem.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hkta.educentresystem.entity.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
	
	User findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.tutorialCentre.id = :tutorialCentreId")
    List<User> findByCentre(@Param("tutorialCentreId") Long tutorialCentreId);
   
	Page<User> findAll(Pageable p);
}
