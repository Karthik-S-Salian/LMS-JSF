package com.example.LearningManagementSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.LearningManagementSystem.model.Department;
import com.example.LearningManagementSystem.repo.DepartmentRepo;

import jakarta.persistence.EntityNotFoundException;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepo deptRepo;
	
	public void deleteDepartment(Long id) {
		// TODO Auto-generated method stub
		Department course = deptRepo.findById(id)
					.orElseThrow(() -> new EntityNotFoundException("Course not found"));
		
		deptRepo.delete(course);
		
	}

	public List<Department> getAllDepartments() {
		// TODO Auto-generated method stub
		return deptRepo.findAll();
	}
}
