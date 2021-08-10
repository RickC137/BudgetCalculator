package com.bohrer.budgetapi.controller;

import com.bohrer.budgetapi.domain.User;
import com.bohrer.budgetapi.service.MyUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @GetMapping("/user")
    public User testing(@RequestParam String username) {
        return myUserDetailsService.findByUsername(username);
    }
    
}
