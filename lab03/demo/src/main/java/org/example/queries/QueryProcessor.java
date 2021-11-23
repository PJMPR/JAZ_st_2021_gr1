package org.example.queries;

import org.example.model.People;
import org.example.queries.calculations.Calculate;
import org.example.queries.calculations.Calculator;
import org.example.queries.criterias.Criteria;
import org.example.queries.criterias.SimpleCriteria;
import org.example.queries.paging.Pager;
import org.example.queries.paging.Paging;
import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.List;

public class QueryProcessor {

    List<QueryResultsProcessor> queryResultsProcessors = List.of(
            new SimpleCriteria(p->p.getAgeFrom()>0, (person, params)->person.getAge()>params.getAgeFrom()),
            new Pager(),
            new Calculator()
    );

    public Results GetResults(SearchParameters parameters){
        Results result = new Results();
        result.setItems(People.Data);
        queryResultsProcessors.stream().filter(x-> x instanceof Criteria).forEach(criteria->criteria.process(result));
        queryResultsProcessors.stream().filter(x->x instanceof Calculate).forEach(calculation->calculation.process(result));
        queryResultsProcessors.stream().filter(x->x instanceof Paging).forEach(paging->paging.process(result));


        return result;
    }
}
