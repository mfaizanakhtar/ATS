package com.ATS.Backend.service;

import com.ATS.Backend.exception.UserException;
import com.ATS.Backend.model.User;
import com.ATS.Backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> findUser = Optional.ofNullable(userRepository.findUserByusername(username));
        if(findUser.get()==null){
            throw new UserException("User Not Found");
        }
        return findUser.get();
    }

    public User Adduser(User user) throws Exception {
        Optional<User> findUser = Optional.ofNullable(userRepository.findUserByusername(user.getUsername()));
        if(findUser.isPresent()){
            throw new UserException("User Already Exists");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        User savedUser = userRepository.save(user);
        return savedUser;

    }
}
