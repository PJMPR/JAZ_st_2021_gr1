package org.example.queries.criteria;

import org.example.model.Person;
import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.List;
import java.util.stream.Collectors;

public class IncomeCriteria implements Criteria {

    @Override
    public void verificator(Results results, SearchParameters searchParameters) {
        if(searchParameters.getIncomeTo() > 0 ){
            results.setItems(results.getItems().stream()
                    .filter(person -> person.getIncome() <= searchParameters.getIncomeTo())
                    .collect(Collectors.toList()));
        }



    }
}
