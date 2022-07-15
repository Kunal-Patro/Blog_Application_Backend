package com.haanbhai.blogs.security;

import com.haanbhai.blogs.entities.User;
import com.haanbhai.blogs.exceptions.ResourceNotFoundException;
import com.haanbhai.blogs.repositiories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepo.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("User","username",username));
        return user;
    }
}
