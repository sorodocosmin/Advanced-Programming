package org.example.dao;

import org.example.database.proprieties.Database;
import org.example.entity.AlbumEntity;
import org.example.entity.ArtistEntity;
import org.example.entity.GenreEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AlbumDAO implements DAO<AlbumEntity>{
    @Override
    public void create (AlbumEntity albumEntity) throws SQLException {

        Optional<AlbumEntity> albumCheck = findByName(albumEntity.getName());

        if(albumCheck.isPresent()){
            albumEntity.setId(albumCheck.get().getId());
            //System.out.println("Album name "+ albumEntity.getName() + " already exists");
            return;
        }

        for(ArtistEntity artist : albumEntity.getAlbumArtists()){
            Optional<ArtistEntity> artistEntity = new ArtistDAO().findByName(artist.getName());

            //System.out.println("Artist name "+ artist.getName() + " exists");

            if(artistEntity.isEmpty()){
                System.out.println("Artist name "+ artist.getName() + " doesn't exist");
                return;
            }
        }

        for(GenreEntity genre : albumEntity.getAlbumGenres()){
            Optional<GenreEntity> genreEntity = new GenreDAO().findByName(genre.getName());

            if(genreEntity.isEmpty()){
                //System.out.println("Genre name "+ genre.getName() + " doesn't exist");
                return;
            }
        }

        //all artists and genres related to this album -> exist

        Connection connection = Database.getConnection();
        //insert in albums
        try(PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO albums (release_year,title) VALUES (?,?)")
        ){
            preparedStatement.setInt(1,albumEntity.getReleaseYear());
            preparedStatement.setString(2,albumEntity.getName());
            preparedStatement.executeUpdate();
            connection.commit();

        }

        AlbumEntity albumEntity1 = findByName(albumEntity.getName()).get();
        albumEntity.setId(albumEntity1.getId());

        //insert in album_has_artists
        for( ArtistEntity artist : albumEntity.getAlbumArtists() ){

            try(PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO album_has_artists (id_album, id_artist) VALUES (?,?)")
            ){
                preparedStatement.setInt(1,albumEntity.getId());
                preparedStatement.setInt(2,artist.getId());
                preparedStatement.executeUpdate();
            }
        }
        connection.commit();

        //insert in album_has_genres

        for( GenreEntity genre : albumEntity.getAlbumGenres() ){

            try(PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO album_has_genres (id_album, id_genre) VALUES (?,?)")
            ){
                preparedStatement.setInt(1,albumEntity.getId());
                preparedStatement.setInt(2,genre.getId());
                preparedStatement.executeUpdate();
            }
        }

        connection.commit();
        connection.close();

    }

    public Optional<AlbumEntity> findById ( int id ) throws SQLException {
        Connection connection = Database.getConnection();

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
    }

    public Optional<AlbumEntity> findByName ( String name ) throws SQLException {

        Connection connection = Database.getConnection();
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
    }

    public Optional<List<AlbumEntity>> findAll() throws SQLException {

        List<AlbumEntity> albums = new ArrayList<>() ;

        Connection connection = Database.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(
                "select * from albums");
        ) {
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                AlbumEntity albumEntity = new AlbumEntity(
                        resultSet.getInt(1),
                        resultSet.getString(3),
                        resultSet.getInt(2)
                );


                //get artists using the JOIN Table
                List<ArtistEntity> artists = new ArrayList<>();

                try(PreparedStatement statement1 = connection.prepareStatement(
                        "select artists.id, artists.name from artists join album_has_artists a_h_a on artists.id = a_h_a.id_artist where a_h_a.id_album = ?"
                )){
                    statement1.setInt(1,albumEntity.getId());
                    ResultSet resultSet1 = statement1.executeQuery();

                    while(resultSet1.next()){
                        ArtistEntity artistEntity = new ArtistEntity(
                                resultSet1.getInt(1),//the id of the artist
                                resultSet1.getString(2)//the name of the artist
                        );
                        artists.add(artistEntity);
                    }
                }

                albumEntity.setAlbumArtists(artists);

                //get genres using the JOIN Table
                List<GenreEntity> genres = new ArrayList<>();

                try(PreparedStatement statement1 = connection.prepareStatement(
                        "select genres.id, genres.name from genres join album_has_genres a_h_g on genres.id = a_h_g.id_genre where a_h_g.id_album = ?"
                )){
                    statement1.setInt(1,albumEntity.getId());
                    ResultSet resultSet1 = statement1.executeQuery();


                    while(resultSet1.next()){
                        GenreEntity genreEntity = new GenreEntity(
                                resultSet1.getInt(1),//the id of the genre
                                resultSet1.getString(2)//the name of the genre
                        );
                        genres.add(genreEntity);
                    }

                }

                albumEntity.setAlbumGenres(genres);

                albums.add(albumEntity);
            }
            connection.close();
        }

        if(albums.size() == 0) {
            return Optional.empty();
        }

        return Optional.of(albums);


    }



//    public String printAllAlbums () throws SQLException {
//        Connection connection = Database.getConnection();
//        try(Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery(
//                    "select a.title , a.release_year, artists.name, genres.name from albums a JOIN album_has_artists a_h_a ON a.id = a_h_a.id_album JOIN artists ON a_h_a.id_artist = artists.id JOIN album_has_genres a_h_g ON a.id = a_h_g.id_album JOIN genres ON a_h_g.id_genre = genres.id"
//            )
//        ){
//            while(resultSet.next()){
//                System.out.println("Album : " + resultSet.getString(1) +
//                        " Release Year : " + resultSet.getInt(2) +
//                        " Artist : " + resultSet.getString(3) +
//                        " Genre  : " + resultSet.getString(4));
//            }
//            return resultSet.next() ? resultSet.getString(1) : null;
//
//        }
//    }
}
