package com.example.LearningManagementSystem.config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// Check the roles of the authenticated user
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))) {
            response.sendRedirect("/admin/");
        } else if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("PROFESSOR"))) {
            response.sendRedirect("/professor/");
        } else if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("STUDENT"))) {
            response.sendRedirect("/student/");
        } else {
            response.sendRedirect("/access-denied");
        }
		
	}

}
