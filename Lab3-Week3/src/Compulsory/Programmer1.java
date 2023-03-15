package Compulsory;

public class Programmer1 extends Person1 implements Node1{

    private String favouriteProgrammingLanguage;

    public Programmer1(String name, String favProgrammingLang){
        this.setName(name);
        this.setFavouriteProgrammingLanguage(favProgrammingLang);
    }

    public void setName(String name){
        this.name = name;
    }

    public String getFavouriteProgrammingLanguage() {
        return this.favouriteProgrammingLanguage;
    }

    public void setFavouriteProgrammingLanguage(String favouriteProgrammingLanguage) {
        this.favouriteProgrammingLanguage = favouriteProgrammingLanguage;
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
        return this.name + "(Programmer)";
    }
}
