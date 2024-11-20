package com.example.LearningManagementSystem.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.LearningManagementSystem.DTO.StudentDTO;
import com.example.LearningManagementSystem.model.Notes;
import com.example.LearningManagementSystem.model.Student;
import com.example.LearningManagementSystem.service.CourseService;
import com.example.LearningManagementSystem.service.StudentService;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    // @GetMapping("/profile")
    // @ResponseBody
    // public ResponseEntity<?> getStudentProfile(Authentication authentication) {
    // String username = authentication.getName();
    // Student student = studentService.getStudentByUsername(username);
    // if (student == null) {
    // return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
    // }
    // StudentDTO studentDTO = new
    // StudentDTO(student.getUser().getUsername(),student.getUser().getEmail(),student.getUser().getPassword(),student.getUsn(),
    // student.getSem(),student.getDepartment().getId());
    // return ResponseEntity.ok(studentDTO);
    // }

    @Autowired
    private CourseService courseService;

    @GetMapping("/")
    public String home(Model model) {

        // String username = "pavan"; // authentication.getName();

        // // Retrieve the professor details from the database
        // Student student = studentService.getStudentByUsername(username);
        // if (student == null) {
        // return "redirect:/login";
        // }

        model.addAttribute("courses", courseService.getAllCourses());
        return "index";
    }

    @GetMapping("/courses/{id}")
    public String course(@PathVariable Long id, Model model) {
        model.addAttribute("course", courseService.getCourse(id));
        model.addAttribute("notes", courseService.getCourseNotes(id));
        return "course";
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable("fileId") Long fileId) {
        try {
            byte[] fileData = studentService.getFile(fileId);
            Notes note = studentService.getFileEntity(fileId);

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + note.getDescription() + "\"")
                    .body(fileData);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
