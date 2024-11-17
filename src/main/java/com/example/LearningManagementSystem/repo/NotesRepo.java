package com.example.LearningManagementSystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.LearningManagementSystem.model.Notes;

@Repository
public interface NotesRepo extends JpaRepository<Notes,Long>{

}
