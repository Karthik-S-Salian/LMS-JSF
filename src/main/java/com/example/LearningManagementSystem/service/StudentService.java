package com.example.LearningManagementSystem.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.LearningManagementSystem.model.Notes;
import com.example.LearningManagementSystem.model.Student;
import com.example.LearningManagementSystem.model.Users;
import com.example.LearningManagementSystem.repo.NotesRepo;
import com.example.LearningManagementSystem.repo.StudentRepo;
import com.example.LearningManagementSystem.repo.UserRepo;

@Service
public class StudentService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private StudentRepo studentRepo;
	
	@Autowired
	private NotesRepo notesRepo;
	
	public Student getStudentByUsername(String username) {
		// TODO Auto-generated method stub
		Users user = userRepo.findByUsername(username);

        if (user != null) {
            // Fetch the professor by user ID
            return studentRepo.findById(user.getUser_id()).orElse(null);
        }

        return null;
	}
	
	public byte[] getFile(Long fileId) throws IOException {
		// TODO Auto-generated method stub
		Notes note = notesRepo.findById(fileId)
                .orElseThrow(() -> new RuntimeException("File not found"));

        Path path = Paths.get(note.getPath());
        return Files.readAllBytes(path);
	}

	public Notes getFileEntity(Long fileId) {
		// TODO Auto-generated method stub
		Optional<Notes> fileEntity = notesRepo.findById(fileId);
	    if (fileEntity.isPresent()) {
	        return fileEntity.get();
	    } else {
	        throw new RuntimeException("File not found with ID: " + fileId);
	    }
	}

}
