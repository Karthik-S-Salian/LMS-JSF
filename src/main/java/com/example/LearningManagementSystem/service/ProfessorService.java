package com.example.LearningManagementSystem.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.LearningManagementSystem.model.Course;
import com.example.LearningManagementSystem.model.Notes;
import com.example.LearningManagementSystem.model.Professor;
import com.example.LearningManagementSystem.model.Users;
import com.example.LearningManagementSystem.repo.CourseRepo;
import com.example.LearningManagementSystem.repo.NotesRepo;
import com.example.LearningManagementSystem.repo.ProfessorRepo;
import com.example.LearningManagementSystem.repo.UserRepo;

@Service
public class ProfessorService {

	private final String uploadDir = "uploads/";
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private NotesRepo notesRepo;
	
	@Autowired
	private ProfessorRepo professorRepo;
	
	@Autowired
	private CourseRepo courseRepo;
	
	public Professor getProfessorByUsername(String username) {
        // Fetch the user by username
        Users user = userRepo.findByUsername(username);

        if (user != null) {
            // Fetch the professor by user ID
            return professorRepo.findById(user.getUser_id()).orElse(null);
        }

        return null;
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
        notes.setDescription(file.getOriginalFilename());
        return notesRepo.save(notes);
		
	}

	public List<Course> getCoursesByProfessor(Professor professor) {		
		return courseRepo.findCourseByProfessor(professor);
	}

	public List<Notes> getNotesByCourseId(Long courseId) {
		return notesRepo.findNotesByCourseId(courseId);
	}
	

	public List<Professor> getAllProfessors() {
		return professorRepo.findAll();
	}
}
