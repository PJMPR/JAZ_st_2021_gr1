package org.example.queries;

import org.example.model.People;
import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

public class QueryProcessor {
    public Results GetResults(SearchParameters parameters){
        Results result = new Results();
        result.setItems(People.Data);

        FilterProcessor filterProcessor = new FilterProcessor(parameters);
        filterProcessor.filterData(result);

        PageProcessor pageProcessor = new PageProcessor(parameters.getPage());
        pageProcessor.prepareDataToDisplay(result);

        FunctionProcessor functionProcessor = new FunctionProcessor(parameters.getFunctions());
        functionProcessor.calculate(result);

        return result;
    }
}
