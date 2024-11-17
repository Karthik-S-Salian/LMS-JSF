package com.example.LearningManagementSystem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table
public class Notes {

    @Id
    @SequenceGenerator(name = "notes_sequence", sequenceName = "notes_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notes_sequence")
    private Long id;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "course_id", nullable = false) // Foreign key to Course table
    private Course course;

    @Column(nullable = false)
    private String path;

    @Column(length = 500)
    private String description;
}
