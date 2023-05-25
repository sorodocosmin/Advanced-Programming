package com.example.Lab11Week12.advice.exceptions;

public class PlayerNotFoundException extends RuntimeException{

        public PlayerNotFoundException(Long id) {
            super("Player with id " + id + " not found");
        }
}
