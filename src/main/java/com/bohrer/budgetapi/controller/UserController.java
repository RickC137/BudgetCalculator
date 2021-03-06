package com.bohrer.budgetapi.controller;

import com.bohrer.budgetapi.domain.User;
import com.bohrer.budgetapi.service.MyUserDetailsService;
import com.bohrer.budgetapi.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    // insecure due to no auth
    @GetMapping("")
    public User testing(@RequestParam String username) {
        return userService.findByUsername(username);
    }

    @PostMapping("/create")
    public String createUser(@RequestBody User user) {
        System.out.println(user.toString());
        myUserDetailsService.signUpUser(user);
        return "User Added";
    }

    @GetMapping("/update")
    User updateUser(@RequestParam("user") String user, @RequestParam("newPass") String password, @RequestParam("curPass") String curPass) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails currentUser = (UserDetails)auth.getPrincipal();
        if(currentUser.getUsername().equals(user)) {
            return userService.updateUserPassword2(user, curPass, password);
        }
       return null;//myUserDetailsService.updateUserPassword2(user, password, );
    }

    
}
