package org.example;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodInvokerForTestCustom {
    private final Class classWithMethods;
    //private final Class annotationClass;
    private int nrMethodsFailed = 0;
    private int nrMethodsPassed = 0;

    /**
     *
     * All methods with annotationClass will be invoked
     *
     * @param classWithMethods - class with methods to invoke
     * //@param annotationClass - annotation to find in methods
     */
    public MethodInvokerForTestCustom(Class classWithMethods) {
        this.classWithMethods = classWithMethods;
    }

    public void invokeMethods() {

        Object object = null;

        try {

             object = this.classWithMethods.getConstructor().newInstance();

        } catch (InstantiationException e) {
            System.out.println("Cannot instantiate class " + this.classWithMethods.getName());
        } catch (IllegalAccessException e) {
            System.out.println("Cannot access constructor of class " + this.classWithMethods.getName());
        } catch (InvocationTargetException e) {
            System.out.println("Cannot invoke constructor of class " + this.classWithMethods.getName());
        } catch (NoSuchMethodException e) {
            System.out.println("Class " + this.classWithMethods.getName() + " has no constructor");
        }

        for (Method method : this.classWithMethods.getDeclaredMethods()) {
            if (method.isAnnotationPresent(TestCustom.class)) {


                method.setAccessible(true);//in case a method is private/protected

                Object[] params = this.getParams(method);

                System.out.println("Method " + method.getName() + " has annotation TestCustom : ");
                TestCustom annotation = method.getAnnotation(TestCustom.class);
                double maxTimeForThisMethod = annotation.maxTime();
                long currentTime = System.currentTimeMillis();

                try {
                    method.invoke(object, params);
                } catch (IllegalAccessException e) {

                    System.out.println("Method " + method.getName() + " is not accessible");
                    this.nrMethodsFailed++;

                } catch (InvocationTargetException e) {

                    System.out.println(method.getName() + " cannot be invoked");
                    this.nrMethodsFailed++;

                } catch (IllegalArgumentException e) {

                    System.out.println(method.getName() + " has wrong arguments");
                    this.nrMethodsFailed++;

                } catch (Exception e) {

                    System.out.println(method.getName() + " has thrown an exception");
                    this.nrMethodsFailed++;
                }

                long endTime = System.currentTimeMillis();

                if ((endTime - currentTime) / 1000.0 > maxTimeForThisMethod) {
                    System.out.println( method.getName() + " has exceeded the time limit of " + maxTimeForThisMethod + " ms");
                    this.nrMethodsFailed++;
                } else {
                    this.nrMethodsPassed++;
                }
                System.out.println();
            }
        }

    }

    public void printStatistics(){
        System.out.println("Number of methods passed: " + this.nrMethodsPassed);
        System.out.println("Number of methods failed: " + this.nrMethodsFailed);
    }

    private Object[] getParams(Method method) {

        Object [] params = new Object[method.getParameterCount()];

        for( int i = 0; i < method.getParameterCount(); i++ ){
            params[i] = this.getObjectOfType(method.getParameterTypes()[i]);
        }

        return params;

    }

    private Object getObjectOfType (Class<?> typeParameter ){

            if( typeParameter == String.class ){
                return TypeGenerator.getString();
            }

            if( typeParameter == int.class ){
                return TypeGenerator.getInt();
            }

            if( typeParameter == double.class ){
                return TypeGenerator.getDouble();
            }

            if( typeParameter == float.class ){
                return TypeGenerator.getFloat();
            }

            if( typeParameter == long.class ){
                return TypeGenerator.getLong();
            }

            if( typeParameter == boolean.class ){
                return TypeGenerator.getBoolean();
            }

            if( typeParameter == char.class ){
                return TypeGenerator.getChar();
            }

            return null;
    }


}
