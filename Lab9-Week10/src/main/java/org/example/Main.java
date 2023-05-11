package org.example;

import org.example.algorithms.albums.RelatedAlbums;
import org.example.entities.*;
import org.example.connections.ManagerConnectionJPA;
import org.example.factory.AbstractFactory;
import org.example.factory.FactoryManager;
import org.example.repositories.DataRepository;
import org.example.repositories.jdbc.AlbumRepositoryJDBC;
import org.example.repositories.jdbc.ArtistRepositoryJDBC;
import org.example.repositories.jdbc.GenreRepositoryJDBC;
import org.example.repositories.jpa.AlbumRepositoryJPA;
import org.example.repositories.jpa.ArtistRepositoryJPA;
import org.example.repositories.jpa.GenreRepositoryJPA;

public class Main {
    public static void main(String[] args) {


        RelatedAlbums relatedAlbums = new RelatedAlbums(5,7);
        relatedAlbums.printRelatedAlbums();

//            AlbumRepositoryJPA albumRepo = (AlbumRepositoryJPA)  FactoryManager.getFactory("JPA").createRepositoryAlbum();
//            albumRepo.deleteAll();

//        ArtistEntity artist2 = new ArtistEntity("Michael Jackson");
//
//
//        DataRepository artistRepository = FactoryManager.getFactory("JPA").createRepositoryArtist();
//
//        artistRepository.create(artist2);
//
//        GenreEntity genre1 = new GenreEntity(1,"Rock");
//        GenreEntity genre2 = new GenreEntity(1,"Funk");
//        GenreEntity genre3 = new GenreEntity(1,"Soul");
//        GenreEntity genre4 = new GenreEntity(1,"Pop");
//
//        GenreRepositoryJDBC genreRepositoryJDBC = new GenreRepositoryJDBC();
//        genreRepositoryJDBC.create(genre1);
//        genreRepositoryJDBC.create(genre2);
//        genreRepositoryJDBC.create(genre3);
//        genreRepositoryJDBC.create(genre4);
//
//
//        AlbumEntity album1 = new AlbumEntity(1,"The Wall",1979);
//        album1.addGenre(genre1);
//        album1.addGenre(genre2);
//        album1.addArtist(artist1);
//
//
//        AlbumRepositoryJDBC albumRepositoryJDBC = new AlbumRepositoryJDBC();
//        albumRepositoryJDBC.create(album1);



//        ArtistRepositoryJPA artistRepository = new ArtistRepositoryJPA();
//
//        artistRepository.create(artist1);
//        artistRepository.create(artist2);
//
//        System.out.println(artistRepository.findById(ArtistEntity.class, 13).isPresent());
//
//        System.out.println("The entire artists : ");
//        System.out.println(artistRepository.findAll(ArtistEntity.class));
//
//        GenreEntity genre1 = new GenreEntity("Nice-music");
//        GenreRepositoryJPA genreRepositoryJPA = new GenreRepositoryJPA();
//
//        genreRepositoryJPA.create(genre1);
//
//        System.out.println("The entire genres : ");
//        System.out.println(genreRepositoryJPA.findAll(GenreEntity.class));
//
//        AlbumEntity albumEntity = new AlbumEntity("Album-new", 2021);
//        AlbumRepositoryJPA albumRepositoryJPA = new AlbumRepositoryJPA();
//
//        albumEntity.addGenre(new GenreEntity("The best music"));
//        albumEntity.addGenre(new GenreEntity("The best music"));
//
//        albumRepositoryJPA.create(albumEntity);
//
//        //albumRepositoryJPA.delete(albumRepositoryJPA.findById(AlbumEntity.class, albumEntity.getId()).get());
//
//        albumRepositoryJPA.deleteAll();
//
//        System.out.println("The entire albums : ");
//        System.out.println(albumRepositoryJPA.findAll(AlbumEntity.class));
//

        ManagerConnectionJPA.closeEntityManager();

    }
}