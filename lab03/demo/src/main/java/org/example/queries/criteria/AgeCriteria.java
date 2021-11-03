package org.example.queries.criteria;

import org.example.model.Person;
import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.List;
import java.util.stream.Collectors;

public class AgeCriteria implements Criteria {

    @Override
    public void verificator(Results results, SearchParameters searchParameters) {
        if (searchParameters.getAgeTo() != 0) {
            results.setItems(results.getItems()
                    .stream()
                    .filter(person -> person.getAge() <= searchParameters.getAgeTo())
                    .collect(Collectors.toList()));
        }
        if (searchParameters.getAgeFrom() != 0) {
            results.setItems(results.getItems()
                    .stream()
                    .filter(person -> person.getAge() >= searchParameters.getAgeFrom())
                    .collect(Collectors.toList()));
        }

    }

}