package org.example.dao;

import org.example.database.proprieties.Database;
import org.example.entity.ArtistEntity;
import org.example.entity.GenreEntity;

import java.sql.*;
import java.util.Optional;

public class GenreDAO implements DAO<GenreEntity> {

    public void create (GenreEntity genreEntity) throws SQLException {

        Optional<GenreEntity> genreCheck = findByName(genreEntity.getName());

        if(genreCheck.isPresent()){
            genreEntity.setId(genreCheck.get().getId());
            //System.out.println("Genre " + genreEntity.getName() + " already exists");
            return;
        }
        Connection connection = Database.getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO genres (name) VALUES (?)")
        ){
            preparedStatement.setString(1,genreEntity.getName());
            preparedStatement.executeUpdate();
            connection.commit();
            connection.close();

            GenreEntity genre = findByName(genreEntity.getName()).get();
            genreEntity.setId(genre.getId());

            //System.out.println("Genre " + genreEntity.getName() + " added successfully " + genreEntity.getId());
        }

    }

    public Optional<GenreEntity> findByName(String name ) throws SQLException {
        Connection connection = Database.getConnection();
        try(PreparedStatement statement = connection.prepareStatement(
                "select * from genres where upper(trim(name))=upper(trim( ? ))" )
        ){
            statement.setString(1,name);
            ResultSet resultSet = statement.executeQuery();


            if( resultSet.next() ){
                GenreEntity genreEntity = new GenreEntity(
                        resultSet.getInt(1),
                        resultSet.getString(2)
                );

                connection.close();
                return Optional.of(genreEntity);
            }
            else{
                connection.close();
                return Optional.empty();
            }
        }
    }

    public Optional<GenreEntity> findById ( int id ) throws SQLException {
        Connection connection = Database.getConnection();

        try(PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM genres WHERE id = ?");

        ){
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                GenreEntity genreEntity = new GenreEntity(
                        resultSet.getInt(1),
                        resultSet.getString(2)
                );
                connection.close();
                return Optional.of(genreEntity);
            }
            else{
                connection.close();
                return Optional.empty();
            }
        }
    }

}
