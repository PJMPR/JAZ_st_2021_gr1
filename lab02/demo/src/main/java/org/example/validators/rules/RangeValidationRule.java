package org.example.validators.rules;

import org.example.annotations.NotNull;
import org.example.annotations.Range;
import org.example.validators.ValidationResult;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class RangeValidationRule extends ValidationRule {

    protected String getAnnoationMessage(Field f) {
        Range range = f.getAnnotation(Range.class);
        return range.message().formatted(range.min(), range.max());
    }

    protected boolean checkField(Field f, Object obj) throws IllegalAccessException {
        Number number = (Number) f.get(obj);
        Range range = f.getAnnotation(Range.class);
        return range.min()<number.doubleValue()&&number.doubleValue()<range.max();
    }

    protected boolean hasAnnotation(Field f) {
        return f.isAnnotationPresent(Range.class);
    }
}
