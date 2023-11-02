package com.roombhada.RoomBhada.services.impl;

import com.roombhada.RoomBhada.entities.User;
import com.roombhada.RoomBhada.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user =    this.userRepo.findByEmail(username);

        if(user==null) {
            System.out.println("User not found");
            throw new UsernameNotFoundException("No user found !!");
        }



        return user;
    }
}
