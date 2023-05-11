package org.example.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "artists")
@NamedQueries({
        @NamedQuery(name="ArtistEntity.findAll",
            query = "select e from ArtistEntity e order by e.name"),
        @NamedQuery(name="ArtistEntity.findByName",
            query = "select e from ArtistEntity e  where e.name = ?1 ")
})
public class ArtistEntity extends AbstractEntity {
    @Column(name = "name")
    private String name;

    @OneToMany( cascade = CascadeType.ALL, mappedBy = "artist")
    private List<AlbumHasArtistsEntity> albums;

    public ArtistEntity(String name) {
        this.name = name;
        this.albums = new ArrayList<>();
    }

    public ArtistEntity() {
        this.albums = new ArrayList<>();
    }

    public ArtistEntity ( int id, String name){
        this.id = id;
        this.name = name;
        this.albums = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ArtistEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", list albums : " + albums +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArtistEntity that = (ArtistEntity) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
