package com.techm.services;

import com.techm.model.Users;

public interface IUserService {
	Users findByEmail(String email, String password);

}
