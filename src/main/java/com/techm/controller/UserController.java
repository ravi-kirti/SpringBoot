package com.techm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techm.model.Users;
import com.techm.repo.UserRepository;
import com.techm.services.IUserService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private IUserService service;

	@GetMapping("/validate/{email}/{password}")
	public Users validateByEmail(@PathVariable("email") String email, @PathVariable("password") String password) {
		System.out.println("Validating user by email...");
		//Users userData = repository.findByEmail(email, password);
		Users userData = service.findByEmail(email, password);
		System.out.println(userData);
		return userData;
	}

	@GetMapping("/users")
	public List<Users> getAllUsers() {
		System.out.println("Get all users...");

		List<Users> users = new ArrayList<>();
		repository.findAll().forEach(users::add);
		System.out.println("Details::" + users);
		return users;
	}

	@GetMapping("/users/{id}")
	public Users getUserById(@PathVariable("id") long id) {
		System.out.println("Get User by Id...");

		Optional<Users> userData = repository.findById(id);
		return userData.get();
	}

	@PostMapping(value = "/users/create")
	public Users postUser(@RequestBody Users user) {

		Users _user = repository.save(new Users(user.getName(), user.getUsername(), user.getPassword(), user.getEmail(),
				user.getCity(), user.getZipcode(), user.getPhone(), user.getRollname()));
		return _user;
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") long id) {
		System.out.println("Delete user with ID = " + id + "...");
		repository.deleteById(id);
		return new ResponseEntity<>("User has been deleted!", HttpStatus.OK);
	}

	@PutMapping("/users/{id}")
	public ResponseEntity<Users> updateUser(@PathVariable("id") long id, @RequestBody Users user) {
		System.out.println("Update User with ID = " + id + "...");

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
