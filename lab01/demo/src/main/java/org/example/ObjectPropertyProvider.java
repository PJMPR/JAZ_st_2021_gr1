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
        //Method m = Arrays.stream(clazz.getDeclaredMethods()).findFirst().or(null).get();
        ArrayList<Method> list = new ArrayList<Method>();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods){
            if (isGetter(method)){
                list.add(method);
            }
        }
        //return Arrays.stream(clazz.getDeclaredMethods()).toList();
        return list;
    }


    public static boolean isGetter(Method method) {
        if (Modifier.isPublic(method.getModifiers()) && method.getParameterTypes().length == 0) {
            if (method.getName().matches("^get[A-Z].*") && !method.getReturnType().equals(void.class))
                return true;
            return method.getName().matches("^is[A-Z].*") && method.getReturnType().equals(boolean.class);
        }

        return false;
    }


    public List<Method> getPublicSetters(Class<?> clazz){
        ArrayList<Method> list = new ArrayList<Method>();
        Method[] methods = clazz.getDeclaredMethods();

        for (Method method : methods)
            if (isSetter(method))
                list.add(method);

        //return Arrays.stream(clazz.getDeclaredMethods()).toList();
        return list;
    }

    public static boolean isSetter(Method method) {
        return Modifier.isPublic(method.getModifiers()) && method.getReturnType().equals(void.class)
                && method.getParameterTypes().length == 1 && method.getName().matches("^set[A-Z].*");
    }


    public List<Field> getFieldsForPublicProperties(Class<?> clazz){
        List<Field> list = new ArrayList<>();
        List<Field> fields = Arrays.stream(clazz.getDeclaredFields()).toList();
        List<Method> all = Stream.concat(getPublicGetters(clazz).stream(), getPublicSetters(clazz).stream()).collect(Collectors.toList());

        for(Field field : fields){
            for(Method All : all){
                String name = All.getName().toLowerCase().replaceAll("set|get","");
                if(field.getName().toLowerCase().equals(name))
                    if(!list.contains(field))
                        list.add(field);

            }
        }

        return list;
        //return Arrays.stream(clazz.getDeclaredFields()).toList();

    }


}
