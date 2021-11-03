package org.example.Functions;

import org.example.queries.results.FunctionResult;
import org.example.queries.results.Results;
import org.example.queries.search.Funcs;
import org.example.queries.search.FunctionsParameters;
import org.example.queries.search.SearchParameters;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Functions {
    public void applyFunctions(Results results, SearchParameters searchParameters){

        List<FunctionResult>functionResultArrayList = new ArrayList<>();

        for (FunctionsParameters functionsParameters: searchParameters.getFunctions()) {
            if(functionsParameters.getFunction() == Funcs.AVARAGE) {
                if (Objects.equals(functionsParameters.getFieldName(), "age")) {
                    functionResultArrayList.add(avgAge(results, searchParameters));
                }
                if (Objects.equals(functionsParameters.getFieldName(), "income")) {
                    functionResultArrayList.add(avgIncome(results, searchParameters));
                }
            }
                else if(functionsParameters.getFunction() == Funcs.SUM){
                    if(Objects.equals(functionsParameters.getFieldName(), "age")){
                        functionResultArrayList.add(sumAge(results, searchParameters));
                    }
                    if(Objects.equals(functionsParameters.getFieldName(), "income")){
                        functionResultArrayList.add(sumIncome(results, searchParameters));
                    }
                }

        }
        results.setFunctionResults(functionResultArrayList);
    }

    public FunctionResult sumAge(Results results, SearchParameters searchParameters){
        FunctionResult functionResult = new FunctionResult();

        functionResult.setFunction(Funcs.SUM);
        functionResult.setValue(results.getItems()
                .stream().mapToInt(person -> person.getAge()).sum());

        return functionResult;
    }

    public FunctionResult sumIncome(Results results, SearchParameters searchParameters){
        FunctionResult functionResult = new FunctionResult();

        functionResult.setFunction(Funcs.SUM);
        functionResult.setValue(results.getItems()
                .stream().mapToDouble(person -> person.getIncome()).sum());

        return functionResult;
    }

    public FunctionResult avgAge(Results results, SearchParameters searchParameters){
        FunctionResult functionResult = new FunctionResult();

        functionResult.setFunction(Funcs.AVARAGE);
        functionResult.setValue(results.getItems()
                .stream().collect(Collectors.averagingInt(person -> person.getAge())));

        return functionResult;
    }

    public FunctionResult avgIncome(Results results, SearchParameters searchParameters){
        FunctionResult functionResult = new FunctionResult();

        functionResult.setFunction(Funcs.AVARAGE);
        functionResult.setValue(results.getItems()
                .stream().collect(Collectors.averagingDouble(person -> person.getIncome())));

        return functionResult;
    }
}
