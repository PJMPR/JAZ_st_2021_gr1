package org.example.validators;

import java.lang.reflect.Field;

public class Validator {
    public <TClass> ValidationResult validate(TClass object){
        ValidationResult result = new ValidationResult();
        result.setValidatedObject(object);

        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                ValidationField validationResult = new ValidationField(field,field.get(object));
                if(!validationResult.isValid()){
                    result.getNotValidFields().put(field.getName(), validationResult.getErrList());
                }
            } catch (IllegalAccessException e){
                e.printStackTrace();
            }
        }

        result.setValid(result.getNotValidFields().isEmpty());
        return result;
    }
}
