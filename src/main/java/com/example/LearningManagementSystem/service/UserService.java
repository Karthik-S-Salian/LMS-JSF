package com.example.LearningManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.LearningManagementSystem.model.Users;
import com.example.LearningManagementSystem.repo.UserRepo;

@Service
public class UserService {

	@Autowired 
	private UserRepo repo;
	
	
	
	public Users registerUser(Users user) {
		// TODO Auto-generated method stub
		return repo.save(user);
		
	}

}
