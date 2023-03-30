package org.example.Compulsory.Exceptions;

public class DuplicateIdException extends Exception{
    public DuplicateIdException(String msg){
        super(msg);
    }

    @Override
    public String toString() {
        return "DuplicateIdException";
    }
}
