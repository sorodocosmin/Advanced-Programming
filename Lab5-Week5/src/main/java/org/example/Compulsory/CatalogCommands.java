package org.example.Compulsory;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class CatalogCommands {

    public static void save(Catalog catalog, String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.writeValue(new File(path),catalog);

    }

    public static Catalog load(String path) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(new File(path), Catalog.class);
    }

}
