package com.example.Lab11Week12.services;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class NodePlayer {

    private final int nrNode;
    private List<Long> neighbours; //in our case, this neighbours will be the players that the current player has played with

    public NodePlayer(int nrNode){
        this.nrNode = nrNode;
        this.neighbours = new ArrayList<>();
    }

    public void addNeighbour(long nrNode){
        this.neighbours.add(nrNode);
    }

    @Override
    public String toString() {
        return "NodePlayer{" +
                "nrNode=" + nrNode +
                ", idPlayers-Neighbours=" + neighbours +
                '}';
    }
}
