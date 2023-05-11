package org.example.repositories.jpa;

import jakarta.persistence.NoResultException;
import org.example.entities.AlbumEntity;
import org.example.entities.AlbumHasArtistsEntity;
import org.example.entities.AlbumHasGenresEntity;
import org.example.factory.FactoryManager;

import java.util.List;
import java.util.Optional;

public class AlbumRepositoryJPA extends JPADataRepository<AlbumEntity, Integer>{
    @Override
    public void create (AlbumEntity entity){
        Optional<AlbumEntity> album = this.findByName(entity.getTitle());

        if(album.isPresent()){
            entity.setId(album.get().getId());
            System.out.println("Album " + entity.getTitle() +" already exists" );
            return;
        }
        for(AlbumHasGenresEntity albumGenre : entity.getAlbumGenres()){
            GenreRepositoryJPA repoGenre = (GenreRepositoryJPA) FactoryManager.getFactory("JPA").createRepositoryGenre();
            repoGenre.create(albumGenre.getGenre());
        }

        for(AlbumHasArtistsEntity albumArtist : entity.getAlbumArtists()){
            ArtistRepositoryJPA repoArtist = (ArtistRepositoryJPA) FactoryManager.getFactory("JPA").createRepositoryArtist();
            repoArtist.create(albumArtist.getArtist());
        }

        super.create(entity);
    }


    public Optional<AlbumEntity> findByName(String name ){

        try {
            return Optional.of(em.createNamedQuery("AlbumEntity.findByName", AlbumEntity.class)
                    .setParameter(1, name)
                    .getSingleResult());
        }
        catch (NoResultException e){
            return Optional.empty();
        }
    }

    public Optional<List<AlbumEntity>> findAll(){
        try {
            return Optional.of(em.createNamedQuery("AlbumEntity.findAll", AlbumEntity.class)
                    .getResultList());
        }
        catch (NoResultException e){
            return Optional.empty();
        }
    }

    public void deleteAll(){
        em.getTransaction().begin();
        em.createNamedQuery("AlbumEntity.deleteAll").executeUpdate();
        em.getTransaction().commit();
        System.out.println("Deleted all albums");

    }

}
