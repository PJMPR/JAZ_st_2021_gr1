package org.example.queries.functions;

import org.example.queries.results.FunctionResult;
import org.example.queries.results.Results;
import org.example.queries.search.Funcs;
import org.example.queries.search.SearchParameters;

public class Functions extends FunctionsBase {

    private double calculateAvgAge(Results result) {
        return result.getItems().stream().mapToDouble(x -> x.getAge()).average().orElse(Double.NaN);
    }

    private double calculateAvgIncome(Results result) {
        return result.getItems().stream().mapToDouble(x -> x.getIncome()).average().orElse(Double.NaN);
    }

    private double calculateSumAge(Results result) {
        return result.getItems().stream().mapToDouble(x -> x.getIncome()).sum();
    }

    private double calculateSumIncome(Results result) {
        return result.getItems().stream().mapToDouble(x -> x.getIncome()).sum();
    }

    protected FunctionResult avgAge(Results results, SearchParameters searchParameters) {
        return new FunctionResult(Funcs.AVARAGE, "age", calculateAvgAge(results));
    }

    protected FunctionResult avgIncome(Results results, SearchParameters searchParameters) {
        return new FunctionResult(Funcs.AVARAGE, "income", calculateAvgIncome(results));
    }

    protected FunctionResult sumAge(Results results, SearchParameters searchParameters) {
        return new FunctionResult(Funcs.SUM, "age", calculateSumAge(results));
    }

    protected FunctionResult sumIncome(Results results, SearchParameters searchParameters) {
        return new FunctionResult(Funcs.SUM, "income", calculateSumIncome(results));
    }
}
