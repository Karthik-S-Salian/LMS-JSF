package com.example.LearningManagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.LearningManagementSystem.model.Users;
import com.example.LearningManagementSystem.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/register")
    public Users register(@RequestBody Users user) {

        return service.registerUser(user);
    }

    @GetMapping("/")
    public String homeToStudent() {
        return "redirect:/student/";
    }
}
