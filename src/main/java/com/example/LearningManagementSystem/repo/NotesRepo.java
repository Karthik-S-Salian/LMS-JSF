package com.example.LearningManagementSystem.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.LearningManagementSystem.model.Notes;

@Repository
public interface NotesRepo extends JpaRepository<Notes,Long>{
	@Query("SELECT n FROM Notes n WHERE n.course.id = :courseId")
    List<Notes> findNotesByCourseId(@Param("courseId") Long courseId);
}
