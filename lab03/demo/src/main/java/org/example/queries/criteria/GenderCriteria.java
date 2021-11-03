package org.example.queries.criteria;

import org.example.model.Gender;
import org.example.model.Person;
import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.List;
import java.util.stream.Collectors;

public class GenderCriteria implements Criteria{
    @Override
    public void verificator(Results results, SearchParameters searchParameters) {
        if(searchParameters.getSelectedGenders().size() > 0){
            results.setItems(results.getItems()
                    .stream()
                    .filter(person -> searchParameters.getSelectedGenders().contains(person.getGender()))
                    .collect(Collectors.toList()));
        }
    }

}
