package com.example.LearningManagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.example.LearningManagementSystem.model.Notes;
import com.example.LearningManagementSystem.model.Professor;
import com.example.LearningManagementSystem.service.ProfessorService;



@Controller
@RequestMapping("/professor")
public class ProfessorController {

	@Autowired
	private ProfessorService professorService;
	
	@GetMapping("/profile/{id}")
	public ResponseEntity<Professor> getProfessorById(@PathVariable Long id) {
		Professor professor = professorService.getProfessorById(id);
		if(professor !=null)
			return new ResponseEntity(professor,HttpStatus.OK);
		else
			return new ResponseEntity(professor,HttpStatus.NOT_FOUND);
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
	
	 
}
