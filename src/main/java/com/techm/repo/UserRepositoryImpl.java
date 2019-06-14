package com.techm.repo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.techm.model.Users;

@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public Users findByEmail(String email, String password) {
		Query query = entityManager
				.createNativeQuery("SELECT * FROM Users WHERE EMAIL=? and password=?", Users.class);
		query.setParameter(1, email);
		query.setParameter(2, password);
		
		Users user = (Users)query.getSingleResult();
		return user;
	}

}
