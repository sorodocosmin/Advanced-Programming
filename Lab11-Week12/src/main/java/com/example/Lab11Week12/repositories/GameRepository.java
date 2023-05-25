package com.example.Lab11Week12.repositories;

import com.example.Lab11Week12.models.entities.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<GameEntity, Long> {



}
