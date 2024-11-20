package com.example.LearningManagementSystem.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.LearningManagementSystem.model.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student,Long> {
    
    @Query("SELECT s FROM Student s WHERE s.id NOT IN (SELECT st.id FROM Course c JOIN c.students st WHERE c.id = :courseId)")
    List<Student> findStudentsNotEnrolledInCourse(@Param("courseId") Long courseId);

}
