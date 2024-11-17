package com.example.LearningManagementSystem.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.LearningManagementSystem.model.Course;
import com.example.LearningManagementSystem.model.Notes;
import com.example.LearningManagementSystem.model.Professor;
import com.example.LearningManagementSystem.repo.CourseRepo;
import com.example.LearningManagementSystem.repo.NotesRepo;
import com.example.LearningManagementSystem.repo.ProfessorRepo;

@Service
public class ProfessorService {

	private final String uploadDir = "uploads/";
	
	@Autowired
	private NotesRepo notesRepo;
	
	@Autowired
	private ProfessorRepo professorRepo;
	
	@Autowired
	private CourseRepo courseRepo;
	
	public Professor getProfessorById(Long id) {
		// TODO Auto-generated method stub
		return professorRepo.findById(id).orElse(null);
	}

	public Notes saveNotes(MultipartFile file,Long courseId, String description) throws IOException {
		// TODO Auto-generated method stub
		//validate course id
		Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
		
		// Create the upload directory if it doesn't exist
		Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        
     // Save the file to the file system
        Path filePath = uploadPath.resolve(file.getOriginalFilename());
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        //save the notes entity in database
        Notes notes = new Notes();
        notes.setCourse(course);
        notes.setPath(filePath.toString());
        notes.setDescription(description);
        return notesRepo.save(notes);
		
	}
	
	

}
