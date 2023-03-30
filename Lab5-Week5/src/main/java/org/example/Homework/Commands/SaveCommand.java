package org.example.Homework.Commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Compulsory.Catalog;

import java.io.File;
import java.io.IOException;

public class SaveCommand implements Command{
    private String path;
    private Catalog catalog;

    public SaveCommand(Catalog catalog, String path){
        this.catalog = catalog;
        this.path = path;
    }
    @Override
    public void execute(){
        ObjectMapper objectMapper = new ObjectMapper();
    try {
        objectMapper.writeValue(new File(path), catalog);
    }
    catch (IOException exception){
            System.out.println("The Path for saving is not Valid");
        }
    }
}
