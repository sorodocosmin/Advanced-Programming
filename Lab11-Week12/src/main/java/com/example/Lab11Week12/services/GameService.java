package com.example.Lab11Week12.services;

import com.example.Lab11Week12.advice.exceptions.InvalidInputException;
import com.example.Lab11Week12.advice.exceptions.PlayerNotFoundException;
import com.example.Lab11Week12.advice.exceptions.SeparatorPlayersException;
import com.example.Lab11Week12.mappers.PlayerMapper;
import com.example.Lab11Week12.models.dtos.GameCreationDto;
import com.example.Lab11Week12.models.dtos.GameDto;
import com.example.Lab11Week12.models.dtos.PlayerDto;
import com.example.Lab11Week12.models.entities.GameEntity;
import com.example.Lab11Week12.models.entities.PlayerEntity;
import com.example.Lab11Week12.repositories.GameRepository;
import com.example.Lab11Week12.repositories.PlayerRepository;
import gurobi.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameService {
    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;

    @Autowired
    public GameService(GameRepository gameRepository,
                       PlayerRepository playerRepository) {

        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;

    }


    public List<GameDto> getAll() {

        return this.gameRepository.findAll().stream().map(gameEntity -> {
            GameDto gameDto = new GameDto();

            gameDto.setPlayer1(PlayerMapper.toDto(gameEntity.getPlayer1(),false));
            gameDto.setPlayer2(PlayerMapper.toDto(gameEntity.getPlayer2(),false));

            gameDto.setIdWinner(gameEntity.getIdWinner());

            return gameDto;
        }).toList();

    }

    public void create(GameCreationDto gameDto) {

        PlayerEntity player1 = this.playerRepository.findById(gameDto.getIdPlayer1())
                .orElseThrow(() -> new PlayerNotFoundException(gameDto.getIdPlayer1()));

        PlayerEntity player2 = this.playerRepository.findById(gameDto.getIdPlayer2())
                .orElseThrow(() -> new PlayerNotFoundException(gameDto.getIdPlayer2()));

        if(!Objects.equals(gameDto.getIdWinner(), gameDto.getIdPlayer1()) && !Objects.equals(gameDto.getIdWinner(), gameDto.getIdPlayer2())){
            throw new InvalidInputException(" at creating a new game");
        }

        GameEntity game = GameEntity.builder()
                .player1(player1)
                .player2(player2)
                .idWinner(gameDto.getIdWinner())
                .build();

        this.gameRepository.save(game);

    }

    public List<Long> getSeparatorPlayers() {

        List<GameDto> games = this.getAll();

        if(games.size() < 2){
            throw new SeparatorPlayersException("nr games < 2");
        }


        Map<Long, NodePlayer> adjacencyList = new HashMap<>();

        this.populateAdjacencyList(games, adjacencyList);

        if(adjacencyList.size() < 2){
            throw new SeparatorPlayersException("nr players < 2");
        }

        Map<Integer, Long> nodeToIdPlayer = new HashMap<>();

        //for every nodeNr from the NodePlayer object, we will map nr node to id player
        //go through the map
        for(Map.Entry<Long, NodePlayer> entry : adjacencyList.entrySet()){

            nodeToIdPlayer.put(entry.getValue().getNrNode(), entry.getKey());

        }


        List<Long> separatorPlayersId = new ArrayList<>();
        try{
            GRBEnv env = new GRBEnv();
            env.set(GRB.IntParam.OutputFlag, 0);

            GRBModel model = new GRBModel(env);

            GRBVar [] x = new GRBVar[adjacencyList.size()];
            GRBVar [] y = new GRBVar[adjacencyList.size()];

            double maximCardinal = 2 * adjacencyList.size() / 3.0;

            //create the variables
            for(int i = 0, n = adjacencyList.size(); i < n; i++){

                x[i] = model.addVar(0, 1, 0, GRB.BINARY, "x[" + i + "]");
                y[i] = model.addVar(0, 1, 0, GRB.BINARY, "y[" + i + "]");

            }

            //nowe, we will ge through the adjacency list and create the constraints

            GRBLinExpr sumAllSameNodes = new GRBLinExpr();
            GRBLinExpr sumFromA = new GRBLinExpr();
            GRBLinExpr sumFromB = new GRBLinExpr();

            for(NodePlayer nodePlayer : adjacencyList.values()){

                int nodeNumber = nodePlayer.getNrNode();

                sumFromA.addTerm(1,x[nodeNumber]);
                sumFromB.addTerm(1,y[nodeNumber]);

                sumAllSameNodes.addTerm(1,x[nodeNumber]);
                sumAllSameNodes.addTerm(1,y[nodeNumber]);

                GRBLinExpr exprSumEachNode = new GRBLinExpr();

                exprSumEachNode.addTerm(1,x[nodeNumber]);
                exprSumEachNode.addTerm(1,y[nodeNumber]);
                //a node won t be part of both sets A and B
                model.addConstr(exprSumEachNode, GRB.LESS_EQUAL, 1,"sum-each-node[" + nodeNumber + "]");

                for(long neighbourPlayerId : nodePlayer.getNeighbours()){

                    GRBLinExpr exprSum = new GRBLinExpr();
                    exprSum.addTerm(1,x[nodeNumber]);
                    exprSum.addTerm(1,y[adjacencyList.get(neighbourPlayerId).getNrNode()]);
                    //there won t be any edeges between nodes from set A <-> B
                    model.addConstr(exprSum, GRB.LESS_EQUAL, 1,"no-edge["+ nodeNumber + "][" + adjacencyList.get(neighbourPlayerId).getNrNode() + "]");

                }

            }

            model.addConstr(sumFromA, GRB.LESS_EQUAL, maximCardinal,"sum-from-a <= 2/3 * n");
            model.addConstr(sumFromB, GRB.LESS_EQUAL, maximCardinal,"sum-from-b <= 2/3 * n");

            model.addConstr(sumFromA, GRB.GREATER_EQUAL, 1,"sum-from-a > 0");
            model.addConstr(sumFromB, GRB.GREATER_EQUAL, 1,"sum-from-b > 0");

            model.setObjective(sumAllSameNodes, GRB.MAXIMIZE);

            model.optimize();

            for( int i = 0, n=adjacencyList.size(); i < n; i++){

//                System.out.println(x[i].get(GRB.StringAttr.VarName) + " = " + x[i].get(GRB.DoubleAttr.X));
//                System.out.println(y[i].get(GRB.StringAttr.VarName) + " = " + y[i].get(GRB.DoubleAttr.X));
                if(x[i].get(GRB.DoubleAttr.X) != 1 && y[i].get(GRB.DoubleAttr.X) != 1){
                    separatorPlayersId.add(nodeToIdPlayer.get(i));
                    System.out.println(" idPlayerBelongs to C : " + nodeToIdPlayer.get(i));

                } else if (x[i].get(GRB.DoubleAttr.X) == 1) {
                    System.out.println(" idPlayerBelongs to A : " + nodeToIdPlayer.get(i));
                } else {
                    System.out.println(" idPlayerBelongs to B : " + nodeToIdPlayer.get(i));
                }

            }


        }catch (GRBException e){
            throw new SeparatorPlayersException("Gurobi could not find a solution");
        }



        return separatorPlayersId;

    }

    private void populateAdjacencyList(List<GameDto> games, Map<Long, NodePlayer> adjacencyList) {

        int nodeNumber = 0;

        for( GameDto game : games){

            long player1Id = game.getPlayer1().getId();
            long player2Id = game.getPlayer2().getId();

            if(adjacencyList.containsKey(player1Id)){

                //verify if the player2 is already in the list of neighbours of player1
                if(!adjacencyList.get(player1Id).getNeighbours().contains(player2Id)){

                    //if it's not -> verify if it's a node in our graph
                    if(!adjacencyList.containsKey(player2Id)){
                        //if it's not -> create the node for player2
                        adjacencyList.put(player2Id, new NodePlayer(nodeNumber++));
                    }

                    adjacencyList.get(player1Id).addNeighbour(player2Id);
                    adjacencyList.get(player2Id).addNeighbour(player1Id);
                }
            }
            else{//if the player1 is not yet a node in out graph

                //create the node for player1
                adjacencyList.put(player1Id, new NodePlayer(nodeNumber++));

                //if the player2 is also NOT  a node in our graph
                if(!adjacencyList.containsKey(player2Id)){
                    adjacencyList.put(player2Id, new NodePlayer(nodeNumber++));
                }

                //we add player2 as a neighbour of player1 and vice-versa
                adjacencyList.get(player1Id).addNeighbour(player2Id);
                adjacencyList.get(player2Id).addNeighbour(player1Id);
            }

        }

    }


}
