package org.example.importdata;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import org.example.dao.AlbumDAO;
import org.example.dao.ArtistDAO;
import org.example.dao.GenreDAO;
import org.example.entity.AlbumEntity;
import org.example.entity.ArtistEntity;
import org.example.entity.GenreEntity;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImportCSV {
    private String filePath;
    private FileReader fileReader;
    private CSVReader csvReader;

    public ImportCSV(String filePath) throws FileNotFoundException {
        this.filePath = filePath;

        this.fileReader = new FileReader(filePath);

        this.csvReader = new CSVReaderBuilder(fileReader).build();
    }
    public void insertInDatabase() throws CsvValidationException, IOException, SQLException {

        String [] record ;
        System.out.println("Inserting in Database...");

        csvReader.readNext();//skip the first line
        while((record = csvReader.readNext()) != null){

            String numberRecord = record[0];
            String yearRecord = record[1];
            String albumRecord = record[2];
            String artistRecord = record[3];
            String genreRecord = record[4];
            String subgenresRecord = record[5];

            //insert the Artists in the database

            String[] artists = artistRecord.split("&|,");//in CSV file, artists are separated by & or ,

            List<ArtistEntity> artistsEntity = new ArrayList<>();
            ArtistDAO artistDAO = new ArtistDAO();

            for(String artist : artists){
                ArtistEntity artistEntity = new ArtistEntity(1,artist);
                artistDAO.create(artistEntity);
                artistsEntity.add(artistEntity);
            }

            //insert the Genres in the database
            String[] genres = genreRecord.split("/|,");//in CSV file, genres are separated by / or ,

            List<GenreEntity> genresEntity = new ArrayList<>();
            GenreDAO genreDAO = new GenreDAO();

            for( String genre : genres){
                GenreEntity genreEntity = new GenreEntity(1,genre);
                genreDAO.create(genreEntity);
                genresEntity.add(genreEntity);
            }

            String[] subgenres = subgenresRecord.split("/|,");//in CSV file, subgenres are separated by / or ,

            for( String subgenre : subgenres){
                GenreEntity subgenreEntity = new GenreEntity(1,subgenre);
                genreDAO.create(subgenreEntity);
                genresEntity.add(subgenreEntity);
            }

            AlbumEntity albumEntity = new AlbumEntity(1,albumRecord,Integer.parseInt(yearRecord),genresEntity,artistsEntity);
            AlbumDAO albumDAO = new AlbumDAO();
            albumDAO.create(albumEntity);

        }

        System.out.println("~~¡Inserting in Database finished!~~\n");

    }

}
