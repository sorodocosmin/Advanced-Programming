package Locations;

public class GasStation extends LocationType{
    private double priceDiesel, pricePetrol;

    public GasStation(double priceDiesel, double pricePetrol){
        this.setPriceDiesel(priceDiesel);
        this.setPricePetrol(pricePetrol);
    }
    public void setPriceDiesel(double priceDiesel) {
        this.priceDiesel = priceDiesel;
    }

    public void setPricePetrol(double pricePetrol) {
        this.pricePetrol = pricePetrol;
    }

    public double getPriceDiesel() {
        return priceDiesel;
    }

    public double getPricePetrol() {
        return pricePetrol;
    }

    @Override
    public String toString() {
        return "GasStation{" +
                "priceDiesel=" + priceDiesel +
                ", pricePetrol=" + pricePetrol +
                '}';
    }
}
