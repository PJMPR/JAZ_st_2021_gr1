package org.example.validators;


import org.example.annotations.NotNull;
import org.example.annotations.Range;
import org.example.annotations.Regex;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Validator {


    String pattern="^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$";
    public <TClass> ValidationResult validate(TClass object){
        ValidationResult valRes = new ValidationResult();
        valRes.setValid(true);
        valRes.setValidatedObject(object);

        for (Field field : object.getClass().getDeclaredFields())
        {
            field.setAccessible(true);
            List<String> err = new ArrayList<>();
            if(field.isAnnotationPresent(Regex.class))
            {
                try {
                    if(!field.get(object).toString().matches(pattern))
                    {
                        err.add(field.getAnnotation(Regex.class).message());
                        valRes.setValid(false);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            if (field.isAnnotationPresent(Range.class))
            {
                try {
                    int number = (int) field.get(object);
                    if(number<0 || number>10)
                    {
                        err.add(field.getAnnotation(Range.class).message());
                        valRes.setValid(false);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            if(field.isAnnotationPresent(NotNull.class))
            {
                try {
                    if(Objects.isNull(field.get(object)))
                    {
                        err.add(field.getAnnotation(NotNull.class).msg());
                        valRes.setValid(false);
                    }
                    if (Objects.toString(field.get(object)).equals(""))
                    {
                        err.add(field.getAnnotation(NotNull.class).msg1());
                        valRes.setValid(false);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            if(!err.isEmpty())
            valRes.getNotValidFields().put(field.getName(), err);
        }

        return valRes;
    }
}
