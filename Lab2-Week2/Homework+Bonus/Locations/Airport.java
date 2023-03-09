package Locations;

public class Airport extends LocationType{
    private int nrTerminals;

    public Airport( int nrTerminals){
        this.setNrTerminals(nrTerminals);
    }
    public void setNrTerminals(int nrTerminals) {
        this.nrTerminals = nrTerminals;
    }

    public int getNrTerminals() {
        return nrTerminals;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "nrTerminals=" + nrTerminals +
                '}';
    }
}
