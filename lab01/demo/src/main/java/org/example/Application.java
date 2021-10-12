package org.example;

import java.lang.reflect.Method;

public class Application {

    public static void main(String[] args){
        Class<?> czlowiek = Person.class;

        printGettersSetters(czlowiek);
    }
    public static void printGettersSetters(Class Person){
        Method[] methods = Person.getDeclaredMethods();

        for(Method method : methods){
            if(isGetter(method)) System.out.println("getter: " + method);
            if(isSetter(method)) System.out.println("setter: " + method);
        }
    }
    public static boolean isGetter(Method method){
        if(!method.getName().startsWith("get"))      return false;
        if(method.getParameterTypes().length != 0)   return false;
        if(void.class.equals(method.getReturnType())) return false;
        return true;
    }

    public static boolean isSetter(Method method){
        if(!method.getName().startsWith("set")) return false;
        if(method.getParameterTypes().length != 1) return false;
        return true;
    }
}
