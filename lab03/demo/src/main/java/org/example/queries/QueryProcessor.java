package org.example.queries;

import org.example.Functions.Functions;
import org.example.Page.Page;
import org.example.filters.*;
import org.example.model.People;
import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.List;

public class QueryProcessor {
    List<Criteria> criteriaList = List.of(
            new FromAgeCriteria(),
            new FromIncomeCriteria(),
            new GanderCriteria(),
            new NameCriteria(),
            new SurnameCriteria(),
            new ToAgeCriteria(),
            new ToIncomeCriteria(),
            new Page()
    );

    public Results GetResults(SearchParameters parameters){
        Results result = new Results();

        result.setItems(People.Data);

        criteriaList.forEach(criteria -> criteria.meetCriteria(result,parameters));

        Functions functions = new Functions();
        functions.applyFunctions(result,parameters);

        return result;
    }
}
