package com.bohrer.budgetapi.service;

import javax.servlet.http.HttpServletRequest;

import com.bohrer.budgetapi.domain.User;
import com.bohrer.budgetapi.repository.UserRepository;
import com.bohrer.budgetapi.securingweb.LoginAttemptService;

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

    @Autowired
    private LoginAttemptService loginAttemptService;

    @Autowired
    private HttpServletRequest request;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        String ip = getClientIp();
        if(loginAttemptService.isBlocked(ip)) {
            throw new RuntimeException("Blocked");
        }
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

    /**
     * Special care should be used when dealing with header values
     * Here if the proxy does not remove/clear the values, passed by a user
     * to the X-Forwarded-For header, then the user could simply change the value
     * in that header to what ever they like after X amount of attempts to get
     * infinite log in attempts.  
     */
    private String getClientIp(){
        String xfHeader = request.getHeader("X-Forwarded-For");
        if(xfHeader == null) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }

}