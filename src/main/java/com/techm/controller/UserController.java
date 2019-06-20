package com.techm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.techm.service.impl.UserService;
import com.techm.services.IUserService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private IUserService iservice;

	@Autowired
	private UserService userService;

	@GetMapping("/validate/{email}/{password}")
	public Users validateByEmail(@PathVariable("email") String email, @PathVariable("password") String password) {
		System.out.println("Validating user by email...");
		Users userData = iservice.findByEmail(email, password);
		System.out.println(userData);
		return userData;
	}

	@GetMapping("/users")
	public List<Users> getAllUsers() {
		System.out.println("Get all users...");
		return userService.getAllUsers();
	}

	@GetMapping("/users/{id}")
	public Users getUserById(@PathVariable("id") long id) {
		System.out.println("Get User by Id...");
		return userService.getUserById(id);
	}

	@PostMapping(value = "/users/create")
	public Users postUser(@RequestBody Users user) {
		return userService.postUser(user);
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") long id) {
		System.out.println("Delete user with ID = " + id + "...");
		return userService.deleteUser(id);
	}

	@PutMapping("/users/{id}")
	public ResponseEntity<Users> updateUser(@PathVariable("id") long id, @RequestBody Users user) {
		System.out.println("Update User with ID = " + id + "...");
		return userService.updateUser(id, user);
	}
}
