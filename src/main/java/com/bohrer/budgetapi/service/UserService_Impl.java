package com.bohrer.budgetapi.service;

import com.bohrer.budgetapi.domain.User;
import com.bohrer.budgetapi.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService_Impl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CreateDefaultData createDefaultData;

    @Override
    public User findByUsername(String username) {
        /**********************************************************/
        //Between this line would be removed for normal coding
        // since I dont have much time to create a database this can be used to load some
        // default test data
        if(userRepository.findAll().size() == 0) {
            createDefaultData.createData();
        }
        /**********************************************************/
        User user = userRepository.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException(username);
        }

        return user;
    }

    @Override
    public User updateUserPassword(String username, String password) {
        User user = userRepository.findByUsername(username);
        user.setPassword(password);
        userRepository.save(user);
        return user;
    }

    @Override
    public User updateUserPassword2(String username, String oldPass, String newPass) {
        User user = userRepository.findByUsername(username);
        if(oldPass != null && oldPass.equals(user.getPassword())) {
            user.setPassword(newPass);
        }
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException(username);
        }
        return org.springframework.security.core.userdetails.User.withUsername(user.getUsername()).password(user.getPassword()).authorities("USER").build();
    }



}
