package com.example.Lab11Week12.advice;

import com.example.Lab11Week12.advice.exceptions.InvalidInputException;
import com.example.Lab11Week12.advice.exceptions.PlayerAlreadyExistsException;
import com.example.Lab11Week12.advice.exceptions.SeparatorPlayersException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(value = PlayerAlreadyExistsException.class)
    public ResponseEntity<String> handlePlayerAlreadyExistsException(PlayerAlreadyExistsException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(value = InvalidInputException.class)
    public ResponseEntity<String> handleInvalidInputException(InvalidInputException e){
        return ResponseEntity.status(405).body(e.getMessage());
    }

    @ExceptionHandler(value = SeparatorPlayersException.class)
    public ResponseEntity<String> handleSeparatorPlayersException(SeparatorPlayersException e){
        return ResponseEntity.status(404).body(e.getMessage());
    }

}
