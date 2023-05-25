package com.example.Lab11Week12.mappers;

import com.example.Lab11Week12.models.dtos.PlayerCreationDto;
import com.example.Lab11Week12.models.dtos.PlayerDto;
import com.example.Lab11Week12.models.entities.PlayerEntity;

public class PlayerMapper {

    public static PlayerDto toDto(PlayerEntity playerEntity, boolean seePlayerCredentials){

        return PlayerDto.builder()
                .id(playerEntity.getId())
                .firstName(playerEntity.getFirstName())
                .lastName(playerEntity.getLastName())
                .email(seePlayerCredentials ? playerEntity.getEmail() : "")
                .password(seePlayerCredentials ? playerEntity.getPassword() : "")
                .build();

    }

    public static PlayerEntity fromCreationDto (PlayerCreationDto playerCreationDto) {

    	return PlayerEntity.builder()
    			.firstName(playerCreationDto.getFirstName())
    			.lastName(playerCreationDto.getLastName())
    			.email(playerCreationDto.getEmail())
    			.password(playerCreationDto.getPassword())
    			.build();
    }


}
