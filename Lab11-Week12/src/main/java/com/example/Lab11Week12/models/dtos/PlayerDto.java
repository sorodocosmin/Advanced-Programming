package com.example.Lab11Week12.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlayerDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

}
