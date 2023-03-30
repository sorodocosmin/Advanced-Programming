package org.example.Compulsory.Exceptions;

public class InvalidFileException extends Exception{
    private String msg;
    public InvalidFileException(String msg){
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "InvalidDocumentException " + this.msg;
    }
}
