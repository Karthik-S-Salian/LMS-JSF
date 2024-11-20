package com.example.LearningManagementSystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
	private String username;
    private String email;
    private String password;
    private String usn;
    private Integer sem;
    private Long departmentid;
	
	}

