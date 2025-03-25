package com.example.estudospringsecurity.model.entity;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.*;

@Builder
@Entity
@Table(name = "user_tb")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String password;
}
