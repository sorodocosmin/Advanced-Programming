package com.example.Lab11Week12;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//http://localhost:4040/swagger-ui/index.html
@SpringBootApplication
@OpenAPIDefinition
public class Lab11Week12Application {

	public static void main(String[] args) {
		SpringApplication.run(Lab11Week12Application.class, args);
	}

}
