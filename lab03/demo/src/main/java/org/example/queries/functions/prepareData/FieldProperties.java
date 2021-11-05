package org.example.queries.functions.prepareData;

import java.lang.reflect.Field;
import java.util.stream.Stream;

public class FieldProperties {
    private final Field field;
    private final Object value;

    public FieldProperties(Field field, Object object) {
        this.field = field;
        this.value = findValue(field, object);
    }

    public FieldProperties(String fieldName, Object object) {
        this.field = findField(fieldName, object);
        this.value = findValue(field, object);
    }

    public boolean isNumber() {
        return Stream.of(int.class, float.class, double.class)
                .anyMatch(type -> this.field.getType().equals(type));
    }

    public boolean isNotNull() {
        return value != null;
    }

    public Number getAsNumber() {
        return (Number) value;
    }

    public Field findField(String fieldName, Object object) {
        Field tempField = null;
        try {
            tempField = object.getClass().getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return tempField;
    }

    public Object findValue(Field field, Object object) {
        Object tempValue = null;
        try {
            field.setAccessible(true);
            tempValue = field.get(object);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return tempValue;
    }
}
