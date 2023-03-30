package org.example.Homework.Commands;

import org.example.Compulsory.Catalog;
import org.example.Compulsory.Document;

public class ListDocumentsCommand implements Command{

    private Catalog catalog;

    public ListDocumentsCommand(Catalog catalog){
        this.catalog = catalog;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public void execute(){
        System.out.println("Catalog name : " + this.catalog.getName());
        for( Document document : this.catalog.getDocuments()){
            System.out.println("\t" + document);
        }
    }
}
