package org.example.filters;

import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.Locale;
import java.util.stream.Collectors;

public class SurnameFilter implements Filter{
    @Override
    public void useFilters(Results results, SearchParameters searchParameters) {
        if(searchParameters.getSurname() != null){
            results.setItems(results.getItems().stream().filter(person -> person.getSurname().toLowerCase(Locale.ROOT).equals(searchParameters.getSurname().toLowerCase(Locale.ROOT))).collect(Collectors.toList()));
        }
    }
}
