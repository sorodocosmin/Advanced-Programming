package com.example.Lab11Week12.controllers;

import com.example.Lab11Week12.models.dtos.PlayerCreationDto;
import com.example.Lab11Week12.models.dtos.PlayerDto;
import com.example.Lab11Week12.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.awt.geom.RectangularShape;
import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

        private final PlayerService playerService;

        @Autowired
        public PlayerController(PlayerService playerService) {
            this.playerService = playerService;
        }

        @GetMapping(value = "/", produces = "application/json")
        public ResponseEntity<List<PlayerDto>> getAll() {
            return ResponseEntity.ok().body(this.playerService.getAll());
        }

        @PostMapping(value = "/", consumes = "application/json")
        public ResponseEntity<String> create(@RequestBody PlayerCreationDto playerCreationDto) {
            this.playerService.create(playerCreationDto);
            return ResponseEntity.ok().body("Player created successfully!");
        }

        @PutMapping(value = "/{id}", consumes = "application/json")
        public ResponseEntity<String> update(@PathVariable Long id, @RequestBody PlayerDto playerDto) {
            this.playerService.update(id, playerDto);
            return ResponseEntity.ok().body("Player updated successfully!");
        }

}
