package com.example.LearningManagementSystem.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class Student {
    @Id
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @MapsId
    @JoinColumn(name = "id")
    private Users user;

    @Column(nullable = false, unique = true)
    private String usn;

    @Column(nullable = false)
    private Integer sem;

    
    @ManyToOne
    @JoinColumn(name = "department_id", nullable = true)
    private Department department;
    
    @ManyToMany(mappedBy = "students")
    private Set<Course> courses = new HashSet<>();

	
}
