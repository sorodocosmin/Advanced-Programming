package org.example.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "albums")
@NamedQueries({
        @NamedQuery( name = "AlbumEntity.findAll",
                query = "select e from AlbumEntity e order by e.title"),

        @NamedQuery( name = "AlbumEntity.findByName",
                query = "select e from AlbumEntity e where e.title = ?1"),
        @NamedQuery( name = "AlbumEntity.deleteAll",
                query = "delete from AlbumEntity")
})
public class AlbumEntity extends AbstractEntity {
    @Column(name = "release_year")
    private int releaseYear;

    @Column(name = "title")
    private String title;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "album")
    private List<AlbumHasGenresEntity> albumGenres;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "album")
    private List<AlbumHasArtistsEntity> albumArtists;

    public AlbumEntity() {
        this.albumArtists = new ArrayList<>();
        this.albumGenres = new ArrayList<>();
    }
    public AlbumEntity(String title, int releaseYear) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.albumArtists = new ArrayList<>();
        this.albumGenres = new ArrayList<>();
    }

    public AlbumEntity (int id, String title, int releaseYear){
        this.id = id;
        this.title = title;
        this.releaseYear = releaseYear;
        this.albumArtists = new ArrayList<>();
        this.albumGenres = new ArrayList<>();
    }

    public void addGenre (GenreEntity genre) {
        AlbumHasGenresEntity albumHasGenre = new AlbumHasGenresEntity();
        albumHasGenre.setAlbum(this);
        albumHasGenre.setGenre(genre);
        if(!this.albumGenres.contains(albumHasGenre)){
            this.albumGenres.add(albumHasGenre);
        }
    }

    public void addArtist(ArtistEntity artist) {
        AlbumHasArtistsEntity albumHasArtist = new AlbumHasArtistsEntity();
        albumHasArtist.setAlbum(this);
        albumHasArtist.setArtist(artist);
        if(!this.albumArtists.contains(albumHasArtist)){
            this.albumArtists.add(albumHasArtist);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public List<AlbumHasGenresEntity> getAlbumGenres() {
        return albumGenres;
    }

    public void setAlbumGenres(List<AlbumHasGenresEntity> albumGenres) {
        this.albumGenres = albumGenres;
    }

    public List<AlbumHasArtistsEntity> getAlbumArtists() {
        return albumArtists;
    }

    public void setAlbumArtists(List<AlbumHasArtistsEntity> albumArtists) {
        this.albumArtists = albumArtists;
    }

    @Override
    public String toString() {
        return "AlbumEntity{" +
                "id=" + id +
                ", releaseYear=" + releaseYear +
                ", name='" + title + '\'' +
                ", albumGenres=" + albumGenres +
                ", albumArtists=" + albumArtists +
                '}';
    }
}
