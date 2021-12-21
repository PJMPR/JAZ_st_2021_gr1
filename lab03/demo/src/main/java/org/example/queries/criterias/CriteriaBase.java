package org.example.queries.criterias;

import org.example.model.Person;
import org.example.queries.QueryResultsProcessor;
import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.stream.Collectors;

public abstract  class CriteriaBase implements QueryResultsProcessor, Criteria {

    SearchParameters parameters;
    public void setParameters(SearchParameters parameters) {
        this.parameters = parameters;
    }

    @Override
    public void process(Results results) {
        if(canFilter())
        results.setItems(results
                .getItems()
                .stream()
                .filter(person->check(person))
                .collect(Collectors.toList()));
    }

    protected abstract boolean canFilter() ;
    protected abstract boolean check(Person person);
}
