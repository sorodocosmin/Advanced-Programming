package org.example;

import java.io.IOException;

import static java.lang.Thread.sleep;

public class ClassForLoading {

    private String name;
    protected final int number[] = new int[1];
    private volatile ClassForLoading instance;

    public ClassForLoading(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void printSpecialMessage(){
        System.out.println(
                "-> " + this.name + " <-"
        );
    }

    @Deprecated
    @TestCustom(maxTime = 1)
    protected synchronized final void testMethod(){
        System.out.println("test - with no parameters");
    }

    @TestCustom(maxTime = 1)
    public void testMethod2(String name, int nr){
        System.out.println("test - with - String : " + name + " - int : " + nr);
    }
    @TestCustom(maxTime = 1)
    public void testMethod3(double nr, char ch){
        System.out.println("test - with - double : " + nr + " - int : " + ch);
    }
    @TestCustom(maxTime = 3)
    public void testMethod4(boolean b, char ch, float f, double d, int i, long l, String s){
        System.out.println("test - with - boolean : " + b + " - char : " + ch + " - float : " + f + " - double : " + d + " - int : " + i + " - long : " + l + " - String : " + s);
    }

    @TestCustom(maxTime = 3)//this should fail
    public void testWithExceedTime() throws InterruptedException {
        sleep(5000);//will sleep 5s
    }

    @TestCustom(maxTime = 1)
    public void testWichThrowsException() throws NullPointerException {

        throw new NullPointerException("ops..");

    }

}
