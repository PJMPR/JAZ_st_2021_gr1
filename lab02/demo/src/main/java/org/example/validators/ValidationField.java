package org.example.validators;

import org.example.annotations.NotNull;
import org.example.annotations.Range;
import org.example.annotations.Regex;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ValidationField {
    private final Field field;
    private final Object fieldValue;
    private final List<String> errList;

    public ValidationField(Field f, Object value){
        errList = new ArrayList<>();
        fieldValue = value;
        field = f;
    }

    public List<String> getErrList() {
        return errList;
    }

    private boolean isFieldNotNull(){
        return fieldValue == null;
    }

    private boolean isNumberNotInRange(){
        int num = (int) fieldValue;
        return (num <= field.getAnnotation(Range.class).min() || num >= field.getAnnotation(Range.class).max());
    }

    private boolean isEmailNotValid(){
        return !fieldValue.toString().matches(field.getAnnotation(Regex.class).pattern());
    }

    public boolean isValid() {
        for (Annotation annotation: field.getDeclaredAnnotations()) {
            switch (annotation.annotationType().getSimpleName()){
                case "NotNull" -> {
                    if(isFieldNotNull()){
                        errList.add(field.getAnnotation(NotNull.class).message());
                    }
                }
                case "Regex" -> {
                    if(isEmailNotValid()){
                        errList.add(field.getAnnotation(Regex.class).message());
                    }
                }
                case "Range" -> {
                    if(isNumberNotInRange()){
                        errList.add(field.getAnnotation(Range.class).message());
                    }
                }
                default -> throw new IllegalStateException("Unexpected value: " + annotation.annotationType());
            }
        }
        return errList.isEmpty();
    }
}
