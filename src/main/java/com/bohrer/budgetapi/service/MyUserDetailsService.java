package com.bohrer.budgetapi.service;

import com.bohrer.budgetapi.domain.User;
import com.bohrer.budgetapi.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService  implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException(username);
        }
        return org.springframework.security.core.userdetails.User.withUsername(user.getUsername()).password(user.getPassword()).authorities("USER","ADMIN").build();
    }

    public void signUpUser(User user) {
        final String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        final User createdUser = userRepository.save(user);
    }

}