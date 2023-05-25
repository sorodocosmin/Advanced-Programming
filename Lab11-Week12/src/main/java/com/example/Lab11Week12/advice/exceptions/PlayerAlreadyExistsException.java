package com.example.Lab11Week12.advice.exceptions;

public class PlayerAlreadyExistsException extends RuntimeException{

    public PlayerAlreadyExistsException(String email) {
        super("Email " + email + " already exists");
    }

}
