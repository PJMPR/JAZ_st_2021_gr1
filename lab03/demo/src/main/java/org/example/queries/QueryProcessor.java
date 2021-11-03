package org.example.queries;

import org.example.functions.AgeIncomeMethods;
import org.example.filters.*;
import org.example.filters.Filter;
import org.example.model.People;
import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.List;

public class QueryProcessor {

    List<Filter> filters = List.of(
            new AgeFromFilter(),
            new AgeToFilter(),
            new GenderFilter(),
            new IncomeFromFilter(),
            new IncomeToFilter(),
            new NameFilter(),
            new SurnameFilter(),
            new PageFilter()
    );

    public Results GetResults(SearchParameters parameters){
        Results result = new Results();
        AgeIncomeMethods function = new AgeIncomeMethods();
        result.setItems(People.Data);

        filters.forEach(filter -> filter.useFilters(result,parameters));

        function.ageIncomeFunction(parameters, result);

        return result;
    }
}
