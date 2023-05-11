import net.datafaker.Faker;
import org.example.connections.ManagerConnectionJPA;
import org.example.entities.AlbumEntity;
import org.example.entities.ArtistEntity;
import org.example.entities.GenreEntity;
import org.example.factory.FactoryManager;
import org.example.repositories.DataRepository;
import org.example.repositories.jdbc.AlbumRepositoryJDBC;
import org.example.repositories.jpa.AlbumRepositoryJPA;
import org.junit.Test;

import java.util.Random;

public class TestCreateAlbums {
    final int NR_ALBUMS_TEST_1 = 100;
    final int MAX_NR_GENRES = 5;
    final int MAX_NR_ARTISTS = 3;
    @Test
    public void test1JPA(){

        DataRepository albumRepo = FactoryManager.getFactory("JPA").createRepositoryAlbum();

        ((AlbumRepositoryJPA)albumRepo).deleteAll();//firstly we delete all existing albums;

        long startTime = System.currentTimeMillis();
        Faker faker = new Faker();
        Random random = new Random();

        for( int i =0 ; i < this.NR_ALBUMS_TEST_1 ; ++i){
            // Generate a fake album name
            String albumName = faker.book().title() + " " + i;
            int releaseYear = random.nextInt(1950,2023);
            // Create the album
            AlbumEntity album = new AlbumEntity(albumName,releaseYear);

            // Generate fake artists name
            int nrArtists = random.nextInt(this.MAX_NR_ARTISTS) + 1;
            for( int j = 0 ; j < nrArtists ; ++j){
                String artistName = faker.artist().name() + " " + i;
                album.addArtist(new ArtistEntity(artistName));
            }

            //Generate fake genres
            int nrGenres = random.nextInt(this.MAX_NR_GENRES) + 1;
            for( int j = 0 ; j < nrGenres ; ++j){
                String genreName = faker.music().genre() + " " + i;
                album.addGenre(new GenreEntity(genreName));
            }

            albumRepo.create(album);

//            if(++count % bachSize == 0){
//                albumRepo.getConnectionFromRepo().getTransaction().begin();
//                albumRepo.getConnectionFromRepo().flush();
//                albumRepo.getConnectionFromRepo().clear();
//
//            }

        }

        long endTime = System.currentTimeMillis();

        System.out.println("The time for creating " + this.NR_ALBUMS_TEST_1 + " albums (using JPA) is " + (endTime - startTime) + " ms");

    }

    @Test
    public void test1JDBC(){


        DataRepository albumRepo = FactoryManager.getFactory("JDBC").createRepositoryAlbum();

        ((AlbumRepositoryJDBC)albumRepo).deleteAll();//firstly we delete all existing albums;

        long startTime = System.currentTimeMillis();
        Faker faker = new Faker();
        Random random = new Random();


        for( int i =0 ; i < this.NR_ALBUMS_TEST_1 ; ++i){
            // Generate a fake album name
            String albumName = faker.book().title();
            int releaseYear = random.nextInt(1950,2023);
            // Create the album
            AlbumEntity album = new AlbumEntity(albumName,releaseYear);

            // Generate fake artists name
            int nrArtists = random.nextInt(this.MAX_NR_ARTISTS) + 1;
            for( int j = 0 ; j < nrArtists ; ++j){
                String artistName = faker.artist().name();
                album.addArtist(new ArtistEntity(artistName));
            }

            //Generate fake genres
            int nrGenres = random.nextInt(this.MAX_NR_GENRES) + 1;
            for( int j = 0 ; j < nrGenres ; ++j){
                String genreName = faker.music().genre();
                album.addGenre(new GenreEntity(genreName));
            }

            albumRepo.create(album);

        }

        long endTime = System.currentTimeMillis();

        System.out.println("The time for creating " + this.NR_ALBUMS_TEST_1 + " albums (using JDBC) is " + (endTime - startTime) + " ms");

    }


}
