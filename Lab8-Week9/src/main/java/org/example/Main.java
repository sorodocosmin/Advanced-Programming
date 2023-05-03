package org.example;

import com.opencsv.exceptions.CsvValidationException;
import org.example.generator.CreateGraph;
import org.example.generator.PlaylistGenerator;
import org.example.importdata.ImportCSV;
import org.graph4j.Graph;
import org.graph4j.alg.clique.BronKerboschCliqueIterator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

//        try {
//            ArtistEntity artist1 = new ArtistEntity(1,"Pink Floyd");
//            ArtistEntity artist2 = new ArtistEntity(1,"Michael Jackson");
//
//            ArtistDAO artistDAO = new ArtistDAO();
//            artistDAO.create(artist1);
//            artistDAO.create(artist2);
//
//            GenreEntity genre1 = new GenreEntity(1,"Rock");
//            GenreEntity genre2 = new GenreEntity(1,"Funk");
//            GenreEntity genre3 = new GenreEntity(1,"Soul");
//            GenreEntity genre4 = new GenreEntity(1,"Pop");
//
//            GenreDAO genreDAO = new GenreDAO();
//            genreDAO.create(genre1);
//            genreDAO.create(genre2);
//            genreDAO.create(genre3);
//            genreDAO.create(genre4);
//
//            AlbumEntity album1 = new AlbumEntity(1,"The Wall",1979,List.of(genre1),List.of(artist1));
//            AlbumEntity album2 = new AlbumEntity(1,"Thriller",1982,List.of(genre2,genre3,genre4),List.of(artist2));
//
//            AlbumDAO albumDAO = new AlbumDAO();
//            albumDAO.create(album1);
//            albumDAO.create(album2);
//
//
//            Optional<List<AlbumEntity>> albumsFromDB = albumDAO.findAll();
//            if(albumsFromDB.isEmpty()){
//                System.out.println("No albums in the database");
//            }
//            else{
//                System.out.println("Albums from the database:");
//                for(AlbumEntity album : albumsFromDB.get()){
//                    System.out.println(album);
//                }
//            }
//
//            Database.closeConnection();
//        }
//
//        catch (SQLException e){
//            System.err.println(e);
//            try {
//                Database.getConnection().rollback();
//            }
//
//            catch (SQLException ex) {
//                System.err.println("Error at rollback");
//            }
//        }

        try {
            ImportCSV importCSV = new ImportCSV("albumlist.csv");
            importCSV.insertInDatabase();
        }
        catch (FileNotFoundException e){
            System.err.println("Error - CSV File not found" + e.getMessage());
        }
        catch (IOException | CsvValidationException e) {
            System.err.println("Error - Error at reading from CSV File" + e.getMessage());
        }
        catch (SQLException e){
            System.err.println("Error - Error at inserting in database" + e.getMessage());
        }

        Graph graph = CreateGraph.createGraphFromDatabaseWithAlbumsUnrelated();

        PlaylistGenerator playlistGenerator = new PlaylistGenerator(graph,300);

        playlistGenerator.printPlaylists();


    }
}