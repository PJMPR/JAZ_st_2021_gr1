package org.example;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ObjectPropertyProvider {

    public List<Method> getPublicGetters(Class<?> clazz){
        Method[] methods = clazz.getDeclaredMethods();
        List<Method> getters = new ArrayList<>();

        for(Method method : methods){
            if(
                    (Modifier.isPublic(method.getModifiers()))
                            &&(method.getName().startsWith("get")
                            || method.getName().startsWith("is"))
                            && (method.getParameterTypes().length==0)
                            && (!void.class.equals(method.getReturnType()))){
                getters.add(method);
            }
        }
        return getters;

    }


    public List<Method> getPublicSetters(Class<?> clazz){
        Method[] methods = clazz.getDeclaredMethods();
        List<Method> setters = new ArrayList<>();
        for (Method method : methods){
            if(Modifier.isPublic(method.getModifiers())){
                if(method.getName().startsWith("set")){
                    if(method.getParameterTypes().length==1){
                        if (void.class.equals(method.getReturnType())){
                            setters.add(method);
                        }
                    }
                }
            }
        }
        return setters;
    }


    public List<Field> getFieldsForPublicProperties(Class<?> clazz){
        Field[] fields = clazz.getDeclaredFields();
        List<Field> result = new ArrayList<>();
        List<Method> setters = getPublicSetters(clazz);
        List<Method> getters = getPublicGetters(clazz);
        for(Field field : fields){
            if(setters.contains(field.getName()) && getters.contains(field.getName())){
                result.add(field);
            }
        }
        return result;

    }


}
