package org.example.repositories.jdbc;

import org.example.connections.ConnectionJDBC;
import org.example.entities.ArtistEntity;
import org.example.repositories.DataRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ArtistRepositoryJDBC extends JDBCDataRepository<ArtistEntity,Integer> {
    @Override
    public void create(ArtistEntity artistEntity) {
        Optional<ArtistEntity> artistCheck = findByName(artistEntity.getName());

        if(artistCheck.isPresent()){
            artistEntity.setId(artistCheck.get().getId());
            //System.out.println("Artist " + artistEntity.getName() + " already exists");
            return;
        }

        Connection connection = ConnectionJDBC.getConnection();


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
        catch (SQLException e ){
            System.out.println("Error at creating an artist");
        }
    }

    @Override
    public void delete(ArtistEntity entity) {
        Connection connection = ConnectionJDBC.getConnection();

        try(PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM artists WHERE id = ?");

        ){
            statement.setInt(1,entity.getId());
            statement.executeUpdate();
            connection.commit();
            connection.close();
        }
        catch (SQLException e ){
            System.out.println("Error at deleting an artist");
        }

    }

    @Override
    public Optional<ArtistEntity> findById(Class<ArtistEntity> classEntity, Integer id) {
        Connection connection = ConnectionJDBC.getConnection();

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
        catch (SQLException e ){
            System.out.println("Error at finding an artist");
            return Optional.empty();
        }
    }

    public Optional<ArtistEntity> findByName(String name) {

        Connection connection = ConnectionJDBC.getConnection();
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
        catch (SQLException e ){
            System.out.println("Error at finding an artist");
            return Optional.empty();
        }
    }

}
