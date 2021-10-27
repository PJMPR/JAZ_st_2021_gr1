package org.example.validators;

import org.example.annotations.NotNull;
import org.example.annotations.Range;
import org.example.annotations.Regex;

import java.lang.reflect.Field;
import java.util.*;


public class Validator {

    public <TClass> ValidationResult validate(TClass object) {

        List errors = new ArrayList();

        ValidationResult validationResult = new ValidationResult();
        validationResult.setValidatedObject(object);
        validationResult.setValid(true);

        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);

            if (field.isAnnotationPresent(NotNull.class)) {
                try {
                    if (field.get(object) == null) {
                        errors.add(field.getAnnotation(NotNull.class).message());
                        validationResult.setValid(false);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            if (field.isAnnotationPresent(Range.class)) {
                try {
                    if (field.getDeclaredAnnotation(Range.class).min() >= (Integer) field.get(object)
                            || field.getDeclaredAnnotation(Range.class).max() <= (Integer) field.get(object)) {
                        errors.add(field.getAnnotation(Range.class).message());
                        validationResult.setValid(false);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            if (field.isAnnotationPresent(Regex.class)) {
                try {
                    if (!field.get(object).toString().matches(field.getDeclaredAnnotation(Regex.class).pattern())) {
                        errors.add(field.getAnnotation(Regex.class).message());
                        validationResult.setValid(false);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            if (!errors.isEmpty()) {
                validationResult.getNotValidFields().put(field.getName(), new ArrayList(errors));
                errors.clear();
            }
        }

        return validationResult;
    }
}
