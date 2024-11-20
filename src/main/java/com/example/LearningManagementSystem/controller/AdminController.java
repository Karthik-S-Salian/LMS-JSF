package com.example.LearningManagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.LearningManagementSystem.DTO.CourseDTO;
import com.example.LearningManagementSystem.DTO.DepartmentDTO;
import com.example.LearningManagementSystem.DTO.EnrollDTO;
import com.example.LearningManagementSystem.DTO.ProfessorDTO;
import com.example.LearningManagementSystem.DTO.StudentDTO;
import com.example.LearningManagementSystem.service.CourseService;
import com.example.LearningManagementSystem.service.DepartmentService;
import com.example.LearningManagementSystem.service.ProfessorService;
import com.example.LearningManagementSystem.service.StudentService;
import com.example.LearningManagementSystem.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UserService service;

	@Autowired
	private CourseService courseService;

	@Autowired
	private ProfessorService professorService;

	@Autowired
	private StudentService studentService;

	@Autowired
	private DepartmentService deptService;

	@GetMapping("/")
	public String index() {
		return "admin/index";
	}

	@GetMapping("/departments")
	public String departments(Model model) {
		model.addAttribute("departmentForm", new DepartmentDTO());
		model.addAttribute("professors", professorService.getAllProfessors());
		model.addAttribute("departments", deptService.getAllDepartments());
		return "admin/department";
	}

	@GetMapping("/professors")
	public String professors(Model model) {
		model.addAttribute("professorForm", new ProfessorDTO());
		model.addAttribute("professors", professorService.getAllProfessors());
		return "admin/professors";
	}

	@PostMapping("/addProfessor")
	public String addProfessor(@ModelAttribute ProfessorDTO professorDTO) {
		service.addProfessor(professorDTO);
		return "redirect:/admin/professors";
	}

	@PostMapping("/addDepartment")
	public String addDepartment(@ModelAttribute DepartmentDTO departmentDTO) {
		service.addDepartment(departmentDTO);
		return "redirect:/admin/departments";
	}

	@PostMapping("/professors/{id}")
	public String deleteProfessor(@PathVariable Long id) {
		service.deleteProfessor(id);
		return "redirect:/admin/professors";
	}

	@PostMapping("/departments/{id}")
	public String deleteDepartment(@PathVariable Long id) {
		deptService.deleteDepartment(id);
		return "redirect:/admin/departments";
	}

	@GetMapping("/students")
	public String students(Model model) {
		model.addAttribute("studentForm", new StudentDTO());
		model.addAttribute("students", studentService.getAllStudents());
		model.addAttribute("departments", deptService.getAllDepartments());
		return "admin/students";
	}

	@PostMapping("/students/{id}")
	public String deleteStudent(@PathVariable Long id) {
		service.deleteStudent(id);
		return "redirect:/admin/students";
	}

	@PostMapping("/addStudent")
	public String addStudent(@ModelAttribute StudentDTO studentDTO) {
		service.addStudent(studentDTO);
		return "redirect:/admin/students";
	}

	@GetMapping("/courses")
	public String courses(Model model) {
		model.addAttribute("courseForm", new CourseDTO());
		model.addAttribute("courses", courseService.getAllCourses());
		model.addAttribute("departments", deptService.getAllDepartments());
		model.addAttribute("professors", professorService.getAllProfessors());
		return "admin/courses";
	}

	@PostMapping("/courses/{id}")
	public String deleteCourse(@PathVariable Long id) {
		courseService.deleteCourse(id);
		return "redirect:/admin/courses";
	}

	@PostMapping("/addCourse")
	public String addCourse(@ModelAttribute CourseDTO courseDTO) {
		courseService.createCourse(courseDTO);
		return "redirect:/admin/courses";
	}

	@GetMapping("/courses/{id}")
	public String deleteCourse(Model model, @PathVariable Long id) {
		model.addAttribute("enrollForm", new EnrollDTO());
		model.addAttribute("course", courseService.getCourse(id));
		model.addAttribute("enrolledStudents", courseService.getStudentsByCourseId(id));
		model.addAttribute("students", studentService.getStudentsNotEnrolledInCourse(id));
		return "admin/course";
	}

	@PostMapping("/courses/enroll/")
	public String enrollStudentInCourse(@ModelAttribute EnrollDTO enrollDTO) {
		System.out.println(enrollDTO.getStudentId() + enrollDTO.getCourseId());
		courseService.enrollStudentInCourse(enrollDTO.getStudentId(), enrollDTO.getCourseId());
		return "redirect:/admin/courses/" + enrollDTO.getCourseId();
	}
}
