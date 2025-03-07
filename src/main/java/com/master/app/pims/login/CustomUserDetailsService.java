package com.master.app.pims.login;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDetailRepo userRepository;
    
    @Autowired
    private Md5PasswordEncoder md5PasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	UserDetail user = userRepository.findByLoginId(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    	
        return User.builder()
                .username(user.getLoginId())
                .password(user.getPwd()) // Password is already MD5 hashed with salt
                //.roles(Collections.EMPTY_LIST)
                .build();
    }

    public boolean validatePassword(String rawPassword, String encodedPassword, String salt) {
        return md5PasswordEncoder.matches(rawPassword, encodedPassword, salt);
    }
}
