package com.vortex.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vortex.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByAlias(String alias); // per login
    boolean existsByAlias(String alias);
}