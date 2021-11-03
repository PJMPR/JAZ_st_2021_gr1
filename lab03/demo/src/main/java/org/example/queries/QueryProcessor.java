package org.example.queries;


import org.example.function.Function;

import org.example.model.People;
import org.example.queries.results.Results;
import org.example.queries.search.*;
import org.example.queries.criteria.*;
import java.util.List;

public class QueryProcessor {
    public List<Criteria> criterias = List.of (
            new AgeCriteria(),
            new GenderCriteria(),
            new IncomeCriteria(),
            new NameCriteria(),
            new SurnameCriteria(),
            new PageCriteria(),
            new FromIncomeCriteries()
    );


    public Results GetResults(SearchParameters parameters){
        Results result = new Results();
        result.setItems(People.Data);

        criterias
                .forEach( criterias -> criterias.verificator(result, parameters));

        Function functions = new Function();
        functions.applyFunctions(result,parameters);

        return result;
    }

}

