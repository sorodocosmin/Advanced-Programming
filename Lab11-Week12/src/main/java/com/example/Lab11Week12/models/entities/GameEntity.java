package com.example.Lab11Week12.models.entities;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "games")
public class GameEntity extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "id_player1")
    private PlayerEntity player1;

    @ManyToOne
    @JoinColumn(name = "id_player2")
    private PlayerEntity player2;

    @Column(name = "id_winner")
    private Long idWinner;

}
