package Main;
import Locations.Location;

import java.util.Objects;

public class Road {
    private RoadType type;
    private int speedLimit;
    private double lengthInKm;
    private Location location1, location2;//the road goes from one Location.Location to another

    Road(RoadType type,int speedLimit, double lengthInKm, Location location1, Location location2){
        this.setType(type);
        this.setSpeedLimit(speedLimit);
        this.setLengthInKm(lengthInKm);
        this.setLocation1(location1);
        this.setLocation2(location2);
    }
    public void setType(RoadType type) {
        this.type = type;
    }

    public void setSpeedLimit(int speedLimit) {
        this.speedLimit = speedLimit;
    }

    public void setLengthInKm(double lengthInKm) {
        this.lengthInKm = lengthInKm;
    }

    public void setLocation1(Location location1) {
        this.location1 = location1;
    }

    public void setLocation2(Location location2) {
        this.location2 = location2;
    }

    public RoadType getType() {
        return type;
    }

    public int getSpeedLimit() {
        return speedLimit;
    }

    public double getLengthInKm() {
        return lengthInKm;
    }

    public Location getLocation1() {
        return location1;
    }

    public Location getLocation2() {
        return location2;
    }

    private double getEuclideanDistance(){

        return Math.sqrt ( Math.pow(this.location1.getCoordinateX()-this.location2.getCoordinateX(),2) + Math.pow(this.location1.getCoordinateY()-this.location2.getCoordinateY(),2) );
    }
    public boolean hasReasonableLength(){
        return !(this.lengthInKm < this.getEuclideanDistance());
    }

    @Override
    public String toString() {
        return "Road{" +
                "type=" + type +
                ", speedLimit=" + speedLimit +
                ", lengthInKm=" + lengthInKm +
                ", location1=" + location1 +
                ", location2=" + location2 +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Road road = (Road) o;
        return speedLimit == road.speedLimit && Double.compare(road.lengthInKm, lengthInKm) == 0 && type == road.type && Objects.equals(location1, road.location1) && Objects.equals(location2, road.location2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, speedLimit, lengthInKm, location1, location2);
    }
}
