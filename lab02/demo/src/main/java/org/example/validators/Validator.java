package org.example.validators;


import org.example.annotations.NotNull;
import org.example.annotations.Range;
import org.example.annotations.Regex;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class Validator {
    public <TClass> ValidationResult validate(TClass object) throws IllegalAccessException {
        ValidationResult result=new ValidationResult();
        result.setValidatedObject(object);
        Field[] fields =object.getClass().getDeclaredFields();
        for (Field field : fields) {

            if(field.isAnnotationPresent(Regex.class)){
                field.setAccessible(true);
                Regex regex=field.getAnnotation(Regex.class);
               String string= (String) field.get(object);
               if(!string.matches(regex.pattern())){
                   //regex.message();
                   creating_mesage(result,field);
                   result.getNotValidFields().get(field.getName()).add(regex.message());
               }
            }
            if(field.isAnnotationPresent(NotNull.class)){
                NotNull notNull= field.getAnnotation(NotNull.class);
                field.setAccessible(true);
                Object o=field.get(object);
                if(o==null || o==""){
                    creating_mesage(result,field);
                    result.getNotValidFields().get(field.getName()).add(notNull.message());
                }

            }
            if (field.isAnnotationPresent(Range.class)){
                Range range=field.getAnnotation(Range.class);
                field.setAccessible(true);
                int intiger = (int) field.get(object);
                if(range.min()>=intiger || range.max()<=intiger){
                    creating_mesage(result, field);
                    result.getNotValidFields().get(field.getName()).add(range.message());
                }

            }

        }
        return result;
    }

    private void creating_mesage(ValidationResult result, Field field) {
        result.setValid(false);
        result.getNotValidFields().put(field.getName(), new ArrayList<>());
    }
}