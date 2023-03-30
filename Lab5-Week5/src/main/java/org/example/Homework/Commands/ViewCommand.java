package org.example.Homework.Commands;

import org.example.Compulsory.Document;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ViewCommand implements Command{
    private Document document;
    public ViewCommand(Document doc){
        this.document = doc;
    }

    public void execute(){
        if(this.document.isValid()) {
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.open(new File(document.getPath()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            System.out.println("The file " + document.getName() + " couldn't be open (is not valid)");
        }

    }
}
