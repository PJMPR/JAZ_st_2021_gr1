package org.example.validators.rules;

import org.example.annotations.NotNull;

import java.lang.reflect.Field;

public class NotNullValidationRule extends ValidationRule{

    protected String getAnnoationMessage(Field f) {
        return f.getAnnotation(NotNull.class).message();
    }

    protected boolean checkField(Field f, Object obj) throws IllegalAccessException {
        return f.get(obj)!=null;
    }

    protected boolean hasAnnotation(Field f) {
        return f.isAnnotationPresent(NotNull.class);
    }

}
