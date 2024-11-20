package com.example.LearningManagementSystem.controller;

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
import org.springframework.web.server.ResponseStatusException;
import com.example.LearningManagementSystem.model.Notes;
import com.example.LearningManagementSystem.model.Student;
import com.example.LearningManagementSystem.service.CourseService;
import com.example.LearningManagementSystem.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/profile")
    public String getStudentProfile(Model model, Authentication authentication) {
        String username = authentication.getName();
        Student student = studentService.getStudentByUsername(username);
        if (student == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found");
        }
        model.addAttribute("student", student);
        return "student/profile";
    }

    @Autowired
    private CourseService courseService;

    @GetMapping("/")
    public String home(Authentication authentication, Model model) {

        String username = authentication.getName();

        // Retrieve the professor details from the database
        Student student = studentService.getStudentByUsername(username);
        if (student == null) {
            return "redirect:/login";
        }

        model.addAttribute("courses", courseService.getCoursesByStudentId(student.getId()));
        return "student/index";
    }

    @GetMapping("/courses/{id}")
    public String course(@PathVariable Long id, Model model) {
        model.addAttribute("course", courseService.getCourse(id));
        model.addAttribute("notes", courseService.getCourseNotes(id));
        return "student/course";
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
