package org.example.repositories.jpa;

import jakarta.persistence.NoResultException;
import org.example.entities.ArtistEntity;

import java.util.Optional;

public class ArtistRepositoryJPA extends JPADataRepository<ArtistEntity, Integer> {

    @Override
    public void create (ArtistEntity entity){
        Optional<ArtistEntity> artist = this.findByName(entity.getName());
        System.out.println("In artisttt");
        if(artist.isPresent()){
            entity.setId(artist.get().getId());
            System.out.println("Artist " + entity.getName() +" already exists" );
            return;
        }

        super.create(entity);
    }


    public Optional<ArtistEntity> findByName(String name ){

        try {
            return Optional.of(em.createNamedQuery("ArtistEntity.findByName", ArtistEntity.class)
                    .setParameter(1, name)
                    .getSingleResult());
        }
        catch (NoResultException e){
            return Optional.empty();
        }
    }

}
