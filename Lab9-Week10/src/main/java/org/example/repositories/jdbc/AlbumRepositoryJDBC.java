package org.example.repositories.jdbc;

import org.example.connections.ConnectionJDBC;
import org.example.entities.*;
import org.example.repositories.DataRepository;
import org.example.repositories.jpa.ArtistRepositoryJPA;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class AlbumRepositoryJDBC extends JDBCDataRepository<AlbumEntity,Integer> {
    @Override
    public void create(AlbumEntity albumEntity) {
        Optional<AlbumEntity> albumCheck = findByName(albumEntity.getTitle());

        if(albumCheck.isPresent()){
            albumEntity.setId(albumCheck.get().getId());
            //System.out.println("Album name "+ albumEntity.getName() + " already exists");
            return;
        }

        ArtistRepositoryJDBC artistRepositoryJDBC = new ArtistRepositoryJDBC();
        //create the non-existing artists
        for(AlbumHasArtistsEntity artist : albumEntity.getAlbumArtists()){
            artistRepositoryJDBC.create(artist.getArtist());
        }

        GenreRepositoryJDBC genreRepositoryJDBC = new GenreRepositoryJDBC();
        //create the non-existing genres
        for(AlbumHasGenresEntity genre : albumEntity.getAlbumGenres()){
            genreRepositoryJDBC.create(genre.getGenre());
        }

        //all artists and genres related to this album -> exist

        Connection connection = ConnectionJDBC.getConnection();
        //insert in albums
        try(PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO albums (release_year,title) VALUES (?,?)")
        ){
            preparedStatement.setInt(1,albumEntity.getReleaseYear());
            preparedStatement.setString(2,albumEntity.getTitle());
            preparedStatement.executeUpdate();
            connection.commit();

        }
        catch (SQLException e) {
            System.out.println("Error in AlbumRepositoryJDBC.create: " + e.getMessage());
        }

        AlbumEntity albumEntity1 = findByName(albumEntity.getTitle()).get();
        albumEntity.setId(albumEntity1.getId());

        //insert in album_has_artists
        for( AlbumHasArtistsEntity artist : albumEntity.getAlbumArtists() ){

            try(PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO album_has_artists (id_album, id_artist) VALUES (?,?)")
            ){
                preparedStatement.setInt(1,albumEntity.getId());
                preparedStatement.setInt(2,artist.getArtist().getId());
                preparedStatement.executeUpdate();
            }
            catch (SQLException e) {
                System.out.println("Error in AlbumRepositoryJDBC.create: when inserting the artists " + e.getMessage());
            }
        }
        try {
            connection.commit();
        }
        catch (SQLException e) {
            System.out.println("Error at committing the insertion of the artists in AlbumRepositoryJDBC.create: " + e.getMessage());
        }

        //insert in album_has_genres

        for( AlbumHasGenresEntity genre : albumEntity.getAlbumGenres() ){

            try(PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO album_has_genres (id_album, id_genre) VALUES (?,?)")
            ){
                preparedStatement.setInt(1,albumEntity.getId());
                preparedStatement.setInt(2,genre.getGenre().getId());
                preparedStatement.executeUpdate();
            }
            catch (SQLException e) {
                System.out.println("Error in AlbumRepositoryJDBC.create: when inserting the genres " + e.getMessage());
            }
        }

        try{

            connection.commit();
            connection.close();
        }
        catch (SQLException e) {
            System.out.println("Error at committing the insertion of the genres in AlbumRepositoryJDBC.create: " + e.getMessage());
        }

    }

    @Override
    public void delete(AlbumEntity entity) {
        Connection connection = ConnectionJDBC.getConnection();

        try(PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM albums WHERE id = ?");

        ){
            statement.setInt(1,entity.getId());
            statement.executeUpdate();
            connection.commit();
            connection.close();
        }
        catch (SQLException e){
            System.out.println("Error in delete albums");
        }
    }

    @Override
    public Optional<AlbumEntity> findById(Class<AlbumEntity> classEntity, Integer id) {
        Connection connection = ConnectionJDBC.getConnection();

        try(PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM albums WHERE id = ?");

        ){
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();


            if(resultSet.next()){
                AlbumEntity albumEntity = new AlbumEntity(
                        resultSet.getInt(1),
                        resultSet.getString(3),
                        resultSet.getInt(2)
                );
                connection.close();
                return Optional.of(albumEntity);
            }
            else{
                connection.close();
                return Optional.empty();
            }
        }
        catch (SQLException e){
            System.out.println("Error in findById");
            return Optional.empty();
        }
    }

    public Optional<AlbumEntity> findByName ( String name ) {

        Connection connection = ConnectionJDBC.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(
                "select * from albums where upper(trim(title))=upper(trim( ? ))");
        ) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();


            if (resultSet.next()) {
                AlbumEntity albumEntity = new AlbumEntity(
                        resultSet.getInt(1),
                        resultSet.getString(3),
                        resultSet.getInt(2)
                );
                connection.close();
                return Optional.of(albumEntity);
            } else {
                connection.close();
                return Optional.empty();
            }
        }
        catch (SQLException e){
            System.out.println("Error in findByName");
            return Optional.empty();
        }
    }

    public void deleteAll(){
        Connection connection = ConnectionJDBC.getConnection();

        try(PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM albums");

        ){
            statement.executeUpdate();
            connection.commit();
            connection.close();
        }
        catch (SQLException e){
            System.out.println("Error in deleteAll albums");
        }
    }


}
