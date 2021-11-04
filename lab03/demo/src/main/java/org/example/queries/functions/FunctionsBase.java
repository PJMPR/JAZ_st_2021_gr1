package org.example.queries.functions;

import java.util.Objects;

import org.example.queries.results.FunctionResult;
import org.example.queries.results.Results;
import org.example.queries.search.Funcs;
import org.example.queries.search.FunctionsParameters;
import org.example.queries.search.SearchParameters;

public abstract class FunctionsBase {

    public void execute(Results results, SearchParameters searchParameters) {

        for (FunctionsParameters f : searchParameters.getFunctions()) {
            if (isSumOfAge(f))
                results.getFunctionResults().add(sumAge(results, searchParameters));

            else if (isSumOfIncome(f))
                results.getFunctionResults().add(sumIncome(results, searchParameters));

            else if (isAvgOfAge(f))
                results.getFunctionResults().add(avgAge(results, searchParameters));

            else if (isAvgOfIncome(f))
                results.getFunctionResults().add(avgIncome(results, searchParameters));

        }
    }

    private boolean isSumFunction(FunctionsParameters f) {
        return f.getFunction() == Funcs.SUM;
    }

    private boolean isAvgFunction(FunctionsParameters f) {
        return f.getFunction() == Funcs.AVARAGE;
    }

    private boolean isSumOfAge(FunctionsParameters f) {
        return isSumFunction(f) && Objects.equals(f.getFieldName(), "age");
    }

    private boolean isSumOfIncome(FunctionsParameters f) {
        return isSumFunction(f) && Objects.equals(f.getFieldName(), "income");
    }

    private boolean isAvgOfAge(FunctionsParameters f) {
        return isAvgFunction(f) && Objects.equals(f.getFieldName(), "age");
    }

    private boolean isAvgOfIncome(FunctionsParameters f) {
        return isAvgFunction(f) && Objects.equals(f.getFieldName(), "income");
    }

    protected abstract FunctionResult sumAge(Results results, SearchParameters searchParameters);

    protected abstract FunctionResult sumIncome(Results results, SearchParameters searchParameters);

    protected abstract FunctionResult avgAge(Results results, SearchParameters searchParameters);

    protected abstract FunctionResult avgIncome(Results results, SearchParameters searchParameters);
}
