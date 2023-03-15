package Homework;

import java.time.LocalDate;
import java.util.Objects;

public class Programmer extends Person implements Node{

    private String favouriteProgrammingLanguage;

    public Programmer(String name, String favProgrammingLang, LocalDate dateOfBirth){
        this.setName(name);
        this.setFavouriteProgrammingLanguage(favProgrammingLang);
        this.setDateOfBirth(dateOfBirth);
    }

    public void setName(String name){
        this.name = name;
    }

    public void setDateOfBirth(LocalDate dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getDateOfBirth(){
        return this.dateOfBirth;
    }

    public String getFavouriteProgrammingLanguage() {
        return this.favouriteProgrammingLanguage;
    }

    public void setFavouriteProgrammingLanguage(String favouriteProgrammingLanguage) {
        this.favouriteProgrammingLanguage = favouriteProgrammingLanguage;
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
        result.append("(Programmer) : ");
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
        return Objects.hash(this.name);
    }

}
