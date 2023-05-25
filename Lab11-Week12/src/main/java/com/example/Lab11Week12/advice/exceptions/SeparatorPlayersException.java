package com.example.Lab11Week12.advice.exceptions;

public class SeparatorPlayersException extends RuntimeException{
    public SeparatorPlayersException(String message){
        super("Couldn't find separator players(" + message + ")");
    }
}
