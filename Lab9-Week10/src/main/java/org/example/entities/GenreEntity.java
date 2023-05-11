package org.example.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table ( name = "genres")
@NamedQueries({
        @NamedQuery(name = "GenreEntity.findAll",
                query = "select e from GenreEntity e order by e.name"),

        @NamedQuery(name = "GenreEntity.findByName",
                query = "select e from GenreEntity e WHERE e.name = ?1")
})
public class GenreEntity extends AbstractEntity {
    @Column(name = "name")
    private String name;

    public GenreEntity() {

    }
    public GenreEntity(String name) {
        this.name = name;
    }

    public GenreEntity ( int id, String name){
        this.id = id;
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "GenreEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenreEntity genre = (GenreEntity) o;
        return Objects.equals(name, genre.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
