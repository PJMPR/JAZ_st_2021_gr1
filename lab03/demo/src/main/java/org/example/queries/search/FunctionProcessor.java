package org.example.queries.search;

import org.example.queries.results.FunctionResult;
import org.example.queries.results.Results;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FunctionProcessor {
    public void functionProcessor(Results results, SearchParameters searchParameters) {
        List<FunctionResult> functionResults = new ArrayList<>();
        for (FunctionsParameters f : searchParameters.getFunctions()) {
            if (f.getFunction() == Funcs.SUM) {
                if (Objects.equals(f.getFieldName(), "age")) {
                    functionResults.add(sumAge(results, searchParameters));
                }
                if (Objects.equals(f.getFieldName(), "income")) {
                    functionResults.add(sumIncome(results, searchParameters));
                }
            } else if (f.getFunction() == Funcs.AVARAGE) {
                if (Objects.equals(f.getFieldName(), "age")) {
                    functionResults.add(avgAge(results, searchParameters));
                }
                if (Objects.equals(f.getFieldName(), "income")) {
                    functionResults.add(avgIncome(results, searchParameters));
                }
            }

        }
        results.setFunctionResults(functionResults);
    }

    private FunctionResult sumAge(Results results, SearchParameters searchParameters) {
        FunctionResult functionResult = new FunctionResult();
        functionResult.setFunction(Funcs.SUM);
        functionResult.setFieldName("age");
        functionResult.setValue(
                results.getItems().
                        stream().
                        mapToInt(person -> person.getAge()).sum());
        return functionResult;
    }

    private FunctionResult sumIncome(Results results, SearchParameters searchParameters) {
        FunctionResult functionResult = new FunctionResult();
        functionResult.setFunction(Funcs.SUM);
        functionResult.setFieldName("income");
        functionResult.setValue(
                results.getItems().
                        stream().
                        mapToDouble(person -> person.getIncome()).sum());
        return functionResult;
    }

    private FunctionResult avgIncome(Results results, SearchParameters searchParameters) {
        FunctionResult functionResult = new FunctionResult();
        functionResult.setFunction(Funcs.AVARAGE);
        functionResult.setFieldName("income");
        functionResult.setValue(
                results.getItems().
                        stream().
                        collect(Collectors.averagingDouble(person -> person.getIncome()))
        );
        return functionResult;
    }

    private FunctionResult avgAge(Results results, SearchParameters searchParameters) {
        FunctionResult functionResult = new FunctionResult();
        functionResult.setFunction(Funcs.AVARAGE);
        functionResult.setFieldName("age");
        functionResult.setValue(
                results.getItems().
                        stream().
                        collect(Collectors.averagingInt(person -> person.getAge()))
        );
        return functionResult;
    }
}
