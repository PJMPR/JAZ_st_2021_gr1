package org.example.filter_rules;

import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.stream.Collectors;

public class FromAgeRule implements FilterInterface {
    @Override
    public void useFilterInterface(Results results, SearchParameters searchParameters) {
        results.setItems(results.getItems().stream().filter(person -> person.getAge() >= searchParameters.getAgeFrom()).collect(Collectors.toList()));
    }
}
