package org.example.queries;

import org.example.model.Person;
import org.example.queries.functions.CalculateField;
import org.example.queries.functions.PersonAge;
import org.example.queries.functions.PersonIncome;
import org.example.queries.results.FunctionResult;
import org.example.queries.results.Results;
import org.example.queries.search.FunctionsParameters;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FunctionProcessor {
    List<FunctionsParameters> functions;

    public FunctionProcessor(List<FunctionsParameters> functions) {
        this.functions = functions;
    }

    public void calculate(Results result) {
        result.setFunctionResults(functions.stream()
                .map(f -> {
                    FunctionResult functionResult = new FunctionResult();
                    functionResult.setFunction(f.getFunction());
                    functionResult.setFieldName(f.getFieldName());
                    functionResult.setValue(calculateValue(functionResult, result.getItems()));
                    return functionResult;
                })
                .collect(Collectors.toList())
        );
    }

    private double calculateValue(FunctionResult functionResult, List<Person> people) {
        List<CalculateField> calculateFields = List.of(
                new PersonAge(people),
                new PersonIncome(people)
        );

        double result = 0.0;
        for (CalculateField calculateField : calculateFields) {
            if (calculateField.getFieldName().equals(functionResult.getFieldName())) {
                result = calculateField.calculate(functionResult.getFunction());
            }
        }
        return result;
    }


}
