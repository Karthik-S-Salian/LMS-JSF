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
    /*@ManyToOne
    @JoinColumn(name = "department_id", nullable = true)
    private Department department;*/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getUsn() {
		return usn;
	}

	public void setUsn(String usn) {
		this.usn = usn;
	}

	public Integer getSem() {
		return sem;
	}

	public void setSem(Integer sem) {
		this.sem = sem;
	}

	/*public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;*/
	
}
