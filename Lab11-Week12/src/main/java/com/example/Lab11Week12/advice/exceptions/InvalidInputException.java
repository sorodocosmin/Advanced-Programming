package com.example.Lab11Week12.advice.exceptions;

public class InvalidInputException extends RuntimeException{

        public InvalidInputException(String message) {
            super("Invalid input " + message );
        }
}
