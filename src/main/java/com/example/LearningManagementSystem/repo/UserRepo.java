package com.example.LearningManagementSystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.LearningManagementSystem.model.User;

@Repository
public interface UserRepo extends JpaRepository<User,Long>{

}
