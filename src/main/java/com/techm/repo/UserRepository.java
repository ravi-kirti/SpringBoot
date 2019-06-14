package com.techm.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.techm.model.Users;

@Repository
public interface UserRepository extends CrudRepository<Users, Long>,UserRepositoryCustom {
	
}
