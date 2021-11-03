package org.example.function;

import org.example.queries.results.FunctionResult;
import org.example.queries.results.Results;
import org.example.queries.search.Funcs;
import org.example.queries.search.FunctionsParameters;
import org.example.queries.search.SearchParameters;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Functions extends FunctionRule{

    protected FunctionResult averageAge(Results results, SearchParameters searchParameters) {
        FunctionResult functionResult = new FunctionResult();
        functionResult.setFunction(Funcs.AVARAGE);
        functionResult.setValue(results.getItems()
                .stream()
                .collect(Collectors.averagingInt(person -> person.getAge())));
        return functionResult;
    }

    protected FunctionResult averageIncome(Results results, SearchParameters searchParameters) {
        FunctionResult functionResult = new FunctionResult();
        functionResult.setFunction(Funcs.AVARAGE);
        functionResult.setValue(results.getItems()
                .stream()
                .collect(Collectors.averagingDouble(person -> person.getIncome())));
        return functionResult;
    }

    protected FunctionResult sumAge(Results results, SearchParameters searchParameters) {
        FunctionResult functionResult = new FunctionResult();
        functionResult.setFunction(Funcs.SUM);
        functionResult.setValue(
                results.getItems()
                        .stream()
                        .mapToInt(person -> person.getAge()).sum());
        return functionResult;
    }

    protected FunctionResult sumIncome(Results results, SearchParameters searchParameters) {
        FunctionResult functionResult = new FunctionResult();
        functionResult.setFunction(Funcs.SUM);
        functionResult.setValue(
                results.getItems().stream().mapToDouble(person -> person.getIncome()).sum());
        return functionResult;
    }
}
