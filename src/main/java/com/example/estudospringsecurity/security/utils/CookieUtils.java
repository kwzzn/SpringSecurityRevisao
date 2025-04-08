package com.example.estudospringsecurity.security.utils;

import com.example.estudospringsecurity.model.exception.CookieNotFoundException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import javax.naming.Name;

public class CookieUtils {

    private final String NAME = "JWTSESSION";

    public Cookie getJWTCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(NAME)) {
                return cookie;
            }
        }
        throw new CookieNotFoundException(NAME);
    }
}
