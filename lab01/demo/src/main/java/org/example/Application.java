package org.example;

import java.lang.reflect.Method;

public class Application {

    public static void main(String[] args){
        Class<?> klasa = Car.class;
        Method[] methods = klasa.getMethods();
        for (Method method:methods) {
            System.out.println(klasa.getName());
        }
    }
}
