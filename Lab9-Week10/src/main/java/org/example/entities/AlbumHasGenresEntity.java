package org.example.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "album_has_genres")
public class AlbumHasGenresEntity extends AbstractEntity {
    @JoinColumn(name = "id_album", referencedColumnName = "id")
    @ManyToOne
    private AlbumEntity album;

    @JoinColumn(name = "id_genre", referencedColumnName = "id")
    @ManyToOne
    private GenreEntity genre;

    public AlbumHasGenresEntity() {

    }
    public AlbumHasGenresEntity(GenreEntity genre) {
        this.genre = genre;
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

    public GenreEntity getGenre() {
        return genre;
    }

    public void setGenre(GenreEntity genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "AlbumHasGenresEntity{" +
                "AlbumName = " + album.getTitle() +
                ", GenreName = " + genre.getName() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlbumHasGenresEntity that = (AlbumHasGenresEntity) o;
        return Objects.equals(album.getTitle(), that.album.getTitle()) && Objects.equals(genre.getName(), that.genre.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(album.getTitle(), genre.getName());
    }
}
