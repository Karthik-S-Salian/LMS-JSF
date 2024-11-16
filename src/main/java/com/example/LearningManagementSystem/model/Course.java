package com.example.LearningManagementSystem.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table
public class Course {
    
    @Id
    @SequenceGenerator(name = "course_sequence", sequenceName = "course_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_sequence")
    private Long id;
    
    @Column(nullable = false)
    private String name;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)  // Cascade delete when Department is deleted
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)  // Cascade delete when Professor is deleted
    @JoinColumn(name = "professor_id", nullable = false)
    private Professor professor;

    // Constructors, Getters and Setters
}
