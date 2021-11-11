package org.example.queries;

import org.example.function.Functions;
import org.example.filter.*;
import org.example.model.People;
import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.List;

public class QueryProcessor {
    List<Filter> filters = List.of(
            new FromAge(),
            new ToAge(),
            new Gender(),
            new FromIncome(),
            new ToIncome(),
            new Surname(),
            new Name(),
            new Page()
    );

    public Results GetResults(SearchParameters parameters){
        Results result = new Results();

        result.setItems(People.Data);

        filters.forEach(filter -> filter.meetCriteria(result, parameters));

        Functions functions = new Functions();
        functions.applyFunctions(result,parameters);

        return result;
    }
}