package Locations;

public class City extends LocationType{
    private int population;
    private int nrUniversities;

    public City(int population, int nrUniversities){
        this.setPopulation(population);
        this.setNrUniversities(nrUniversities);
    }
    public void setNrUniversities(int nrUniversities) {
        this.nrUniversities = nrUniversities;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getNrUniversities() {
        return nrUniversities;
    }

    public int getPopulation() {
        return population;
    }

    @Override
    public String toString() {
        return "City{" +
                "population=" + population +
                ", nrUniversities=" + nrUniversities +
                '}';
    }
}
