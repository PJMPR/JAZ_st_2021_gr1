package org.example.validators;

import org.example.annotations.NotNull;
import org.example.annotations.Range;
import org.example.annotations.Regex;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Validator {

    public <TClass> ValidationResult validate(TClass object){

        List<String> errors = new ArrayList<>();

        ValidationResult validationResult = new ValidationResult();
        validationResult.setValidatedObject(object);
        validationResult.setValid(true);

        for (Field f :  object.getClass().getDeclaredFields()){
            f.setAccessible(true);
            System.out.println(f);

            if(f.isAnnotationPresent(NotNull.class)){
                try {
                    if(f.get(object) == null){
                        errors.add(f.getAnnotation(NotNull.class).messageNull());
                        validationResult.setValid(false);
                    }else if(f.get(object) == ""){
                        errors.add(f.getAnnotation(NotNull.class).messageEmpty());
                        validationResult.setValid(false);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            if(f.isAnnotationPresent(Range.class)){
                try {
                    if(f.getDeclaredAnnotation(Range.class).min() >= (Integer) f.get(object) || f.getDeclaredAnnotation(Range.class).max() <= (Integer) f.get(object)){
                        errors.add(f.getAnnotation(Range.class).message());
                        validationResult.setValid(false);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            if(f.isAnnotationPresent(Regex.class)){
                try {
                    if(!f.get(object).toString().matches(f.getDeclaredAnnotation(Regex.class).pattern())){
                        errors.add(f.getAnnotation(Regex.class).message());
                        validationResult.setValid(false);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            if(!errors.isEmpty()){
                validationResult.getNotValidFields().put(f.getName() , new ArrayList<>(errors));
                errors.clear();
            }
        }

        return validationResult;
    }
}
