package org.example;

import org.example.Compulsory.Catalog;
import org.example.Compulsory.Document;
import org.example.Homework.Commands.AddCommand;
import org.example.Homework.Commands.CreateReportCommand;

public class Main {
    public static void main(String[] args) {

        createDocs();

    }

    public static void createDocs(){
        //create the documents
        Document doc1 = new Document("1","C:\\Users\\sorod\\Desktop\\FACULTATE\\an2_sem2\\Advanced_Programming\\Cristian_Frasinaru-Curs_practic_de_Java.pdf");
        doc1.setName("Java Book");
        doc1.addTag("Author", "Frasinaru");

        //open a file
//        ViewCommand viewCommand = new ViewCommand(doc1);
//        viewCommand.execute();

        Document doc2 = new Document("2","https://docs.google.com/spreadsheets/d/e/2PACX-1vQTOQqyD5oKDuzMm5R3If-T0gEKhbMZ-aB1xFMJde6pxpF7ks797H4CBJRcrCedzbEfcF7jY15PG6hT/pubhtml?gid=2120950335&single=true");
        doc2.setName("Points Java Labs");
        doc2.addTag("Author", "Java Lab");
        doc2.addTag("Group","A4");

        Document doc3 = new Document("3","");
        doc3.setName("Faker library");
        doc3.addTag("Author", "Unknown");

        Document doc4 = new Document("4","https://profs.info.uaic.ro/~acf/java/labs/lab_05.html");
        doc4.setName("Java - Lab5");
        doc4.addTag("Homework Deadline","31.03.2023");
        //create the catalog
        Catalog catalog = new Catalog("Java Docs");
        AddCommand addDoc = new AddCommand(catalog,doc1);
        addDoc.execute();

        addDoc.setDocument(doc2);
        addDoc.execute();

        addDoc.setDocument(doc3);
        addDoc.execute();

        addDoc.setDocument(doc4);
        addDoc.execute();

        System.out.println(catalog);

        //create report
        CreateReportCommand createReportCommand = new CreateReportCommand(catalog);
        createReportCommand.execute();

//        //print all documents in the catalog
//        ListDocumentsCommand listCommand = new ListDocumentsCommand(catalog);
//        listCommand.execute();
//
//        //save the document in a .json format
//        SaveCommand saveCommand = new SaveCommand(catalog,"C:\\Users\\sorod\\Desktop\\FACULTATE\\an2_sem2\\Advanced_Programming\\Labs\\Lab5-Week5\\catalog2.json");
//
//        //load a catalog
//        Catalog newCatalog = new LoadCatalog("C:\\Users\\sorod\\Desktop\\FACULTATE\\an2_sem2\\Advanced_Programming\\Labs\\Lab5-Week5\\catalog2.json").createCatalog();
//
//        listCommand.setCatalog(newCatalog);
//        listCommand.execute();
//
//
//        System.out.println( "The doc with id 1  : " + newCatalog.findById("1"));

    }
}