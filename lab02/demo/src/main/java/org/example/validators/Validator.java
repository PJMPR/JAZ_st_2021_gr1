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

        List<String> errorList = new ArrayList<>();

        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);

            if (field.isAnnotationPresent(NotNull.class)){
                try{
                    if (field.get(object) == null)
                        errorList.add(field.getAnnotation(NotNull.class).message());
                }catch(IllegalAccessException e){
                    e.printStackTrace();
                }
            }

            if (field.isAnnotationPresent(Range.class)){
                try{
                    if(field.getDeclaredAnnotation(Range.class).min() >= (Integer) field.get(object) || field.getDeclaredAnnotation(Range.class).max() <= (Integer) field.get(object))
                        errorList.add(field.getAnnotation(Range.class).message());
                }catch (IllegalAccessException e){
                    e.printStackTrace();
                }
            }

            if (field.isAnnotationPresent(Regex.class)){
                try {
                    if(!field.get(object).toString().matches(field.getDeclaredAnnotation(Regex.class).pattern()))
                        errorList.add(field.getAnnotation(Regex.class).message());
                }catch(IllegalAccessException e){
                    e.printStackTrace();
                }
            }

            if (!errorList.isEmpty()){
                validationResult.getNotValidFields().put(field.getName(),new ArrayList<>(errorList));
                errorList.clear();
            }
        }

        if(validationResult.getNotValidFields().size() != 0)
            validationResult.setValid(false);

        return validationResult;
    }
}
