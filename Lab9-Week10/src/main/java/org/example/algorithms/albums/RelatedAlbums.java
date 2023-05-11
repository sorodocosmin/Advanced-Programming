package org.example.algorithms.albums;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.util.iterators.IntVarValueIterator;
import org.example.entities.AlbumEntity;
import org.example.factory.FactoryManager;
import org.example.repositories.jpa.AlbumRepositoryJPA;

import java.util.List;
import java.util.Optional;

public class RelatedAlbums {
    private int nrOfAlbums;
    private int maxDifferenceYear;

    public RelatedAlbums(int nrOfAlbums, int maxDifferenceYear) {

        this.nrOfAlbums = nrOfAlbums;
        this.maxDifferenceYear = maxDifferenceYear;

    }

    public void printRelatedAlbums() {

        Model model = new Model("RelatedAlbums");

        Optional<List<AlbumEntity>> albums = ((AlbumRepositoryJPA) FactoryManager.getFactory("JPA").createRepositoryAlbum()).findAll();

        if (albums.isEmpty() || albums.get().size() == 0 ){
            System.out.println("No albums found!");
            return;
        }

        //give the array to the model
        IntVar[] albumsVar = new IntVar[albums.get().size()];

        for( int i = 0 ; i < albums.get().size() ; ++i) {
            albumsVar[i] = model.intVar("x[" +i+"]",0, 1);
        }

        //add the constraints
        for (int i = 0; i < albums.get().size() - 1; ++i) {
            for (int j = 0  ; j < albums.get().size() ; ++j){
                if ( !(this.startWithSameLetter(albums.get().get(i), albums.get().get(j)) &&
                     this.releasedSamePeriodTime(albums.get().get(i), albums.get().get(j)))
                    ) {//add constrain
                    model.arithm(albumsVar[i], "+", albumsVar[j], "<=" , 1).post();
                }
            }
        }

        model.sum(albumsVar, ">=", this.nrOfAlbums).post();

        int count = 0 ;
        while (model.getSolver().solve()){
            System.out.println("\n\nSolution "+ ++count +" found!");
            for (int i = 0; i < albums.get().size(); ++i){
                if (albumsVar[i].getValue() == 1){
                    System.out.println( " Title  : " + albums.get().get(i).getTitle() + " year : " + albums.get().get(i).getReleaseYear());
                }
            }
        }
        if(count == 0){
            System.out.println("No solution found");
        }

    }

    private boolean releasedSamePeriodTime(AlbumEntity albumEntity, AlbumEntity albumEntity1) {
        return Math.abs(albumEntity.getReleaseYear() - albumEntity1.getReleaseYear()) <= this.maxDifferenceYear;
    }

    private boolean startWithSameLetter(AlbumEntity album1, AlbumEntity album2) {
        return Character.toLowerCase(album1.getTitle().charAt(0)) ==
                Character.toLowerCase(album2.getTitle().charAt(0));
    }

}
