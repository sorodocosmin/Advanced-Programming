package org.example.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table (name = "album_has_artists")
public class AlbumHasArtistsEntity extends AbstractEntity {
    @JoinColumn(name = "id_album", referencedColumnName = "id")
    @ManyToOne
    private AlbumEntity album;

    @JoinColumn(name = "id_artist", referencedColumnName = "id")
    @ManyToOne
    private ArtistEntity artist;

    public AlbumHasArtistsEntity() {

    }
    public AlbumHasArtistsEntity(ArtistEntity artist) {
        this.artist = artist;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AlbumEntity getAlbum() {
        return album;
    }

    public void setAlbum(AlbumEntity album) {
        this.album = album;
    }

    public ArtistEntity getArtist() {
        return artist;
    }

    public void setArtist(ArtistEntity artist) {
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "AlbumHasArtistsEntity{" +
                "ArtistName = " + artist.getName() +
                ", AlbumName = " + album.getTitle() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlbumHasArtistsEntity that = (AlbumHasArtistsEntity) o;
        return Objects.equals(album.getTitle(), that.album.getTitle()) && Objects.equals(artist.getName(), that.artist.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(album.getTitle(), artist.getName());
    }
}
