package org.example.queries.criteria;

import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.stream.Collectors;

public class FromIncomeCriteries implements Criteria {


    @Override
    public void verificator(Results results, SearchParameters searchParameters) {
        results.setItems(results.getItems().stream()
                .filter(person -> person.getIncome() >= searchParameters.getIncomeFrom())
                .collect(Collectors.toList()));
    }
}
