package org.example.validators.rules;

import org.example.annotations.NotNull;
import org.example.validators.ValidationResult;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public abstract class ValidationRule {

    public void validateRule(ValidationResult validationResult){

        if(validationResult.getValidatedObject()==null)return;
        List<Field> fields = Arrays.stream(validationResult.getValidatedObject().getClass().getDeclaredFields())
                .toList();
        fields.stream().filter(f->hasAnnotation(f))
                .forEach(f->validateField(f, validationResult));
    }

    private void validateField(Field f, ValidationResult validationResult) {
        try {

            f.setAccessible(true);
            if(!checkField(f, validationResult.getValidatedObject())){
                validationResult.setValid(false);
                prepareMessage(f, validationResult.getNotValidFields());
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void prepareMessage(Field f, Map<String, List<String>> notValidFields) {
        if(!notValidFields.containsKey(f.getName())) notValidFields.put(f.getName(), new ArrayList<>());
        notValidFields.get(f.getName()).add(getAnnoationMessage(f));
    }

    protected abstract String getAnnoationMessage(Field f);

    protected abstract boolean checkField(Field f, Object obj) throws IllegalAccessException;

    protected abstract boolean hasAnnotation(Field f);
}
