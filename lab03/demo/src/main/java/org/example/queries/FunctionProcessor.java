package org.example.queries;

import org.example.model.Person;
import org.example.queries.functions.CalculateFunction;
import org.example.queries.functions.prepareData.PreparePersonData;
import org.example.queries.results.FunctionResult;
import org.example.queries.results.Results;
import org.example.queries.search.FunctionsParameters;

import java.util.List;
import java.util.stream.Collectors;

public class FunctionProcessor {
    private List<FunctionsParameters> functions;

    public FunctionProcessor(List<FunctionsParameters> functions) {
        this.functions = functions;
    }

    public void calculate(Results result) {
        result.setFunctionResults(functions.stream()
                .map(f -> new FunctionResult(f.getFunction(), f.getFieldName(), calculateValue(f, result.getItems())))
                .collect(Collectors.toList())
        );
    }

    private double calculateValue(FunctionsParameters functionsParameters, List<Person> people) {
        return new CalculateFunction(functionsParameters.getFunction())
                .calculate(new PreparePersonData(people).getDouble(functionsParameters.getFieldName()));
    }
}
