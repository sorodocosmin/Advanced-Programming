package org.example.factory;

import org.example.repositories.DataRepository;
import org.example.repositories.jdbc.JDBCDataRepository;
import org.example.repositories.jpa.JPADataRepository;

public interface AbstractFactory {
    DataRepository createRepositoryArtist();
    DataRepository createRepositoryGenre();
    DataRepository createRepositoryAlbum();

}
