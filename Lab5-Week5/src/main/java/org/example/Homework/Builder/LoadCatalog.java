package org.example.Homework.Builder;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Compulsory.Catalog;

import java.io.File;
import java.io.IOException;

public class LoadCatalog implements Builder{
    private String path;

    public LoadCatalog(String path){
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    @Override
    public Catalog createCatalog() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.readValue(new File(path), Catalog.class);
        }
        catch (IOException e) {
            System.out.println("The path for loading is not valid");;
        }

        return null;//the loading was not successfully done
    }
}
