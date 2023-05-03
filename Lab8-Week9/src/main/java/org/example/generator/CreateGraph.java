package org.example.generator;

import org.example.dao.AlbumDAO;
import org.example.entity.AlbumEntity;
import org.example.entity.ArtistEntity;
import org.example.entity.GenreEntity;
import org.graph4j.Graph;
import org.graph4j.GraphBuilder;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CreateGraph {

    public CreateGraph() {
    }

    static public Graph createGraphFromDatabaseWithAlbumsUnrelated(){


        Optional<List<AlbumEntity>> albumsFromDatabase = takeAlbumsFromDatabase();
        if(albumsFromDatabase.isEmpty()){
            System.out.println("Error at creating graph - no albums in database");
            return null;
        }

        Graph graph = GraphBuilder.
                labeledVertices(albumsFromDatabase.get())
                .buildGraph();

        addEdgesForUnrelatedAlbumsGraph(graph,albumsFromDatabase.get());

        return graph;

    }

    static private Optional<List<AlbumEntity>> takeAlbumsFromDatabase(){
        AlbumDAO albumDAO = new AlbumDAO();
        try {
            return albumDAO.findAll();
        }
        catch (SQLException e) {
            System.out.println("Error at creating graph - while taking albums from database");
        }
        return Optional.empty();
    }

    static private void addEdgesForUnrelatedAlbumsGraph(Graph graph, List<AlbumEntity> albumsFromDatabase){

        for(AlbumEntity node1 : albumsFromDatabase){
            for(AlbumEntity node2 : albumsFromDatabase){
                if(node1.equals(node2)){
                    continue;
                }
                if(!relatedAlbums(node1,node2)){
                    graph.addEdge(node1,node2);
                }
            }
        }
    }

    static private boolean relatedAlbums (AlbumEntity album1, AlbumEntity album2){

        if(album1.getReleaseYear() == album2.getReleaseYear()){//if they have been released in the same year
            return true;
        }

        for(ArtistEntity artist : album1.getAlbumArtists()){
            if(album2.getAlbumArtists().contains(artist)){//if they have the same artist
                return true;
            }
        }

        for(GenreEntity genre : album1.getAlbumGenres()){
            if(album2.getAlbumGenres().contains(genre)){//if they have the same genre
                return true;
            }
        }

        return false;

    }

}
