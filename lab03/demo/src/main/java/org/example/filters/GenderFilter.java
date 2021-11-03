package org.example.filters;

import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.stream.Collectors;

public class GenderFilter implements Filter{
    @Override
    public void useFilters(Results results, SearchParameters searchParameters) {
        if(searchParameters.getSelectedGenders().size()>0){
            results.setItems(results.getItems().stream().filter(person -> searchParameters.getSelectedGenders().contains(person.getGender())).collect(Collectors.toList()));
        }
    }
}
