package Locations;

import java.util.Objects;

public class Location {
    private LocationType type;
    private String name;
    private int coordinateX, coordinateY;

    public Location(LocationType type, String name, int coordinateX, int coordinateY){
        this.setType(type);
        this.setName(name);
        this.setCoordinateX(coordinateX);
        this.setCoordinateY(coordinateY);
    }

    public LocationType getType() {
        return type;
    }

    public void setType(LocationType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(int coordinateX) {
        this.coordinateX = coordinateX;
    }

    public int getCoordinateY() {
        return this.coordinateY;
    }

    public void setCoordinateY(int getCoordinateY) {
        this.coordinateY = getCoordinateY;
    }

    @Override
    public String toString() {
        return "Location{" +
                "type=" + type +
                ", name='" + name + '\'' +
                ", coordinateX=" + coordinateX +
                ", coordinateY=" + coordinateY +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return coordinateX == location.coordinateX && coordinateY == location.coordinateY && Objects.equals(type, location.type) && Objects.equals(name, location.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, name, coordinateX, coordinateY);
    }
}
