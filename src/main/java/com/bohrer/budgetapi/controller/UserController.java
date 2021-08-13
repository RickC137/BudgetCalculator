package com.bohrer.budgetapi.controller;

import com.bohrer.budgetapi.domain.User;
import com.bohrer.budgetapi.service.MyUserDetailsService;
import com.bohrer.budgetapi.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    // insecure due to no auth
    @GetMapping("/user")
    public User testing(@RequestParam String username) {
        return userService.findByUsername(username);
    }

    @PostMapping("/user/add")
    public String createUser(@RequestBody User user) {
        myUserDetailsService.signUpUser(user);
        return "User Added";
    }

    
}
