package org.example.functions;

import org.example.model.Person;
import org.example.queries.results.FunctionResult;
import org.example.queries.results.Results;
import org.example.queries.search.Funcs;
import java.lang.reflect.Field;
import java.util.List;

public class GetFunctionResult {

    public FunctionResult getFunctionResultForSumOrAverage(FunctionResult functionResult, Results results) {

        if(functionResult.getFunction() == Funcs.SUM) {
            String fieldString = functionResult.getFieldName();
            List<Person> personList = results.getItems();

            double sum = 0.0;

            try {
                Field personField = Person.class.getField(fieldString);
                for (Person person : personList) {
                    try {
                        personField.setAccessible(true);
                        sum += (double) personField.get(person);
                    }
                    catch (IllegalAccessException ex) {
                    }
                }
                functionResult.setValue(sum);
            }
            catch (NoSuchFieldException e) {
            }
        }
        else if (functionResult.getFunction() == Funcs.AVARAGE) {
            String fieldString = functionResult.getFieldName();
            List<Person> personList = results.getItems();

            double sum = 0.0;

            try {
                Field personField = Person.class.getField(fieldString);
                for (Person person : personList) {
                    try {
                        personField.setAccessible(true);
                        sum += (double) personField.get(person);
                    }
                    catch (IllegalAccessException ex) {
                    }
                }
                functionResult.setValue(sum / results.getItems().size());
            }
            catch (NoSuchFieldException e) {
            }
        }
        return functionResult;
    }
}