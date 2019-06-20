package com.techm.service.impl;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.techm.model.Users;
import com.techm.repo.UserRepository;
import com.techm.services.IUserService;

@Service
public class UserService implements IUserService {

	@PersistenceContext
	EntityManager entityManager;

	@Autowired
	UserRepository repository;

	@Override
	public Users findByEmail(String email, String password) {
		Query query = entityManager.createNativeQuery("SELECT * FROM Users WHERE EMAIL=? and password=?", Users.class);
		query.setParameter(1, email);
		query.setParameter(2, password);
		Users user = (Users) query.getSingleResult();
		return user;
	}

	public ResponseEntity<String> deleteUser(long id) {
		repository.deleteById(id);
		return new ResponseEntity<>("User has been deleted!", HttpStatus.OK);
	}

	public List<Users> getAllUsers() {
		List<Users> users = new ArrayList<>();
		repository.findAll().forEach(users::add);
		return users;
	}

	public Users getUserById(long id) {
		Optional<Users> userData = repository.findById(id);
		return userData.get();
	}

	public Users postUser(Users user) {
		Users _user = repository.save(new Users(user.getName(), user.getUsername(), user.getPassword(), user.getEmail(),
				user.getCity(), user.getZipcode(), user.getPhone(), user.getRollname()));
		return _user;
	}

	public ResponseEntity<Users> updateUser(long id, Users user) {
		Optional<Users> userData = repository.findById(id);

		if (userData.isPresent()) {
			Users _user = userData.get();
			_user.setName(user.getName());
			_user.setUsername(user.getUsername());
			_user.setPassword(user.getPassword());
			_user.setEmail(user.getEmail());
			_user.setCity(user.getCity());
			_user.setZipcode(user.getZipcode());
			_user.setPhone(user.getPhone());
			_user.setRollname(user.getRollname());
			System.out.println("Saving user details");
			return new ResponseEntity<>(repository.save(_user), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

}
