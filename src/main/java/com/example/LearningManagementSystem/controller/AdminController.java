package com.example.LearningManagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.LearningManagementSystem.DTO.ProfessorDTO;
import com.example.LearningManagementSystem.DTO.StudentDTO;
import com.example.LearningManagementSystem.model.Student;
import com.example.LearningManagementSystem.service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UserService service;
	
	@PostMapping("/addStudent")
	public ResponseEntity<String> addStudent(@RequestBody StudentDTO studentDTO) {
        if (service.addStudent(studentDTO)) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Student added successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add student");
        }
    }
	
	@PostMapping("/addProfessor")
    public ResponseEntity<String> addProfessor(@RequestBody ProfessorDTO professorDTO) {
        boolean isAdded = service.addProfessor(professorDTO);
        if (isAdded) {
            return ResponseEntity.ok("Professor registered successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to register professor.");
        }
    }
}
