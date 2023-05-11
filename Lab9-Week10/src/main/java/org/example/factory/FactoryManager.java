package org.example.factory;

public class FactoryManager {
    public static AbstractFactory getFactory(String type){
        if (type.equalsIgnoreCase("JDBC")){
            return new ConcreteFactoryJDBC();
        }
        else if (type.equalsIgnoreCase("JPA")){
            return new ConcreteFactoryJPA();
        }

        return null;
    }
}
