package org.example.repositories.jpa;

import jakarta.persistence.NoResultException;
import org.example.entities.ArtistEntity;
import org.example.entities.GenreEntity;

import java.util.Optional;

public class GenreRepositoryJPA extends JPADataRepository<GenreEntity, Integer>{
    @Override
    public void create (GenreEntity entity){
        Optional<GenreEntity> genre = this.findByName(entity.getName());

        if(genre.isPresent()){
            entity.setId(genre.get().getId());
            System.out.println("Genre " + entity.getName() +" already exists" );
            return;
        }

        super.create(entity);
    }


    public Optional<GenreEntity> findByName(String name ){

        try {
            return Optional.of(em.createNamedQuery("GenreEntity.findByName", GenreEntity.class)
                    .setParameter(1, name)
                    .getSingleResult());
        }
        catch (NoResultException e){
            return Optional.empty();
        }
    }
}
