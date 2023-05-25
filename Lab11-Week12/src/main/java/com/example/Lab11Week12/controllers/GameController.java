package com.example.Lab11Week12.controllers;

import com.example.Lab11Week12.models.dtos.GameCreationDto;
import com.example.Lab11Week12.models.dtos.GameDto;
import com.example.Lab11Week12.models.dtos.PlayerDto;
import com.example.Lab11Week12.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<List<GameDto>> getAll() {

        return ResponseEntity.ok().body(this.gameService.getAll());

    }

    @GetMapping(value = "/separatorPlayers/", produces = "application/json")
    public ResponseEntity<List<Long>> getSeparatorPlayers() {

        return ResponseEntity.ok().body(this.gameService.getSeparatorPlayers());

    }

    @PostMapping(value = "/", consumes = "application/json")
    public ResponseEntity<String> create(@RequestBody GameCreationDto gameDto) {

        this.gameService.create(gameDto);
        return ResponseEntity.ok().body("Game created successfully!");

    }

}
