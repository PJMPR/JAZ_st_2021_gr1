package org.example;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ObjectPropertyProvider {

    public List<Method> getPublicGetters(Class<?> clazz){
        List<Method> classGetters = new ArrayList<Method>();
        Method[] methods = clazz.getDeclaredMethods();

        for(Method method : methods){
            if(!void.class.equals(method.getReturnType()))
            {
                if(method.getParameterCount()==0)
                {
                    if(Modifier.isPublic(method.getModifiers()))
                    {
                        if(method.getName().startsWith("get") || method.getName().startsWith("is"))
                        {
                            classGetters.add(method);
                        }
                    }
                }
            }
        }
        return classGetters;
    }


    public List<Method> getPublicSetters(Class<?> clazz){
        List<Method> classSetters = new ArrayList<Method>();
        Method[] methods = clazz.getDeclaredMethods();

        for(Method method : methods){
            if(void.class.equals(method.getReturnType()))
            {
                if(method.getParameterCount()==1)
                {
                    if(Modifier.isPublic(method.getModifiers()))
                    {
                        if(method.getName().startsWith("set"))
                        {
                            classSetters.add(method);
                        }
                    }
                }
            }
        }
        return classSetters;
    }

    public List<Field> getFieldsForPublicProperties(Class<?> clazz){

        return Arrays.stream(clazz.getDeclaredFields()).toList();

    }


}
