package org.example.function;

import org.example.queries.results.FunctionResult;
import org.example.queries.results.Results;
import org.example.queries.search.Funcs;
import org.example.queries.search.FunctionsParameters;
import org.example.queries.search.SearchParameters;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public  abstract class HeirdomFunction {
    public void applyFunctions(Results results, SearchParameters searchParameters) {
        List<FunctionResult> functionResults = new ArrayList<>();
        for (FunctionsParameters functionsParameters : searchParameters.getFunctions()) {
            if (functionsParameters.getFunction() == Funcs.SUM) {
                addSum(results, searchParameters, functionResults, functionsParameters);

            } else if (functionsParameters.getFunction() == Funcs.AVARAGE) {
                addAvg(results, searchParameters, functionResults, functionsParameters);
            }
        }
        results.setFunctionResults(functionResults);
    }

    private void addAvg(Results results, SearchParameters searchParameters, List<FunctionResult> functionResults, FunctionsParameters f) {
        if (Objects.equals(f.getFieldName(), "age")) {
            functionResults
                    .add(avgAge(results, searchParameters));
        }
        if (Objects.equals(f.getFieldName(), "income")) {
            functionResults
                    .add(avgIncome(results, searchParameters));
        }
    }

    private void addSum(Results results, SearchParameters searchParameters, List<FunctionResult> functionResults, FunctionsParameters f) {
        if (Objects.equals(f.getFieldName(), "age")) {
            functionResults
                    .add(sumAge(results, searchParameters));
        }
        if (Objects.equals(f.getFieldName(), "income")) {
            functionResults
                    .add(sumIncome(results, searchParameters));
        }
    }

    protected abstract FunctionResult avgAge(Results results, SearchParameters searchParameters);
    protected abstract FunctionResult avgIncome(Results results, SearchParameters searchParameters);
    protected abstract FunctionResult sumAge(Results results, SearchParameters searchParameters);
    protected abstract FunctionResult sumIncome(Results results, SearchParameters searchParameters);
}
