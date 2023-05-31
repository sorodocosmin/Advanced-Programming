package org.example;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class LoaderClass {

    private String path;
    private String className;
    private Class loadedClass;

    public LoaderClass (String path, String className){
        this.path = path;
        this.className = className;
        this.loadClass();
    }

    private void loadClass(){
        //first, we'll add the path to the CLASSPATH
        //then we'll load the class
        try {
            File file = new File(this.path);
            URL url = file.toURI().toURL();
            URLClassLoader urlLoader = new URLClassLoader(new URL[]{url});

            this.loadedClass =  urlLoader.loadClass(this.className);

        } catch (MalformedURLException e) {
            System.out.println("Invalid path");
        } catch (ClassNotFoundException e) {
            System.out.println("Class with name " + this.className + " not found");
        }


    }
    public Class getLoadedClass(){
        return this.loadedClass;
    }

}
