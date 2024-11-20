package com.example.LearningManagementSystem.repo;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.LearningManagementSystem.model.Course;
import com.example.LearningManagementSystem.model.Professor;
import com.example.LearningManagementSystem.model.Student;

@Repository
public interface CourseRepo extends JpaRepository<Course,Long>{

	@Query("SELECT c FROM Course c WHERE c.professor = :professor")
    List<Course> findCourseByProfessor(@Param("professor") Professor professor);


    @Query("SELECT c.students FROM Course c WHERE c.id = :courseId")
    List<Student> findStudentsByCourseId(@Param("courseId") Long courseId);

    @Query("SELECT c FROM Course c JOIN c.students s WHERE s.id = :studentId")
    List<Course> findCoursesByStudentId(@Param("studentId") Long studentId);
}
