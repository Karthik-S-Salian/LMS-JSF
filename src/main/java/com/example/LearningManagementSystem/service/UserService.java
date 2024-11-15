package com.example.LearningManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.LearningManagementSystem.DTO.ProfessorDTO;
import com.example.LearningManagementSystem.DTO.StudentDTO;
import com.example.LearningManagementSystem.model.Professor;
import com.example.LearningManagementSystem.model.Role;
import com.example.LearningManagementSystem.model.Student;
import com.example.LearningManagementSystem.model.Users;
import com.example.LearningManagementSystem.repo.ProfessorRepo;
import com.example.LearningManagementSystem.repo.StudentRepo;
import com.example.LearningManagementSystem.repo.UserRepo;

@Service
public class UserService {

	@Autowired 
	private UserRepo repo;
	
	@Autowired
	private StudentRepo sturepo;
	
	@Autowired
	private ProfessorRepo professorRepo;
	
	public Users registerUser(Users user) {
		// TODO Auto-generated method stub
		return repo.save(user);
		
	}

	public boolean addStudent(StudentDTO studentDTO) {
		// TODO Auto-generated method stub
		Users user = new Users();
		user.setUsername(studentDTO.getUsername());
		user.setPassword(studentDTO.getPassword());
		user.setEmail(studentDTO.getEmail());        
	    user.setRole(Role.STUDENT);
	    repo.save(user);
	    
	    Student student = new Student();
	    student.setUser(user);
	    
	    student.setSem(studentDTO.getSem());
	    student.setUsn(studentDTO.getUsn());
	    sturepo.save(student);
	    
	    return true;
	    
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

}
