package com.example.LearningManagementSystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration        //to indicate that it is a configuration class
@EnableWebSecurity   //to indicate spring not to go for default web security
public class SecurityConfig {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.csrf(customizer-> customizer.disable());  //to disable csrf
		//http.authorizeHttpRequests(request -> request.anyRequest().authenticated()); //allow access for only authenticated users
		
		http.authorizeHttpRequests(auth -> auth
	            .requestMatchers("/register","/login").permitAll() // Allow access to registration endpoint
	            .requestMatchers("/admin/**").hasAuthority("ADMIN") // Accessible only to ADMIN
	            .requestMatchers("/student/**").hasAuthority("STUDENT") // Accessible only to STUDENT
	            .requestMatchers("/professor/**").hasAuthority("PROFESSOR") // Accessible only to PROFESSOR
	            .anyRequest().authenticated() // All other requests require authentication
	        );

		
		http.formLogin(Customizer.withDefaults());  //Enables  spring security built in Form-Based Login and use the credentials to validate the user
		http.httpBasic(Customizer.withDefaults()); //to enable  REST API access using postman
		
		//http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); //makes http stateless By setting SessionCreationPolicy.STATELESS, 
		//you’re telling Spring Security not to create or use HTTP sessions for storing authentication information.
		
		//http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);  //add jwtFilter before UPAF
		return http.build();  //build() creates a object of security filter chain
		
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(new BCryptPasswordEncoder(10));
		provider.setUserDetailsService(userDetailsService);
		return provider;
	}
}
