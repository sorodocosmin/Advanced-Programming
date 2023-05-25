package com.example.Lab11Week12.repositories;

import com.example.Lab11Week12.models.entities.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerEntity, Long> {
    @Query(value = "SELECT count('c') FROM players WHERE email = :email", nativeQuery = true)
    int findByEmail (@Param("email") String email);
}
