package org.example.filter;

import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.stream.Collectors;

public class FromIncome implements Filter{
    @Override
    public void meetCriteria(Results results, SearchParameters searchParameters) {
        results.setItems(results.getItems().stream()
                .filter(person -> person.getIncome() >= searchParameters
                        .getIncomeFrom()).collect(Collectors.toList()));
    }
}
