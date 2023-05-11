package org.example.factory;

import org.example.repositories.DataRepository;
import org.example.repositories.jdbc.AlbumRepositoryJDBC;
import org.example.repositories.jdbc.ArtistRepositoryJDBC;
import org.example.repositories.jdbc.GenreRepositoryJDBC;

public class ConcreteFactoryJDBC implements AbstractFactory{

    @Override
    public DataRepository<?, ?> createRepositoryArtist() {
        return new ArtistRepositoryJDBC();
    }

    @Override
    public DataRepository<?, ?> createRepositoryGenre() {
        return new GenreRepositoryJDBC();
    }

    @Override
    public DataRepository<?, ?> createRepositoryAlbum() {
        return new AlbumRepositoryJDBC();
    }
}
