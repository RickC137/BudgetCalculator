package com.bohrer.budgetapi.service;

import com.bohrer.budgetapi.domain.User;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    
    public User findByUsername(String username);
    public UserDetails loadUserByUsername(String username);
    // vuln since anyone can update any user
    public User updateUserPassword(String username, String password);
    public User updateUserPassword2(String username, String oldPass, String newPass);
}