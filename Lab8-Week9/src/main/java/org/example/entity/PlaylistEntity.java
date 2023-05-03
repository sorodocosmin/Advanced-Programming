package org.example.entity;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

public class PlaylistEntity {
    private String name;
    private Timestamp dateCreated;
    private List<AlbumEntity> albums;

    public PlaylistEntity (String name, Timestamp dateCreated, List<AlbumEntity> albums) {
        this.name = name;
        this.dateCreated = dateCreated;
        this.albums = albums;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();

        res.append("Playlist Name : ");
        res.append(name);

        res.append(" Date Created : ");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String dateString = sdf.format(this.dateCreated);

        res.append(dateString + "\n");

        res.append("Number of albums : " + albums.size());
        res.append("\n");

        for(AlbumEntity album : albums) {
            res.append(album.toString());
            res.append("\n");
        }

        return res.toString();
    }
}
