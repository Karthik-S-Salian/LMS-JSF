package com.example.LearningManagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.LearningManagementSystem.model.Users;
import com.example.LearningManagementSystem.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping("/")
	public String greet() {
		return "Hello";
	}
	/*@GetMapping("/csrf-token")
	public CsrfToken getCsrfToken(HttpServletRequest request) {
		return (CsrfToken) request.getAttribute("_csrf");
	}*/
	@PostMapping("/register")
	public Users register(@RequestBody Users user) {
		
		return service.registerUser(user);
	}
}
