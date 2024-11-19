package com.example.LearningManagementSystem.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.LearningManagementSystem.model.Course;
import com.example.LearningManagementSystem.model.Notes;
import com.example.LearningManagementSystem.model.Professor;
import com.example.LearningManagementSystem.service.ProfessorService;



@Controller
@RequestMapping("/professor")
public class ProfessorController {

	@Autowired
	private ProfessorService professorService;
	
	@GetMapping("/profile")
	@ResponseBody
	public ResponseEntity<?> getProfessorProfile(Authentication authentication) {
		// Get the username of the authenticated professor
        String username = authentication.getName();
        System.out.println(username);
        // Retrieve the professor details from the database
        Professor professor = professorService.getProfessorByUsername(username);
        if (professor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Professor not found");
        }

        return ResponseEntity.ok(professor);
	}
	
	@GetMapping("/upload")
    public String showUploadPage() {
        return "upload";
    }
	
	@PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
    		@RequestParam("courseId") Long courseId,
    		@RequestParam("description") String description,
    		Model model) {
        try {
            Notes notes = professorService.saveNotes(file,courseId,description);
            model.addAttribute("message", "File uploaded successfully! File ID: " + notes.getId());
        } catch (Exception e) {
            model.addAttribute("message", "File upload failed: " + e.getMessage());
        }
        return "upload";
    }
	
	//get all the courses assigned to the professor
	@GetMapping("/courses")
    @ResponseBody
    public ResponseEntity<List<String>> getCoursesAssignedToProfessor(Authentication authentication) {
        // Get the username of the authenticated professor
        String username = authentication.getName();

        // Retrieve the professor details from the database
        Professor professor = professorService.getProfessorByUsername(username);

        if (professor != null) {
            List<String> courses = professorService.getCoursesByProfessor(professor);
            return new ResponseEntity<>(courses, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
	@GetMapping("/notes/{courseId}")
	public ResponseEntity<List<String>> getNoteDescriptionsByCourseId(@PathVariable Long courseId) {
	    List<String> noteDescriptions = professorService.getNotesByCourseId(courseId);
	    
	    if (noteDescriptions.isEmpty()) {
	        return ResponseEntity.noContent().build();
	    }
	    
	    return ResponseEntity.ok(noteDescriptions);
	}
	 
}
