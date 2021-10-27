package org.example.validators;

import org.example.annotations.NotNull;
import org.example.annotations.Range;
import org.example.annotations.Regex;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Validator {

    private Map<String, List<String>> notValidFields = new HashMap<String, List<String>>();

    public <TClass> ValidationResult validate(TClass tClass) {

        List<Field> fieldsWithAnnotations = collectAllFieldsWithAnnotations(tClass);

        checkNotNull(fieldsWithAnnotations.stream(), tClass);
        checkRegex(fieldsWithAnnotations.stream(), tClass);
        checkRange(fieldsWithAnnotations.stream(), tClass);

        ValidationResult result = new ValidationResult();
        result.setValidatedObject(tClass);
        result.setValid(notValidFields.isEmpty());
        result.getNotValidFields().putAll(notValidFields);
        notValidFields.clear();
        return result;
    }

    public <TClass> List<Field> collectAllFieldsWithAnnotations(TClass tClass) {
        return Arrays
                .stream(tClass.getClass().getDeclaredFields()).filter(x -> x.isAnnotationPresent(NotNull.class)
                        || x.isAnnotationPresent(Range.class) || x.isAnnotationPresent(Regex.class))
                .peek(x -> x.setAccessible(true)).toList(); // z takim filtrowaniem zadziala w kazdym przypadku
    }

    public <TClass> void checkNotNull(Stream<Field> f, TClass tClass) {

        for (Field field : f.filter(x -> x.isAnnotationPresent(NotNull.class)).toList()) {
            if (!isNotNull(field, tClass)) {
                if (!notValidFields.containsKey(field.getName())) {
                    notValidFields.put(field.getName(), new ArrayList<String>() {
                        {
                            add(field.getAnnotation(NotNull.class).messageIsNull());
                        }
                    });
                } else {
                    notValidFields.get(field.getName()).add(field.getAnnotation(NotNull.class).messageIsNull());
                }
            }
        }

    }

    public <TClass> void checkRegex(Stream<Field> f, TClass tClass) {

        for (Field field : f.filter(x -> x.isAnnotationPresent(Regex.class)).toList()) {
            if (!isValidRegex(field, tClass)) {
                if (!notValidFields.containsKey(field.getName())) {
                    notValidFields.put(field.getName(), new ArrayList<String>() {
                        {
                            add(field.getAnnotation(Regex.class).message());
                        }
                    });
                } else {
                    notValidFields.get(field.getName()).add(field.getAnnotation(Regex.class).message());
                }
            }
        }

    }

    public <TClass> void checkRange(Stream<Field> f, TClass tClass) {

        for (Field field : f.filter(x -> x.isAnnotationPresent(Range.class)).toList()) {
            if (!isValidRange(field, tClass)) {
                if (!notValidFields.containsKey(field.getName())) {
                    notValidFields.put(field.getName(), new ArrayList<String>() {
                        {
                            add(field.getAnnotation(Range.class).message());
                        }
                    });
                } else {
                    notValidFields.get(field.getName()).add(field.getAnnotation(Range.class).message());
                }
            }
        }

    }

    private <TClass> boolean isNotNull(Field f, TClass tClass) {
        try {
            f.setAccessible(true);
            Object tmp = f.get(tClass);
            return tmp != null;

        } catch (Exception ex) {

            return false;
        }

    }

    private <TClass> boolean isValidRegex(Field f, TClass tClass) {
        try {
            if (isNotNull(f, tClass)
                    && f.get(tClass).toString().matches(f.getDeclaredAnnotation(Regex.class).pattern())) {
                return true;
            }
        } catch (Exception ex) {

            return false;
        }
        return false;
    }

    private <TClass> boolean isValidRange(Field f, TClass tClass) {
        try {
            if (isNotNull(f, tClass)
                    && Integer.parseInt(f.get(tClass).toString()) >= f.getDeclaredAnnotation(Range.class).min()
                    && Integer.parseInt(f.get(tClass).toString()) <= f.getDeclaredAnnotation(Range.class).max()) {
                return true;
            }
        } catch (Exception ex) {

            return false;
        }
        return false;
    }
}