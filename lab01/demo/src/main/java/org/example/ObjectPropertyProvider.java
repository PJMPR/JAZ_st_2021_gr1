package org.example;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ObjectPropertyProvider {

    public List<Method> getPublicGetters(Class<?> clazz){
        List<Method> m = Arrays.stream(clazz.getDeclaredMethods()).toList();
        List<Method> out = new ArrayList<Method>();
        for (Method method : m) {
            if((Modifier.isPublic(method.getModifiers()) && method.getParameterCount() == 0 && !method.getReturnType().toString().equals("void")) && (method.getName().startsWith("is") || method.getName().startsWith("get")))
                out.add(method);
        }

        return out;

    }


    public List<Method> getPublicSetters(Class<?> clazz){

        List<Method> m = Arrays.stream(clazz.getDeclaredMethods()).toList();
        List<Method> out = new ArrayList<Method>();
        for (Method method : m) {
            if((Modifier.isPublic(method.getModifiers()) &&
            method.getParameterCount() == 1 &&
            method.getReturnType().toString().equals("void")) &&
            (method.getName().startsWith("set")))
            {

                out.add(method);
            }
        }

        return out;   

    }

    public List<Field> getFieldsForPublicProperties(Class<?> clazz){

        List<Method> mm = Arrays.stream(clazz.getDeclaredMethods()).toList();
        List<Field> m = Arrays.stream(clazz.getDeclaredFields()).toList();
        List<Field> out = new ArrayList<Field>();


        for (Method method : getPublicGetters(clazz)) {
            
        for (Field field : m) {
            if(method.getName().toLowerCase().contains(field.getName()) || method.getName().equals(field.getName()))
            {
                if(!out.contains(field)){
                    out.add(field);
                    break;
                }
            }
        }
        }

        for (Method method : getPublicSetters(clazz)) {
            
            for (Field field : m) {
                if(method.getName().toLowerCase().contains(field.getName()))
                {
                    if(!out.contains(field)){
                        out.add(field);
                        break;
                    }
                }
            }
            }



        return out;

    }


}
