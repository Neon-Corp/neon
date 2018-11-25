package com.neon.service;

import com.neon.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        com.neon.model.User appUser = userService.findOneByUsername(s);

        if (appUser == null) {
            throw new UsernameNotFoundException("User " + s + " was not found in the database");
        }

        List<GrantedAuthority> grantList = new ArrayList<>();
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_CUSTOMER");
        grantList.add(authority);
        UserDetails userDetails = new User(appUser.getUsername(), appUser.getPassword(), grantList);
        return userDetails;
    }
}
