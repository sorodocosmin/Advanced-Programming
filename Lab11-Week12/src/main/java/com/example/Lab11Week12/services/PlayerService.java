package com.example.Lab11Week12.services;

import com.example.Lab11Week12.advice.exceptions.InvalidInputException;
import com.example.Lab11Week12.advice.exceptions.PlayerAlreadyExistsException;
import com.example.Lab11Week12.advice.exceptions.PlayerNotFoundException;
import com.example.Lab11Week12.mappers.PlayerMapper;
import com.example.Lab11Week12.models.dtos.PlayerCreationDto;
import com.example.Lab11Week12.models.dtos.PlayerDto;
import com.example.Lab11Week12.models.entities.PlayerEntity;
import com.example.Lab11Week12.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository){
        this.playerRepository = playerRepository;
    }

    public List<PlayerDto> getAll(){

        return this.playerRepository.findAll().stream().map(playerEntity -> {

            PlayerDto playerDto = PlayerMapper.toDto(playerEntity, true);
            playerDto.setId(playerEntity.getId());

            return playerDto;
        }).toList();

    }

    public void create(PlayerCreationDto playerCreationDto){

        if(!playerCreationIsValid(playerCreationDto)){
            throw new InvalidInputException(" at creating a new player");
        }
        if(this.playerRepository.findByEmail(playerCreationDto.getEmail()) > 0){
            throw new PlayerAlreadyExistsException(playerCreationDto.getEmail());
        }

        PlayerEntity player = PlayerMapper.fromCreationDto(playerCreationDto);

        this.playerRepository.save(player);

    }


    public void update(Long id, PlayerDto playerDto) {

        PlayerEntity player = this.playerRepository.findById(id)
                .orElseThrow( () -> new PlayerNotFoundException(id));

        if(!playerIsValid(playerDto)){
            throw new InvalidInputException(" at updating a player");
        }

        if(this.playerRepository.findByEmail(playerDto.getEmail()) > 0){
            throw new PlayerAlreadyExistsException(playerDto.getEmail());
        }

        player.setFirstName(playerDto.getFirstName());
        player.setLastName(playerDto.getLastName());
        player.setEmail(playerDto.getEmail());
        player.setPassword(playerDto.getPassword());

        this.playerRepository.save(player);

    }

    private boolean playerIsValid(PlayerDto playerDto) {

        return validFields(playerDto.getFirstName(),
                playerDto.getLastName(),
                playerDto.getEmail(),
                playerDto.getPassword());

    }

    private boolean playerCreationIsValid(PlayerCreationDto playerCreationDto) {

        return validFields(playerCreationDto.getFirstName(),
                playerCreationDto.getLastName(),
                playerCreationDto.getEmail(),
                playerCreationDto.getPassword()
        );
    }

    private boolean validFields(String firstName, String lastName, String email, String password) {
        if(firstName == null || firstName.isEmpty()){
            return false;
        }

        if(lastName == null || lastName.isEmpty()){
            return false;
        }

        if(email == null || email.isEmpty()){
            return false;
        }

        if(password == null || password.isEmpty()){
            return false;
        }

        return true;
    }

}
