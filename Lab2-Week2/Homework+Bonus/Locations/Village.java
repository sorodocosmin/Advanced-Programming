package Locations;

public class Village extends LocationType {
    private int population;

    public Village(int population){
        this.setPopulation(population);
    }
    public void setPopulation(int population) {
        this.population = population;
    }

    public int getPopulation() {
        return population;
    }

    @Override
    public String toString() {
        return "Village{" +
                "population=" + population +
                '}';
    }
}
