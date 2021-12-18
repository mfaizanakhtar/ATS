package com.ATS.Backend.controller;

import com.ATS.Backend.DTO.AuthRequest;
import com.ATS.Backend.DTO.AuthResponse;
import com.ATS.Backend.exception.UserException;
import com.ATS.Backend.model.User;
import com.ATS.Backend.utils.JwtUtil;
import com.ATS.Backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtil jwtTokenUtil;

    @PostMapping("/signup")
    public User userSignup(@RequestBody User user) throws Exception {
        User savedUser = userService.Adduser(user);
        return savedUser;
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthToken(@RequestBody AuthRequest authRequest) throws Exception {
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));
        }catch (Exception e){
            throw new UserException("Incorrect Username or Password");
        }

        final UserDetails user = userService.loadUserByUsername(authRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(user);
        return ResponseEntity.ok(new AuthResponse(jwt));

    }
}
