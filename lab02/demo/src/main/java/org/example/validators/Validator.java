package org.example.validators;

import org.example.annotations.NotNull;
import org.example.annotations.Range;
import org.example.annotations.Regex;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class Validator {

    public <TClass> ValidationResult validate(TClass object) throws IllegalAccessException {
        ValidationResult validationResult = new ValidationResult();
        validationResult.setValidatedObject(object);
        Field[] fields = object.getClass().getDeclaredFields();

        for (Field field : fields) {
            if(field.isAnnotationPresent(NotNull.class)){
                accessTrue(field);
                String string = (String) field.get(object);
                NotNull temp = field.getAnnotation(NotNull.class);
                if(string == "" || string==null){
                    showValidationResult(validationResult, field);
                    validationResult.getNotValidFields().get(field.getName()).add(temp.message());
                }
            }
            if(field.isAnnotationPresent(Regex.class)){
                accessTrue(field);
                String string = (String) field.get(object);
                Regex temp = field.getAnnotation(Regex.class);
                if(!string.matches(temp.pattern())){
                    showValidationResult(validationResult, field);
                    validationResult.getNotValidFields().get(field.getName()).add(temp.message());
                }
            }
            if(field.isAnnotationPresent(Range.class)){
                accessTrue(field);
                int number = (int) field.get(object);
                Range temp = field.getAnnotation(Range.class);
                if(number < temp.min() || number > temp.max()){
                    showValidationResult(validationResult, field);
                    validationResult.getNotValidFields().get(field.getName()).add(temp.message());
                }
            }
        }
        return validationResult;
    }

    private void accessTrue(Field field) {
        field.setAccessible(true);
    }

    private void showValidationResult(ValidationResult validationResult, Field field) {
        validationResult.setValid(false);
        validationResult.getNotValidFields().put(field.getName(),new ArrayList<>());
    }
}
