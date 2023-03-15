package Homework;

import java.time.LocalDate;
import java.util.Objects;

public class Designer extends Person{
    private String favouriteColour;

    public Designer(String name, String favouriteColour,LocalDate dateOfBirth){
        this.setName(name);
        this.setFavouriteColour(favouriteColour);
        this.setDateOfBirth(dateOfBirth);
    }

    void setName(String name){
        this.name = name;
    }

    void setDateOfBirth(LocalDate dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }
    public LocalDate getDateOfBirth(){
        return this.dateOfBirth;
    }
    public String getFavouriteColour() {
        return favouriteColour;
    }

    public void setFavouriteColour(String favouriteColour) {
        this.favouriteColour = favouriteColour;
    }

    @Override
    public int compareTo(Person person) {
        return this.name.compareTo(person.name);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(this.name);
        result.append("(Designer) : ");
        this.relations.forEach(
                (key,value) -> {
                    result.append(key.getName());
                    result.append("=");
                    result.append(value);
                    result.append(" ; ");
                }
        );
        return result.toString() ;

    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name,this.favouriteColour);
    }
}
