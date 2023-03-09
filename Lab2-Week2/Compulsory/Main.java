package Compulsory;

public class Main {
    public static void main(String[] args) {
        Location location1 = new Location(LocationType.CITY,"Iasi", 10,50);
        System.out.println("Location 1 : " + location1);

        Location location2 = new Location(LocationType.AIRPORT,"Luton",-3210,-200);
        System.out.println("Location 2 : " + location2);

        Road road1 = new Road(RoadType.HIGHWAY,100,300);
        System.out.println("Road 1 : " + road1);

        Road road2 = new Road(RoadType.COUNTRY,50,4.3);
        System.out.println("Road 2 : " + road2);
    }
}