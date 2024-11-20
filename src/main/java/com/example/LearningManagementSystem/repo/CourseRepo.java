package com.example.LearningManagementSystem.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.LearningManagementSystem.model.Course;
import com.example.LearningManagementSystem.model.Professor;

@Repository
public interface CourseRepo extends JpaRepository<Course,Long>{

	@Query("SELECT c FROM Course c WHERE c.professor = :professor")
    List<Course> findCourseByProfessor(@Param("professor") Professor professor);

}
