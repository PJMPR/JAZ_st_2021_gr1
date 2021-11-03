package org.example.filter;

import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.stream.Collectors;

public class Surname implements Filter{
    @Override
    public void meetCriteria(Results results, SearchParameters searchParameters) {
        if (searchParameters.getSurname()!=null) {
            results.setItems(results.getItems().stream()
                    .filter(person -> person.getSurname().equals(searchParameters
                            .getSurname())).collect(Collectors.toList()));
        }
    }
}
