package org.example.generator;

import org.example.entity.AlbumEntity;
import org.example.entity.PlaylistEntity;
import org.graph4j.Graph;
import org.graph4j.alg.clique.BronKerboschCliqueIterator;
import org.graph4j.util.Clique;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PlaylistGenerator {

    private Graph graph;
    private BronKerboschCliqueIterator cliqueIterator;
    private int nrMaxPlaylists;

    public PlaylistGenerator(Graph graph, int nrMaxPlaylists) {
        this.nrMaxPlaylists = nrMaxPlaylists;

        this.graph = graph;
        this.cliqueIterator = new BronKerboschCliqueIterator(graph);
    }

    public void printPlaylists() {

        int nrPlaylists = 0;
        while (cliqueIterator.hasNext() && nrPlaylists < nrMaxPlaylists) {

            int[] cliqueNodes = cliqueIterator.next().vertices();

            List < AlbumEntity > albums = new ArrayList<>();

            for (int cliqueNode : cliqueNodes) {
                albums.add((AlbumEntity) this.graph.getVertexLabel(cliqueNode));
            }

            PlaylistEntity playlist = new PlaylistEntity("Playlist " + nrPlaylists, new Timestamp(System.currentTimeMillis()),albums);

            System.out.println(playlist);

            nrPlaylists++;
        }
    }



}
