package com.backendDelivery.backendDelivery.controller;

import com.backendDelivery.backendDelivery.DTO.AuthRequest;
import com.backendDelivery.backendDelivery.DTO.AuthResponse;
import com.backendDelivery.backendDelivery.service.MyUserDetailsService;
import com.backendDelivery.backendDelivery.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Authenticate {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    MyUserDetailsService userService;

    @Autowired
    JwtUtil jwtTokenUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthToken(@RequestBody AuthRequest authRequest) throws Exception {
    try{
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));
    }catch (BadCredentialsException e){
        throw new Exception("Incorrect Username or Password");
    }
    final UserDetails user = userService.loadUserByUsername(authRequest.getUsername());
    final String jwt = jwtTokenUtil.generateToken(user);
    return ResponseEntity.ok(new AuthResponse(jwt));

    }

}
