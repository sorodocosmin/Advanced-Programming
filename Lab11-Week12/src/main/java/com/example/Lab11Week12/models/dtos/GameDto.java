package com.example.Lab11Week12.models.dtos;


import lombok.Data;

@Data
public class GameDto {

    private PlayerDto player1;
    private PlayerDto player2;
    private Long idWinner;

}
