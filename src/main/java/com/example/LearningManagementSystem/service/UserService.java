package com.example.LearningManagementSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.LearningManagementSystem.DTO.DepartmentDTO;
import com.example.LearningManagementSystem.DTO.ProfessorDTO;
import com.example.LearningManagementSystem.DTO.StudentDTO;
import com.example.LearningManagementSystem.model.Department;
import com.example.LearningManagementSystem.model.Professor;
import com.example.LearningManagementSystem.model.Role;
import com.example.LearningManagementSystem.model.Student;
import com.example.LearningManagementSystem.model.Users;
import com.example.LearningManagementSystem.repo.DepartmentRepo;
import com.example.LearningManagementSystem.repo.ProfessorRepo;
import com.example.LearningManagementSystem.repo.StudentRepo;
import com.example.LearningManagementSystem.repo.UserRepo;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

	@Autowired 
	private UserRepo repo;
	
	@Autowired
	private StudentRepo sturepo;
	
	@Autowired
	private ProfessorRepo professorRepo;
	
	@Autowired
	private DepartmentRepo deptRepo;
	
	public Users registerUser(Users user) {
		// TODO Auto-generated method stub
		return repo.save(user);
		
	}

	public void addStudent(StudentDTO studentDTO) {
		// TODO Auto-generated method stub
		Users user = new Users();
		user.setUsername(studentDTO.getUsername());
		user.setPassword(studentDTO.getPassword());
		user.setEmail(studentDTO.getEmail());        
	    user.setRole(Role.STUDENT);
	    repo.save(user);
	    
	    Department department = deptRepo.findById(studentDTO.getDepartmentid())
	            .orElseThrow(() -> new RuntimeException("Department not found"));
	    Student student = new Student();
	    student.setUser(user);
	    
	    student.setSem(studentDTO.getSem());
	    student.setUsn(studentDTO.getUsn());
	    student.setDepartment(department);
	    sturepo.save(student);
	    
	    
	    
	}
	
	public boolean addProfessor(ProfessorDTO professorDTO) {
		Users user = new Users();
        user.setUsername(professorDTO.getUsername());
        user.setEmail(professorDTO.getEmail());
        user.setPassword(professorDTO.getPassword());
        user.setRole(Role.PROFESSOR); 
        repo.save(user);
        
        Professor professor = new Professor();
        professor.setUser(user);  // Link the User entity with Professor
        professor.setGrade(professorDTO.getGrade());
        
        professorRepo.save(professor); 
        
        return true;
	}

	public void addDepartment(DepartmentDTO departmentDTO) {
		// TODO Auto-generated method stub
		if (deptRepo.existsByName(departmentDTO.getName())) {
            throw new RuntimeException("Department with this name already exists.");
        }
		Professor professor = professorRepo.findById(departmentDTO.getProfessorId())
                .orElseThrow(() -> new RuntimeException("Professor not found."));
		
		Department department = new Department();
        department.setName(departmentDTO.getName());
        department.setProfessor(professor); 

        deptRepo.save(department);
	}

	public List<Professor> getAllProfessors() {
		// TODO Auto-generated method stub
		return professorRepo.findAll();
	}

	public List<Student> getAllStudents() {
		// TODO Auto-generated method stub
		return sturepo.findAll();
	}

	public void deleteProfessor(Long id) {
		// Find the professor by ID
        Professor professor = professorRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Professor not found"));

        // Remove the associated user
        Department dept = deptRepo.findByProfessor(professor);
        if (dept != null) {
            deptRepo.delete(dept);
        }

        // Delete the professor entry
        professorRepo.delete(professor);
		
	}

	public void deleteStudent(Long id) {
		// Find the student by ID
        Student student = sturepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));

        // Deleting the student will also delete the associated User due to cascading
        sturepo.delete(student);
		
	}

}
