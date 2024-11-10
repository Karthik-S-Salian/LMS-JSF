package com.example.LearningManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.LearningManagementSystem.model.User;
import com.example.LearningManagementSystem.repo.UserRepo;

@Service
public class UserService {

	@Autowired 
	private UserRepo repo;
	
	public User registerUser(User user) {
		// TODO Auto-generated method stub
		return repo.save(user);
		
	}

}
