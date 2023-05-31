package org.example;


public class Main {
    public static void main(String[] args) {

        String path = "C:\\Users\\sorod\\Desktop\\FACULTATE\\an2_sem2\\Advanced_Programming\\Labs\\Lab11-Week13-Classes\\target\\classes";
        String nameClass = "org.example.ClassForLoading";

        LoaderClass loader = new LoaderClass(
                path,nameClass
        );

        Class clazz = loader.getLoadedClass();
//
//        PrinterInfos printer = new PrinterInfos(clazz);
//        printer.printFields();
//        printer.printMethods();

        MethodInvokerForTestCustom invoker = new MethodInvokerForTestCustom(clazz);
        invoker.invokeMethods();
        invoker.printStatistics();
    }
}