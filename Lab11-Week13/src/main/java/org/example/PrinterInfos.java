package org.example;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

public class PrinterInfos {
    private Class clazz ;


    public PrinterInfos(Class clazz){
        this.clazz = clazz;
    }

    public void printMethods() {

        System.out.println("--------------------------------------------------");
        Method[] methods = this.clazz.getDeclaredMethods();
        System.out.println("Methods: ");
        for (Method method : methods) {
            //System.out.println(method.toString());
            //putem folosi so method.toString() : )
            if( method.isAnnotationPresent(TestCustom.class) ){
                TestCustom annotation = method.getAnnotation(TestCustom.class);
                System.out.println("Method " + method.getName() + " has annotation TestCustom with maxTime = " + annotation.maxTime());
            }else{
                System.out.println("Method " + method.getName() + " has no annotation TestCustom");
            }
            this.printModifiers(method.getModifiers());
            this.printType(method.getReturnType());
            System.out.print(method.getName() + " (");
            this.printParameters(method.getParameters());
            System.out.print("){\n . . . \n}\n");
        }
        System.out.println("--------------------------------------------------");

    }

    public void printFields() {

            System.out.println("--------------------------------------------------");
            System.out.println("Fields: ");
            Field[] fields = this.clazz.getDeclaredFields();
            for (Field field : fields) {
                System.out.println(field.toString());
                this.printModifiers(field.getModifiers());
                this.printType(field.getType());
                System.out.println(field.getName() + ";");
            }

            System.out.println("--------------------------------------------------");

    }

    private void printParameters(Parameter[] parameters) {

        for ( Parameter parameter : parameters){
            this.printType(parameter.getType());
            System.out.print(" " + parameter.getName() + ", ");
        }

    }

    private void printType(Class returnType) {

        System.out.print( returnType.getName() + " ");

    }

    private void printModifiers(int modifiers) {


        if(Modifier.isAbstract(modifiers)){
            System.out.print(" abstract ");
        }
        if(Modifier.isFinal(modifiers)){
            System.out.print(" final ");
        }

        if(Modifier.isPrivate(modifiers)){
            System.out.print(" private ");
        }

        if(Modifier.isProtected(modifiers)){
            System.out.print(" protected ");
        }

        if(Modifier.isPublic(modifiers)){
            System.out.print(" public ");
        }

        if(Modifier.isStatic(modifiers)){
            System.out.print(" static ");
        }

        if(Modifier.isSynchronized(modifiers)){
            System.out.print(" synchronized ");
        }

        if(Modifier.isVolatile(modifiers)){
            System.out.print(" volatile ");
        }




    }

}
