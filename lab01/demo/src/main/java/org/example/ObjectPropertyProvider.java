package org.example;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class ObjectPropertyProvider {

    public List<Method> getPublicGetters(Class<?> clazz){
        List<Method> methods = Arrays.stream(clazz.getDeclaredMethods()).toList();
        List<Method> result = new ArrayList<>();

        for (Method method: methods) {
            if(method.getModifiers() == Modifier.PUBLIC)
                if(method.getParameterCount() == 0) {
                    if (method.getName().matches("^get[A-Z].*") && !method.getReturnType().equals(void.class))
                        result.add(method);
                    if (method.getName().matches("^is[A-Z].*") && method.getReturnType().equals(boolean.class))
                        result.add(method);
                }
        }
        
        return result;
    }


    public List<Method> getPublicSetters(Class<?> clazz){
        List<Method> methods = Arrays.stream(clazz.getDeclaredMethods()).toList();
        List<Method> result = new ArrayList<>();

        for (Method method: methods) {
            if(method.getModifiers() == Modifier.PUBLIC)
                if(method.getName().matches("^set[A-Z].*"))
                    if(method.getParameterCount() == 1)
                        if(method.getReturnType().equals(Void.TYPE))
                            result.add(method);
        }

        return result;
    }


    public List<Field> getFieldsForPublicProperties(Class<?> clazz){
        List<Field> fields = Arrays.stream(clazz.getDeclaredFields()).toList();
        List<Field> result = new ArrayList<>();

        List<Method> gettersAndSetters = Stream.concat(
                getPublicGetters(clazz).stream(),
                getPublicSetters(clazz).stream()
        ).collect(Collectors.toList());

        for (Field field: fields) {
            for (Method getOrSet : gettersAndSetters) {
                String nameGetOrSet = getOrSet.getName().toLowerCase().replaceAll("(?:set|get)", "");
                if (field.getName().toLowerCase().equals(nameGetOrSet)) {
                    if(!result.contains(field))
                        result.add(field);
                }
            }
        }

        return result;
    }
}
