package org.example.validators.rules;

import org.example.annotations.Regex;

import java.lang.reflect.Field;

public class RegexVallidationRule extends ValidationRule{
    @Override
    protected String getAnnoationMessage(Field f) {
        return f.getAnnotation(Regex.class).message();
    }

    @Override
    protected boolean checkField(Field f, Object obj) throws IllegalAccessException {
        String value = (String)f.get(obj);
        Regex regex = f.getAnnotation(Regex.class);
        return value.matches(regex.pattern());
    }

    @Override
    protected boolean hasAnnotation(Field f) {
        return f.isAnnotationPresent(Regex.class);
    }
}
