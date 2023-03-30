package org.example.Compulsory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Catalog implements Serializable {

    private String name;
    private List<Document> documents = new ArrayList<>();
    public Catalog(){

    }
    public Catalog(String name){
        this.name=name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Document> getDocuments() {
        return this.documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public Document findById(String id){
        return documents.stream()
                .filter( d -> d.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "name='" + name + '\'' +
                ", documents=\n" + documents +
                '}';
    }
}
