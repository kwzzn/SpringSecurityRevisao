package com.example.estudospringsecurity.security.filter;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.estudospringsecurity.model.exception.CookieNotFoundException;
import com.example.estudospringsecurity.security.service.AuthentificationService;
import com.example.estudospringsecurity.security.utils.CookieUtils;
import com.example.estudospringsecurity.security.utils.JWTUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtils jwtUtils = new JWTUtils();
    private final CookieUtils cookieUtils = new CookieUtils();
    private AuthentificationService authService;

    public JWTFilter(AuthentificationService authService) {
        this.authService = authService;
    }

    @Override
    protected void doFilterInternal (
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
        throws ServletException, IOException {
        try {
            Cookie cookie = cookieUtils.getJWTCookie(request);
            String token = cookie.getValue();
            jwtUtils.validarToken(token);
            String username = jwtUtils.getUsername(token);
            UserDetails user = authService.loadUserByUsername(username);
            Authentication auth = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        } catch (CookieNotFoundException cookieE) {
            filterChain.doFilter(request, response);
        } catch (SignatureVerificationException | TokenExpiredException jwtE) {

        }
        filterChain.doFilter(request,response);
    }
}
