package com.example.estudospringsecurity.repository;

import com.example.estudospringsecurity.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional <User> findByUsername(String username);
}
