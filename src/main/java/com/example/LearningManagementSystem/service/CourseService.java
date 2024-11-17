package com.example.LearningManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LearningManagementSystem.DTO.CourseDTO;
import com.example.LearningManagementSystem.model.Course;
import com.example.LearningManagementSystem.model.Department;
import com.example.LearningManagementSystem.model.Professor;
import com.example.LearningManagementSystem.model.Student;
import com.example.LearningManagementSystem.repo.CourseRepo;
import com.example.LearningManagementSystem.repo.DepartmentRepo;
import com.example.LearningManagementSystem.repo.ProfessorRepo;
import com.example.LearningManagementSystem.repo.StudentRepo;

@Service
public class CourseService {

	@Autowired
	private DepartmentRepo deptRepo;
	
	@Autowired
	private ProfessorRepo professorRepo;
	
	@Autowired
	private CourseRepo courseRepo;
	
	@Autowired
	private StudentRepo stuRepo;
	
	public void createCourse(CourseDTO courseDTO) {
		// TODO Auto-generated method stub
		Department department = deptRepo.findById(courseDTO.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        Professor professor = professorRepo.findById(courseDTO.getProfessorId())
                .orElseThrow(() -> new RuntimeException("Professor not found"));

        Course course = new Course();
        course.setName(courseDTO.getName());
        course.setDepartment(department);
        course.setProfessor(professor);

        courseRepo.save(course);
		
	}

	public void enrollStudentInCourse(Long studentId, Long courseId) {
		// TODO Auto-generated method stub
		Student student = stuRepo.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));
        Course course = courseRepo.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));

        // Add the student to the course
        course.getStudents().add(student);

        // Add the course to the student's courses
        student.getCourses().add(course);

        // Save the updated entities
        courseRepo.save(course);
        stuRepo.save(student);
		
	}

}
