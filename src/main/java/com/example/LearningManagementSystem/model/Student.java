package com.example.LearningManagementSystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table
public class Student {
    @Id
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private Users user;

    @Column(nullable = false, unique = true)
    private String usn;

    @Column(nullable = false)
    private Integer sem;

    // Constructors
    public Student() {}

    public Student(Users user, String usn, Integer sem) {
        this.user = user;
        this.usn = usn;
        this.sem = sem;
    }
    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;
}
