package com.example.estudospringsecurity.controller;

import com.example.estudospringsecurity.security.model.dto.LoginDTO;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final  SecurityContextRepository securityContextRepository;

    @PostMapping("/api/auth/login")
    public void login(@RequestBody LoginDTO loginDTO, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        Authentication auth = new UsernamePasswordAuthenticationToken(loginDTO.username(), loginDTO.password());
        authenticationManager.authenticate(auth);
        auth = authenticationManager.authenticate(auth);
        if (auth.isAuthenticated()) {
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(auth);
            securityContextRepository.saveContext(securityContext ,httpServletRequest,httpServletResponse);
        }
    }

    @PostMapping("/api/auth/logout")
    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        securityContextRepository.saveContext(SecurityContextHolder.createEmptyContext(), httpServletRequest, httpServletResponse);
    }
}
