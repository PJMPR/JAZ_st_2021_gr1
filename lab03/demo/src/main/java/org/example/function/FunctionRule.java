package org.example.function;

import org.example.queries.results.FunctionResult;
import org.example.queries.results.Results;
import org.example.queries.search.Funcs;
import org.example.queries.search.FunctionsParameters;
import org.example.queries.search.SearchParameters;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class FunctionRule {
    public void applyFunctions(Results results, SearchParameters searchParameters){
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
                    functionResults.add(averageAge(results, searchParameters));
                }
                if (Objects.equals(f.getFieldName(), "income")) {
                    functionResults.add(averageIncome(results, searchParameters));
                }
            }
        }
        results.setFunctionResults(functionResults);
    }

    protected abstract FunctionResult sumAge(Results results, SearchParameters searchParameters);

    protected abstract FunctionResult sumIncome(Results results, SearchParameters searchParameters);

    protected abstract FunctionResult averageAge(Results results, SearchParameters searchParameters);

    protected abstract FunctionResult averageIncome(Results results, SearchParameters searchParameters);



}
