package com.example.estudospringsecurity.repository;

import com.example.estudospringsecurity.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByAuthentication_Username (String username);
}
