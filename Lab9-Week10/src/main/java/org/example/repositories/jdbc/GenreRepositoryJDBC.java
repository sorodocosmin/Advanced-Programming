package org.example.repositories.jdbc;

import org.example.connections.ConnectionJDBC;
import org.example.entities.GenreEntity;
import org.example.repositories.DataRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class GenreRepositoryJDBC extends JDBCDataRepository <GenreEntity,Integer> {
    @Override
    public void create(GenreEntity genreEntity) {
        Optional<GenreEntity> genreCheck = findByName(genreEntity.getName());

        if(genreCheck.isPresent()){
            genreEntity.setId(genreCheck.get().getId());
            //System.out.println("Genre " + genreEntity.getName() + " already exists");
            return;
        }
        Connection connection = ConnectionJDBC.getConnection();

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
        catch (Exception e ){
            System.out.println("Error at creating a genre");
        }
    }

    @Override
    public void delete(GenreEntity entity) {
        Connection connection = ConnectionJDBC.getConnection();

        try(PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM genres WHERE id = ?");

        ){
            statement.setInt(1,entity.getId());
            statement.executeUpdate();
            connection.commit();
            connection.close();
        }
        catch (SQLException e ){
            System.out.println("Error at deleting an genre");
        }
    }

    @Override
    public Optional<GenreEntity> findById(Class<GenreEntity> classEntity, Integer id) {
        return Optional.empty();
    }

    public Optional<GenreEntity> findByName(String name ){
        Connection connection = ConnectionJDBC.getConnection();
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
        catch (Exception e ){
            System.out.println("Error at finding a genre by name");
            return Optional.empty();
        }
    }

}
