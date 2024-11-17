package com.example.LearningManagementSystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.LearningManagementSystem.model.Course;

public interface CourseRepo extends JpaRepository<Course,Long>{

}
