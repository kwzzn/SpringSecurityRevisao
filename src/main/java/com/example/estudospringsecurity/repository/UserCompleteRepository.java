package com.example.estudospringsecurity.repository;

import com.example.estudospringsecurity.model.entity.User;
import com.example.estudospringsecurity.model.entity.UserComplete;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserCompleteRepository extends JpaRepository<UserComplete, Long> {

    Optional<UserComplete> findByUsername(String username);

}