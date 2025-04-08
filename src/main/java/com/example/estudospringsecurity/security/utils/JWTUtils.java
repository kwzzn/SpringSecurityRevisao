package com.example.estudospringsecurity.security.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.estudospringsecurity.model.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class JWTUtils {

    private final String SENHAJWT = "SenhaSegur4!";

    public String gerarToken(UserDetails user) {
        Algorithm alg = Algorithm.HMAC256(SENHAJWT);
        return JWT.create()
                .withIssuer("SENAI")
                .withSubject(user.getUsername())
                .withIssuedAt(criacao())
                .withExpiresAt(validade())
                .sign(alg);
    }

    private Instant criacao() {
        return ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).toInstant();
    }

    private Instant validade() {
        return ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).plusMinutes(30).toInstant();
    }

    public void validarToken(String token) {
        Algorithm alg = Algorithm.HMAC256(SENHAJWT);
        DecodedJWT decodedJWT = JWT.decode(token);
        alg.verify(decodedJWT);
        if (decodedJWT.getExpiresAt().toInstant().isBefore(criacao())) {
            throw new TokenExpiredException("", decodedJWT.getExpiresAt().toInstant());
        }
    }

    public String getUsername(String token) {
        return JWT.decode(token).getSubject();
    }
}
