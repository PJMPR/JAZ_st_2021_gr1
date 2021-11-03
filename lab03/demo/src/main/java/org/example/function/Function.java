package org.example.function;

import org.example.queries.results.FunctionResult;
import org.example.queries.results.Results;
import org.example.queries.search.Funcs;
import org.example.queries.search.SearchParameters;
import java.util.stream.Collectors;


public  class Function extends HeirdomFunction {

    protected FunctionResult avgAge(Results results, SearchParameters searchParameters) {
        FunctionResult functionResult = getFunctionResult();
        functionResult.setValue(results.
                getItems()
                .stream()
                .collect(Collectors.averagingInt(person -> person.getAge())));
        return functionResult;
    }

    protected FunctionResult sumAge(Results results, SearchParameters searchParameters) {
        FunctionResult functionResult = getFunctionResult();
        functionResult.setValue(
                results.getItems().stream()
                        .mapToInt(person -> person.getAge())
                        .sum());
        return functionResult;
    }

    protected FunctionResult sumIncome(Results results, SearchParameters searchParameters) {
        FunctionResult functionResult = getFunctionResult();
        functionResult.setValue(
                results
                        .getItems()
                        .stream()
                        .mapToDouble(person -> person.getIncome())
                        .sum());
        return functionResult;
    }

    protected FunctionResult avgIncome(Results results, SearchParameters searchParameters) {
        FunctionResult functionResult = getFunctionResult();
        functionResult.setValue(results
                .getItems()
                .stream()
                .collect(Collectors.averagingDouble(person -> person.getIncome())));
        return functionResult;
    }

    private FunctionResult getFunctionResult() {
        FunctionResult functionResult = new FunctionResult();
        functionResult.setFunction(Funcs.AVARAGE);
        return functionResult;
    }

}
