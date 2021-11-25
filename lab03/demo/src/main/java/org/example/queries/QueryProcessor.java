package org.example.queries;

import org.example.filters.*;
import org.example.model.People;
import org.example.queries.results.Results;
import org.example.queries.search.FunctionProcessor;
import org.example.queries.search.SearchParameters;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Filter;

public class QueryProcessor {

    public Results GetResults(SearchParameters parameters){
        Results result = new Results();
        List<FilterInterface> filters = List.of(
                new AgeToFilter(),
                new FromAgeFilter(),
                new GenderFilter(),
                new IncomeFrom(),
                new IncomeTo(),
                new NameFilter(),
                new SurnameFilter(),
                new toAgeFilter(),
                new PageInator()
        );

        result.setItems(People.Data);

        filters.forEach(filter -> filter.meetCriteria(result, parameters));

        FunctionProcessor functionProcessor = new FunctionProcessor();
        functionProcessor.functionProcessor(result, parameters);

        return result;
    }
}
