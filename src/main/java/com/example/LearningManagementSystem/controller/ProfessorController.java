package com.example.LearningManagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import com.example.LearningManagementSystem.model.Notes;
import com.example.LearningManagementSystem.model.Professor;
import com.example.LearningManagementSystem.service.CourseService;
import com.example.LearningManagementSystem.service.ProfessorService;

@Controller
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private ProfessorService professorService;

    @GetMapping("/profile")
    public String getProfessorProfile(Authentication authentication, Model model) {
        String username = authentication.getName();
        Professor professor = professorService.getProfessorByUsername(username);
        if (professor == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found");
        }
        model.addAttribute("professor", professor);
        return "professor/profile";
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
            @RequestParam("courseId") Long courseId,
            @RequestParam("description") String description,
            Model model) {
        try {
            Notes notes = professorService.saveNotes(file, courseId, description);
            model.addAttribute("message", "File uploaded successfully! File ID: " + notes.getId());
        } catch (Exception e) {
            model.addAttribute("message", "File upload failed: " + e.getMessage());
        }
        return "redirect:/professor/courses/" + courseId;
    }

    // get all the courses assigned to the professor
    @GetMapping("/")
    public String getCoursesAssignedToProfessor(Authentication authentication, Model model) {
        // Get the username of the authenticated professor
        String username = authentication.getName();

        // Retrieve the professor details from the database
        Professor professor = professorService.getProfessorByUsername(username);

        if (professor == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found");
        }

        model.addAttribute("courses", professorService.getCoursesByProfessor(professor));
        return "professor/index";
    }

    @GetMapping("/courses/{id}")
    public String course(@PathVariable Long id, Model model) {
        model.addAttribute("course", courseService.getCourse(id));
        model.addAttribute("notes", courseService.getCourseNotes(id));
        return "professor/course";
    }

}
