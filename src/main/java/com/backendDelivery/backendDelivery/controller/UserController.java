package com.backendDelivery.backendDelivery.controller;

import com.backendDelivery.backendDelivery.model.User;
import com.backendDelivery.backendDelivery.service.MyUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/user")
public class UserController {
    @Autowired
    MyUserDetailsService userService;

    @PostMapping("/signup")
    public User userSignup(@RequestBody User user) throws Exception {
        User savedUser = userService.Adduser(user);
        return savedUser;
    }
}
