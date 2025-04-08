package com.example.estudospringsecurity.model.exception;

public class CookieNotFoundException extends RuntimeException {

    public CookieNotFoundException(String name) {
        super("O cookie de nome " + name + " não foi encontrado!");
    }
}
