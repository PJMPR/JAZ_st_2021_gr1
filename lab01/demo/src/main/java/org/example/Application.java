package org.example;

import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Application {

    public static void main(String[] args){
//        Method[] methods = Student.class.getMethods();
//
//
//
//
//
//        for(Method method : methods){
//            if(method.getName().startsWith("get") || method.getName().startsWith("is"))
//            System.out.println("method = " + method.getName());
//        }
        ObjectPropertyProvider opp = new ObjectPropertyProvider();
        System.out.println(opp.getPublicGetters(Student.class));


    }
}
