package org.example.Homework.Commands;

import org.example.Compulsory.Document;

import org.example.Compulsory.Catalog;
import org.example.Compulsory.Exceptions.DuplicateIdException;

public class AddCommand implements Command{
    private Catalog catalog;
    private Document document;

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public AddCommand(Catalog catalog, Document document){
        this.setCatalog(catalog);
        this.setDocument(document);
    }

    public void execute() {
        if(document.isValid()) {
            try {
                if (this.catalog.getDocuments().indexOf(document) == -1) {
                    this.catalog.getDocuments().add(document);
                } else {
                    throw new DuplicateIdException(document.getId() + " is the same id");
                }
            } catch (DuplicateIdException e) {
                System.out.println("Warning you tried to introduce in a catalogue 2 documents with the same id (" + e.toString() + ")");
            }
        }
    }
}
