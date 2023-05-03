package org.example.entity;

import java.util.List;
import java.util.Objects;

public class AlbumEntity extends BaseEntity{
    private String name;
    private int releaseYear;
    private List<GenreEntity> albumGenres;
    private List<ArtistEntity> albumArtists;

    public AlbumEntity (int id, String name, int releaseYear){
        this.id = id;
        this.name = name;
        this.releaseYear = releaseYear;
    }
    public AlbumEntity (int id, String name, int releaseYear, List<GenreEntity> albumGenres, List<ArtistEntity> albumArtists){
        this.id = id;
        this.name = name ;
        this.releaseYear = releaseYear;
        this.albumGenres = albumGenres;
        this.albumArtists = albumArtists;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public List<GenreEntity> getAlbumGenres() {
        return albumGenres;
    }

    public void setAlbumGenres(List<GenreEntity> albumGenres) {
        this.albumGenres = albumGenres;
    }

    public List<ArtistEntity> getAlbumArtists() {
        return albumArtists;
    }

    public void setAlbumArtists(List<ArtistEntity> albumArtists) {
        this.albumArtists = albumArtists;
    }

    @Override
    public String toString() {
        return "AlbumEntity{" +
                "name='" + name + '\'' +
                ", releaseYear=" + releaseYear +
                ", albumGenres=" + albumGenres +
                ", albumArtists=" + albumArtists +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlbumEntity that = (AlbumEntity) o;
        return releaseYear == that.releaseYear && Objects.equals(name, that.name) && Objects.equals(albumGenres, that.albumGenres) && Objects.equals(albumArtists, that.albumArtists);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, releaseYear, albumGenres, albumArtists);
    }
}
