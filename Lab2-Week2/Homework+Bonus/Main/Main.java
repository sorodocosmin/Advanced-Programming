package Main;

import Locations.City;
import Locations.GasStation;
import Locations.Location;
import Bonus.Algorithm;
import Bonus.DijkstraAlgorithm;
import Bonus.Solution;

import java.util.ArrayList;

public class Main {
    static final int NUMBER_OF_LOCATIONS = 5_000;
    static final int NUMBER_OF_ROADS = 10_000;

    static final int NUMBER_TESTS = 10;


    public static void main(String[] args) {

        System.out.println("Rezultate Homework : \n");
        exampleForHomework();

        for( int i=0 ; i < NUMBER_TESTS ; ++i ){
            System.out.println("\n\nRezultate Bonus : " + i + " \n");
            exampleForBonus();
        }

    }

    private static void exampleForHomework(){
        Location suceava = new Location(new City(120_000,1),"Suceava",100,70);
        Location iasi = new Location(new City(121_000,3),"Iasi",400,-30);
        Location galati = new Location(new City(70_000,0),"Galati",450,-200);
        Location cluj = new Location(new City(90_000,2),"Cluj",-300,-180);
        Location petromIasi = new Location(new GasStation(7.3,6.89),"Petrom-Gara",389,-35);

        ArrayList<Location> listLocations = new ArrayList<>();
        listLocations.add(suceava);
        listLocations.add(iasi);
        listLocations.add(galati);
        listLocations.add(cluj);
        listLocations.add(petromIasi);

        Road road1 = new Road(RoadType.HIGHWAY,120,320,suceava,iasi);
        Road road2 = new Road(RoadType.HIGHWAY,120,350,suceava,iasi);
        Road road3 = new Road(RoadType.EXPRESS,150,450,iasi,galati);
        Road road4 = new Road(RoadType.COUNTRY,50,500,suceava,galati);

        ArrayList<Road> listRoads = new ArrayList<>();
        listRoads.add(road1);
        listRoads.add(road2);
        listRoads.add(road3);
        listRoads.add(road4);

        Problem problem1 = new Problem(listLocations,listRoads);

        System.out.println("problema 1 este valida : " + problem1.isValid());

        problem1.addLocation(suceava);
        problem1.addRoad(road1);
        if(problem1.isValid()) {
            System.out.println("Se poate ajunge de la " + suceava + " la\n" + galati + "\n:" + problem1.goFromXtoY(suceava, galati));
            System.out.println("Se poate ajunge de la " + petromIasi + " la\n" + cluj + "\n:" + problem1.goFromXtoY(petromIasi, cluj));
        }
        else{
            System.out.println("Problema 1 nu este valida");
        }

        Algorithm alg = new DijkstraAlgorithm(problem1,suceava,galati);

        Solution solution1 = new Solution(alg.solveForShortestPath());
        System.out.println("Solution for shortest route : ");
        solution1.showRoute();

        Solution solution2 = new Solution(alg.solveForShortestTime());
        System.out.println("Solution for fastest route : ");
        solution2.showRoute();

    }

    private static void exampleForBonus(){
        System.gc();
        Runtime runtime = Runtime.getRuntime();
        long usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();
        long initialTime = System.currentTimeMillis();

        runTest();

        long runningTime = System.currentTimeMillis() - initialTime;
        long usedMemoryAfter = runtime.totalMemory() - runtime.freeMemory();
        long memoryIncrease = usedMemoryAfter - usedMemoryBefore;

        System.out.println("This test used " + memoryIncrease + " memory and executed in " + runningTime + " milliseconds ");

    }
    private static void runTest(){
        ArrayList<Location> locationsArray = new ArrayList<>();
        ArrayList<Road> roadsArray = new ArrayList<>();

        for( int i=0 ; i< NUMBER_OF_LOCATIONS ; ++i){
            Location location = new Location(new City(10_000,3),"City"+i,i,i);
            locationsArray.add(location);
        }

        for( int i=0 ; i< NUMBER_OF_ROADS ; ++i){
            Road road = new Road(RoadType.HIGHWAY,70,NUMBER_OF_ROADS,
                    locationsArray.get((int)(Math.random()*NUMBER_OF_LOCATIONS)),
                    locationsArray.get((int)(Math.random()*NUMBER_OF_LOCATIONS)));
            roadsArray.add(road);
        }

        Problem problem = new Problem(locationsArray,roadsArray);

        Algorithm algorithm = new DijkstraAlgorithm(problem,locationsArray.get((int)(Math.random()*NUMBER_OF_LOCATIONS)),locationsArray.get((int)(Math.random()*NUMBER_OF_LOCATIONS)));

        Solution solution = new Solution(algorithm.solveForShortestPath());
        solution.showRoute();



    }
}