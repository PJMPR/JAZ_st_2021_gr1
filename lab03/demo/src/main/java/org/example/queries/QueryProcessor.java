package org.example.queries;

import org.example.model.People;
import org.example.criteria.*;
import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;
import java.util.List;

public class QueryProcessor {

    public List<Criteria> criteriaList = List.of (
            new AgeFromCriteria(),
            new AgeToCriteria(),
            new GenderCriteria(),
            new IncomeFromCriteria(),
            new IncomeToCriteria(),
            new NameCriteria(),
            new SurnameCriteria()
    );

    public Results GetResults(SearchParameters parameters) {
        Results result = new Results();
        result.setItems(People.Data);

        for (Criteria Criteria : criteriaList)
            result.setItems(Criteria.meetCriteria(result.getItems(), parameters));

        FunctionProcessor functionProcessor = new FunctionProcessor();
        result = functionProcessor.returnResultObject(result, parameters);

        return result;
    }

}