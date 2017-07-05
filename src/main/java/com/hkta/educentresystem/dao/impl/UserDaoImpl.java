package com.hkta.educentresystem.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hkta.educentresystem.dao.UserDao;
import com.hkta.educentresystem.entity.User;

@Repository
public class UserDaoImpl implements UserDao {

	private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public User getUserByUsername(String username) {
        Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("username", username));
        
        List<User> result = criteria.list();
        if (!result.isEmpty()) {
        	return result.get(0);
        }
        return null;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
