package com.example.estudospringsecurity.model.entity;

import com.example.estudospringsecurity.security.model.entity.UserAuthentification;
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
    private Long id;
    private String email;
    private String endereco;

    @OneToOne
    private UserAuthentification authentication;
}
