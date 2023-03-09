package Compulsory;

public class Road {
    private RoadType type;
    private int speedLimit;
    private double lengthInKm;

    Road ( RoadType type, int speedLimit , double lengthInKm){
        this.setType(type);
        this.setSpeedLimit(speedLimit);
        this.setLengthInKm(lengthInKm);
    }
    public RoadType getType() {
        return type;
    }

    public void setType(RoadType type) {
        this.type = type;
    }

    public int getSpeedLimit() {
        return speedLimit;
    }

    public void setSpeedLimit(int speedLimit) {
        this.speedLimit = speedLimit;
    }

    public double getLengthInKm() {
        return lengthInKm;
    }

    public void setLengthInKm(double lengthInKm) {
        this.lengthInKm = lengthInKm;
    }

    @Override
    public String toString() {
        return "Road{" +
                "type=" + type +
                ", speedLimit=" + speedLimit +
                ", lengthInKm=" + lengthInKm +
                '}';
    }
}
