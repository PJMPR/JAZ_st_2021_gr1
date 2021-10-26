package org.example;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class ObjectPropertyProvider {

    public List<Method> getPublicGetters(Class<?> clazz){

        return Arrays.stream(clazz.getDeclaredMethods())
                .filter(method->new SimpleMethod(method)
                        .isGetter())
                .toList();
    }


    public List<Method> getPublicSetters(Class<?> clazz){

        return Arrays.stream(clazz.getDeclaredMethods())
                .filter(method->new SimpleMethod(method)
                        .isSetter())
                .toList();
    }


    public List<Field> getFieldsForPublicProperties(Class<?> clazz){

        List<Method> props = getPublicGetters(clazz);
        props.addAll(getPublicSetters(clazz));
        /*
        List<Field> fields =Arrays.stream(clazz.getDeclaredFields()).toList();
        List<Field> result = new ArrayList<>();
        for (Field field : fields) {
            for (Method prop : props) {
                if(checkFieldName(prop,field)
                        && !result.contains(field))
                    result.add(field);
            }
        }
*/
        return Arrays.stream(clazz.getDeclaredFields())
                .filter(field->props.stream().anyMatch(prop->checkFieldName(prop, field)))
                .toList();

    }

private boolean checkFieldName(Method prop, Field field){
    return new SimpleMethod(prop).fieldName().equals(field.getName().toLowerCase(Locale.ROOT));
}
}
