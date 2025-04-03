package com.example.estudospringsecurity.controller;

import com.example.estudospringsecurity.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste")
@AllArgsConstructor
public class testeController {

    @GetMapping
    public String teste() {
        return "MI74";
    }

}
