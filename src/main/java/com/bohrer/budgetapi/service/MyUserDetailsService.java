package com.bohrer.budgetapi.service;

import com.bohrer.budgetapi.domain.Account;
import com.bohrer.budgetapi.domain.User;

public interface MyUserDetailsService {

    public User findByUsername(String username);
    // vuln since anyone can update any user
    public User updateUserPassword(String username, String password);
    public User updateUserPassword2(String username, String oldPass, String newPass);
}