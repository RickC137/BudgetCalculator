package com.bohrer.budgetapi.service;

import com.bohrer.budgetapi.domain.User;
import com.bohrer.budgetapi.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService_Impl implements UserService{
    @Autowired
    private UserRepository userRepository;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();


    @Override
    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException(username);
        }

        return user;
    }

    @Override
    public User updateUserPassword(String username, String password) {
        User user = userRepository.findByUsername(username);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        userRepository.save(user);
        return user;
    }

    @Override
    public User updateUserPassword2(String username, String oldPass, String newPass) {
        User user = userRepository.findByUsername(username);
        if(oldPass != null && bCryptPasswordEncoder.encode(oldPass).equals(user.getPassword())) {
            user.setPassword(bCryptPasswordEncoder.encode(newPass));
        }
        return user;
    }



}
