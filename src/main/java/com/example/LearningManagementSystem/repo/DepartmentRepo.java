package com.example.LearningManagementSystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.LearningManagementSystem.model.Department;

@Repository
public interface DepartmentRepo extends JpaRepository<Department,Long> {

	boolean existsByName(String name);

}