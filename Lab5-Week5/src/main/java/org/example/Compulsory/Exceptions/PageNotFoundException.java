package org.example.Compulsory.Exceptions;

public class PageNotFoundException extends Exception{
    private String msg;
    public PageNotFoundException(String msg){
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "PageNotFoundException " + this.msg;
    }
}
