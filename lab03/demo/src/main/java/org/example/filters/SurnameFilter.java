package org.example.filters;

import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.Objects;
import java.util.stream.Collectors;

public class SurnameFilter implements FilterInterface{
        @Override
            public void meetCriteria (Results results, SearchParameters searchParameters){
                if (searchParameters.getSurname() != null) {
                    results.setItems(results.getItems()
                            .stream()
                            .filter(person -> Objects.
                                    equals(person.getSurname(), searchParameters.getSurname()))
                            .collect(Collectors.toList()));
                }
            }
        }

