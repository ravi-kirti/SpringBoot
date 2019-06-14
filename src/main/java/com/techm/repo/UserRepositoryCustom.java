package com.techm.repo;

import com.techm.model.Users;

public interface UserRepositoryCustom {
	Users findByEmail(String email, String password);
}
