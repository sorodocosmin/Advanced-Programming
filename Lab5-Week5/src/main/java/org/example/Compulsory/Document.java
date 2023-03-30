package org.example.Compulsory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.example.Compulsory.Exceptions.InvalidFileException;
import org.example.Compulsory.Exceptions.PageNotFoundException;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Document implements Serializable, Comparable<Document> {
    private String id;
    private String path;
    private String name;
    private boolean isValid = false;
    private Map<String, Object> tags = new HashMap<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @JsonIgnore
    public boolean isValid(){
        return this.isValid;
    }
    public Document(){

    }

    public Document(String id, String path) {
        this.setId(id);
        this.setPath(path);
    }

    public void addTag(String nameTag, Object obj){
        this.tags.put(nameTag,obj);
    }


    public void setId(String id) {
        try {
            if( id.equals("")){
                throw new InvalidFileException("Invalid id");
            }
            else{
                this.id = id;
            }
        }
        catch (InvalidFileException e) {
            System.out.println("The id is invalid, by default it will be set to 1 (Note : if you try to add in a catalog 2 docs with the same id -> the program stops)");
            this.id = "1";
        }
    }
    public String getId(){
        return this.id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        File file = new File(path);
        try {
            if (file.exists() && !file.isDirectory()) {
                this.path = path;
                this.isValid = true;
            } else {
                URL myUrl = new URL(path);
                myUrl.toURI();
                HttpURLConnection connection = (HttpURLConnection) myUrl.openConnection();
                if(connection.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND){
                    throw new PageNotFoundException("The website with link : " + path + " couldn't be found");
                }
                this.path = path;
                this.isValid = true;
                throw new InvalidFileException("File with path : " + path + "\ndoesn't exist on this computer");
            }
        }


        catch (InvalidFileException e){
            System.out.println(e + "(it is actually a link)");
        }
        catch (PageNotFoundException e){
            System.out.println(e + "\n(if you try to add it to a catalog, it will not be added)");
        }
        catch (MalformedURLException e) {
            System.out.println("The Path "+path+" is not for a file nor a URL\n(if you try to add it to a catalog, it will not be added)");
        }
        catch (URISyntaxException e) {
            System.out.println(path + "is not a valid link \n(if you try to add it to a catalog, it will not be added)");
        }
        catch (IOException e) {
            System.out.println("The connection couldn't have been made with the site \n(if you try to add it to a catalog, it will not be added)");
        }

    }

    public Map<String, Object> getTags() {
        return this.tags;
    }

    public void setTags(Map<String, Object> tags) {
        this.tags = tags;
    }

    @Override
    public int compareTo(Document o) {
        return this.id.compareTo(o.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Document document = (Document) o;
        return Objects.equals(id, document.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {

        StringBuilder res = new StringBuilder();
        res.append("Document : name = ");
        res.append(this.name);
        res.append(" ; id = ");
        res.append(this.id);

        if(this.path!=null){
            res.append("\nPath : ");
            res.append(this.path);
        }

        for( Map.Entry<String, Object> tag : this.tags.entrySet()){
            res.append("\n");
            res.append(tag.getKey() + " = ");
            res.append(tag.getValue().toString());
        }

        res.append("\n\n");
        return res.toString();
    }
}
