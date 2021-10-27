package org.example.validators;


import org.example.annotations.NotNull;
import org.example.annotations.Range;
import org.example.annotations.Regex;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Validator {

    public <TClass> ValidationResult validate(TClass object) {
        ValidationResult validationResult = new ValidationResult();
        validationResult.setValidatedObject(object);
        validationResult.setValid(true);

        List<String> exceptions = new ArrayList<>();
        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);

            if (field.isAnnotationPresent(Range.class)){
                try{
                    Integer fieldObject = (Integer) field.get(object);
                    int min = field.getDeclaredAnnotation(Range.class).min();
                    int max = field.getDeclaredAnnotation(Range.class).max();

                    if (min >= fieldObject || max <= fieldObject) {
                        exceptions.add(field.getAnnotation(Range.class).message());
                        validationResult.setValid(false);
                    }
                }catch (IllegalAccessException e){
                    e.printStackTrace();
                }
            }
            if (field.isAnnotationPresent(Regex.class)){
                try {
                    if(!field.get(object).toString().matches(field.getDeclaredAnnotation(Regex.class).pattern())){
                        exceptions.add(field.getAnnotation(Regex.class).message());
                        validationResult.setValid(false);
                    }

                }catch(IllegalAccessException e){
                    e.printStackTrace();
                }
            }
            if (field.isAnnotationPresent(NotNull.class)){
                try{
                    if (field.get(object) == null){
                        exceptions.add(field.getAnnotation(NotNull.class).message());
                        exceptions.add(field.getAnnotation(NotNull.class).message2());
                        validationResult.setValid(false);
                    }

                }catch(IllegalAccessException e){
                    e.printStackTrace();
                }
            }
            if (!exceptions.isEmpty()){
                    validationResult.getNotValidFields().put(field.getName(),new ArrayList<>(exceptions));
                    exceptions.clear();
            }
        }
        return validationResult;
    }
}
