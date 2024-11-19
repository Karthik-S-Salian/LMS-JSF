package com.example.LearningManagementSystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.LearningManagementSystem.model.Professor;

@Repository
public interface ProfessorRepo extends JpaRepository<Professor,Long> {
	
}
