package Compulsory;

public class Designer1 extends Person1{
    private String favouriteColour;

    public Designer1(String name, String favouriteColour){
        this.setName(name);
        this.setFavouriteColour(favouriteColour);
    }

    void setName(String name){
        this.name = name;
    }
    public String getFavouriteColour() {
        return favouriteColour;
    }

    public void setFavouriteColour(String favouriteColour) {
        this.favouriteColour = favouriteColour;
    }

    @Override
    public int compareTo(Person1 person) {
        return this.name.compareTo(person.name);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.name + "(Designer)";
    }
}
