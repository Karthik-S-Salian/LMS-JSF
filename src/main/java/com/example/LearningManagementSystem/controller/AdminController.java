package com.example.LearningManagementSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.LearningManagementSystem.DTO.DepartmentDTO;
import com.example.LearningManagementSystem.DTO.ProfessorDTO;
import com.example.LearningManagementSystem.DTO.StudentDTO;
import com.example.LearningManagementSystem.model.Professor;
import com.example.LearningManagementSystem.model.Student;
import com.example.LearningManagementSystem.service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UserService service;
	
	@PostMapping("/addStudent")
	public ResponseEntity<String> addStudent(@RequestBody StudentDTO studentDTO) {
		try {
			service.addStudent(studentDTO);
			return ResponseEntity.status(HttpStatus.CREATED).body("Student added successfully!");
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
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
	
	@PostMapping("/addDepartment")
    public ResponseEntity<String> addDepartment(@RequestBody DepartmentDTO departmentDTO) {
		try {
            service.addDepartment(departmentDTO);
            return ResponseEntity.ok("Department added successfully!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
	
	@GetMapping("/professors")
	public List<Professor> getAllProfessors(){
		return service.getAllProfessors();
	}
	
	@GetMapping("/students")
	public List<Student> getAllStudents(){
		return service.getAllStudents();
	}
	
	@DeleteMapping("/professor/{id}")
	public ResponseEntity<String> deleteProfessor(@PathVariable Long id) {
        service.deleteProfessor(id);
        return ResponseEntity.ok("Professor  deleted successfully.");
    }
	@DeleteMapping("/student/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        service.deleteStudent(id);
        return ResponseEntity.ok("Student deleted successfully.");
    }
}
