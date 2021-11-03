package org.example.filters;

import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.stream.Collectors;

public class IncomeFromFilter implements Filter{
    @Override
    public void useFilters(Results results, SearchParameters searchParameters){
        results.setItems(results.getItems().stream().filter(person -> person.getIncome() >= searchParameters.getIncomeFrom()).collect(Collectors.toList()));
    }
}
