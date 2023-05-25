package com.example.Lab11Week12.models.dtos;

import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class PlayerCreationDto {

    private String firstName;
    private String lastName;
    private String email;
    private String password;

}
