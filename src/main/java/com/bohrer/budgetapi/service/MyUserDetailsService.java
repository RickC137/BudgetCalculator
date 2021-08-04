package com.bohrer.budgetapi.service;

import com.bohrer.budgetapi.domain.User;

public interface MyUserDetailsService {

    public User findByUsername(String username);
}