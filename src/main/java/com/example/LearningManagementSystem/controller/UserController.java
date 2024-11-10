package com.example.LearningManagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.LearningManagementSystem.model.User;
import com.example.LearningManagementSystem.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping("/")
	public String greet() {
		return "Hello";
	}

	@PostMapping("/register")
	public User register(@RequestBody User user) {
		return service.registerUser(user);
	}
}
