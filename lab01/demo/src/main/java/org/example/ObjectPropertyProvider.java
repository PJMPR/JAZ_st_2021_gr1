package org.example;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ObjectPropertyProvider {

    public List<Method> getPublicGetters(Class<?> clazz){
       List<Method> result= new ArrayList<Method>();
       List<Method> result1= new ArrayList<Method>();
       result=Arrays.stream(clazz.getDeclaredMethods()).toList();
        for (Method o : result) {
            if(o.getName().startsWith("get")){
            result1.add(o);
            }
        }
        return result1;

    }


    public List<Method> getPublicSetters(Class<?> clazz){
        List<Method> result= new ArrayList<Method>();
        List<Method> result1= new ArrayList<Method>();
        result=Arrays.stream(clazz.getDeclaredMethods()).toList();
        for (Method o : result) {

            if(o.getName().startsWith("set")){
                result1.add(o);

            }
        }
        return result1;

    }



    public List<Field> getFieldsForPublicProperties(Class<?> clazz){

        return Arrays.stream(clazz.getDeclaredFields()).toList();

    }


}
