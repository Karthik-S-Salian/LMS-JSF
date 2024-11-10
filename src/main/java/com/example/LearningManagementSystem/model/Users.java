package com.example.LearningManagementSystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table
public class Users {
	
	@Id
	@SequenceGenerator(
		name ="user_sequence",
		sequenceName="user_sequence",
		allocationSize=1
	)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,
		generator="user_sequence"
	)
	private int user_id;
	private String username;
	private String password;
	private String email;
	


	@Override
	public String toString() {
		return "Users [user_id=" + user_id + ", username=" + username + ", password=" + password + ", email=" + email
				+ "]";
	}
}
