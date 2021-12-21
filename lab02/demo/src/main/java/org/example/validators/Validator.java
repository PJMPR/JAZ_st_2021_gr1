package org.example.validators;


<<<<<<< HEAD
import org.example.annotations.NotNull;
import org.example.annotations.Range;
import org.example.annotations.Regex;

import java.lang.reflect.Field;
import java.util.ArrayList;
=======
import org.example.validators.rules.NotNullValidationRule;
import org.example.validators.rules.RangeValidationRule;
import org.example.validators.rules.RegexVallidationRule;
import org.example.validators.rules.ValidationRule;

>>>>>>> 62e3a4e14484aa83fc53cd8bd6a8184df1b08217
import java.util.List;

public class Validator {

<<<<<<< HEAD
    public <TClass> ValidationResult validate(TClass object) {
        ValidationResult validationResult = new ValidationResult();
        validationResult.setValidatedObject(object);
        validationResult.setValid(true);

        List<String> exceptions = new ArrayList<>();
        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);

            if (field.isAnnotationPresent(Range.class)){
                try{
                    Integer fieldObject = (Integer) field.get(object);
                    int min = field.getDeclaredAnnotation(Range.class).min();
                    int max = field.getDeclaredAnnotation(Range.class).max();

                    if (min >= fieldObject || max <= fieldObject) {
                        exceptions.add(field.getAnnotation(Range.class).message());
                        validationResult.setValid(false);
                    }
                }catch (IllegalAccessException e){
                    e.printStackTrace();
                }
            }
            if (field.isAnnotationPresent(Regex.class)){
                try {
                    if(!field.get(object).toString().matches(field.getDeclaredAnnotation(Regex.class).pattern())){
                        exceptions.add(field.getAnnotation(Regex.class).message());
                        validationResult.setValid(false);
                    }

                }catch(IllegalAccessException e){
                    e.printStackTrace();
                }
            }
            if (field.isAnnotationPresent(NotNull.class)){
                try{
                    if (field.get(object) == null){
                        exceptions.add(field.getAnnotation(NotNull.class).message());
                        exceptions.add(field.getAnnotation(NotNull.class).message2());
                        validationResult.setValid(false);
                    }

                }catch(IllegalAccessException e){
                    e.printStackTrace();
                }
            }
            if (!exceptions.isEmpty()){
                    validationResult.getNotValidFields().put(field.getName(),new ArrayList<>(exceptions));
                    exceptions.clear();
            }
        }
        return validationResult;
=======
    List<ValidationRule> rules = List.of(
            new NotNullValidationRule(),
            new RangeValidationRule(),
            new RegexVallidationRule()
    );

    public <TClass> ValidationResult validate(TClass object){

        ValidationResult result = new ValidationResult();
        result.setValidatedObject(object);
        result.setValid(true);
        rules.stream().forEach(rule->rule.validateRule(result));
        return result;
>>>>>>> 62e3a4e14484aa83fc53cd8bd6a8184df1b08217
    }
}
