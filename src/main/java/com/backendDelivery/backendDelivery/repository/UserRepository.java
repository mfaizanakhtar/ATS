package com.backendDelivery.backendDelivery.repository;

import com.backendDelivery.backendDelivery.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    public User findUserByusername(String username);
}
