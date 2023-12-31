package com.roombhada.RoomBhada.controllers;

import com.roombhada.RoomBhada.config.JwtUtils;
import com.roombhada.RoomBhada.entities.JwtRequest;
import com.roombhada.RoomBhada.entities.JwtResponse;
import com.roombhada.RoomBhada.entities.User;
import com.roombhada.RoomBhada.services.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private JwtUtils jwtUtils;


    //generate-token
    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {

        try {
            authenticate(jwtRequest.getEmail(), jwtRequest.getPassword());
        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
            throw new Exception("User not found !!");
        }

        //authenticate

        UserDetails userDetails = this.userDetailsServiceImpl.loadUserByUsername(jwtRequest.getEmail());
        String token = this.jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }



    private  void authenticate(String username, String password) throws Exception {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        } catch (DisabledException e) {
            throw new Exception("User Disabled");
        } catch (BadCredentialsException e) {
            throw new Exception("Invalid Credentials" +e.getMessage());
        }
    }



    //get current user details
    @GetMapping("/current-user")
    public User getCurrentUser(Principal principal) {

       return  ((User) this.userDetailsServiceImpl.loadUserByUsername(principal.getName()));
    }



}
