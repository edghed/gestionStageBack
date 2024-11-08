package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.security.User;

public interface UserRepository extends JpaRepository<User, Long>

{
    Optional<User> findByUsername(String username);

}
