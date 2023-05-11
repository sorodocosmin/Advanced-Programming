package org.example.factory;

import org.example.repositories.DataRepository;
import org.example.repositories.jpa.AlbumRepositoryJPA;
import org.example.repositories.jpa.ArtistRepositoryJPA;
import org.example.repositories.jpa.GenreRepositoryJPA;

public class ConcreteFactoryJPA implements AbstractFactory {

    @Override
    public DataRepository<?, ?> createRepositoryArtist() {
        return new ArtistRepositoryJPA();
    }

    @Override
    public DataRepository<?, ?> createRepositoryGenre() {
        return new GenreRepositoryJPA();
    }

    @Override
    public DataRepository<?, ?> createRepositoryAlbum() {
        return new AlbumRepositoryJPA();
    }
}
