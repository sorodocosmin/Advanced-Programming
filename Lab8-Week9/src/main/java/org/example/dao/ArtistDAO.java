package org.example.dao;

import org.example.database.proprieties.Database;
import org.example.entity.ArtistEntity;

import java.sql.*;
import java.util.Optional;

public class ArtistDAO implements DAO<ArtistEntity> {
    public void create (ArtistEntity artistEntity) throws SQLException{

        Optional<ArtistEntity> artistCheck = findByName(artistEntity.getName());

        if(artistCheck.isPresent()){
            artistEntity.setId(artistCheck.get().getId());
            //System.out.println("Artist " + artistEntity.getName() + " already exists");
            return;
        }

        Connection connection = Database.getConnection();


        try(PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO artists (name) VALUES (?)")
        ){
            preparedStatement.setString(1,artistEntity.getName());
            preparedStatement.executeUpdate();
            connection.commit();
            connection.close();


            ArtistEntity artist = findByName(artistEntity.getName()).get();
            artistEntity.setId(artist.getId());
            //System.out.println("Artist " + artistEntity.getName() + " added successfully " + artistEntity.getId());
        }
    }

    public Optional<ArtistEntity> findByName(String name) throws SQLException {

        Connection connection = Database.getConnection();
        try(PreparedStatement statement = connection.prepareStatement(
                "select * from artists where upper(trim(name))=upper(trim( ? ))" );
        ){

            statement.setString(1,name);
            ResultSet resultSet = statement.executeQuery();


            //return resultSet.next() ? resultSet.getInt(1) : null;
            if(resultSet.next()){
                ArtistEntity artistEntity = new ArtistEntity(
                        resultSet.getInt(1),
                        resultSet.getString(2)
                );
                connection.close();

                return Optional.of(artistEntity);
            }
            else{
                connection.close();
                return Optional.empty();
            }
        }
    }

    public Optional<ArtistEntity> findById (int id ) throws SQLException {

        Connection connection = Database.getConnection();

        try(PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM artists WHERE id = ?");

        ){
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();


            if(resultSet.next()){
                ArtistEntity artistEntity = new ArtistEntity(
                        resultSet.getInt(1),
                        resultSet.getString(2)
                );

                connection.close();

                return Optional.of(artistEntity);
            }
            else{
                connection.close();

                return Optional.empty();
            }
        }
    }

}
