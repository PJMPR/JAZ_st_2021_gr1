package org.example;


import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class ObjectPropertyProvider {



    public List<Method> getPublicGetters(Class<?> clazz){
        Method[] methods = Student.class.getMethods();

        for(Method method : methods) {
            if(method.getName().startsWith("get") || method.getName().startsWith("is")) {

            }
        }

        return Arrays.stream(methods).toList();

    }


    public List<Method> getPublicSetters(Class<?> clazz){

        return Arrays.stream(clazz.getDeclaredMethods()).toList();
    }


    public List<Field> getFieldsForPublicProperties(Class<?> clazz){

        return Arrays.stream(clazz.getDeclaredFields()).toList();

    }


}
